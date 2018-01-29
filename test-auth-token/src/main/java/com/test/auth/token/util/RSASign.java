package com.test.auth.token.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/28  11:45
 * Version: V1.0
 * Description: 签名 以及 验签类
 * ======================
 */
public class RSASign
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RSASign.class);


    private static final String PRIVATE_KEY_FILE_PATH = "D:/RAS/private.txt";

    //签名的算法 可在sign中查找
    private static final String SIGN_ALGORITHM = "SHA1withRSA";

    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 通过私匙 签发 签名
     *
     * @param data       ：加密数据
     * @param privateKey ： 私匙
     */
    public static String sign(byte[] data, String privateKey)
    {
        try
        {
            //先使用Base64解密
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
            //使用规范获取私匙
            PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privateKeyBytes);

            //使用工厂 进行初始化
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            //更具规范 从工厂中获取到私匙
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) factory.generatePrivate(pkcs8);

            //获取签名对象
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            //有私匙 初始化签名
            signature.initSign(rsaPrivateKey);
            signature.update(data);

            return Base64.getEncoder().encodeToString(signature.sign());

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            LOGGER.info("签名时，找不到指定的算法");
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的私匙");
        } catch (SignatureException e)
        {
            e.printStackTrace();
            LOGGER.info("签名时出现异常");
        } catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的规范");
        }
        return null;
    }


    /**
     * 验签
     *
     * @param content   ： 原始签名内容
     * @param publicKey ：公匙
     * @param sign      ：使用Base64加密后的签名串
     */
    public static boolean checkSign(String content, String publicKey, String sign)
    {
        try
        {
            //处理字符串公匙
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);

            X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(x509);
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initVerify(rsaPublicKey);
            signature.update(content.getBytes());

            return signature.verify(RSAEncryptUtil.decodeByBase64(sign));

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的算法签名");
        } catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的公匙规范");
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的公匙");
        } catch (SignatureException e)
        {
            e.printStackTrace();
            LOGGER.info("更新数据时，出现异常");
        }
        return false;
    }


}
