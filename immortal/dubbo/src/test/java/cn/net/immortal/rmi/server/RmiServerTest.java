package cn.net.immortal.rmi.server;

import cn.net.immortal.rmi.server.service.InfoService;
import cn.net.immortal.rmi.server.service.impl.InfoServiceImpl;
import org.junit.Test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * rmi 服务端
 */
public class RmiServerTest {

    @Test
    public void rmiServerTest() throws Exception {
        InfoService infoService = new InfoServiceImpl();
        LocateRegistry.createRegistry(RmiServer.PORT);
        Naming.bind(RmiServer.RMI_URI,infoService);
        System.out.println("server start");
        System.in.read();
    }
}
