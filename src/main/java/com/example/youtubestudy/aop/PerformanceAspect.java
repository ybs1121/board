package com.example.youtubestudy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {


    //특정 어노테이션을 대상 지정
    @Pointcut("@annotation(com.example.youtubestudy.annotation.RunningTime)")
    private void enableRunningTime(){}

    @Pointcut("execution(* com.example.youtubestudy..*.*(..))")
    private void cut(){}


    // 실행 시점 설정: 두 조건을 모두 만족하는 대상을 전후로 부가 기능을 삽입
    @Around("cut() && enableRunningTime()")
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //메소드 수행 전 측정
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //메소드 수행
        Object returningObj = joinPoint.proceed();
        //메소드 수행 후 측정 종료 및 로깅
        stopWatch.stop();

        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        log.info("{}의 총 수행 시간 => {} sec", methodName,stopWatch.getTotalTimeSeconds());
    }
}
