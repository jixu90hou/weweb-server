package org.weweb.zookeeper.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jackshen on 2016/12/17.
 */
public interface HelloService extends Remote{
    String sayHello(String name) throws RemoteException;
}
