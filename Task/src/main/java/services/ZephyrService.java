package services;


import lv.ctco.zephyr.Config;
import lv.ctco.zephyr.beans.zapi.Execution;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import static java.lang.String.format;
import static org.testng.internal.Utils.log;


public class ZephyrService {

    private static final int TOP = 20;

    private Config config;

    ZephyrService(Config config) {
        this.config = config;
    }

    private Map<String, Execution> getAllExecutions(Config config) throws IOException, AuthenticationException {
        log("Fetching JIRA Test Executions for the project");
        int skip = 0;
        String search = "project='" + config.getValue(PROJECT_KEY) + "'%20and%20fixVersion='"
                + URLEncoder.encode(config.getValue(RELEASE_VERSION), "UTF-8") + "'%20and%20cycleName='" + config.getValue(TEST_CYCLE) + "'";

        ExecutionResponse executionResponse = searchInZQL(search, skip);
        if (executionResponse == null || executionResponse.getExecutions().isEmpty()) {
            return new HashMap<>();
        }

        List<Execution> executions = executionResponse.getExecutions();

        int totalCount = executionResponse.getTotalCount();
        if (totalCount > TOP) {
            while (executions.size() != totalCount) {
                skip += TOP;
                List<Execution> nextPageExecutions = searchInZQL(search, skip).getExecutions();
                if (nextPageExecutions.isEmpty()) {
                    break;
                }
                executions.addAll(nextPageExecutions);
            }
        }
        Map<String, Execution> result = new HashMap<>(executions.size());
        for (Execution execution : executions) {
            result.put(execution.getIssueKey(), execution);
        }
        log(format("Retrieved %s Test executions\n", executions.size()));
        return result;
    }

    private ExecutionResponse searchInZQL(String search, int skip) throws IOException, AuthenticationException {
        ClientResponse response = getResponse( config.getValue(JIRA_URL) + "zapi/latest/zql/executeSearch?zqlQuery=" + search + "&offset=" + skip);
        return  ObjectTransformer.deserialize(response.getEntity(String.class),ExecutionResponse.class);
    }

    void linkExecutionsToTestCycle(MetaInfo metaInfo, List<TestCase> testCases) throws IOException, InterruptedException, AuthenticationException {
        Map<String, Execution> executions = getAllExecutions(config);

        List<String> keys = new ArrayList<>();

        for (TestCase testCase : testCases) {
            if (!executions.containsKey(testCase.getKey())) {
                keys.add(testCase.getName());
            }
        }
        if (keys.size() > 0) {
            linkTestToCycle(metaInfo, keys);
        } else {
            log("All Test cases are already linked to the Test cycle.\n");
        }

    }

    private void linkTestToCycle(MetaInfo metaInfo, List<String> keys) throws IOException, InterruptedException, AuthenticationException {
        log("INFO: Linking Test Cases to Test Cycle:" + keys.toString() + "");
        RequestExecutions requestExecutions = new RequestExecutions(keys,metaInfo.getVersionId(),metaInfo.getCycleId(),metaInfo.getProjectId(),1);
        String json = ObjectTransformer.serialize(requestExecutions);
        ClientResponse response = postResponse(json,config.getValue(JIRA_URL) + "zapi/latest/execution/addTestsToCycle/");
        response.getEntity(String.class);

        int iteration = 0;
        while (!checkTestCycleIsInSync(keys) && iteration < 5) {
            log("INFO: Test Cycle is not in sync. Giving Zephyr another chance.");
            Thread.sleep(5000);
            iteration++;
        }
    }

    private boolean checkTestCycleIsInSync(List<String> keys) throws IOException, AuthenticationException {
        Map<String, Execution> executions = getAllExecutions(config);
        for (String testId : keys) {
            if (!executions.containsKey(testId)) {
                log("INFO: Test Case " + testId + " was not found in Test Cycle");
                return false;
            }
        }
        return true;
    }

    void updateExecutionStatuses(MetaInfo metaInfo, List<TestCase> testCases) throws IOException, AuthenticationException {
        String comment = "Test Failed";
        Map<String, Execution> executions = getAllExecutions(config);

        for (TestCase testCase : testCases) {
            log("INFO: Setting status " + testCase.getStatus() + " for Test: " + testCase.getName() + "");

            Execution execution = executions.get(testCase.getName());
            if (execution == null) {
                log("WARN: Test " + testCase.getName() + " not found in Test Cycle " + config.getValue(TEST_CYCLE) + "");
                continue;
            }
            if (!testCase.getStatus().name().equals("FAILED")){
               comment = testCase.getSteps().get(testCase.getSteps().size() - 1).getSteps().get(0).getDescription();
            }

            RequestUpdate requestUpdate = new RequestUpdate(testCase.getStatus().getId(),comment);

            ClientResponse response = putResponse(requestUpdate,config.getValue(JIRA_URL) +"zapi/latest/execution/" + execution.getId() + "/execute");
            ObjectTransformer.deserialize(response.getEntity(String.class),ExecutionResponse.class);
        }
    }

