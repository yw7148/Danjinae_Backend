package com.capstone.danjinae.fcm.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FcmService {
    private static final String firebase_server_key = "AAAAfPsMzXg:APA91bFl2C0618YmaCL87FrOXq19Y9hn1CDC-xeYCMldF7DVI3smBNUN0p6JsDVeTh_gwKe8FYT7nu7ViGh8KtRVhgyEoVcx5QH1j9-N3_0FLJMtfbw8I-ha836UzTPTMm4Kf1FIoJ-V";
    private static final String firebase_api_url = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        interceptors.add(new FcmInterceptor("Authorization", "key=" + firebase_server_key));
        interceptors.add(new FcmInterceptor("Content-Type", "application/json; UTF-8 "));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}