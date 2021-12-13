package com.capstone.danjinae.fcm.service;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class FcmPush {

    @Autowired
    FcmService fcmService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<String> push(String token, String content) throws JSONException, InterruptedException, UnsupportedEncodingException {
        String notification = FcmNotification.NotificationJson(token,content);

        HttpEntity<String> request = new HttpEntity<>(notification);

        CompletableFuture<String> pushNotification = fcmService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            logger.debug("Interrupted Exception");
            throw new InterruptedException();
        } catch (ExecutionException e) {
            logger.debug("Execution Exception");
        }

        return new ResponseEntity<>("Push error", HttpStatus.BAD_REQUEST);
    }
}