    void updateStepExecutionStatuses(List<Issue> issues, List<TestCase> testCases) throws IOException, AuthenticationException {


        ArrayList<ZapiTestStep> zapiSteps = getAllTestSteps(issues,testCases);
        List<TestStep>  testStepList = getJiraSteps(testCases);
        int i = 0;
        for (ZapiTestStep zapiStep : zapiSteps) {

            log("INFO: Setting status  \"" + testStepList.get(i).getStatus() + "\"  for Test Step: " + testStepList.get(i).getDescription());

            RequestTeststepUpdate requestTeststepUpdate = new RequestTeststepUpdate(getStatus(testStepList.get(i).getStatus()), "");
            //RequestTeststepUpdate requestTeststepUpdate = new RequestTeststepUpdate(getStatus(testCases.iterator().next().getSteps().iterator().next().getStatus()), "");

            ClientResponse response = putResponse(requestTeststepUpdate, config.getValue(JIRA_URL) + "zapi/latest/stepResult/" + zapiStep.getId());
            i++;
        }
    }




    private int getStatus(String status) {
        switch (status) {
            case "failed":
                return 2;
            case "broken":
                return 2;
            case "passed":
                return 1;
            default:
                return -1;
        }
    }

    private void executeIssues(List<Issue> issues, TestCase testCase) throws AuthenticationException, IOException {
        ClientResponse response = null;
        Map<String, Execution> executions = getAllExecutions(config);
        Execution execution = executions.get(testCase.getName());

        response = getResponse(config.getValue(JIRA_URL) + "zapi/latest/execution/" + execution.getId() + "?expand=checksteps");
        response = getResponse(config.getValue(JIRA_URL) + "zapi/latest/stepResult?executionId=" + execution.getId());

//        for (Issue issue:issues) {
//
//            response = getResponse(config.getValue(JIRA_URL) + "/secure/ExecuteTest!default.jspa?scheduleId=" + execution.getId() + "&issueId=" + issue.getId());
//        }

    }

    private ZapiTestStep[] getTestSteps(TestCase testCase) throws AuthenticationException, IOException {
        ClientResponse response = null;
        Map<String, Execution> executions = getAllExecutions(config);

            Execution execution = executions.get(testCase.getName());
            response = getResponse(config.getValue(JIRA_URL) + "zapi/latest/stepResult?executionId=" + execution.getId());
        return ObjectTransformer.deserialize(response.getEntity(String.class), ZapiTestStep[].class);
    }


    private ArrayList<ZapiTestStep> getAllTestSteps(List<Issue> issues, List<TestCase> testCases) throws AuthenticationException, IOException {
        ArrayList<ZapiTestStep> alltestSteps = new ArrayList<>();

        for (TestCase testCase: testCases){
            log("INFO: Getting info from " + testCase.getName());
            executeIssues(issues, testCase);
            alltestSteps.addAll(new ArrayList<>(Arrays.asList(getTestSteps(testCase))));
        }
        return alltestSteps;
    }

    private List<TestStep> getJiraSteps(List<TestCase> testCases){
        List<TestStep> list = new ArrayList<>();
        for (TestCase testCase: testCases){
            list.addAll(testCase.getSteps());
        }
        return list;
    }

    public void mapTestCasesToIssues(Collection<TestCase> resultTestCases, List<Issue> issues) {
        Map<String, Issue> uniqueKeyMap = new HashMap<>(issues.size());
        for (Issue issue : issues) {
            uniqueKeyMap.put(issue.getKey(), issue);
            String testCaseUniqueId = issue.getFields().getTestCaseUniqueId();
            if (testCaseUniqueId != null) {
                uniqueKeyMap.put(testCaseUniqueId, issue);
            }
        }

        for (TestCase testCase : resultTestCases) {
            String testCaseKey = testCase.getKey();
            String testCaseName = testCase.getName();
            // case when test case can be matched by id
            if (testCaseKey != null && uniqueKeyMap.containsKey(testCaseKey)) {
                testCase.setId(uniqueKeyMap.get(testCaseKey).getId());
                continue;
            }
            // if no exact match by id was found, trying to match by exact name
            if (testCaseKey == null && testCaseName != null) {
                for (Issue issue : issues) {
                    if (issue.getFields().getSummary().equalsIgnoreCase(testCaseName)) {
                        testCase.setId(issue.getId());
                        testCase.setKey(issue.getKey());
                        continue;
                    }
                }
            }
            // if no exact match by id or by name, creating new one
            if (testCaseKey != null) {
                log(format("INFO: Key %s not found, new Test Case will be created", testCaseKey));
                testCase.setId(null);
                testCase.setKey(null);
                continue;
            }
        }
    }
}

