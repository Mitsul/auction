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
import com.cygnet.Auction.controller.AddressController;
import com.cygnet.Auction.dto.AddressDto;
import com.cygnet.Auction.responseDto.ResponseAddressDto;
import com.cygnet.Auction.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AddressController.class, secure = false)
@ContextConfiguration(classes=AuctionApplicationTests.class)
public class AddressControllerTest {

	@InjectMocks private AddressController addressController;
	@Mock private AddressService addressService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ResponseAddressDto> address;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(addressController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/address.json"));
		jsonArray= (JSONArray)obj; 
		address = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponseAddressDto>>(){}.getType());
	}
	
	@Test
	public void getAddressById() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/addressId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseAddressDto> addressID = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseAddressDto>>(){}.getType());
				
		when(addressService.getAddress(addressID.get(0).getEmpId())).thenReturn(address.get(1));
		mockMvc.perform(get("/employee/address/get/{empId}",addressID.get(0).getEmpId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.addressId", is(addressID.get(0).getAddressId())))
		.andExpect(jsonPath("$.empId", is(addressID.get(0).getEmpId())))
		.andExpect(jsonPath("$.name", is(addressID.get(0).getName())))
		.andExpect(jsonPath("$.city", is(addressID.get(0).getCity())))
		.andExpect(jsonPath("$.state", is(addressID.get(0).getState())))
		.andExpect(jsonPath("$.contactNo", is(addressID.get(0).getContactNo())));
		verify(addressService, times(1)).getAddress(addressID.get(0).getEmpId());
		verifyNoMoreInteractions(addressService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updateAddressById() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/addressId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseAddressDto>addressID = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseAddressDto>>(){}.getType());
		
		AddressDto addressDto = new AddressDto(addressID.get(0).getCity(), addressID.get(0).getState(), addressID.get(0).getContactNo(), addressID.get(0).getEmpId());
		String jsonStr = Obj.writeValueAsString(addressDto);
		
		when(addressService.updateAddress(addressDto)).thenReturn(addressDto.toString());
		mockMvc.perform(put("/employee/address/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(addressService, times(1)).updateAddress(org.mockito.Matchers.refEq(addressDto));
		verifyNoMoreInteractions(addressService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addAddress() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/addressId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseAddressDto>addressID = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseAddressDto>>(){}.getType());
		
		AddressDto addressDto = new AddressDto(addressID.get(0).getCity(), addressID.get(0).getState(), addressID.get(0).getContactNo(), addressID.get(0).getEmpId());
		String jsonStr = Obj.writeValueAsString(addressDto);
		
		when(addressService.addAddress(addressDto)).thenReturn(jsonStr);
		mockMvc.perform(post("/employee/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(addressService, times(1)).addAddress(org.mockito.Matchers.refEq(addressDto));
		verifyNoMoreInteractions(addressService);
	}
}