package com.nancy.service;


import com.github.pagehelper.Page;
import com.nancy.entity.Book;

import java.util.List;

public interface TestService {

    public Book queryBook();

    public Page<Book> queryAllBook(Integer pageNo, Integer pageSize) ;

}
