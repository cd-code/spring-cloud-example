package me.cauley.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created at 2018/3/9
 */
@RestController
public class HomeController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Environment environment;

    @RequestMapping("/hi")
    public String home(@RequestParam String name){
        return "Hi " + name + ", service from server port: " + this.serverPort + " at time " + LocalDateTime.now()
                + "\n ENV: " + Arrays.deepToString(environment.getActiveProfiles());
    }
}
