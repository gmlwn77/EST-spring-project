package com.estsoft.springproject;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {
	@Test
	public void test(){
		// given : 값 세팅
		int a = 1;
		int b = 2;

		// when : 검증하고 싶은 메소드 호출
		int sum = a+b;

		// then : when절 실행 결과 검증
		//Assertions.assertEquals(3, sum);
		Assertions.assertThat(sum).isEqualTo(3);
		Assertions.assertThat(sum).isOdd();
	}
}
