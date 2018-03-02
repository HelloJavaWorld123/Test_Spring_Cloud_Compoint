package com.test.auth.token.service.impl;

import com.test.auth.token.service.rpc.TokenUtilsService;
import com.test.auth.token.util.RSAEncryptUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/2  9:20
 * Version: V1.0
 * Description:
 * ======================
 */
@Service
public class TokenUtilsServiceImpl implements TokenUtilsService {

    private static final String PRIVATE_KEY_FILE = "D:/RAS/private.txt";


    @Override
    public String decodePassword(String password) {

        if(null == password){
            return null ;
        }
        byte[] privateKey = RSAEncryptUtil.decoderByPrivateKey(password.getBytes(), RSAEncryptUtil.getPrivateKeyByString(RSAEncryptUtil.getKeyByFile(PRIVATE_KEY_FILE)));

        if(ArrayUtils.isEmpty(privateKey)){
            return null;
        }

        byte[] decode = Base64.getDecoder().decode(privateKey);

        return new String(decode);
    }
}
