package me.cauley.servicefeign;

import org.springframework.stereotype.Component;

/**
 * Created at 2018/3/11
 */
@Component
public class RemoteService1FallbackImpl implements RemoteService1 {
    @Override
    public String sayHiFromClient(String name) {
        String info = "[Feign]Got error while calling service with " + name;
        return info;
    }
}
