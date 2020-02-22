package cn.net.immortal.rmi.client;

import cn.net.immortal.rmi.server.RmiServer;
import cn.net.immortal.rmi.server.service.InfoService;

import java.rmi.Naming;

public class RmiClient {

    public void invoke() throws Exception{
        InfoService infoService = (InfoService) Naming.lookup(RmiServer.RMI_URI);
        System.out.println(infoService.helloWorld());
    }
}
