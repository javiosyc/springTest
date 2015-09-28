package com.packt.dao;

public interface RegistrationDao {

	boolean isExistingUserId(String userId);

	void create(String userId, String password, String firstName,
			String lastName);

}
