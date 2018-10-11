package com.qpp.utils.common;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author qipengpai
 * @Title: GetAddressByBaiduApi
 * @ProjectName bound
 * @Description: TODO 通过API获取 指定经纬度地区
 * @date 16:58 2018/10/11
 */
public class GetAddressByBaiduApi {

    public static JSONObject loadJson (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(json.toString());
    }

    public static void main(String[] args) throws IOException, JSONException {
        String url = "http://api.map.baidu.com/geocoder?location=44.6906623414,124.8110859929&output=json";
        System.out.println(loadJson(url).get("status"));
        System.out.println(JSONObject.parseObject(JSONObject.parseObject(loadJson(url).getString("result")).getString("addressComponent")).getString("province"));
    }
}
