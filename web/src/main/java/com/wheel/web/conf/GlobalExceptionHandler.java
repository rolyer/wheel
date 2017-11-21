package com.wheel.web.conf;

import com.wheel.common.result.ReturnCodeEnum;
import com.wheel.common.util.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 类RestExceptionHandler.java的实现描述：
 *
 */
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("error:", e);
        Result result=new Result();
        String msg = null;
        if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;
            result.setCode(exception.getReturnCodeEnum().value());
            if (exception.getMessage() != null) {
                result.setMsg(exception.getReturnCodeEnum().getDesc() + "[" + exception.getMessage() + "]");
            } else {
                result.setMsg(exception.getReturnCodeEnum().getDesc());
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            result.setCode(ReturnCodeEnum.RESTFUL_REQUEST_OBJECT_INVALID.value());
            result.setMsg(parseArgumentNotValid(ex.getBindingResult()));

        } else if (e instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException ex = (HttpMessageNotReadableException) e;
            result.setCode(ReturnCodeEnum.RESTFUL_REQUEST_OBJECT_INVALID.value());
            result.setMsg(ex.getMessage());

        } else if (e instanceof BindException) {

            BindException bindException=(BindException) e;

           List<ObjectError> list= bindException.getAllErrors();

            if(list!=null){
                for (ObjectError error:list ) {
                    if(error instanceof  FieldError){
                        FieldError fieldError=(FieldError) error;
                        String fieldName= fieldError.getField();
                        String fieldMsg=fieldError.getDefaultMessage();
                        msg=fieldName+":"+fieldMsg+"|";
                    }
                }
            }
            result.setCode(ReturnCodeEnum.RESTFUL_REQUEST_OBJECT_INVALID.value());
            result.setMsg(msg);

        } else {
            result.setCode(ReturnCodeEnum.UNKNOWN_FAIL.value());
            result.setMsg(e.getMessage());
        }

        log.error("", e);

        return result;
    }

    private String parseArgumentNotValid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            StringBuilder errorSB = new StringBuilder();
            for (FieldError fieldError : fieldErrorList) {
                errorSB.append("[").append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("]");
            }
            return errorSB.toString();
        }
        return "";
    }
}
