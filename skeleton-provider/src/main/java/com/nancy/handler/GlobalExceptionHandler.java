package com.nancy.handler;

import com.nancy.response.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public DataResult jsonErrorHandler(HttpServletRequest request, Exception e){
    DataResult response = new DataResult();
    response.setErrorCode("10000");
    String errResult = ExceptionUtils.getStackTrace(e) ;
    response.setErrorDesc(errResult);
    log.info("异常", e);
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
