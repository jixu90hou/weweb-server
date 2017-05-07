package org.weweb.zookeeper.rmi;

import java.rmi.RemoteException;

/**
 * Created by jackshen on 2016/12/18.
 */
public class Client {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        ServiceConsumer consumer=new ServiceConsumer();

        while (true){
            HelloService helloService=consumer.lookup();
            String result=helloService.sayHello("Feng");
            System.out.println(result);
            Thread.sleep(3000);
        }
    }
}
