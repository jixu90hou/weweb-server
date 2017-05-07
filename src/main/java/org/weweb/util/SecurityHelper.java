package org.weweb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by jackshen on 2017/5/6.
 */

public class SecurityHelper {
    protected final static Log logger = LogFactory.getLog(SecurityHelper.class);
    private final static int ITERATIONS = 20;

    public static String encrypt(String key, String plainText) throws Exception {
        String encryptTxt = "";
        try {
            byte[] salt = new byte[8];
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            byte[] digest = md.digest();
            for (int i = 0; i < 8; i++) {
                salt[i] = digest[i];
            }
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndDES");
            SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            String saltString = new String(Base64.getEncoder().encode(salt));
            String ciphertextString = new String(Base64.getEncoder().encode(cipherText));
            return saltString + ciphertextString;
        } catch (Exception e) {
            throw new Exception("Encrypt Text Error:" + e.getMessage(), e);
        }
    }

    public static String decrypt(String key, String encryptTxt)
            throws Exception {
        int saltLength = 12;
        try {
            String salt = encryptTxt.substring(0, saltLength);
            String ciphertext = encryptTxt.substring(saltLength, encryptTxt
                    .length());
            byte[] saltarray = Base64.getDecoder().decode(salt.getBytes());
            byte[] ciphertextArray = Base64.getDecoder().decode(ciphertext.getBytes());
            PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndDES");
            SecretKey skey = keyFactory.generateSecret(keySpec);
            PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray,
                    ITERATIONS);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
            byte[] plaintextArray = cipher.doFinal(ciphertextArray);
            return new String(plaintextArray);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    public static void main(String[] args) {
        String encryptTxt = "";
        String plainTxt = "21|zhangmingming.jpeg";
        try {
            System.out.println(plainTxt);
            encryptTxt = encrypt("sstparts", plainTxt);
            plainTxt = decrypt("sstparts", encryptTxt);
            System.out.println(encryptTxt);
            System.out.println(plainTxt);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
