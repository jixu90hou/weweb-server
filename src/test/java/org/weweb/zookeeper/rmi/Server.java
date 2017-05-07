package org.weweb.zookeeper.rmi;

import java.rmi.RemoteException;

/**
 * Created by jackshen on 2016/12/18.
 */
public class Server {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        if (args.length!=2){
            System.err.println("please using command:java Server <rmi_host> <rmi_port>");
            System.exit(-1);
        }
        String host=args[0];
        int port=Integer.valueOf(args[1]);

        ServiceProvider provider=new ServiceProvider();

        HelloService helloService=new HelloServiceImpl();
        provider.publish(helloService,host,port);


        Thread.sleep(Long.MAX_VALUE);
    }
}
