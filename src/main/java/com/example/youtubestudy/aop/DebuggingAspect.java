package com.example.youtubestudy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Aspect //AOP 클래스 선언: 부가기능을 주입하는 클래스
@Component //Ioc 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect {

    // 대상 메소드 선택: CommentService#Create()
    @Pointcut("execution(* com.example.youtubestudy.api.*.*(..))")
    private void cut(){}

    //실행 시점 설정: cut()의 대상이 수행되기 전에 실행
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint){//cut()에 대상 메소드
        //입력값 가져오기
        Object[] args = joinPoint.getArgs();
        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        //입력값 로깅하기

        for(Object obj : args){
            log.info("{}#{}의 입력값 => {}",className,methodName,obj);
        }


    }

    @AfterReturning(value = "cut()", returning = "returnObj") // 실행 시점 설정: cut에 지정된 대상 호출 성공 후
    public void loggingReturnValue(JoinPoint joinPoint,Object returnObj){

        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        //변환값 로깅
        log.info("{}#{}의 반환값 => {}",className,methodName,returnObj);


    }
}
