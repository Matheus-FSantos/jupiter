package io.github.matheus_fsantos.jupiter.jp_user.adapters;

import io.github.matheus_fsantos.jupiter.jp_user.application.ports.out.InternalSecurityTokenOutputPort;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class InternalSecurityTokenAdapter implements InternalSecurityTokenOutputPort {
    private final PublicKey publicKey;

    public InternalSecurityTokenAdapter(
        @Value("${spring.application.public-key}") String publicKeyStr
    ) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = keyFactory.generatePublic(encodedKeySpec);
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
            .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
