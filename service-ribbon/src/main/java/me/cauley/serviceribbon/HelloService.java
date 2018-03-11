package me.cauley.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created at 2018/3/10
 */
@Service
public class HelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.service.name}")
    private String serviceName;

    @HystrixCommand(fallbackMethod = "methodForFail")
    public String hiService(String name){
        LOGGER.info("Request " + serviceName + " by " + name);
        return restTemplate.getForObject("http://" + serviceName + "/hi?name=" + name, String.class);
    }

    public String methodForFail(String name){
        String info = "[Ribbon]Got error while calling service[" + serviceName + "] with " + name;
        LOGGER.info(info);
        return info;
    }
}
