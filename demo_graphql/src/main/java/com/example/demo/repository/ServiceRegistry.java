package com.example.demo.repository;

import com.example.demo.model.Author;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Config;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//  kubectl.exe describe svc hello-svc
@Component
public class ServiceRegistry {

    //    @Value("${books_svc}"/)
    private String books_svc = "books_svc";


    //    @Value("${authors_svc}")
    private String authors_svc = "authors_svc";

    private static final String DEFAULT_NAME_SPACE = "default";
    private static final Integer TIME_OUT_VALUE = 180;

    public static String getSvcName(String tag) {
        String result = null;
        try {

            ApiClient client = Config.defaultClient();
            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api(client);

            V1ConfigMapList configMaps = null; //Boolean watch

            configMaps = api.listNamespacedConfigMap(
                    DEFAULT_NAME_SPACE,
                    null,//String pretty,
                    Boolean.FALSE, //Boolean allowWatchBookmarks,
                    null, //String _continue,
                    null, //String fieldSelector,
                    "app=demo-graphql",  //String labelSelector,
                    Integer.MAX_VALUE, //Integer limit,
                    null, // String resourceVersion,
                    //                null, //String resourceVersionMatch,
                    TIME_OUT_VALUE, //Integer timeoutSeconds,
                    Boolean.FALSE);


            List<V1ConfigMap> items = configMaps.getItems();
            V1ConfigMap configMap = items.get(items.size() - 1);
            result = configMap.getData().get(tag);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null; // try local host
        }
        return result;
    }

    public static String determineK8sRootFromHttpTillPort(String svcName) throws IOException, ApiException {
        String result = null;
        System.out.println("@@@@@@@@@@@@@2: trying to determine roor for " + svcName);
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
                        null, // TODO use this for selections
                        Integer.MAX_VALUE,
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


    String getRestRoot(Class toLookup) throws ApiException, IOException {
        String svc = books_svc;
        String port = "8081";
        if (toLookup.equals(Author.class)) {
            port = "8082";
            svc = authors_svc;
        }
        svc = getSvcName(svc);
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        String root = "http://localhost:" + port;
        if (svc != null) {
            try {
                root = determineK8sRootFromHttpTillPort(svc);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("ERROR");
                // TODO better error handling
            }
        }
        return root;
    }
}
