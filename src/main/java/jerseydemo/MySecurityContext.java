package jerseydemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.sql.Date;
import java.time.Instant;

public class MySecurityContext implements SecurityContext {
    public static String generateToken(String user) {
        String token = Jwts.builder()
                .setSubject(user)
                .setIssuer("bep1-hu")
                .setExpiration(Date.from(Instant.now().plusSeconds(5 * 60)))
                .signWith(SignatureAlgorithm.HS512, StartupListener.signingKey).compact();
        return token;
    }

    public static MyPrincipal validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(StartupListener.signingKey).parseClaimsJws(token);

            return new MyPrincipal(claims.getBody().getSubject());
        } catch (Exception ex) {
            return null;
        }
    }

    private final MyPrincipal principal;

    public MySecurityContext(MyPrincipal principal) {
        this.principal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (this.principal.getName().equals("tom") && role.equals("beheerder")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return "onzin";
    }
}
