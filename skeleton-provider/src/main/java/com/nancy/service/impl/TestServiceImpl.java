package com.nancy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nancy.entity.Book;
import com.nancy.mapper.BookMapper;
import com.nancy.response.DataResult;
import com.nancy.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private BookMapper bookMapper ;

    @Override
    public DataResult queryBook() {
        Book book = bookMapper.selectByPrimaryKey(1) ;
        DataResult dataResult = new DataResult() ;
        dataResult.setData(book);
        return dataResult ;
    }

    @Override
    public DataResult queryAllBook(Integer pageNo, Integer pageSize) {
        Page<Book> page = PageHelper.startPage(pageNo, pageSize) ;
        List<Book> books = bookMapper.selectAllBook()  ;
        DataResult dataResult = new DataResult() ;
        dataResult.setData(page);
        return dataResult ;
    }

    @Override
    public DataResult searchName(String name){
        DataResult dataResult = new DataResult();
        List<Book> book = bookMapper.searchName(name);
        dataResult.setData(book);
        return dataResult ;
    }
}
