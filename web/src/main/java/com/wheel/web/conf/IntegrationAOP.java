package com.wheel.web.conf;

import com.alibaba.fastjson.JSON;
import com.wheel.common.result.BaseReqVo;
import com.wheel.common.util.LogIdUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IntegrationAOP.java的实现描述:统一AOP，
 * 1.权限验证
 * 2.登陆轨迹日志
 * 3.才参数验证结果
 * 4.方法性能：时间消耗
 *
 */
@Component
@Aspect
@Log4j2
public class IntegrationAOP {
//    @Pointcut("execution(* com.wheel.web.controller.*.*(..)) && !execution(* com.whell.asset.controller.CallbackCotroller.*(..))")
//    private void anyMethod() {
//    }

    @Pointcut("execution(* com.wheel.web.controller.*.*(..))")
    private void anyMethod() {
    }


    @Around("anyMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        StringBuilder methodSB = new StringBuilder();
        String methodName = joinPoint.getSignature().getName();
        String logId = "";

        try {

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            String url = request.getRequestURL().toString();

            HttpServletResponse res=  sra.getResponse();
            res.setCharacterEncoding("utf-8");

            Object[] args = joinPoint.getArgs();
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    Object obj = args[i];
                    if (obj instanceof HttpServletRequest) {
                    } else if (obj instanceof HttpServletResponse) {
                    } else {
                        if (obj != null) {
                            methodSB.append("args:[").append(JSON.toJSONString(obj)).append("]");
                            if (obj instanceof BaseReqVo) {
                                BaseReqVo baseReqVo = (BaseReqVo) obj;
                                logId = baseReqVo.getLogId();
                                LogIdUtil.setLogId(logId);
                            }
                        } else {
                            methodSB.append("[null]");
                        }
                        log.info("logId:{},Begin method : " + methodName + "()," + methodSB.toString(), logId);
                    }
                }
            }
            Object retVal = joinPoint.proceed();
            log.info("logId:{},end  method : " + methodName + "()," + "result:" + (null == retVal ? "" : JSON.toJSONString(retVal)), logId);
            return retVal;
        } catch (Exception e) {
            log.error("logId:{},error:", logId, e);
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            long expendTime = end - start;
            log.info("logId:{},method :" + methodName + ",consume time:" + expendTime, logId);
        }
    }
//
//
//    private Result handleException(Exception e, String logId) throws Exception {
//        Result baseResult = new Result();
//        if (e instanceof BusinessException) {
//            BusinessException exception = (BusinessException) e;
//            baseResult.setCode(exception.getReturnCodeEnum().value());
//            if (exception.getMessage() != null) {
//                baseResult.setMsg(exception.getReturnCodeEnum().getDesc() + "[" + exception.getMessage() + "]");
//            } else {
//                baseResult.setMsg(exception.getReturnCodeEnum().getDesc());
//            }
//            log.error("logId:{},error:", logId, exception.getException());
//        } else if (e instanceof SysException) {
//            SysException exception = (SysException) e;
//            if (exception.getReturnCodeEnum() != null) {
//                baseResult.setCode(exception.getReturnCodeEnum().value());
//                if (exception.getMessage() != null) {
//                    baseResult.setMsg(exception.getReturnCodeEnum().getDesc() + "[" + exception.getMessage() + "]");
//                } else {
//                    baseResult.setMsg(exception.getReturnCodeEnum().getDesc());
//                }
//            } else {
//                baseResult.setCode(ReturnCodeEnum.RESTFU_FAIL.value());
//                baseResult.setMsg(ReturnCodeEnum.RESTFU_FAIL.getDesc());
//            }
//        } else {
//            throw e;
//        }
//        return baseResult;
//    }

}
