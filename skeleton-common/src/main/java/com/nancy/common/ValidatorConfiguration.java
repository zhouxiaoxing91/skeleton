//package com.nancy.common;
//
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.validator.HibernateValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//@Slf4j
//@Configuration
//public class ValidatorConfiguration {
//
//    @Bean("validator")
//    public Validator getValidatorFactory(){
//        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory();
//        return validatorFactory.getValidator();
//    }
//}