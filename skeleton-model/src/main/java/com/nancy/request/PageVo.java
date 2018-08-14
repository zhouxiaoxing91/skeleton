package com.nancy.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageVo {

    @NotNull(message = "pageNo为空")
    private Integer pageNo ;
    @NotNull(message = "pageSize为空")
    private Integer pageSize ;
}
