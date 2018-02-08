package edu.fjnu.mapper;

import edu.fjnu.entity.Document;

import java.util.List;
import java.util.Map;

public interface DocumentMapper {
    void insert(Document record);

    void insertSelective(Document record);

    void deleteByPrimaryKey(String docNum);

    void updateByPrimaryKeySelective(Document record);

    void updateByPrimaryKey(Document record);

    Document selectByPrimaryKey(String docNum);

    List<Document> findAllDocument();

    List<Document> findByDocTitle(Map map);
}