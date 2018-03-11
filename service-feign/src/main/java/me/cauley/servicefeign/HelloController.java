package me.cauley.servicefeign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created at 2018/3/10
 */
@RestController
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private RemoteService1 remoteService1;

    @RequestMapping("/hi")
    public String hi(@RequestParam String name){
        LOGGER.info("Request by " + name);
        return remoteService1.sayHiFromClient(name);
    }
}
