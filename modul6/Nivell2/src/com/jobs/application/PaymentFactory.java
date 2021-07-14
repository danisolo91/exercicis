package com.jobs.application;

import com.jobs.domain.IPaymentRate;

public class PaymentFactory {

	public static IPaymentRate createPaymentRateBoss() {
		return new IPaymentRate() {
			@Override
			public double pay(double salaryPerMonth) {
				return salaryPerMonth * 1.5;
			}
		};
	}

	public static IPaymentRate createPaymentRateManager() {
		return new IPaymentRate() {
			@Override
			public double pay(double salaryPerMonth) {
				return salaryPerMonth * 1.1;
			}
		};
	}

	public static IPaymentRate createPaymentRateSeniorEmployee() {
		return new IPaymentRate() {
			@Override
			public double pay(double salaryPerMonth) {
				return salaryPerMonth * 0.95; // todo
			}
		};
	}

	public static IPaymentRate createPaymentRateMidEmployee() {
		return new IPaymentRate() {
			@Override
			public double pay(double salaryPerMonth) {
				return salaryPerMonth * 0.90;
			}
		};
	}

	public static IPaymentRate createPaymentRateJuniorEmployee() {
		return new IPaymentRate() {
			@Override
			public double pay(double salaryPerMonth) {
				return salaryPerMonth * 0.85;
			}
		};
	}
}
