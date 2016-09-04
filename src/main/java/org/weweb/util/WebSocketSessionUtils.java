package org.weweb.util;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * http://www.open-open.com/lib/view/open1428648292500.html
 * 功能说明：用来存储业务定义的sessionId和连接的对应关系
 * 利用业务逻辑中组装的sessionId获取有效连接后进行后续操作
 * @author shen(2016-05-16)
 */
public class WebSocketSessionUtils {

    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static void put(String relationId, Session session) {
        clients.put(relationId, session);
    }

    public static Session get(String relationId) {
        return clients.get(relationId);
    }

    public static void remove(String relationId) {
        clients.remove(relationId);
    }

    /**
     * 判断是否有连接
     *
     * @param relationId
     * @return
     */
    public static boolean hasConnection(String relationId) {
        return clients.containsKey(relationId);
    }
}