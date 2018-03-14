package edu.fjnu.service;

import edu.fjnu.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * @author xiaoze
 * @date 2018/3/8
 */
public interface ItemService {

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
