package cn.net.immortal.rmi.server.service.impl;

import cn.net.immortal.rmi.server.service.InfoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InfoServiceImpl extends UnicastRemoteObject implements InfoService {
    public InfoServiceImpl() throws RemoteException {
        super();
    }


    @Override
    public String helloWorld() {
        return "hello world";
    }
}
