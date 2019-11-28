package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.LiangZhenJi.www.dao.UserDao;

@ServerEndpoint(value ="/newwebsocket/{userId}")
public class WeChatVeiw {
    @Resource
    private WeChatVeiw weChatVeiw;
    
    //静态变量，用来记录当前在线连接数
    private static int onlineCount = 0; 
     //线程安全的Map  
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<String,Session>();//建立连接的方法
    @OnOpen
    public void onOpen(Session session,@PathParam("userId")String  userId){
        /*获取从/websocket开始的整条链接，用于获取userId？***=***的参数
        String uri = session.getRequestURI().toString();*/
    webSocketMap.put(userId, session);
    addOnlineCount(); //在线数加
    }
    
    /**
    * 连接关闭调用的方法
    */
    @OnClose
    public void onClose(Session session){
    Map<String, String> map = session.getPathParameters();
    webSocketMap.remove(map.get("userId")); //从set中删除
    subOnlineCount(); //在线数减
    }
    
    /**
    * 收到客户端消息后调用的方法
    * @param message 客户端发送过来的消息
    * @param session 可选的参数
    */
    @OnMessage
    public void onMessage(String message, Session session) {
    //获取用户ID
    Map<String, String> map = session.getPathParameters();
    String userId = map.get("userId");
    UserDao userDao=new UserDao();
    String userName=userDao.idFindUserName(Integer.parseInt(userId));
    for(String user:webSocketMap.keySet()){
        try {
            sendMessage(userName+":   "+message,webSocketMap.get(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    
    /**
    * 发生错误时调用
    * @param session
    * @param error
    */
    @OnError
    public void onError(Session session, Throwable error){
    error.printStackTrace();
    }
    
    public void sendMessage(String message,Session session) throws IOException{
        if(session.isOpen()){
        session.getAsyncRemote().sendText(message);
        }
        //this.session.getAsyncRemote().sendText(message);
        }
    public static synchronized int getOnlineCount() {
        return onlineCount;
        }
    public static synchronized void addOnlineCount() {
        WeChatVeiw.onlineCount++;
        }
    public static synchronized void subOnlineCount() {
        WeChatVeiw.onlineCount--;
        }
}