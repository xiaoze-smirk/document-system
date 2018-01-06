package edu.fjnu.service;

import edu.fjnu.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ItemService {

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
