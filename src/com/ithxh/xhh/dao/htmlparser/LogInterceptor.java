package com.ithxh.xhh.dao.htmlparser;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ithxh.baseCore.annotation.BussAnnotation;
import com.ithxh.baseCore.baseUtils.DateUtils;

@Aspect
@Component
public class LogInterceptor {

	private static Logger logger = Logger.getLogger(LogInterceptor.class.getName());

	@Pointcut("execution(* com.ithxh.xhh.dao.htmlparser.BaseParserBook.*(..))")  
    public void log() {}
	
	@Before(value = "log() && @annotation(annotation) &&args(object,..) ", argNames = "annotation,object")
	public void before(BussAnnotation annotation, Object object){
		
    	logger.info("-->开始时间："+DateUtils.getNowDateTime());
    	logger.info("-->"+annotation.moduleName()+","+annotation.option());
	}
	
	@After(value = "log() && @annotation(annotation) &&args(object,..) ", argNames = "annotation,object")
	public void after(BussAnnotation annotation, Object object){
		
        logger.info("-->结束时间："+DateUtils.getNowDateTime());
	}
	
    
}
