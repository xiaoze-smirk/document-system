package edu.fjnu.mapper;

import edu.fjnu.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemMapper {

    void insert(Item record);

    void insertSelective(Item record);

    void deleteByPrimaryKey(Integer itemId);

    void updateByPrimaryKeySelective(Item record);

    void updateByPrimaryKey(Item record);

    Item selectByPrimaryKey(Integer itemId);

    List<Item> findAllItem();

    List<Item> findByItemName(Map map);

    Integer selectNextAutoId();
}