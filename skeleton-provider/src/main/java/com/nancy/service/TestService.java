package com.nancy.service;


import com.nancy.response.DataResult;

public interface TestService {

    public DataResult queryBook();

    public DataResult queryAllBook(Integer pageNo, Integer pageSize) ;

    public DataResult searchName(String name);

}
