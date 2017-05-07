package org.weweb.zookeeper.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by jackshen on 2016/12/17.
 */
public class RmiClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String url="rmi://localhost:1099/org.weweb.zookeeper.rmi.HelloServiceImpl";
        HelloService helloService= (HelloService) Naming.lookup(url);
        String result=helloService.sayHello("Jack");
        System.out.println(result);
    }
}
