package com.korogi.rest.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import com.korogi.rest.config.KorogiProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * @author Daan Peelman
 */
@Component

@RequiredArgsConstructor
public class EncryptorUtil {
    private final KorogiProperties properties;

    @SneakyThrows
    public String encrypt(
        byte[] salt,
        String stringToEncrypt
    ) {
        IvParameterSpec iv = new IvParameterSpec(salt);
        SecretKeySpec spec = new SecretKeySpec(properties.getEncryption().getKey().getBytes(UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(ENCRYPT_MODE, spec, iv);

        return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes(UTF_8)));
    }

    @SneakyThrows
    public String decrypt(
        String salt,
        String stringToDecrypt
    ) {
        IvParameterSpec iv = new IvParameterSpec(salt.getBytes(UTF_8));
        SecretKeySpec spec = new SecretKeySpec(properties.getEncryption().getKey().getBytes(UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(DECRYPT_MODE, spec, iv);

        return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
    }
}
