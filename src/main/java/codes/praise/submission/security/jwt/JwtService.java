package codes.praise.submission.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SIGNING_KEY;


    public String generateToken(UserDetails userDetails) {
        return buildToken(userDetails, new HashMap<>());
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return buildToken(userDetails, extraClaims);
    }

    public String buildToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .setSubject(userDetails.getUsername())
                .setClaims(extraClaims)
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractAllClaims(String jwToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwToken)
                .getBody();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public boolean isTokenValid(String jwt, UserDetails user) {
        return !isTokenExpired(jwt) && user.getUsername().equals(extractUsername(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractClaim(jwt, Claims::getExpiration).before(new Date());
    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(this.SIGNING_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
