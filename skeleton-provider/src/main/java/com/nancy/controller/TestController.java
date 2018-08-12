package com.nancy.controller;

import com.github.pagehelper.Page;
import com.nancy.entity.Book;
import com.nancy.response.DataResult;
import com.nancy.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private TestService testService ;

    @PostMapping("book")
    private DataResult test() throws Exception {
        Book book = testService.queryBook() ;
        DataResult dataResult = new DataResult() ;
        dataResult.setData(book);
        return dataResult ;
    }

    @PostMapping("bookAll")
    private DataResult bookAll(){
        DataResult dataResult = new DataResult() ;
        Page<Book> books = testService.queryAllBook(1, 3) ;
        dataResult.setData(books);
        return dataResult ;
    }

    @PostMapping("exception")
    private DataResult testException() throws Exception {
        throw new Exception("异常测试") ;
    }
}
