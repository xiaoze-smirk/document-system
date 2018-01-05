package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Item;
import edu.fjnu.mapper.ItemMapper;
import edu.fjnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public void deleteByPrimaryKey(Integer id) {
        itemMapper.deleteByPrimaryKey(id);
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
    public Item selectByPrimaryKey(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Item> findAllItem() {
        return itemMapper.findAllItem();
    }

    @Override
    public Item selectByLastRecord() {
        return itemMapper.selectByLastRecord();
    }
}
