package org.weweb.util;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *  http://www.open-open.com/lib/view/open1428648292500.html
 * 功能说明：websocket处理类, 使用J2EE7的标准
 * 切忌直接在该连接处理类中加入业务处理代码
 * 作者：liuxing(2014-11-14 04:20)
 */
//relationId和userCode是我的业务标识参数,websocket.ws是连接的路径，可以自行定义
@ServerEndpoint("/websocket.ws/{relationId}/{userCode}")
public class WebsocketEndPoint {
    private static Logger logger=Logger.getLogger(WebsocketEndPoint.class);

    /**
     * 打开连接时触发
     * @param relationId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("relationId") String relationId,
                       Session session){
        logger.info("Websocket Start Connecting:"+ relationId);
        WebSocketSessionUtils.put(relationId, session);
    }

    /**
     * 收到客户端消息时触发
     * @param relationId
     * @param message
     * @return
     */
    @OnMessage
    public String onMessage(@PathParam("relationId") String relationId,
                            String message) {
        return"Got your message ("+ message +").Thanks !";
    }

    /**
     * 异常时触发
     * @param relationId
     * @param session
     */
    @OnError
    public void onError(@PathParam("relationId") String relationId,
                        Throwable throwable,
                        Session session) {
        logger.info("Websocket Connection Exception:"+ relationId);
        logger.info(throwable.getMessage(), throwable);
        WebSocketSessionUtils.remove(relationId);
    }

    /**
     * 关闭连接时触发
     * @param relationId
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("relationId") String relationId,
                        Session session) {
        logger.info("Websocket Close Connection:"+ relationId);
        WebSocketSessionUtils.remove(relationId);
        WebSocketSessionUtils.get("11").getAsyncRemote().sendText("22222");
    }

}