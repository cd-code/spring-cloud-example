package me.cauley.servicefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "${remote.service.name}", fallback = RemoteService1FallbackImpl.class)
public interface RemoteService1 {
    @RequestMapping("/hi")
    String sayHiFromClient(@RequestParam("name") String name);
}
