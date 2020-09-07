package com.example.dialog_demo.utils;

import com.example.dialog_demo.utils.BaseMethod;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.Security;
import java.util.Base64;

/**
 * DESede Encrypt/Decrypt class
 */
public class DESede extends BaseMethod {

    public static String enD(String rk,String content) throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        DESede desede = new DESede();
        // 加解密 密钥

//         byte[] keybytes = {(byte)0x87,(byte)0xe9,(byte)0x11,(byte)0xfe,(byte)0x82,(byte)0xce,(byte)0x8d,'S',(byte)0x08,';','9',(byte)0xd2,(byte)0xbf,'x',(byte)0x03,(byte)0x95,(byte)0x15,(byte)0x99,(byte)0xc2,(byte)0x02,(byte)0x9c,(byte)0xcb,(byte)0xaa,(byte)0xd7,'G',(byte)0x16,(byte)0xd8,(byte)0xae,(byte)0x15,'7','U',(byte)0xdb};
        byte[] keybytes = {(byte)0x87,(byte)0xe9,(byte)0x11,(byte)0xfe,(byte)0x82,(byte)0xce,(byte)0x8d,'S',(byte)0x08,';','9',(byte)0xd2,(byte)0xbf,'x',(byte)0x03,(byte)0x95,(byte)0x15,(byte)0x99,(byte)0xc2,(byte)0x02,(byte)0x9c,(byte)0xcb,(byte)0xaa,(byte)0xd7};
        byte[] ivbytes = {(byte)0x87,(byte)0xe9,(byte)0x11,(byte)0xfe,(byte)0x82,(byte)0xce,(byte)0x8d,'S'};
//        ivbytes = null;
        //加密字符串
//        String content = "{\"Kcv\":\"123456\",\"IDc\":\"User1\",\"IDo\":\"DataOwner1\",\"IDv\":\"222\",\"TS4\":\"1564657964010\",\"lifetime4\":\"657964010\","
//                + "\"AC\":{\"IDc\":\"User1\",\"permission\":{\"Folder_pdf\":{\"Folder_pdf\":[0,1,1,1]}," +
//                "\"Folder_txt\":{\"Folder_txt\":[0,1,0,0],\"xiaohua.txt\":[0,1,1]}}}}";
//        String content = "Hello, 韩- 梅 -梅";
        System.out.println("加密前的：" + content);
//        String key = Base64.getEncoder().encodeToString(keybytes);
//        System.out.println("加密密钥：" + key);
        System.out.println("加密密钥：" + new String(rk));
        // 加密方法
        String enc = desede.encrypt(Method.DESEDE_CBC_PKCS7Padding, rk.getBytes(), ivbytes, content.getBytes());
//        String encode = new BASE64Encoder().encode(enc);


        System.out.println("加密后的内容：" + enc);
//        System.out.println("加密后的内容：" + new String(Hex.encode(enc)));

        // 解密方法
//        byte[] bytes = new BASE64Decoder().decodeBuffer(enc);
        String dec = desede.decrypt(Method.DESEDE_CBC_PKCS7Padding,rk.getBytes(), Key.SIZE_192,ivbytes, enc.getBytes());
        System.out.println("解密后的内容：" + dec);


        return enc;
    }

    private static final int VECTOR_LEGHT = 8;

    /**
     * All supported methods
     */
    public enum Method{
        DESEDE ("DESEDE"),
        DESEDE_CBC_NoPadding ("DESEDE/CBC/NoPadding"),
        DESEDE_CBC_PKCS5Padding ("DESEDE/CBC/PKCS5Padding"),
        DESEDE_CBC_PKCS7Padding ("DESEDE/CBC/PKCS7Padding"),
        DESEDE_CBC_ISO10126Padding ("DESEDE/CBC/ISO10126Padding");

        private final String method;

        Method(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }

    /**
     * Keysize must be equal to 128 or 192 bits.
     * Default Keysize equals 128 bits.
     */
    public enum Key{
        SIZE_128 (16),
        SIZE_192 (24);

        private final int size;

        Key(int size) {
            this.size = size;
        }
    }

    /**
     * Implementation of DESede encryption
     */
//    public static String encrypt(Method method, byte[] key, Key keySize, byte[] vector, byte[] message) throws Exception{

    public static String encrypt(Method method, byte[] key, byte[] vector, byte[] message) throws Exception{
//        generate Key
//        byte[] keyBytes = generateKey(key, keySize.size);
        SecretKeySpec keySpec = new SecretKeySpec(key, method.getMethod());

//        generate Initialization Vector
//        byte[] keyBytesIv = generateVector(vector, VECTOR_LEGHT);
        IvParameterSpec ivSpec = new IvParameterSpec(vector);

        Cipher cipher = Cipher.getInstance(method.getMethod());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(message);

        String encodeCipertext = Base64.getEncoder().encodeToString(cipherText);
//        System.out.println(encodeCipertext);

        return encodeCipertext;
    }

    /**
     * Implementation of DESede decryption
     */
    public static String decrypt(Method method, byte[] key, Key keySize, byte[] vector, byte[] message) throws Exception{

//        generate Key
        byte[] keyBytes = generateKey(key, keySize.size);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, method.getMethod());

//        generate Initialization Vector
        byte[] keyBytesIv = generateVector(vector, VECTOR_LEGHT);
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytesIv);

        Cipher cipher = Cipher.getInstance(method.getMethod());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
//        byte[] cipherText = cipher.doFinal(Base64.decode(message, Base64.DEFAULT));
        byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(message));

        return new String(cipherText);
    }


}