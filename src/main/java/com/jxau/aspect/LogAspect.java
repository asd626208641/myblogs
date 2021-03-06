package com.jxau.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(* com.jxau.controller..*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String ip = request.getRemoteAddr();
		String classMethod =joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
		logger.info("Request : {}", requestLog);
	}

	@After("log()")
	public void doAfter() {
	}

	// 能够获取切点中方法的返回值，并封装到returning=result中，然后再下面result中打印出来
	@AfterReturning(returning = "result", pointcut = "log()")
	public void doAfterReturen(Object result) {
		logger.info("Result:{}" , result);
	}
	
	
	private class RequestLog {
		private String url;
		private String ip;
		private String classMethod;
		private Object[] args;

		public RequestLog(String url, String ip, String classMethod, Object[] args) {
			this.url = url;
			this.ip = ip;
			this.classMethod = classMethod;
			this.args = args;
		}

		@Override
		public String toString() {
			return "{" + "url='" + url + '\'' + ", ip='" + ip + '\'' + ", classMethod='" + classMethod + '\''
					+ ", args=" + Arrays.toString(args) + '}';
		}
	}
	
}
