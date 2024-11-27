package com.frontend.train_booking_frontend_customer.services;

import java.util.List;

import com.frontend.train_booking_frontend_customer.models.Coach;

public interface ICoachService {
	public List<Coach> getCoachesByTrainId(int trainId);
}
