package com.test.eureka.web.utils;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/20  15:16
 * Version: V1.0
 * Description: 获取公匙 和 私匙的一些列操作
 * 先从文件中获取到私匙
 * 在使用base64 还原成 原本的私匙或者公匙
 * ======================
 */
public class KeyUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyUtils.class);

    /**
     * 从指定的文件中获取读取数据
     *
     * @param rsaFilePrivate ：文件的路径
     */
    private static String getKeyFromFile(String rsaFilePrivate) {

        if (StringUtils.isBlank(rsaFilePrivate)) {
            Assert.notNull(rsaFilePrivate, "文件路径不能为空");
        }

        FileReader reader = null;
        BufferedReader br = null;
        try {
            reader = new FileReader(rsaFilePrivate);
            br = new BufferedReader(reader);
            return br.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.info("************读取文件失败**********************");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("************从文件中获取数据失败********************");
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(reader);
        }
        return null;
    }

    /**
     * 通过从文件冲获取到的字符串  通过64工具进行 encoder
     *
     * @param keyFromFile ：从文件中获取到的 key 字符串
     * @param num         509 或者 8
     */
    private static Key encoderByBase64(String keyFromFile, int num) {
        Assert.notNull(keyFromFile, "key DON'T NULL");

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KeyPairsUtils.ALGORITHM);
            if (num == 8) {
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(keyFromFile));
                return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            } else if (num == 509) {
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(keyFromFile));
                return (RSAPublicKey) keyFactory.generatePublic(keySpec);
            } else {
                Assert.isTrue(true, "参数有误");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.info("***************无法找到对应的算法*************");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            LOGGER.info("获取公匙或者私匙的时候 规则有误");
        }
        return null;
    }


    /**
     * 使用私匙加密
     *
     * @param privateKey 私匙
     *                   如果有大段的文字 需要进行 分段加密
     * @param text
     */
    private static String privateEncrypt(RSAPrivateKey privateKey, String text) {

        try {
            Cipher cipher = Cipher.getInstance(KeyPairsUtils.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey, new SecureRandom());
            //优化 加密时  分段加密  当明文长度超过 密文的长度时
            return Base64Utils.encodeToString(spiltencript(cipher,Cipher.ENCRYPT_MODE ,text.getBytes() ,privateKey.getModulus().bitLength() ));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            LOGGER.info("*********创建加密对象时 出现异常 NoSuchAlgorithmException | NoSuchPaddingException**********");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            LOGGER.info("***********加密时出现异常  InvalidKeyException****************");
        }
        return null;
    }

    /**
     * private static Object getPublicKey(String publicKeyFromFile) {
     * }
     * <p>
     * /**
     * 使用公匙解密
     *
     * @param publicKey ：公匙解密
     * @param data
     */
    private static String publicKeyUnEncrypt(RSAPublicKey publicKey, String data) {
        try {
            Cipher cipher = Cipher.getInstance(KeyPairsUtils.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            //解密时 分段解密
            byte[] bytes = spiltencript(cipher, Cipher.DECRYPT_MODE, data.getBytes(), publicKey.getModulus().bitLength());
            return new String(Base64Utils.decode(bytes));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            LOGGER.info("****************使用公匙进行解密时出现异常****************");
        }
        return null;
    }

    /**
     * javax.crypto.IllegalBlockSizeException: Data must not be longer than 128 bytes
     * 针对以上 长度大于128位的问题 要采取分段加密的方法
     *
     * @param cipher        ：加密对象
     * @param decryptMode   ：Cipher.DECRYPT_MODE 或者 Cipher.ENCRYPT_MODE
     * @param bytes         ：加密或者解密的数据
     * @param keyModelsSize :获取到的公匙或者私匙的模数
     * @return ：返回分段后组成的数组
     */
    private static byte[] spiltencript(Cipher cipher, int decryptMode, byte[] bytes, int keyModelsSize) {
        int maxBlock = 0; //一个阈值
        //根据 decryptMode 判断是 加密还是解密
        if (decryptMode == Cipher.DECRYPT_MODE) {
            maxBlock = keyModelsSize / 8;  //解密的时候  要求密文长度不超过128字节
        } else {
            maxBlock = keyModelsSize / 8 - 11; // 加密的时候 要求不超过117字节
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            int offset = 0;
            byte[] buff;
            int num = 0; //作为标记
            int length = bytes.length;
            while (length > offset) {
                if (length - offset > maxBlock) {
                    buff = cipher.doFinal(bytes, offset, maxBlock);
                } else {
                    buff = cipher.doFinal(bytes, offset, length - offset);
                }
                outputStream.write(buff, 0, buff.length);
                num++;
                offset = num * maxBlock;
            }
            byte[] array = outputStream.toByteArray();
            IOUtils.closeQuietly(outputStream);
            return array;

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
       /* String text = "铭文";
        String keyFromFile = getKeyFromFile(KeyPairsUtils.RSA_FILE_PRIVATE);
        LOGGER.debug("私匙："+keyFromFile);
        RSAPrivateKey privateKey = (RSAPrivateKey) encoderByBase64(keyFromFile,8);
        //使用私匙进行加密
        System.out.println("加密以后的内容："+privateEncrypt(privateKey,text));*/
        //相同的铭文 加密以后 不相同

        //加密以后的内容
        String data = "QbyS/cPCQxu2njNN2oVP8JBluVdrkrlC/rXvP+mhtn/YQfp2JpDzldepdEDpyFG/A/ht5qKg4KzZHuR4aH9sYssCOuAgRg1vfIWLT5zuDz0kfD13k11xiRP5C5BGvANWCVntBXcBzw4eZ0fS/eSk1ExGfpMKMN56tf56/nAO8NM=";
        //获取公匙
        String publicKeyFromFile = getKeyFromFile(KeyPairsUtils.RSA_FILE_PUBLIC);
        RSAPublicKey publicKey = (RSAPublicKey) encoderByBase64(publicKeyFromFile, 509);
        System.out.println("解密以后的内容：" + publicKeyUnEncrypt(publicKey, data));

    }


}
