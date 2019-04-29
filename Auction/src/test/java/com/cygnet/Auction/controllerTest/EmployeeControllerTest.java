package com.cygnet.Auction.controllerTest;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cygnet.Auction.AuctionApplicationTests;
import com.cygnet.Auction.controller.EmployeeController;
import com.cygnet.Auction.dto.EmployeeDto;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
@ContextConfiguration(classes=AuctionApplicationTests.class)
public class EmployeeControllerTest {

	@InjectMocks private EmployeeController employeeController;
	@Mock private EmployeeService employeeService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ResponseEmployeeDto> responseEmployeeDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/Employee.json"));
		jsonArray= (JSONArray)obj; 
		responseEmployeeDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponseEmployeeDto>>(){}.getType());
	
	}
	
	@Test
	public void getEmployeeById() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/EmployeeId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		System.out.println("jsonArrayId " + jsonArrayId + " objId " + objId);
		ArrayList<ResponseEmployeeDto> responseEmployeeDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseEmployeeDto>>(){}.getType());
		
		when(employeeService.getEmployee(responseEmployeeDtoId.get(0).getEmpId())).thenReturn(responseEmployeeDto.get(1));
		mockMvc.perform(get("/employee/{empId}",responseEmployeeDtoId.get(0).getEmpId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.empId", is(responseEmployeeDtoId.get(0).getEmpId())))
		 .andExpect(jsonPath("$.email", is(responseEmployeeDtoId.get(0).getEmail())))
         .andExpect(jsonPath("$.name", is(responseEmployeeDtoId.get(0).getName())))
         .andExpect(jsonPath("$.gender", is(responseEmployeeDtoId.get(0).getGender())));
		verify(employeeService, times(1)).getEmployee(responseEmployeeDtoId.get(0).getEmpId());
		verifyNoMoreInteractions(employeeService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updateEmployee() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/employeeId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseEmployeeDto> responseEmployeeDto = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseEmployeeDto>>(){}.getType());
		
		EmployeeDto employeeDto = new EmployeeDto(responseEmployeeDto.get(0).getEmpId(), responseEmployeeDto.get(0).getEmail(), responseEmployeeDto.get(0).getName(), responseEmployeeDto.get(0).getGender(), "abcdefghi", "ROLE_EMLOYEE");
		String jsonStr = Obj.writeValueAsString(employeeDto);
		
		when(employeeService.updateEmp(employeeDto)).thenReturn(employeeDto.toString());
		mockMvc.perform(put("/admin/update/Emloyee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(employeeService, times(1)).updateEmp(org.mockito.Matchers.refEq(employeeDto));
		verifyNoMoreInteractions(employeeService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addEmployee() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/EmployeeId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseEmployeeDto> responseEmployeeDto = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseEmployeeDto>>(){}.getType());
		
		EmployeeDto employeeDto = new EmployeeDto(responseEmployeeDto.get(0).getEmpId(), responseEmployeeDto.get(0).getEmail(), responseEmployeeDto.get(0).getName(), responseEmployeeDto.get(0).getGender(), "abcdefghi", "ROLE_EMLOYEE");
		String jsonStr = Obj.writeValueAsString(employeeDto);
		
		when(employeeService.addEmployee(employeeDto)).thenReturn(employeeDto.toString());
		mockMvc.perform(post("/admin/addEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(employeeService, times(1)).addEmployee(org.mockito.Matchers.refEq(employeeDto));
		verifyNoMoreInteractions(employeeService);
	}
}
