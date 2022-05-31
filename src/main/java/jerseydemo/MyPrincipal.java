package jerseydemo;

import java.security.Principal;

public class MyPrincipal implements Principal {
    @Override
    public String getName() {
        return "Sjakie";
    }
}
