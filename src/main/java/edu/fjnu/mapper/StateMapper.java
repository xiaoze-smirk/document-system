package edu.fjnu.mapper;

import edu.fjnu.entity.State;

import java.util.List;

public interface StateMapper {
    void insert(State record);

    void insertSelective(State record);

    void deleteByPrimaryKey(String stateStr);

    void updateByPrimaryKeySelective(State record);

    void updateByPrimaryKey(State record);

    State selectByPrimaryKey(String stateStr);

    List<State> findAllState();
}