package com.estsoft.springproject.tdd;

// TDD
// 1. 계좌 생성
// 2. 잔금 조회
// 3. 입/출금

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	Account account;

	@BeforeEach
	public void setUp() {
		account = new Account(10000);
	}

	@Test
	public void test() {
		if(account.getBalance() != 10000) {
			fail();
		}

		Account account2 = new Account(20000);
		//MatcherAssert.assertThat(account2.getBalance(), Matchers.is(20000));
		assertThat(account2.getBalance(), is(20000));

		Account account3 = new Account(30000);
		assertThat(account3.getBalance(), is(30000));
	}

	@Test
	public void testDeposit() {
		account.deposit(1000);
		assertThat(account.getBalance(), is(11000));
	}

	@Test
	public void testWithdraw() {
		account.withdraw(10000);
		assertThat(account.getBalance(), is(0));
	}
}
