package com.nancy.handler;

import com.nancy.response.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理机制
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {MethodArgumentNotValidException.class, Exception.class} )
  @ResponseBody
  public DataResult jsonErrorHandler(HttpServletRequest request, Exception e){
    DataResult response = new DataResult();
    response.setErrorCode("10000");

    StringBuilder errResult = new StringBuilder() ;
    if(e instanceof MethodArgumentNotValidException){
      MethodArgumentNotValidException argsException = (MethodArgumentNotValidException) e;
      for (FieldError error : argsException.getBindingResult().getFieldErrors()) {
        errResult.append(error.getField()) ;
        errResult.append("=[value=") ;
        errResult.append(error.getRejectedValue()) ;
        errResult.append(", message=") ;
        errResult.append(error.getDefaultMessage()) ;
        errResult.append("], ") ;
      }
    }else {
      errResult.append(ExceptionUtils.getStackTrace(e));
    }

    response.setErrorDesc(errResult.toString());
    log.error("GlobalExceptionHandler errResult {}", errResult, e);
    return response;
  }


//  /**
//   * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
//   * @param binder
//   */
//  @InitBinder
//  public void initBinder(WebDataBinder binder) {}
//
//  /**
//   * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
//   * @param model
//   */
//  @ModelAttribute
//  public void addAttributes(Model model) {
//    model.addAttribute("author", "Magical Sam");
//  }

}
