package sejong.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final Secret secret;

    public DataInitializer(Secret secret) {
        this.secret = secret;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataInitializer");
        System.out.println(secret.getAdmin().getAdminId());
        System.out.println(secret.getAdmin().getAdminPass());
    }
}
