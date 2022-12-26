package com.hello.project.config;

import com.hello.project.config.filter.MyFilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

//test
public class FilterConfig {

    public FilterRegistrationBean<MyFilter1> filter1() {
        System.out.println("===========필터 등록==============");
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }

}
