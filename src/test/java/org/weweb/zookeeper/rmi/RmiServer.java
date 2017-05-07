package org.weweb.zookeeper.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by jackshen on 2016/12/17.
 */
public class RmiServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        int port=1099;
        String url="rmi://localhost:1099/org.weweb.zookeeper.rmi.HelloServiceImpl";
        LocateRegistry.createRegistry(port);
        Naming.rebind(url,new HelloServiceImpl());
    }
}
