package services;

import junit.framework.TestCase;
import lv.ctco.zephyr.Config;
import lv.ctco.zephyr.beans.jira.Issue;
import lv.ctco.zephyr.beans.jira.SearchResponse;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static lv.ctco.zephyr.enums.ConfigProperty.JIRA_URL;
import static lv.ctco.zephyr.enums.ConfigProperty.PROJECT_KEY;
import static org.testng.internal.Utils.log;

public class JiraService {

    private static final int TOP = 20;
    private Config config;

    public JiraService(Config config) {
        this.config = config;
    }

    public List<Issue> getTestIssues() throws IOException, AuthenticationException {
        int skip = 0;
        log("Fetching JIRA Test issues for the project");
        String search = "project='" + config.getValue(PROJECT_KEY) + "'%20and%20issueType=Test";
        SearchResponse searchResults = searchInJQL(search, skip);
        if (searchResults == null || searchResults.getIssues() == null) {
            throw new ZephyrException("Unable to fetch JIRA test issues");
        }

        List<Issue> issues = searchResults.getIssues();

        int totalCount = searchResults.getTotal();
        if (totalCount > TOP) {
            while (issues.size() != totalCount) {
                skip += TOP;
                issues.addAll(searchInJQL(search, skip).getIssues());
            }
        }
        log(format("Retrieved %s Test issues\n", issues.size()));
        return issues;
    }

    SearchResponse searchInJQL(String search, int skip) throws IOException, AuthenticationException {
        ClientResponse response = getResponse(config.getValue(JIRA_URL) + "api/latest/search?jql=" + search + "&maxResults=" + TOP + "&startAt=" + skip);
        return ObjectTransformer.deserialize(response.getEntity(String.class), SearchResponse.class);
    }

//    public void createTestIssue(TestCase testCase) throws IOException {
//        log("Creating JIRA test issue with Unique ID " + testCase.getUniqueId());
//        Issue issue = TestCaseToIssueTransformer.transform(config, testCase);
//
//        HttpResponse response = post(config, "api/2/issue", issue);
//        ensureResponse(response, 201, "Could not create a Test issue in JIRA");
//
//        String responseBody = readInputStream(response.getEntity().getContent());
//        Metafield result = ObjectTransformer.deserialize(responseBody, Metafield.class);
//        if (result != null) {
//            testCase.setId(Integer.valueOf(result.getId()));
//            testCase.setKey(result.getKey());
//        }
//    }
//
//    public void linkToStory(TestCase testCase) throws IOException {
//        List<String> storyKeys = testCase.getStoryKeys();
//        if (Boolean.valueOf(config.getValue(FORCE_STORY_LINK))) {
//            if (storyKeys == null || storyKeys.isEmpty()) {
//                throw new ZephyrException("Linking Test issues to Story is mandatory, please check if Story marker exists in " + testCase.getKey());
//            }
//        }
//        if (storyKeys == null) return;
//
//        log("Linking Test issue " + testCase.getKey() + " to Stories " + testCase.getStoryKeys());
//        for (String storyKey : storyKeys) {
//            HttpResponse response = post(config, "api/2/issueLink", new IssueLink(testCase.getKey(), storyKey.toUpperCase()));
//            ensureResponse(response, 201, "Could not link Test issue: " + testCase.getId() + " to Story " + storyKey + ". " +
//                    "Please check if Story issue exists and is valid");
//        }
//    }


    List<Issue> getAllIssues(List<TestCase> testCases) throws AuthenticationException {
        List<Issue> allIssues = new ArrayList<>();

        for (TestCase testCase: testCases){
            allIssues.add(getIssue(testCases));
        }
        return allIssues;
    }


    private Issue getIssue(List<TestCase> testCases) throws AuthenticationException {
        ClientResponse response = getResponse(config.getValue(JIRA_URL) + "api/latest/issue/" + testCases.iterator().next().getName());
        return ObjectTransformer.deserialize(response.getEntity(String.class), Issue.class);
    }


}
