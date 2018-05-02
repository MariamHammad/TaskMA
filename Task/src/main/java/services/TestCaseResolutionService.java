package services;


import com.vistajet.zephyr.configuration.Config;
import com.vistajet.zephyr.exceptions.ZephyrException;
import com.vistajet.zephyr.model.beans.zapi.TestCase;
import com.vistajet.zephyr.transformer.ReportTransformer;
import com.vistajet.zephyr.transformer.ReportTransformerFactory;

import java.util.Iterator;
import java.util.List;

import static com.vistajet.zephyr.configuration.ConfigProperty.REPORT_PATH;
import static com.vistajet.zephyr.configuration.ConfigProperty.REPORT_TYPE;


public class TestCaseResolutionService {

    private static Config config;

    public TestCaseResolutionService(Config config) {
        this.config = config;
    }

    public static List<TestCase> resolveTestCases() {
        String reportType = config.getValue(REPORT_TYPE);
        String path = config.getValue(REPORT_PATH);
        ReportTransformer transformer = ReportTransformerFactory.getInstance().getTransformer(reportType);
        List<TestCase> testCases = transformer.transformToTestCases(path);
        if (testCases == null) {
            throw new ZephyrException("No Test Cases extracted from the Test Report");
        }
        for (Iterator<TestCase> it = testCases.iterator(); it.hasNext(); ) {
            TestCase testCase = it.next();
            if (testCase.getName() == null || testCase.getName().length() == 0) {
                it.remove();
            }
        }
        if (testCases.isEmpty()) {
            throw new ZephyrException("No Test Cases extracted from the Test Report");
        }
        return testCases;
    }
}