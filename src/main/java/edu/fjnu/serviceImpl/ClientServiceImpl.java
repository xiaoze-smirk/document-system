package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Client;
import edu.fjnu.mapper.ClientMapper;
import edu.fjnu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientMapper clientMapper;

    @Override
    public void insert(Client record) {
        clientMapper.insert(record);
    }

    @Override
    public void insertSelective(Client record) {
        clientMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(String clientId) {
        clientMapper.deleteByPrimaryKey(clientId);
    }

    @Override
    public void updateByPrimaryKeySelective(Client record) {
        clientMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Client record) {
        clientMapper.updateByPrimaryKey(record);
    }

    @Override
    public Client selectByPrimaryKey(String clientId) {
        return clientMapper.selectByPrimaryKey(clientId);
    }

    @Override
    public List<Client> findAllClient() {
        return clientMapper.findAllClient();
    }
}
