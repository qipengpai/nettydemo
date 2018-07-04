package com.qpp.nettydemo.Controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.qpp.nettydemo.entity.ClientInfo;
import com.qpp.nettydemo.service.ClientInfoRepository;
import com.qpp.nettydemo.tool.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class MessageEventHandler {
    private final SocketIOServer server;
    Map<UUID, String> ZHUBO =new HashMap<UUID, String>();
    Map<UUID,Object> YONGHU =new HashMap<>();
    Map<String,Object> ROOM =new HashMap<>();
    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    public MessageEventHandler(SocketIOServer server)
    {
        this.server = server;
    }
    //添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client)
    {
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        ClientInfo clientInfo = clientInfoRepository.findClientByclientid(clientId);
        if (clientInfo != null){
            clientInfo.setConnected(1);
            clientInfo.setMostsignbits(client.getSessionId().getMostSignificantBits());
            clientInfo.setLeastsignbits(client.getSessionId().getLeastSignificantBits());
            clientInfo.setLastconnecteddate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
            clientInfoRepository.save(clientInfo);
        }
    }

   /* //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client)
    {
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        ClientInfo clientInfo = clientInfoRepository.findClientByclientid(clientId);
        if (clientInfo != null)
        {
            clientInfo.setConnected(0);
            clientInfo.setMostsignbits(null);
            clientInfo.setLeastsignbits(null);
            clientInfoRepository.save(clientInfo);
        }
    }*/

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
        ClientInfo clientInfo = clientInfoRepository.findClientByclientid(clientId);
        if (clientInfo != null)
        {
            clientInfo.setConnected(0);
            clientInfo.setMostsignbits(null);
            clientInfo.setLeastsignbits(null);
            clientInfoRepository.save(clientInfo);
        }

    }
    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
//    @OnEvent(value = "messageevent")
//    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
//        String targetClientId = data.getTargetClientId();
//        ClientInfo clientInfo = clientInfoRepository.findClientByclientid(targetClientId);
//        if (clientInfo != null && clientInfo.getConnected() != 0){
//            UUID uuid = new UUID(clientInfo.getMostsignbits(), clientInfo.getLeastsignbits());
//            System.out.println(uuid.toString());
//            MessageInfo sendData = new MessageInfo();
//            sendData.setSourceClientId(data.getSourceClientId());
//            sendData.setTargetClientId(data.getTargetClientId());
//            sendData.setMsgType("chat");
//            sendData.setMsgContent(data.getMsgContent());
//            client.sendEvent("messageevent", sendData);
//            server.getClient(uuid).sendEvent("messageevent", sendData);
//        }
//
//    }



    @OnEvent(value = "zhubologin")
    public void onEvent(SocketIOClient client, String zhuboid) {
        client.joinRoom(zhuboid);
        ZHUBO.put(client.getSessionId(),zhuboid);
        ROOM.put(zhuboid,0);
        UUID uuid = new UUID(-8333072771227500401L, 8333072771227500401L);
        server.getClient(uuid).sendEvent("getRooms",ROOM);
    }
    @OnEvent(value = "yonghulogin")
    public void onEvent1(SocketIOClient client, String yonghuid) {
        client.joinRoom("yonghu");
        YONGHU.put(client.getSessionId(),yonghuid);
        client.sendEvent("getRooms",ROOM);
    }
    @OnEvent(value = "joinRoom")
    public void onEvent2(SocketIOClient client, String zhuboid) {
        client.leaveRoom("yonghu");
        client.joinRoom(zhuboid);
        ROOM.put(zhuboid,1);
        server.getRoomOperations("yonghu").sendEvent("getRooms",ROOM);
        server.getRoomOperations(zhuboid).sendEvent("ready",YONGHU.get(client.getSessionId()));
    }
}
