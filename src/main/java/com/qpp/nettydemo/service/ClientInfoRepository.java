package com.qpp.nettydemo.service;


import com.qpp.nettydemo.dao.ClientInfoMapper;
import com.qpp.nettydemo.entity.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientInfoRepository {

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    public ClientInfo findClientByclientid(String clientId) {
       return  clientInfoMapper.selectByPrimaryKey(clientId);
    }

    public int save(ClientInfo clientInfo) {
        return clientInfoMapper.updateByPrimaryKeySelective(clientInfo);
    }
}
