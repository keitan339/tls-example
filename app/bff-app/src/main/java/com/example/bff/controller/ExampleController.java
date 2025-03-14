package com.example.bff.controller;

import java.net.Socket;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/example")
@RequiredArgsConstructor
public class ExampleController {

    private final RestClient.Builder restClientBuilder;

    @PostMapping(value = "/execute")
    public ExampleResponse execute(@RequestBody ExampleRequest request)
            throws KeyManagementException, NoSuchAlgorithmException {

        // final String SVC_URL = "http://svc.example.com:8080/example/execute";
        final String SVC_URL = "https://svc.example.com:8443/example/execute";
        // final String SVC_URL = "https://svc-app:8443/example/execute";

        RestClient restClient = createRestClient(true);

        ExampleResponse result =
                restClient.post().uri(SVC_URL).body(request).retrieve().body(ExampleResponse.class);
        System.out.println(result);

        return result;
    }

    public RestClient createRestClient(boolean enableSslCertificateValidation)
            throws NoSuchAlgorithmException, KeyManagementException {
        if (enableSslCertificateValidation) {
            return createDefaultRestClient();
        } else {
            return createDisableSslCertificateValidationRestClient();
        }
    }

    private RestClient createDefaultRestClient() {
        return RestClient.create();
    }

    private RestClient createDisableSslCertificateValidationRestClient()
            throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager trustManager = new X509ExtendedTrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers");
                return new X509Certificate[] {};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                System.out.println("ignore checkClientTrusted 1");
            }


            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
                    throws CertificateException {
                System.out.println("ignore checkClientTrusted 2");
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType,
                    SSLEngine engine) throws CertificateException {
                System.out.println("ignore checkClientTrusted 3");
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                System.out.println("ignore checkServerTrusted 1");
            }


            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
                    throws CertificateException {
                System.out.println("ignore checkServerTrusted 2");
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType,
                    SSLEngine engine) throws CertificateException {
                System.out.println("ignore checkServerTrusted 3");
            }

        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] {trustManager}, new SecureRandom());

        HttpClient httpClient = HttpClient.newBuilder().sslContext(sslContext).build();

        ClientHttpRequestFactory clientHttpRequestFactory =
                new JdkClientHttpRequestFactory(httpClient);

        return restClientBuilder.clone().requestFactory(clientHttpRequestFactory).build();
    }
}
