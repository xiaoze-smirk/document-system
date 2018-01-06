package edu.fjnu.mapper;

import edu.fjnu.entity.Item;

import java.util.List;

public interface ItemMapper {

    void insert(Item record);

    void insertSelective(Item record);

    void deleteByPrimaryKey(Integer autoId);

    void updateByPrimaryKeySelective(Item record);

    void updateByPrimaryKey(Item record);

    Item selectByPrimaryKey(Integer autoId);

    Integer selectNextAutoId();

    List<Item> findAllItem();

    Item selectByLastRecord();
}