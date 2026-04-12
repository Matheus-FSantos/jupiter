package io.github.matheus_fsantos.jupiter.jp_cloud_gateway.adapters;

import io.github.matheus_fsantos.jupiter.jp_cloud_gateway.application.ports.out.InternalSecurityFilterOutputPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class InternalSecurityFilterAdapter implements InternalSecurityFilterOutputPort {
    private final PrivateKey privateKey;

    public InternalSecurityFilterAdapter(
        @Value("${spring.application.private-key}") String privateKey
    ) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.privateKey = keyFactory.generatePrivate(encodedKeySpec);
    }

    @Override
    public String generateToken() {
        return Jwts.builder()
            .setSubject("jp-cloud-gateway")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
            .signWith(privateKey, SignatureAlgorithm.RS256)
        .compact();
    }
}
