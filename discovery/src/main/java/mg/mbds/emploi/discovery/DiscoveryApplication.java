package mg.mbds.emploi.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication {

    public static void main(String[] args) {

        SpringApplication.run(DiscoveryApplication.class, args);
        System.out.println("**********Discovery service is running...***********");
    }

}
