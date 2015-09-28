package com.packt.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RegistratioDaoSpringTest {

	@Mock
	JdbcTemplate mockJdbcTemplate;

	RegistratioDaoSpring springDao;

	@Before
	public void init() {
		springDao = new RegistratioDaoSpring(mockJdbcTemplate);
	}

	@Test
	public void when_creates_user() throws Exception {
		// Prepare data for user registration
		String joesUserId = "joe4u";
		String joesPassword = "joe@123";
		String joesFirstName = "Joseph";
		String joesLastName = "Lawrence";

		// Stub jdbcTemplate’s update to return 1
		when(
			mockJdbcTemplate.update(anyString(), anyString(), anyObject(),
						anyObject(), anyObject())).thenReturn(1);

		// Execute
		springDao.create(joesUserId, joesPassword, joesFirstName, joesLastName);

		// Create argument capture
		ArgumentCaptor<Object> varArgs = ArgumentCaptor.forClass(Object.class);

		ArgumentCaptor<String> strArg = ArgumentCaptor.forClass(String.class);

		// Verify update method was called and capture args
		verify(mockJdbcTemplate).update(strArg.capture(), varArgs.capture(),
				varArgs.capture(), varArgs.capture(), varArgs.capture());

		// Verify 1st dynamic argument was the userId
		assertEquals(joesUserId, varArgs.getAllValues().get(0));
		// Verify the password arguments
		assertEquals(joesPassword, varArgs.getAllValues().get(1));
		// Verify the name arguments
		assertEquals(joesFirstName, varArgs.getAllValues().get(2));
		assertEquals(joesLastName, varArgs.getAllValues().get(3));
	}
	
	@Test(expected=RuntimeException.class)
	public void when_create_fails_then_raises_error() throws Exception {
		// Prepare data for user registration
		String joesUserId = "joe4u";
		String joesPassword = "joe@123";
		String joesFirstName = "Joseph";
		String joesLastName = "Lawrence";

		// Stub jdbcTemplate’s update  to return no update
		when(
			mockJdbcTemplate.update(anyString(), anyString(), anyObject(),
						anyObject(), anyObject())).thenReturn(0);

		// Execute for fail
		springDao.create(joesUserId, joesPassword, joesFirstName, joesLastName);
	}

}
