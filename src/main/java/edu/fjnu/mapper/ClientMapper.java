package edu.fjnu.mapper;

import edu.fjnu.entity.Client;

import java.util.List;

public interface ClientMapper {
    void insert(Client record);

    void insertSelective(Client record);

    void deleteByPrimaryKey(String clientId);

    void updateByPrimaryKeySelective(Client record);

    void updateByPrimaryKey(Client record);

    Client selectByPrimaryKey(String clientId);

    List<Client> findAllClient();
}