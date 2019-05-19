package springclode.feign.service;

import org.springframework.stereotype.Component;

@Component
public class EurekaClientHystrix implements EurekaClientInterface {
    @Override
    public String hello(String name) {
        return "error "+name;
    }
}

