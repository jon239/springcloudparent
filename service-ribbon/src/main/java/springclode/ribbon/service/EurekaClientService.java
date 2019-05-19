package springclode.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EurekaClientService {

    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(String name){
        return restTemplate.getForObject("http://eureka-client/hello?name="+name,String.class);
    }

    public String helloError(String name){
        return "error"+name;
    }
}
