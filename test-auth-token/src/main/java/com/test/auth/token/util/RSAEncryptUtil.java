package com.test.auth.token.util;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

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
import java.util.Base64;
import java.util.Objects;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/27  14:34
 * Version: V1.0
 * Description: 通过RSA 获取 Public_key 及 Private_key
 * ======================
 */
public class RSAEncryptUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RSAEncryptUtil.class);

    //使用的加密算法
    private static final String KEY_ALGORITHM = "RSA";

    //长度 （单位 位；）RSA得到的密文长度不能超过 密匙长度的11位；
    private static final Integer KEY_SIZE = 1024;

    //将公匙  和 私匙 保存到指定的 文件中
    private static final String PRIVATE_KEY_FILE = "D:/RAS/private.txt";
    private static final String PUBLIC_KEY_FILE = "D:/RAS/public.txt";


    public static void main(String[] args)
    {
        String text = "123456789";
        //获取密匙对
//        getKeyPair();
        //通过公匙对内容加密
        byte[] bytes = encodeByPublicKey(text.getBytes(), getPublicKeyByStr(getKeyByFile(PUBLIC_KEY_FILE)));
        String string = Base64.getEncoder().encodeToString(bytes);
        System.out.println(string);
    }


    /**
     * 获取 密匙对
     */
    private static void getKeyPair()
    {
        try
        {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            kpg.initialize(KEY_SIZE, new SecureRandom());

            KeyPair keyPair = kpg.generateKeyPair();

            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            String strPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String strPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            LOGGER.info("kpg.generateKeyPair() 生成的公匙：" + strPublicKey);
            LOGGER.info("kpg.generateKeyPair() 生成的私匙：" + strPrivateKey);

            FileWriter privateKeyFW = new FileWriter(PRIVATE_KEY_FILE);
            FileWriter publicKeyFW = new FileWriter(PUBLIC_KEY_FILE);

            BufferedWriter privateKeyBw = new BufferedWriter(privateKeyFW);
            BufferedWriter publicKeyBw = new BufferedWriter(publicKeyFW);

            publicKeyBw.write(strPublicKey);
            privateKeyBw.write(strPrivateKey);

            privateKeyBw.flush();
            publicKeyBw.flush();

            privateKeyBw.close();
            publicKeyBw.close();

            privateKeyFW.close();
            publicKeyFW.close();

            LOGGER.info("写入完成");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            LOGGER.info("加密算法有误，无法找到对应的加密算法");
        } catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.info("创建文件出现异常");
        }
    }


    /**
     * 从文件中 读取 密匙
     *
     * @param filePath ：文件路径
     */
    public static String getKeyByFile(String filePath)
    {
        try
        {
            if (StringUtils.isEmpty(filePath))
            {
                LOGGER.info("文件路径为空");
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String readLine = "";
            if ((readLine = bufferedReader.readLine()) != null)
            {
                sb.append(readLine);
            }

            bufferedReader.close();

            return sb.toString();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            LOGGER.info("未找到对应的文件");
        } catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.info("读取文件出现异常");
        }
        return null;
    }


    /**
     * 从 字符串中 还原 公匙
     *
     * @param string ： 字符串
     * @return ： RSApublicKey 公匙
     */
    public static RSAPublicKey getPublicKeyByStr(String string)
    {
        if (StringUtils.isEmpty(string))
        {
            LOGGER.info("缺少字符串来源");
            return null;
        }
        try
        {
            byte[] decode = Base64.getDecoder().decode(string);
            //使用 公匙规范 还原 公匙
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return (RSAPublicKey) factory.generatePublic(x509EncodedKeySpec);

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            LOGGER.info("指定的算法有误");
        } catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的公匙规范");
        }
        return null;
    }


    /**
     * 从给定的字符串中加载 私匙
     *
     * @param string ：指定的字符串
     * @return : RSAPrivatekey
     */
    public static RSAPrivateKey getPrivateKeyByString(String string)
    {
        if (StringUtils.isEmpty(string))
        {
            LOGGER.info("原字符串为空");
            return null;
        }
        try
        {
            byte[] decode = Base64.getDecoder().decode(string);
            //私匙还原规范
            PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(decode);

            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);

            return (RSAPrivateKey) factory.generatePrivate(pkcs8);

        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            LOGGER.info("找不到指定的算法");
        } catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的规范");
        }
        return null;
    }

    /**
     * 公匙 加密
     *
     * @param content   ：加密内容
     * @param publicKey ： 公匙
     */
    public static byte[] publicKeyEncrypt(byte[] content, RSAPublicKey publicKey)
    {
        if (Objects.isNull(publicKey) || ArrayUtils.isEmpty(content))
        {
            LOGGER.info("通过publicKey加密时 参数有误");
            return null;
        }
        try
        {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            e.printStackTrace();
            LOGGER.info("找不到指定的算法出现异常或者。。。。。。");

        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的key异常");

        } catch (BadPaddingException | IllegalBlockSizeException e)
        {
            e.printStackTrace();
            LOGGER.info("使用公匙加密");
        }
        return null;
    }


    /**
     * 通过 私匙进行 加密
     *
     * @param content    ： 需要加密的过程
     * @param privateKey ：私匙
     */
    public static byte[] encodeByPrivateKey(byte[] content, RSAPrivateKey privateKey)
    {
        if (ArrayUtils.isEmpty(content) || Objects.isNull(privateKey))
        {
            LOGGER.info("通过privateKey 加密时，参数有误");
            return null;
        }

        try
        {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的算法异常");
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的privateKey异常");
        } catch (BadPaddingException | IllegalBlockSizeException e)
        {
            e.printStackTrace();
            LOGGER.info("privateKey加密时出现异常");
        }

        return null;
    }


    /**
     * 通过私匙进行 解密
     *  @param content    ： 解密的内容
     * @param privateKey ：私匙
     */
    public static byte[] decoderByPrivateKey(byte[] content, RSAPrivateKey privateKey)
    {
        if (ArrayUtils.isEmpty(content) || Objects.isNull(privateKey))
        {
            LOGGER.info("私匙机密时参数有误");
            return null ;
        }

        try
        {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e)
        {
            e.printStackTrace();
            LOGGER.info("私匙解密的过程中出现异常");
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的私匙");
        }
        return null;
    }


    /**
     * 公匙解密过程
     *
     * @param content   ： 解密内容
     * @param publicKey ： 公匙
     */
    public static byte[] decoderByPublicKey(byte[] content, RSAPublicKey publicKey)
    {
        if (ArrayUtils.isEmpty(content) || Objects.isNull(publicKey))
        {
            LOGGER.info("publicKey解密过程中 参数有误");
            return null;
        }
        try
        {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            return cipher.doFinal(content);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            e.printStackTrace();
            LOGGER.info("解密时，没有找到指定的算法");
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的公匙");
        } catch (BadPaddingException | IllegalBlockSizeException e)
        {
            e.printStackTrace();
            LOGGER.info("通过公匙解密时出现异常");
        }
        return null;
    }

    /**
     * 公匙 加密
     *
     * @param content   : 加密的内容
     * @param publicKey ： 公匙
     */
    public static byte[] encodeByPublicKey(byte[] content, RSAPublicKey publicKey)
    {
        if (ArrayUtils.isEmpty(content) || Objects.isNull(publicKey))
        {
            LOGGER.info("公匙加密的过程中 参数有误");
            return null;
        }

        try
        {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            e.printStackTrace();
            LOGGER.info("无效的算法");
        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
            LOGGER.debug("无效的私匙");
        } catch (BadPaddingException | IllegalBlockSizeException e)
        {
            e.printStackTrace();
            LOGGER.debug("PrivateKey加密时 出现异常");
        }
        return null;
    }

    /**
     * 将字节内容 通过 Base64 加密成 字符串
     *
     * @param content ： 内容
     * @return ： 加密以后的字符串内容
     */
    public static String encodeByBase64(byte[] content)
    {
        return Base64.getEncoder().encodeToString(content);
    }

    /**
     * 通过Base64 将 字符串 转换成 字节数组
     *
     * @param content ： 字符串内容
     * @return ： 字节数组
     */
    public static byte[] decodeByBase64(String content)
    {
        return Base64.getDecoder().decode(content);
    }
}
