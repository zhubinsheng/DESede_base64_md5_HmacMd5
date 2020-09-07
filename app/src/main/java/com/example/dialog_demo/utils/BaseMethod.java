package com.example.dialog_demo.utils;

import java.io.UnsupportedEncodingException;
 
public abstract class BaseMethod {
    /**
     * Method for creation of valid byte array from key
     */
    static byte[] generateKey(byte[] key, int lenght) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[lenght];
        int len = key.length;
 
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
 
        System.arraycopy(key, 0, keyBytes, 0, len);
        return keyBytes;
    }
 
    /**
     * Method for creation of valid byte array from initialization vector
     */
    static byte[] generateVector(byte[] vector, int lenght) throws UnsupportedEncodingException {
        byte[] keyBytesIv = new byte[lenght];
        int len = vector.length;
 
        if (len > keyBytesIv.length) {
            len = keyBytesIv.length;
        }
 
        System.arraycopy(vector, 0, keyBytesIv, 0, len);
        return keyBytesIv;
    }
 
    /**
     * This method contains a list of encryption methods, that do does not have a initialization vector
     */
    public static boolean hasInitVector(String method){
 
//        All ECB methods do not support initialization vector
        if(method.contains("ECB")){
            return false;
        }
 
        switch (method){
            case "PBEWITHSHA1AND128BITRC4":
            case "PBEWITHSHA1AND40BITRC4":
            case "PBEWITHSHAAND128BITRC4":
            case "PBEWITHSHAAND40BITRC4":
                return false;
        }
        return true;
    }
}