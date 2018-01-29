import com.test.auth.token.util.RSAEncryptUtil;
import com.test.auth.token.util.RSASign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/29  10:01
 * Version: V1.0
 * Description: 公私匙 分别加密 然后再检验以及验签
 * ======================
 */
public class TestRSA
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRSA.class);


    private static final String PRIVATE_KEY_FILE = "D:/RAS/private.txt";
    private static final String PUBLIC_KEY_FILE = "D:/RAS/public.txt";

    public static void main(String[] args)
    {
        //需要加密的 内容
        String encryptText = "测试私匙加密公匙解密的文字";

        LOGGER.info("私匙加密 -------  公匙解密");

        //从文件中加载私匙
        String privateKeyByFile = RSAEncryptUtil.getKeyByFile(PRIVATE_KEY_FILE);

        //将字符串还原成私匙
        RSAPrivateKey rsaPrivateKey = RSAEncryptUtil.getPrivateKeyByString(privateKeyByFile);

        //加密以后的内容
        byte[] encodeData = RSAEncryptUtil.encodeByPrivateKey(encryptText.getBytes(), rsaPrivateKey);

        //Base64加密
        String secretData = RSAEncryptUtil.encodeByBase64(encodeData);

        //从文件中加载公匙
        String publicKeyByFile = RSAEncryptUtil.getKeyByFile(PUBLIC_KEY_FILE);

        //将文件中的公匙通过 Base64 以及 公匙规范 工厂 得到公匙
        RSAPublicKey rsaPublicKey = RSAEncryptUtil.getPublicKeyByStr(publicKeyByFile);

        // 通过公匙解密
        byte[] decodeDataByte = RSAEncryptUtil.decoderByPublicKey(RSAEncryptUtil.decodeByBase64(secretData), rsaPublicKey);

        assert decodeDataByte != null;
        String decodeData = new String(decodeDataByte);

        System.out.println("原文是："+encryptText);
        System.out.println("加密以后是："+secretData);
        System.out.println("解密以后是："+decodeData);

        System.out.println("----------------------------------------------------------------------------------------");

        //对需要加密的内容使用私匙进行 签名； 一起发送给 另一方
        String sign = RSASign.sign(encryptText.getBytes(), RSAEncryptUtil.getKeyByFile(PRIVATE_KEY_FILE));

        System.out.println("签名串："+sign);

        System.out.println("************************验签**********************************");

        //接收方 使用公匙 先解密数据，然后再使用公匙 以及 签名 对解密的数据进行 验签 确保没有被更改过
        boolean checkSign = RSASign.checkSign(decodeData, RSAEncryptUtil.getKeyByFile(PUBLIC_KEY_FILE), sign);
        System.out.println("检验结果："+checkSign);


        LOGGER.info("公匙加密 ------------------------------ 私匙解密 ");

        String testText = "测试使用公匙加密后然后使用私匙解密的过程";

        //获取到公匙 并对内容进行加密
        byte[] encodeByPublicKey = RSAEncryptUtil.encodeByPublicKey(testText.getBytes(), RSAEncryptUtil.getPublicKeyByStr(RSAEncryptUtil.getKeyByFile(PUBLIC_KEY_FILE)));
        String strEncodeByPublicKey = RSAEncryptUtil.encodeByBase64(encodeByPublicKey);

        //获取到私匙 并对内容进行 解密
        byte[] decoderByPrivateKey = RSAEncryptUtil.decoderByPrivateKey(RSAEncryptUtil.decodeByBase64(strEncodeByPublicKey), RSAEncryptUtil.getPrivateKeyByString(RSAEncryptUtil.getKeyByFile(PRIVATE_KEY_FILE)));

        assert decoderByPrivateKey != null;
        String text = new String(decoderByPrivateKey);

        System.out.println("公匙加密以后的内容："+strEncodeByPublicKey);
        System.out.println("私匙解密以后的内容："+text);
    }


}
