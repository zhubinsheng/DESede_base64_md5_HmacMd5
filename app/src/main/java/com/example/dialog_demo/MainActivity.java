package com.example.dialog_demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.example.dialog_demo.beans.Data;
import com.example.dialog_demo.utils.DESede;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String k = "zXxWLlX4+dQyIUp0sgs=";
    private static final String sk = "kyrOfCUuXBDCIVH5fSuni4s2rivdu27qrMVSL8PR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show_dialog(View view) throws Exception {
//        String result = SPUtil.getString(this,SPUtil.FILE_NAME,"serverList","");
//        if (!result.isEmpty()){
//            try
//            {
//                JSONArray jsonArray = new JSONArray(result);
//                for (int i=0; i < jsonArray.length(); i++)    {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    String domain_addr = jsonObject.getString("domain_addr");
//
//                    item2.url = dnP(k,domain_addr);
//
//                    String secretKey = "my.oschina.net/penngo?#@" ;
//
//                    String plainText = "来自http://my.oschina.net/penngo的博客";
//                    String encryptText = DesUtils.encode(secretKey,plainText);
//                    System.out.println(encryptText);
//                    System.out.println(DesUtils.decode(secretKey,encryptText));
//
//                    String encode_DEFAULT = Base64.decode(domain_addr,Base64.DEFAULT);
//
//                    DesUtils.decode(k,encode_DEFAULT)
//
//                    function dnP(vk, str) {
//                        let s = Latin1.stringify(Base64.parse(domain_addr));
//                        let p = des.des(vk, s, 0, 0, null, 1);
//                        return p;
//                    }
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//        String name = jsonObject.getString("name");
//        int age = jsonObject.optInt("age");
//        String sex = jsonObject.optString("sex");


//        headers['Content-Type'] = 'application/x-www-form-urlencoded';
//        headers['Accept'] = 'application/json, application/xml, text/play, text/html, *.*';
//        headers['cmd_id'] = 371136; // 老接口是371134 ，老接口直接返回一个线路数组
//        headers['cust_id'] = "";
//        headers['tid'] = "0";
//        signature = 'react_clientgrant_type=' + grant_type + 'scope=' + scope + 'cmd_id=' + 371136 + 'react';
//        headers['signature'] = md5(signature);

        String url = "ym.wc9955.com/api/v1/";
	    long t = new Date().getTime();

        String rk = md5(String.valueOf(Math.random() + t)).substring(0, 8);
        String sendStr = DESede.enD(rk, format_api(new Data(),0));
        // vk = config.VersionKey
        String enpKsy = rk + t;
        String pwd = DESede.enD(vk, enpKsy);

        String sendDateStr = DESede.enD(rk, String.valueOf(t));
        String checkOr = enC(rk, vk, sendDateStr);

        // Latin1是ISO-8859-1的别名，有些环境下写作Latin-1
        sendStr =  Base64.encodeToString(new String(sendStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).getBytes(),Base64.DEFAULT);
        pwd =  Base64.encodeToString(new String(pwd.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).getBytes(),Base64.DEFAULT);

        Map<String, String> h2 = new HashMap<String, String>();
        h2.put("checkor",checkOr);
        h2.put("pwds",pwd);
        h2.put("datetime",t);
        h2.put("aseq_id",aseq_id);
        h2.put("Cache-Control","no-cache");
        h2.put("'platform'","android");

        Map<String, String> newH2 = new HashMap<String, String>();
        Iterator it = h2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + "---" + "value:" + value);
            key = key.replace("_","");
            key = key.replaceAll("\\s*", "");
            key = key.toUpperCase();

            newH2.put(key,value);
        }

        OkHttpClient okHttpClient = new OkHttpClient();

//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response;

        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("Accept","application/json, application/xml, text/play, text/html, *.*")
                .addHeader("cmd_id","371136")
                .addHeader("cust_id","")
                .addHeader("tid","0")
                .addHeader("signature","7005BEEAD6517453C965C90E057EAFBA")


                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });


//        CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
//        builder.setMessage("这个就是自定义的提示框");
//        builder.setTitle("选择线路");
//        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                //设置你的操作事项
//                Toast.makeText(MainActivity.this,"queding",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.setNegativeButton("重新检测最佳路线",
//                new android.content.DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"queding",Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });
//
//        builder.create().show();

    }

    //type 默认0
    private String format_api(Data data,int type){
        return "AseqId="+data.getAseqId()+"&tid="+data.getTid()+"&ChanCode="+data.getChanCode()+"&clusters_id="+data.getClusters_id();
    }

    public static void reflect(Object o){
        //获取参数类
        Class cls = o.getClass();
        //将参数类转换为对应属性数量的Field类型数组（即该类有多少个属性字段 N 转换后的数组长度即为 N）
        Field[] fields = cls.getDeclaredFields();
        for(int i = 0;i < fields.length; i ++){
            Field f = fields[i];
            f.setAccessible(true);
            try {
                //f.getName()得到对应字段的属性名，f.get(o)得到对应字段属性值,f.getGenericType()得到对应字段的类型
                System.out.println("属性名："+f.getName()+"；属性值："+f.get(o)+";字段类型：" + f.getGenericType());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String enC(String rk,String vk,String m) {
        String enc = getHmacMd5Str(m + rk, vk);
        return Base64.encodeToString(enc.getBytes(),Base64.DEFAULT);
    }

    public static String getHmacMd5Str(String s, String keyString)
    {
        String sEncodedString = null;
        try
        {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(key);

            byte[] bytes = mac.doFinal(s.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();

            for (int i=0; i<bytes.length; i++) {
                String hex = Integer.toHexString(0xFF &  bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            sEncodedString = hash.toString();
        }
        catch (UnsupportedEncodingException e) {}
        catch(InvalidKeyException e){}
        catch (NoSuchAlgorithmException e) {}
        return sEncodedString ;
    }
}
