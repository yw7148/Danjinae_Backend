package com.capstone.danjinae.fcm.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class FcmNotification {
    public static String NotificationJson(String token, String content) throws JSONException, UnsupportedEncodingException {

        String deviceToken[] = {token};

        List<String> tokenList = new ArrayList<String>();

        for (int i = 0; i < deviceToken.length; i++) {
            tokenList.add(deviceToken[i]);
        }

        JSONArray arr = new JSONArray();

        for (int i = 0; i < tokenList.size(); i++) {
            arr.put(tokenList.get(i));
        }

        JSONObject body = new JSONObject();
        body.put("registration_ids", arr);

        JSONObject notification = new JSONObject();
        notification.put("title", "민원 댓글");
        notification.put("body",  URLDecoder.decode(content, "UTF-8"));

        body.put("notification", notification);

        //
        System.out.println(body.toString());

        return body.toString();
    }
}