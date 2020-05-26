package com.jxau.hanlder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

	// 获取日志对象，ControllerExceptionHandler.class 可以使得日志在打印的时候显示出所属的哪个具体的类
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler
	public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) {
		logger.error("Request URL : {},Exception : {}", request.getRequestURL(), e.getMessage());
		ModelAndView mav = new ModelAndView("error/error");
		mav.addObject("url", request.getRequestURL());
		mav.addObject("exception", e);
		return mav;
	}
}
