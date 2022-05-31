package jerseydemo;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
@Priority(Priorities.AUTHENTICATION) //Nodig omdat @RolesAllowed op Priorities.Authorization zit
public class SecurityFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Ik wordt elk request uitgevoerd!");

        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null) {
            return;
        }

        String token = authHeader.replace("Bearer ", "");
        MyPrincipal principal = MySecurityContext.validateToken(token);

        boolean maghet = principal != null; //afgezien van al het class-gehannes is het eigenlijke probleem dus deze boolean
        if (maghet) {

            requestContext.setSecurityContext(new MySecurityContext(principal));
        }
    }
}
