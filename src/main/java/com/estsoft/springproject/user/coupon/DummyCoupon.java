package com.estsoft.springproject.user.coupon;

public class DummyCoupon implements ICoupon {
	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public int getDiscountPercent() {
		return 0;
	}

	@Override
	public boolean isAppliable(Item item) {
		return false;
	}

	@Override
	public void doExpire() {

	}
}
