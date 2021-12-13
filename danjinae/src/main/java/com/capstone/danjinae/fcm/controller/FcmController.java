package com.capstone.danjinae.fcm.controller;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.capstone.danjinae.fcm.service.FcmService;
import com.capstone.danjinae.fcm.service.FcmNotification;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class FcmController {

    @Autowired
    FcmService fcmService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/push")
    public @ResponseBody
    ResponseEntity<String> push(@RequestParam("token") String token,@RequestParam("content") String content) throws JSONException, InterruptedException, UnsupportedEncodingException {
        String notification = FcmNotification.NotificationJson(token, content );

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
