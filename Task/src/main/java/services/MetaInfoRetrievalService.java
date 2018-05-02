package services;

import com.sun.jersey.api.client.ClientResponse;

import com.vistajet.zephyr.configuration.Config;
import com.vistajet.zephyr.configuration.ConfigProperty;
import com.vistajet.zephyr.exceptions.ZephyrException;
import com.vistajet.zephyr.model.*;

import com.vistajet.zephyr.model.beans.zapi.Cycle;
import com.vistajet.zephyr.model.beans.zapi.CycleList;
import com.vistajet.zephyr.model.beans.zapi.ExistedCycle;
import com.vistajet.zephyr.model.beans.jira.Project;
import com.vistajet.zephyr.request.RequestCycle;
import com.vistajet.zephyr.transformer.ObjectTransformer;
import com.vistajet.zephyr.util.Utils;


import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static com.vistajet.zephyr.util.HttpUtils.getResponse;
import static com.vistajet.zephyr.util.HttpUtils.postResponse;


public class MetaInfoRetrievalService {
    private static HashMap<String, String> map = new HashMap<String, String>();
    private Config config;

    public MetaInfoRetrievalService(Config config) {
        this.config = config;
    }

    public MetaInfo retrieve() throws IOException, AuthenticationException {
        MetaInfo metaInfo = new MetaInfo();
        retrieveProjectMetaInfo(metaInfo);
        retrieveTestCycleId(metaInfo);
        return metaInfo;
    }

    private void retrieveProjectMetaInfo(MetaInfo metaInfo) throws IOException, AuthenticationException {
        String projectKey = config.getValue(ConfigProperty.PROJECT_KEY);
        ClientResponse response = getResponse(config.getValue(ConfigProperty.JIRA_URL) + format("api/latest/project/%s", projectKey));
        Project project = ObjectTransformer.deserialize(response.getEntity(String.class), Project.class);

        if (project == null || !project.getKey().equals(projectKey)) {
            throw new ZephyrException("Improper JIRA project retrieved");
        }

        String projectId = project.getId();
        Utils.log("Retrieved project ID - " + projectId);
        metaInfo.setProjectId(projectId);

        for (Metafield version : project.getVersions()) {
            if (version.getName().equals(config.getValue(ConfigProperty.RELEASE_VERSION))) {
                String versionId = version.getId();
                Utils.log("Retrieved version ID - " + versionId);
                metaInfo.setVersionId(versionId);
            }
        }
    }

    private void retrieveTestCycleId(MetaInfo metaInfo) throws IOException, AuthenticationException {
        String projectId = metaInfo.getProjectId();
        String versionId = metaInfo.getVersionId();
        if (projectId == null || versionId == null)
            throw new ZephyrException("JIRA projectID or versionID are missing");

        ClientResponse response = getResponse(format(config.getValue(ConfigProperty.JIRA_URL) + "zapi/latest/cycle?projectId=%s&versionId=%s", projectId, versionId));
        CycleList cycleList = ObjectTransformer.deserialize(response.getEntity(String.class), CycleList.class);
        if (cycleList == null || cycleList.getCycleMap().isEmpty()) {
            throw new ZephyrException("Unable to retrieve JIRA test cycle");
        }

        for (Map.Entry<String, ExistedCycle> entry : cycleList.getCycleMap().entrySet()) {
            ExistedCycle value = entry.getValue();
            if (value != null
                    && value.getProjectKey().equals(config.getValue(ConfigProperty.PROJECT_KEY))
                    && value.getVersionId().toString().equals(versionId)
                    && value.getName().equals(config.getValue(ConfigProperty.TEST_CYCLE))) {
                String cycleId = entry.getKey();
                Utils.log("Retrieved target Test Cycle ID - " + cycleId + "\n");
                metaInfo.setCycleId(cycleId);
                return;
            }
        }
        if (Boolean.parseBoolean(config.getValue(ConfigProperty.AUTO_CREATE_TEST_CYCLE))) {
            Utils.log("Creating new test cycle");
            String cycleId = createNewTestCycle(projectId, versionId);
            Utils.log("New test cycle created - " + cycleId);
            metaInfo.setCycleId(cycleId);
        } else {
            throw new ZephyrException("Unable to retrieve JIRA test cycle");
        }
    }


    private String createNewTestCycle(String projectId, String versionId) throws AuthenticationException {
        RequestCycle requestCycle = new RequestCycle(
                config.getValue(ConfigProperty.TEST_CYCLE),
                projectId,
                versionId
        );

        ClientResponse response = postResponse(requestCycle, config.getValue(ConfigProperty.JIRA_URL) + "zapi/latest/cycle");
        Cycle cycle = ObjectTransformer.deserialize(response.getEntity(String.class), Cycle.class);
        return cycle.getId();
    }



}
