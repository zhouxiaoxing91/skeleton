package com.nancy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nancy.entity.Book;
import com.nancy.mapper.BookMapper;
import com.nancy.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private BookMapper bookMapper ;

    @Override
    public Book queryBook() {
        return bookMapper.selectByPrimaryKey(1) ;
    }

    @Override
    public Page<Book> queryAllBook(Integer pageNo, Integer pageSize) {
        Page<Book> page = PageHelper.startPage(pageNo, pageSize) ;
        List<Book> books = bookMapper.selectAllBook()  ;
        return page ;
    }
}
