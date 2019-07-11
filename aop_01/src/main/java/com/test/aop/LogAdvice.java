package com.test.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;
//관점 지향 프로그래밍 
//관점이라는 용어는 개발자들에게 관심사 말로 통용된다
//개발시 필요한 고민이나 염두에 두어야 하는 일 예를
//1. 파라미터가 올바르게 돌아왔을까
//2.사용자가 적절한 권한을 가진 사용자인가
//3. 작업에서 발생할 수 있는 모든 예외는 어떻게 처리해야 하는가

//위와 같은 고민들은 핵심 로직은 아니지만 필요한 고민들인데 전통적인 방식에서는 개발자가
//반복적으로 이러한 고민을 코드에 반영하게 됩니다 
//AOP는 이런 일들은 별도의 관심사로 분리하고 핵심 비즈니스 로직만을 작성 합니다 AOP는 관심사의 분리 separate concerns 라고합니다

/*
주변 로직이라고 표현할 수있다. 예를 들어 누눗셈을 구현한다면 핵심 로직은 두 개의 숫자를 나누는 것이지만,
주변 로직은 0을 나누는 것이 나닌지 등을 체크하는 것입니다

관심사 + 비즈니스 로직을 분리해서 별도의 코드로 작성하고 실행할 때 이를 결합하는 방식으로 접근합니다
과거에 비즈니스 로직 그 내부에 필요한 관심사를 처리하던 방식과 반대 접근 방식
실제 실행은 결합된 상태의 코드가 실행되기 때문에 개발자들은 핵심 비즈니스 로직에만 근거해서 코드를 작성하고
나머지는 어떤 관심사들과 결합할 것인지를 설정하는 것 만으로 모든 개발을 마칠 수 있게 됩니다.

잘못된 파라미터가 들어와서 예외가 발생한는 상황을 기존 코드의 수정없이도 제어할 수 있습니다*/

/*
target 개발자가 작성한 핵심 비즈니스 로직 객체
joinPoint 는 target객체가 가진 메서드 (스프링 AOP에서는 메서드만이 JoinPoint가 됩니다)

외부에서 호출은 proxy객체를 통해 target객체의 joinpoint를 호출하는 방식

target에는 여러 메서드가 존재하기 때문에 어떤 joinPoint와 관시사(aspect(advice)) 를 결합할 것인가 결정해야 하는데
이결정을 PointCut이라고 합니다
pointCut 관심사와 비즈니스 로직이 결합되는 지점을 결정하는 것
Advice는 Aspect를 구현한 코드
*/



@Aspect
@Log4j
@Component
public class LogAdvice {

	//execution 문자열은 aspectj의 표현식 접근제한자와 특정 클래스 메서드지정
	// 맨앞 * 접근제한자 맨뒤의 *는 클래스이름과 메서드 이름 의미한다
	@Before("execution(* com.test.service.SampleService*.*(..))")
	public void logBefore() {
		
		log.info("===============");
	}
	
	
	
	
	//어떤위치에 advice를 적용할 것인지를 결정한는 pointcut
	//설정시에 args를 이용하면 간단한 파라미터를 구할 수 있다.
	//pointcut설정에 doAdd()메서드를 명시하고 파라미터의 타입을 지정했습니다. 
	//뒤쪽의 &&args 부분에는 변수명을 지정하는데 이 정보를 위해서 밑에 메서드의 파라미터를 설정
	//&& args 이용하는 설정은 간단히 파라미터를 찾아서 기록할 때에는 유용하지만 
	//파라미터가 다른 여러종류의 메서드에 적용할때 간단하지가 않다  @Around 와 ProceedingJoinPoint를 이용해서 해결할 수있다
	
	@Before("execution(* com.test.service.SampleService*.doAdd(String,String)) && args(str1,str2)")
	public void logBeforeWithParam(String str1, String str2) {
		
		log.info("===============");
		log.info("str1 :" + str1);
		log.info("str1 :" + str1);
	}
	

	//@AfterThrowing 지정된 대상이 예외를 발생한 후에 동작하면서 문제를 찾을 수 있도록 도와줄 수 있습니다.
	@AfterThrowing(pointcut = "execution(* com.test.service.SampleService*.*(..))" , throwing = "exception")
	public void logException(Exception exception) {
		
		log.info("Exception...........!!!!!");
		log.info("exceptino : "+ exception);
		
		
		
	}


	//Around 직접대상 메서드를 실행할 수있는 권한을 가지고있고 메서드의 실행 전과 후의 처리가 가능합니다.
	//proceedingjoinPoint는 Around와 같이 결합해서 파라미터나 예외 등을 처리 할 수 있습니다.
	//ProceedingJoinPoint 는 AOP 대상이 되는 target이나 파라미터 등을 파악할 뿐만 아니라
	//직접 실행을 결정 할 수도 있다 @before 등과 달리 @Around가 적용되는 메서드의 경우 리턴 타입이 void가 아닌 타입으로 설정
	//실행결과 Around가 먼저 동작
	@Around("execution(* com.test.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target : "+ pjp.getTarget());
		log.info("Param  : " + Arrays.toString(pjp.getArgs()));
		
		
		//invoke method
		Object result = null;
		
		try {
			result = pjp.proceed();
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		long end= System.currentTimeMillis();
		log.info("Time : "+ (end - start));
		
		
		log.info("result : "+ result);
		
		return result;
	}
	
}
