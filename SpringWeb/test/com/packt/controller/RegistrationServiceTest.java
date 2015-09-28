package com.packt.controller;
import static com.packt.controller.RegistrationService.COULD_NOT_CREATE_USER;
import static com.packt.controller.RegistrationService.NAME_CONTAINS_NUMBER;
import static com.packt.controller.RegistrationService.NAME_CONTAINS_SPECIAL_CHAR;
import static com.packt.controller.RegistrationService.PLEASE_ENTER_FIRST_NAME;
import static com.packt.controller.RegistrationService.PLEASE_ENTER_LAST_NAME;
import static com.packt.controller.RegistrationService.PLEASE_ENTER_PASSWORD;
import static com.packt.controller.RegistrationService.PLEASE_ENTER_USER_ID;
import static com.packt.controller.RegistrationService.USER_ID_EXISTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.packt.dao.RegistrationDao;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
	
	private RegistrationService registrationService;
	@Mock
	private RegistrationDao registrationDao;
	
	@Before
	public void setup(){
		registrationService = new RegistrationService();
		registrationService.setRegistrationDao(registrationDao);
	}
	
    @Test
	public void when_empty_imputs_raises_error() throws Exception {
		String error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(PLEASE_ENTER_USER_ID, error);
		
		registrationService.setUserId("john123");
		error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(PLEASE_ENTER_PASSWORD, error);
		
		registrationService.setPassword("Passw@rd");
		error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(PLEASE_ENTER_FIRST_NAME, error);
		
		registrationService.setFirstName("john");
		error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(PLEASE_ENTER_LAST_NAME, error);
		
		registrationService.setLastName("doe");
		error = registrationService.hasError();
		assertNull(error);
	}
    
    @Test
  	public void when_name_contains_number_raises_error() throws Exception {
  		registrationService.setFirstName("john1");
  		registrationService.setLastName("doe");
  		registrationService.setUserId("john123");
  		registrationService.setPassword("Passw@rd");
  		String error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(NAME_CONTAINS_NUMBER, error);
  		
  	}
	
    @Test
  	public void when_name_contains_special_chars_raises_error() throws Exception {
  		registrationService.setFirstName("john@");
  		registrationService.setLastName("doe");
  		registrationService.setUserId("john123");
  		registrationService.setPassword("Passw@rd");
  		String error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(NAME_CONTAINS_SPECIAL_CHAR, error);
  	}
    
    @Test
  	public void when_user_exists_raises_error() throws Exception {
    	when(registrationDao.isExistingUserId(Mockito.anyString())).thenReturn(true);
  		registrationService.setFirstName("john");
  		registrationService.setLastName("doe");
  		registrationService.setUserId("john123");
  		registrationService.setPassword("Passw@rd");
  		String error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(USER_ID_EXISTS, error);
  	}
    
    @Test
  	public void when_user_creation_fails_then_raises_error() throws Exception {
    	doThrow(new RuntimeException("save failed")).when(registrationDao).create(anyString(), anyString(), anyString(), anyString());
  		registrationService.setFirstName("john");
  		registrationService.setLastName("doe");
  		registrationService.setUserId("john123");
  		registrationService.setPassword("Passw@rd");
  		String error = registrationService.hasError();
		assertNotNull(error);
		assertEquals(COULD_NOT_CREATE_USER, error);
  	}
    
    @Test
  	public void when_no_validation_error_then_creates_user() throws Exception {
  		registrationService.setFirstName("john");
  		registrationService.setLastName("doe");
  		registrationService.setUserId("john123");
  		registrationService.setPassword("Passw@rd");
  		assertNull(registrationService.hasError());
  		
  	}
}

