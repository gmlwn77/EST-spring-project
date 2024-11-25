package com.estsoft.springproject.coupon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.estsoft.springproject.user.coupon.ICoupon;
import com.estsoft.springproject.user.coupon.User;

public class UserCouponTest {

	@Test
	public void testAddCoupon() {
		User user = new User("area00");
		assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

		//ICoupon coupon = new DummyCoupon();    // dummy 쿠폰 만들어서 사용
		ICoupon coupon = Mockito.mock(ICoupon.class);   // mock객체 - 행위 정의 가능
		//Mockito.when(coupon.isValid()).thenReturn(true); // stub
		Mockito.doReturn(true).when(coupon).isValid(); //stub

		user.addCoupon(coupon);
		assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
	}
}
