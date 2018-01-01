package edu.fjnu.service;

import edu.fjnu.entity.Client;
import edu.fjnu.entity.Department;

import java.util.List;

public interface ClientService {

    void insert(Client record);

    void insertSelective(Client record);

    void deleteByPrimaryKey(String clientId);

    void updateByPrimaryKeySelective(Client record);

    void updateByPrimaryKey(Client record);

    Client selectByPrimaryKey(String clientId);

    List<Client> findAllClient();

}
