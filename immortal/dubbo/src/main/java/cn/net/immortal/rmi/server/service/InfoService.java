package cn.net.immortal.rmi.server.service;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InfoService extends Remote {

    String helloWorld() throws RemoteException;
}
