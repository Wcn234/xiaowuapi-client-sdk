package com.example.xiaowuapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.xiaowuapiclientsdk.model.User;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.example.xiaowuapiclientsdk.Utils.SignUtils.getSign;


public class XiaowuApiClient {
    private String accessKey;
    private String secretKey;

    public XiaowuApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){

      HashMap<String,Object> paramMap = new HashMap<>();
       paramMap.put("name",name);

        String result4= HttpUtil.get("http://localhost:8123/api/name/", paramMap);

       System.out.println(result4);

      return result4;
    };


    public String getNameByPost( String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        System.out.println(result);
        return result;
    }
private Map<String,String> getHeaderMap(String body) {
        Map<String,String> map = new HashMap<>();
        map.put("accessKey",accessKey);
//        map.put("secretKey",secretKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body",body);
        map.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        map.put("sign",secretKey);
        map.put("secretKey",secretKey);
        map.put("sign", getSign(body,secretKey));
        return map;
}
    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/user/")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        System.out.println(httpResponse.body());
        return httpResponse.body();
    }
}
