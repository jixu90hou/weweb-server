package org.weweb.zookeeper.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by jackshen on 2016/12/17.
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{
    protected HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s",name);
    }
}
