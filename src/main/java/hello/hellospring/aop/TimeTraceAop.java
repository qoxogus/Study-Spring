package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //스프링 빈에 등록 Config에 등록해서 하는거러 더 선호함 (특별한거라서)
@Aspect //aop는 적어줘야함
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //다 적용을 한 상태
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " +timeMs + "ms");
        }

    }
}
