package com.qpp.nettydemo.dao;

import com.qpp.nettydemo.entity.ClientInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInfoMapper {
    int deleteByPrimaryKey(String clientid);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    ClientInfo selectByPrimaryKey(String clientid);

    int updateByPrimaryKeySelective(ClientInfo record);

    int updateByPrimaryKey(ClientInfo record);
}