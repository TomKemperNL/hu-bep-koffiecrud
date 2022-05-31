package jerseydemo;

import domein.KoffieSoort;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class StartupListener implements ServletContextListener {
    public static final List<KoffieSoort> alleKoffie = new ArrayList<>();
    public static final Key signingKey = new SecretKeySpec("BlablablablaEnEenHoopGedoe".getBytes(), SignatureAlgorithm.HS512.getJcaName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        KoffieSoort k1 = new KoffieSoort("DE Sterk", 5.50, "DE1", 7);
        KoffieSoort k2 = new KoffieSoort("DE Medium", 4.50, "DE2", 4);
        KoffieSoort k3 = new KoffieSoort("DE Decaf", 2.50, "DE3", 1);

        alleKoffie.add(k1);
        alleKoffie.add(k2);
        alleKoffie.add(k3);
    }

    public static void main(String[] args) {

        String testToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20iLCJpc3MiOiJiZXAxLWh1IiwiZXhwIjoxNjU0MDM0ODIwfQ.hwKqw519POgjFntsvYpZyzPhoNRdJ8Ez3HieXlW0vvE7f_jzgm4cVyxcd2vUBI7819Wc6bgbOt_so3vHd4VTXQ";

//
//        String token = MySecurityContext.generateToken("tom");
//        System.out.println(token);

        MyPrincipal user = MySecurityContext.validateToken(testToken);
        System.out.println(user.getName());

    }
}
