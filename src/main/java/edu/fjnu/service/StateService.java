package edu.fjnu.service;

import edu.fjnu.entity.State;

import java.util.List;

public interface StateService {

    void insert(State record);

    void insertSelective(State record);

    void deleteByPrimaryKey(String stateStr);

    void updateByPrimaryKeySelective(State record);

    void updateByPrimaryKey(State record);

    State selectByPrimaryKey(String stateStr);

    List<State> findAllState();

}
