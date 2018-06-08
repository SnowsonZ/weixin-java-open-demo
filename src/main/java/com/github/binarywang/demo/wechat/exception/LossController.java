package com.example.springboot.exception;

import com.github.binarywang.demo.wechat.bean.ErrorInfo;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 页面不存在的异常处理
 */
@RestController
public class LossController implements ErrorController {
    @Override
    public String getErrorPath(){
        return "/error";
    }

    @RequestMapping(value = "/error")
    public ErrorInfo error(HttpServletResponse resp,HttpServletRequest req){
        // 错误处理逻辑
        return new ErrorInfo("page or api lost...");
    }


}
