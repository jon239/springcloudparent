package springclode.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableHystrixDashboard
@RestController
public class ServiceFeignApplication {

    @Value("${server.port}")
    String port;

    @Autowired
    EurekaClientInterface eurekaClientInterface;

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }



    @RequestMapping("/hi")
    public String hello(String name){
        return eurekaClientInterface.hello(name)+" -- from ServiceFeignApplication";
    }
}

