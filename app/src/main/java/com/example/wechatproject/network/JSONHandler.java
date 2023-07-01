package com.example.wechatproject.network;


import android.content.Context;
import android.net.Uri;
import android.os.Build;

import com.example.wechatproject.util.CurrentUserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * Created by: 神楽坂千紗
 * 用以生成JSON字符串，在登陆、信息传递都有用途。
 */
public class JSONHandler {
    private CurrentUserInfo info;
    private static Context context;

    //生成注册JSON对象
    public static JSONObject generateRegisterJSON(String username, String password, String photoId, String siganture) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "register");
            json.put("username", username);
            json.put("password", password);
            json.put("photoId", photoId);
            json.put("signature", siganture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //生成更新JSON对象
    public static JSONObject generateUpdateJSON(String username, String password, String photoId, String siganture, String fileType) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "update");
            json.put("username", username);
            json.put("password", password);
            json.put("photoId", photoId);
            json.put("signature", siganture);
            json.put("fileType",fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONObject generateAddFriendJSON(String username, String targetUsername){
        JSONObject json = new JSONObject();
        try{
            json.put("type","addFriend");
            json.put("username",username);
            json.put("targetUsername",targetUsername);
        } catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public static JSONObject generateAddConfirmJSON(String username, String targetUsername){
        JSONObject json = new JSONObject();
        try{
            json.put("type","addConfirm");
            json.put("username",username);
            json.put("targetUsername",targetUsername);
        } catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    //生成登陆JSON对象
    public static JSONObject generateLoginJSON(String username, String password) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "login");
            json.put("username", username);
            json.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //生成发送消息JSON对象
    public static JSONObject generatePostMessageJSON(String sendId, String receiveId, String content) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "postMessage");
            json.put("sendId", sendId);
            json.put("receiveId", receiveId);
            json.put("content", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //生成接收消息请求JSON对象
    public static JSONObject generateGetContentJSON(String receiveId) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "getContent");
            json.put("receiveId", receiveId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //生成base64编码的JSON对象
    public static JSONObject generateBase64JSON(String sendId, String receiveId, String content, int fileType, String fileName) {
        JSONObject json = new JSONObject();
        try {
            json.put("type", "postBase64");
            json.put("sendId", sendId);
            json.put("receiveId", receiveId);
            json.put("fileType", fileType);
            json.put("fileName", fileName);
            json.put("content", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 以下是解析逻辑
     */

    //解析消息传递JSON字符串
    public static List<String> parseGetJSON(String json) throws JSONException {
        List<String> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);

        String sendId = jsonObject.getString("sendId");
        String TS = jsonObject.getString("TS");
        String type = jsonObject.getString("type");
        String content = jsonObject.getString("content");
        list.add(sendId);
        list.add(type);
        list.add(TS);
        list.add(content);

        return list;
    }


    public JSONHandler() {}
}
