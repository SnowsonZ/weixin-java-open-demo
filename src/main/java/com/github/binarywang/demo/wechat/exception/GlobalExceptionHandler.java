package com.github.binarywang.demo.wechat.exception;

import com.github.binarywang.demo.wechat.bean.ErrorInfo;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 正确输入URL到进入逻辑前异常处理，eg:入参类型错误
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,WebRequest request){
        if(ex instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException)ex;
            return new ResponseEntity<Object>(new ErrorInfo("param : " + exception.getName() +
                    " is invalid,please check..."),HttpStatus.BAD_REQUEST);
        }else if(ex instanceof TypeMismatchException){
            TypeMismatchException exception = (TypeMismatchException)ex;
            return new ResponseEntity<Object>(new ErrorInfo("param : " + exception.getPropertyName()
                    + "is invalid,please check..."),HttpStatus.BAD_REQUEST);
        }else{
            return super.handleExceptionInternal(ex,body,headers,status,request);
        }
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo runTimeExceptionHandle(HttpServletRequest request,Exception e){
        log(e,request);
        return new ErrorInfo("server internal error...");
    }

    @ExceptionHandler(value = com.example.springboot.exception.ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo resourceNotFoundExceptionHandle(HttpServletRequest request,Exception e){
        log(e,request);
        return new ErrorInfo(e.getMessage());
    }


    private void log(Exception ex,HttpServletRequest request){
        logger.error("************************异常开始*******************************");
        logger.error(ex);
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }

        StackTraceElement[] error = ex.getStackTrace();
        for(StackTraceElement stackTraceElement : error){
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************异常结束*******************************");
    }
}
