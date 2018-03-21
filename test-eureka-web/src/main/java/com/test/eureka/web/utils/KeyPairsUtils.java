package com.test.eureka.web.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/20  13:55
 * Version: V1.0
 * Description: 非对称加密
 * 使用JDK自带的Base64 以及 对应的类 产生私匙 以及 公匙
 * ======================
 */
public class KeyPairsUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairsUtils.class);


    public static final String ALGORITHM = "RSA"; //使用RSA算法

    private static final Integer SIZE = 1024; //初始化的长度


    public static final String RSA_FILE_PRIVATE = "D:/RAS/private.txt";
    public static final String RSA_FILE_PUBLIC = "D:/RAS/public.txt";

    /**
     * 产生私匙 和 公匙 对
     */
    private static void getSecrity() {

        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);

            kpg.initialize(SIZE, new SecureRandom());

            //得到 私匙 和公匙
            RSAPrivateKey privateKey = (RSAPrivateKey) kpg.generateKeyPair().getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) kpg.generateKeyPair().getPublic();

            //使用Base64 将 公匙 和私匙 进行处理
            String strPrivateKey = Base64Utils.encodeToString(privateKey.getEncoded());
            String strPublicKey = Base64Utils.encodeToString(publicKey.getEncoded());

            writeToFile(strPrivateKey, RSA_FILE_PRIVATE);
            writeToFile(strPublicKey,RSA_FILE_PUBLIC );

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.info("**************未找到指定的算法的异常*******");
        }


    }

    /**
     * 将私匙和公匙写入到指定的文件中
     *
     * @param strPrivateKey  ：私匙或者公匙
     * @param rsaFilePrivate ：文件的名称
     */
    private static void writeToFile(String strPrivateKey, String rsaFilePrivate) {

        if(StringUtils.isBlank(strPrivateKey) || StringUtils.isBlank(rsaFilePrivate)){
            Assert.notNull(strPrivateKey,"参数不能为空" );
            Assert.notNull(rsaFilePrivate,"参数不能为空" );
        }

        BufferedWriter bw = null;
        FileWriter writer = null;
        try {
            writer = new FileWriter(rsaFilePrivate);
            bw = new BufferedWriter(writer);
            bw.write(strPrivateKey);

            bw.flush();
            LOGGER.info("****************写入成功********************");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("***************写入文件失败******************");
        } finally {
            IOUtils.closeQuietly(bw);
            IOUtils.closeQuietly(writer);
        }
    }


    public static void main(String[] args) {
        getSecrity();
    }

}
