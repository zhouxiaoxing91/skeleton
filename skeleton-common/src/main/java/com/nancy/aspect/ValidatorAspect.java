//package com.nancy.aspect;
//
//import com.nancy.response.DataResult;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolation;
//import javax.validation.Path;
//import javax.validation.Validator;
//import java.utils.Set;
//
//@Aspect
//@Component
//public class ValidatorAspect {
//
//    @Resource(name = "validator")
//    private Validator validator;
//
//    @Pointcut("execution(* com.nancy.controller.*.*(..))")
//    public void pointcut() {
//    }
//
//    /**
//     * 入参校验
//     * @param joinPoint
//     * @throws Throwable
//     */
//    @Around("pointcut()")
//    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
//        DataResult ds = new DataResult();
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args){
//            if(arg != null){
//                ds.setErrorDesc("10000");
//                Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);
//                if(constraintViolations.size() > 0){
//                    for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
//                        Path property = constraintViolation.getPropertyPath();
//                        String name = property.iterator().next().getName();
//                        baseResponse.setMessage("[" + name + "]" + constraintViolation.getMessage());
//                        break;
//                    }
//                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                    HttpServletResponse response = attributes.getResponse();
//                    String responseStr = JsonUtil.objectToJson(baseResponse);
//                    log.error(responseStr);
//                    HttpResponseUtil.setResponseJsonBody(response,responseStr);
//                    return null;
//                }
//            }
//        }
//        return joinPoint.proceed();
//    }
//
//}
