package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Item;
import edu.fjnu.mapper.ItemMapper;
import edu.fjnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public void insert(Item record) {
        itemMapper.insert(record);
    }

    @Override
    public void insertSelective(Item record) {
        itemMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(Integer autoId) {
        itemMapper.deleteByPrimaryKey(autoId);
    }

    @Override
    public void updateByPrimaryKeySelective(Item record) {
        itemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Item record) {
        itemMapper.updateByPrimaryKey(record);
    }

    @Override
    public Item selectByPrimaryKey(Integer autoId) {
        return itemMapper.selectByPrimaryKey(autoId);
    }

    @Override
    public Integer selectNextAutoId() {
        return itemMapper.selectNextAutoId();
    }

    @Override
    public List<Item> findAllItem() {
        return itemMapper.findAllItem();
    }

    @Override
    public List<Item> findByItemName(Map map) {
        return itemMapper.findByItemName(map);
    }

    @Override
    public Item selectByLastRecord() {
        return itemMapper.selectByLastRecord();
    }
}
