package com.soen.app.disney;

import java.io.IOException;

import javax.servlet.ServletException;

//import org.mockito.InjectMocks;
import org.junit.Test;

public class ControllerTest {

	//@InjectMocks
	Controller controllerTest= new Controller();
	
	@Test
	public void testInit() throws ServletException {
		controllerTest.init();
	}

	@Test
	public void testGetJsonDataForDisneyCharacters() throws IOException {
		controllerTest.getJsonDataForDisneyCharacters();
	}

}
