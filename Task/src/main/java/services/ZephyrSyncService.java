package services;

import com.vistajet.zephyr.configuration.Config;
import com.vistajet.zephyr.model.beans.jira.Issue;
import com.vistajet.zephyr.model.beans.zapi.TestCase;
import com.vistajet.zephyr.configuration.CustomPropertyNamingStrategy;
import com.vistajet.zephyr.model.beans.zapi.ZapiTestStep;
import com.vistajet.zephyr.transformer.ObjectTransformer;
import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.List;

public class ZephyrSyncService {

    private MetaInfoRetrievalService metaInfoRetrievalService;
    private JiraService jiraService;
    private ZephyrService zephyrService;
    private AuthService authService;
    private TestCaseResolutionService testCaseResolutionService;

    public ZephyrSyncService(Config config) {
        ObjectTransformer.setPropertyNamingStrategy(new CustomPropertyNamingStrategy(config));
        authService = new AuthService(config);
        testCaseResolutionService = new TestCaseResolutionService(config);
        metaInfoRetrievalService = new MetaInfoRetrievalService(config);
        jiraService = new JiraService(config);
        zephyrService = new ZephyrService(config);
    }

    public void execute() throws IOException, AuthenticationException, InterruptedException {
        MetaInfo metaInfo = metaInfoRetrievalService.retrieve();
        List<TestCase> testCases = TestCaseResolutionService.resolveTestCases();
        List<Issue> issues = jiraService.getAllIssues(testCases);
        zephyrService.linkExecutionsToTestCycle(metaInfo, testCases);
        zephyrService.updateExecutionStatuses(metaInfo,testCases);
        zephyrService.updateStepExecutionStatuses(issues,testCases);

    }
}
