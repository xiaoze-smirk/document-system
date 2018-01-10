package edu.fjnu.serviceImpl;

import edu.fjnu.entity.State;
import edu.fjnu.mapper.StateMapper;
import edu.fjnu.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    @Autowired
    private StateMapper stateMapper;

    @Override
    public void insert(State record) {
        stateMapper.insert(record);
    }

    @Override
    public void insertSelective(State record) {
        stateMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(String stateStr) {
        stateMapper.deleteByPrimaryKey(stateStr);
    }

    @Override
    public void updateByPrimaryKeySelective(State record) {
        stateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(State record) {
        stateMapper.updateByPrimaryKey(record);
    }

    @Override
    public State selectByPrimaryKey(String stateStr) {
        return stateMapper.selectByPrimaryKey(stateStr);
    }

    @Override
    public List<State> findAllState() {
        return stateMapper.findAllState();
    }
}
