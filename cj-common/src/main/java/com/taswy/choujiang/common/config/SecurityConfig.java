package com.taswy.choujiang.common.config;

import com.taswy.choujiang.common.condition.UserCondition;
import com.taswy.choujiang.common.util.JwtTool;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnClass(UserCondition.class)
//@ConditionalOnClass(DispatcherServlet.class)
@RequiredArgsConstructor
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final ResourceLoader resourceLoader;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KeyPair keyPair(JwtProperties prop) {
        try {
            // 加载密钥库
//            logger.debug("开始加载");
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream inputStream = resourceLoader.getResource(prop.getLocation()).getInputStream();
            keyStore.load(inputStream, prop.getPassword().toCharArray());


            // 获取秘钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // 获取公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyStore.getCertificate(prop.getAlias()).getPublicKey();
            byte[] publicKeyBytes = publicKey.getEncoded();
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey rsaPublicKey = keyFactory.generatePublic(publicKeySpec);

            // 获取私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey(prop.getAlias(), prop.getPassword().toCharArray());
            byte[] privateKeyBytes = privateKey.getEncoded();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey rsaPrivateKey = keyFactory.generatePrivate(privateKeySpec);

//            logger.debug("已加载KeyPair" );
//            logger.debug("pub ========= ");
//            logger.debug(String.valueOf(rsaPublicKey));
//            logger.debug("pri ========= ");
//            logger.debug(String.valueOf(rsaPrivateKey));
            return new KeyPair(rsaPublicKey, rsaPrivateKey);
            // 使用公钥和私钥进行加解密操作
        } catch (KeyStoreException | CertificateException|IOException | NoSuchAlgorithmException| UnrecoverableKeyException | InvalidKeySpecException e) {
            logger.error("KeyPair加载失败");
            logger.error(e.toString());
//            e.printStackTrace();
        }


        return null;
    }


}
