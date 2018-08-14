package com.nancy.controller;

import com.github.pagehelper.Page;
import com.nancy.entity.Book;
import com.nancy.request.PageVo;
import com.nancy.response.DataResult;
import com.nancy.service.TestService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private DataResult bookAll(@RequestBody @Validated PageVo pageVo){
        DataResult dataResult = new DataResult() ;
        Page<Book> books = testService.queryAllBook(pageVo.getPageNo(), pageVo.getPageSize()) ;
        dataResult.setData(books);
        return dataResult ;
    }

    @PostMapping("exception")
    private DataResult testException() throws Exception {
        throw new Exception("异常测试") ;
    }

    @RequestMapping(value="search",method = {RequestMethod.POST,RequestMethod.GET})
    private DataResult testLike(@RequestParam(value="name") String name){
        DataResult dataResult = new DataResult();
        List<Book> book = testService.searchName(name);
        dataResult.setData(book);
        return dataResult;
    }


}
