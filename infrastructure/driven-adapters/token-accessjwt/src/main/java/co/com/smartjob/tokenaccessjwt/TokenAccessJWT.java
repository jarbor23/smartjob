package co.com.smartjob.tokenaccessjwt;

import co.com.smartjob.model.user.gateways.TokenAccess;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import io.jsonwebtoken.Jwts;



@Component
public class TokenAccessJWT implements TokenAccess {
    @Value("${token.jwt.secretKey}")
    private String secretKey;
    @Override
    public String getToken(String username) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }
}
