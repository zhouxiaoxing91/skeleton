package com.nancy.controller;

import com.nancy.request.PageVo;
import com.nancy.response.DataResult;
import com.nancy.service.TestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private TestService testService ;

    @PostMapping("book")
    private DataResult test() throws Exception {
        return testService.queryBook() ;
    }

    @PostMapping("bookAll")
    private DataResult bookAll(@RequestBody @Validated PageVo pageVo){
        return testService.queryAllBook(pageVo.getPageNo(), pageVo.getPageSize()) ;
    }

    @PostMapping("exception")
    private DataResult testException() throws Exception {
        throw new Exception("异常测试") ;
    }

    @RequestMapping(value="search",method = {RequestMethod.POST,RequestMethod.GET})
    private DataResult testLike(@RequestParam(value="name") String name){
        return testService.searchName(name);
    }


}
