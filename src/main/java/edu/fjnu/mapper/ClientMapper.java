package edu.fjnu.mapper;

import edu.fjnu.entity.Client;

import java.util.List;
import java.util.Map;

public interface ClientMapper {
    void insert(Client record);

    void insertSelective(Client record);

    void deleteByPrimaryKey(String clientId);

    void updateByPrimaryKeySelective(Client record);

    void updateByPrimaryKey(Client record);

    Client selectByPrimaryKey(String clientId);

    Client selectLikePrimaryKey(String clientId);

    List<Client> findAllClient();

    List<Client> findByClientId(Map map);
}