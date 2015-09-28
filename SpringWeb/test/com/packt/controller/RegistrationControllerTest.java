package com.packt.controller;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {
	@Mock
	private RegistrationService registrationService;
	private RegistrationController controller;
	
	@Before
	public void setup(){
		controller = new RegistrationController();
		controller.setRegistrationService(registrationService);
	}
	

	@Test
	public void when_invalid_user_id_geneartes_error_message() throws Exception {
		when(registrationService.hasError()).thenReturn("User exists");
		ModelMap model = new ModelMap();
		ModelAndView modelAndView = controller.onRegistration(model);
		String message = (String) modelAndView.getModel().get("message");
		assertNotNull(message);
		assertTrue(message.contains(RegistrationController.ERROR));
	}
	
	@Test
	public void when_valid_user_id_creates_user() throws Exception {
		when(registrationService.hasError()).thenReturn(null);
		ModelMap model = new ModelMap();
		ModelAndView modelAndView = controller.onRegistration(model);
		String message = (String) modelAndView.getModel().get("message");
		assertNotNull(message);
		assertTrue(message.contains(RegistrationController.SUCCESS));
	}
	
}

