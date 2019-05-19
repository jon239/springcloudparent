package springclode.ribbon.service;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@RestController
public class ServiceRibbonApplication {

    @Value("${server.port}")
    String port;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Autowired
    EurekaClientService eurekaClientService;


    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }



    @RequestMapping("/hi")
    public String hello(String name){

        return eurekaClientService.hello(name) +" -- from ServiceRibbonApplication";
    }

}

