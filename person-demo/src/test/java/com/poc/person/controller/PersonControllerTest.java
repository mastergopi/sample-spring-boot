package com.poc.person.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.poc.person.model.PersonVO;
import com.poc.person.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	PersonVO personVO;

	@Before
	public void setup() {
		personVO = new PersonVO(1, "tony", "stark", 'M', 35);
	}

	@Test
	public void createPersonTest() throws Exception {
		String createPersonReq = "{	\"firstName\": \"tony\",  \"lastName\": \"stark\",  \"gender\": \"M\",  \"age\": 35}";
		Mockito.when(personService.createPerson(Mockito.any())).thenReturn(personVO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createPerson").accept(MediaType.APPLICATION_JSON)
				.content(createPersonReq).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void getAllPersonDetailsTest() throws Exception {

		List<PersonVO> personsList = new ArrayList<PersonVO>();
		personsList.add(personVO);
		Mockito.when(personService.getAllPersonDetails()).thenReturn(personsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void updatePersonTest() throws Exception {

		String updatePersonReq = "{	\"firstName\": \"tony\",  \"lastName\": \"stark\",  \"gender\": \"M\",  \"age\": 40}";

		personVO.setId(1);
		Mockito.when(personService.updatePersonDetails(Mockito.anyLong(), Mockito.any())).thenReturn(personVO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/updatePerson/1")
				.accept(MediaType.APPLICATION_JSON).content(updatePersonReq).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void getPersonsByAgeTest() throws Exception {
		List<PersonVO> personsList = new ArrayList<PersonVO>();
		personsList.add(personVO);

		Mockito.when(personService.getPersonsByAge(Mockito.anyInt())).thenReturn(personsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getPersonsByAge/20")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void deletePersonTest() throws Exception {			

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/deletePerson/1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * @Test public void contextLoads() { }
	 */

}
