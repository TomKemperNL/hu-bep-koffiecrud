package jerseydemo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private static class InlogRequest {
        public String username;
        public String password;
    }

    @POST
    public Response login(InlogRequest req){
        if(req.username.equals("tom") && req.password.equals("test")){
            return Response.ok("Tsjaa.... wat nu").build();
        }else{
            return Response.status(401).entity("Invalid Credentials").build();
        }
    }
}
