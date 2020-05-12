package cn.net.immortal.client.controller;

import cn.net.immortal.client.service.ClientService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
@RequestMapping("/client")
public class ClientController {

    @Resource
    private ClientService clientService;


    @GetMapping("/name")
    public String name(){
        return clientService.getName();
    }

}
