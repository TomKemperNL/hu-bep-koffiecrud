package jerseydemo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.Instant;

@Path("/login")
public class LoginResource {

    private static class InlogRequest {
        public String username;
        public String password;
    }

    private static class InlogResponse {
        public String token;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(InlogRequest req){
        if(req.username.equals("tom") && req.password.equals("test")){

            String token = MySecurityContext.generateToken("tom");
            InlogResponse resp = new InlogResponse();
            resp.token = token;

            return Response.ok(resp).build();
        }else{
            return Response.status(401).entity("Invalid Credentials").build();
        }
    }
}
