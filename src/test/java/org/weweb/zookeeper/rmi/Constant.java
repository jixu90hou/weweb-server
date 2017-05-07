package org.weweb.zookeeper.rmi;

/**
 * Created by jackshen on 2016/12/18.
 */
public interface Constant {

    String ZK_CONNECTION_STRING = "192.168.85.100:2181";
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/registry";
    String ZK_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider";
}
