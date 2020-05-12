package cn.net.immortal.client.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class ClientService {

    @Value("${spring.application.name}")
    private String name;

    public String getName() {
        return name;
    }
}
