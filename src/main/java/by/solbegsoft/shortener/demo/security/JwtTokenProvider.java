package by.solbegsoft.shortener.demo.security;

import by.solbegsoft.shortener.demo.exception.JwtTokenException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.prefix}")
    private String prefix;
    @Value("${jwt.claim.uuid}")
    private String claimUuid;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getUuid(){
        String bearerToken = getBearerToken();
        if (bearerToken == null){
            throw new JwtTokenException("Jwt token cannot be null");
        }
        String token = bearerToken.replace(prefix,"");
        if (validateToken(token)){
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .get(claimUuid, String.class);
        }else {
            throw new JwtTokenException("Jwt token is expired!");
        }
    }

    private boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtException | IllegalArgumentException e){
            throw new JwtTokenException("Jwt token is invalid!");
        }
    }

    private String getBearerToken(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getHeader(header);
    }
}