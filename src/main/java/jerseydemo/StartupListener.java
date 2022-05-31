package jerseydemo;

import domein.KoffieSoort;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class StartupListener implements ServletContextListener {
    public static final List<KoffieSoort> alleKoffie = new ArrayList<>();
    public static final Key signingKey = MacProvider.generateKey();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        KoffieSoort k1 = new KoffieSoort("DE Sterk", 5.50, "DE1", 7);
        KoffieSoort k2 = new KoffieSoort("DE Medium", 4.50, "DE2", 4);
        KoffieSoort k3 = new KoffieSoort("DE Decaf", 2.50, "DE3", 1);

        alleKoffie.add(k1);
        alleKoffie.add(k2);
        alleKoffie.add(k3);
    }
}
