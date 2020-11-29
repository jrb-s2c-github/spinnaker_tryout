package com.example.demo.repository;

import com.example.demo.model.Author;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceList;
import io.kubernetes.client.openapi.models.V1ServiceSpec;
import io.kubernetes.client.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

//  kubectl.exe describe svc hello-svc
@Component
public class ServiceRegistry {

    @Value("${books_svc}")
    private static String books_svc;


    @Value("${authors_svc}")
    private static String authors_svc;



    private static final String DEFAULT_NAME_SPACE = "default";
    private static final Integer TIME_OUT_VALUE = 180;

    public static String determineK8sRootFromHttpTillPort(String svcName) throws IOException, ApiException {
        String result = null;

        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api(client);
        V1ServiceList listNamespacedService =
                api.listNamespacedService(
                        DEFAULT_NAME_SPACE,
                        null,
                        null,
                        null,
                        null,
                        null,
                        Integer.MAX_VALUE,
                        null,
                        null,
                        TIME_OUT_VALUE,
                        Boolean.FALSE);
        for (V1Service service : listNamespacedService.getItems()) {
            if (service.getMetadata().getName().equals(svcName)) {
                V1ServiceSpec spec = service.getSpec();
                String portNr = "#";

                result = "http://" + spec.getClusterIP() + ":" + spec.getPorts().get(0).getPort();
                System.out.println("################ " + result);
            }
        }

        return result;
    }


    static String getRestRoot(Class toLookup) throws IOException, ApiException {
        String svc = books_svc;
        String port = "8081";
        if (toLookup.equals(Author.class)) {
            port = "8082";
            svc = authors_svc;
        }
        String root = "http://localhost:" + port;
        if (svc != null) {
            root = determineK8sRootFromHttpTillPort(svc);
        }
        return root;
    }
}
