package com.qpp.nettydemo.configuration;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MyExceptionListener extends ExceptionListenerAdapter {
    @Override
    public void onPingException(Exception e, SocketIOClient client) {

    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        System.out.println(e.getMessage());
        ctx.close();

        return true;
    }
}
