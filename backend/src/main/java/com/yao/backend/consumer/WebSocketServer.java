package com.yao.backend.consumer;

import com.yao.backend.mapper.UserMapper;
import com.yao.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {

    //存储所有以用户id映射的用户链接，static使得所有实例对象都可见这个变量,这是一个线程安全的hashmap
    private static ConcurrentHashMap<Integer , WebSocketServer> users = new ConcurrentHashMap<>();
    private static Integer OnlineUserNum = 0;
    private static Set<WebSocketServer> webSocketServerSet = new HashSet<>();
    private User user;
    private Session session = null;         //这个session不是http里面的session，是websocket的一个包


    private static UserMapper userMapper;   //这里不像controller,service这种可以直接@Autowired
    @Autowired
    public void setUserMapper(UserMapper userMapper){   //需要用这样的方法才能注入
        WebSocketServer.userMapper = userMapper;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) { //token存的是前端传回来的userId,未来也可以改成加密的
        // 建立连接
        this.session = session;

        Integer userId = Integer.parseInt(token);
        this.user = userMapper.selectById(userId);

        users.put(userId , this);
        webSocketServerSet.add(this);
        OnlineUserNum ++;

        System.out.println("connected！该用户名ID为：" + token +" 当前在线人数为：" + OnlineUserNum);
        sendToAllUsers("[系统消息]:用户" + this.user.getUsername() + "已上线");
    }


    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("disconnected" + "当前在线人数为：" + OnlineUserNum);
        users.remove(this.user.getId());
        webSocketServerSet.remove(this);
        OnlineUserNum --;
        sendToAllUsers("[系统消息]:用户" + this.user.getUsername() + "已离线");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        System.out.println("receive message:" + message);
        //sendMessage("receiveSuccess from:" + this.session.getId());
        sendToAllUsers(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    //发送信息给前端
    public void sendMessage(String message){
        synchronized (this.session){        //不能改成final
            try{
                this.session.getBasicRemote().sendText(message);    //websocket发送信息的api
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //发给所有在线的人
    public void sendToAllUsers(String message){
        for(WebSocketServer item : webSocketServerSet){
            item.sendMessage(message);
        }
    }
}