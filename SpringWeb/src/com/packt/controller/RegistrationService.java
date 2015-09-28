package com.packt.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.dao.RegistrationDao;

public class RegistrationService {
	public static final String COULD_NOT_CREATE_USER = "Could not create user.";
	public static final String USER_ID_EXISTS = "User Id exists";
	public static final String DOESNT_CONTAIN_A_SPECIAL_CHAR = "Password should contain a special character";
	public static final String NAME_CONTAINS_NUMBER = "Name cannot contain numbers";
	public static final String NAME_CONTAINS_SPECIAL_CHAR = "Name cannot contain special characters";
	public static final String PLEASE_ENTER_LAST_NAME = "Please enter last name";
	public static final String PLEASE_ENTER_FIRST_NAME = "Please enter first name";
	public static final String PLEASE_ENTER_PASSWORD = "Please enter password";
	public static final String PLEASE_ENTER_USER_ID = "Please enter user id";
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	@Autowired
	private RegistrationDao registrationDao;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String hasError() {
		if (isEmpty(userId)) {
			return PLEASE_ENTER_USER_ID;
		}

		if (isEmpty(password)) {
			return PLEASE_ENTER_PASSWORD;
		}

		if (isEmpty(firstName)) {
			return PLEASE_ENTER_FIRST_NAME;
		}

		if (isEmpty(lastName)) {
			return PLEASE_ENTER_LAST_NAME;
		}

		if (isSpecial(firstName) || isSpecial(lastName)) {
			return NAME_CONTAINS_SPECIAL_CHAR;
		}

		if (isNumeric(firstName) || isNumeric(lastName)) {
			return NAME_CONTAINS_NUMBER;
		}

		if (!isSpecial(password)) {
			return DOESNT_CONTAIN_A_SPECIAL_CHAR;
		}

		if (registrationDao.isExistingUserId(userId)) {
			return USER_ID_EXISTS;
		}

		try {
			registrationDao.create(userId, password, firstName, lastName);
		} catch (Exception e) {
			return COULD_NOT_CREATE_USER;
		}
		return null;
	}

	private boolean isEmpty(String value) {
		if (value == null || "".equals(value.trim())) {
			return true;
		}
		return false;
	}

	private boolean isSpecial(String expression) {
		Pattern regex = Pattern.compile("[-*()/^!$&+,:;=?@#|]");
		Matcher matcher = regex.matcher(expression);
		if (matcher.find()) {
			return true;
		}

		return false;
	}

	public boolean isNumeric(String str) {
		return str.contains("0") || str.contains("1") || str.contains("2")
				|| str.contains("3") || str.contains("4") || str.contains("5")
				|| str.contains("6") || str.contains("7") || str.contains("8")
				|| str.contains("9");
	}

	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}
}
