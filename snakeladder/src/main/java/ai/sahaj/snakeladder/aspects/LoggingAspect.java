package ai.sahaj.snakeladder.aspects;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Pointcut(value = "execution(* ai.sahaj.snakeladder.controllers.*.*(..) )")
	public void controllerPointCut() {

	}

	@Around("controllerPointCut()")
	public Object controllerLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object[] array = proceedingJoinPoint.getArgs();
		log.info("Controller :: Method invoked: {} of class: {} with args: {} ", methodName, className,
				Arrays.toString(array));
		Object response = proceedingJoinPoint.proceed();
		log.debug("Controller :: Method executed: {} of class: {} with response: {} ", methodName, className, response);
		return response;
	}

	@Pointcut(value = "execution(* ai.sahaj.snakeladder.services.impl.*.*(..) )")
	public void servicePointCut() {

	}

	@Around("servicePointCut()")
	public Object serviceLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object[] array = proceedingJoinPoint.getArgs();
		log.debug("Service :: Method invoked: {} of class: {} with args: {} ", methodName, className,
				Arrays.toString(array));
		Object response = proceedingJoinPoint.proceed();
		log.debug("Service :: Method executed: {} of class: {} with response: {} ", methodName, className, response);
		return response;
	}

	@Pointcut(value = "execution(* ai.sahaj.snakeladder.repositories.*.*(..) )")
	public void repoPointCut() {

	}

	@Around("repoPointCut()")
	public Object repoLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object[] array = proceedingJoinPoint.getArgs();
		log.debug("Repository :: Method invoked: {} of class: {} with args: {} ", methodName, className,
				Arrays.toString(array));
		Object response = proceedingJoinPoint.proceed();
		log.debug("Repository :: Method executed: {} of class: {} with response: {} ", methodName, className, response);
		return response;
	}

	@Pointcut(value = "execution(* ai.sahaj.snakeladder.util.*.*(..) )")
	public void utilPointCut() {

	}

	@Around("utilPointCut()")
	public Object utilLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object[] array = proceedingJoinPoint.getArgs();
		log.debug("Util :: Method invoked: {} of class: {} with args: {} ", methodName, className,
				Arrays.toString(array));
		Object response = proceedingJoinPoint.proceed();
		log.debug("Util :: Method executed: {} of class: {} with response: {} ", methodName, className, response);
		return response;
	}

	@Pointcut(value = "execution(* ai.sahaj.snakeladder.validators.*.*(..) )")
	public void validatorPointCut() {

	}

	@Around("validatorPointCut()")
	public Object validatorLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object[] array = proceedingJoinPoint.getArgs();
		log.debug("Validator :: Method invoked: {} of class: {} with args: {} ", methodName, className,
				Arrays.toString(array));
		Object response = proceedingJoinPoint.proceed();
		log.debug("Validator :: Method executed: {} of class: {} with response: {} ", methodName, className, response);
		return response;
	}
}
