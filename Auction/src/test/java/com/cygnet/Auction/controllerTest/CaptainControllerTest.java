package com.cygnet.Auction.controllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cygnet.Auction.controller.CaptainController;
import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.responseDto.ResponseCaptainList;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;
import com.cygnet.Auction.service.CaptainService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class CaptainControllerTest {

	@InjectMocks private CaptainController captainController;
	@Mock private CaptainService captainService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ReturnCapFromCapReviewDto> returnCapFromCapReviewDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(captainController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/ReturnCapFromCapReview.json"));
		jsonArray= (JSONArray)obj; 
		returnCapFromCapReviewDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ReturnCapFromCapReviewDto>>(){}.getType());
	
	}
	
	@Test
	public void getAllCaptain() throws Exception{
		
		when(captainService.selectCaptains()).thenReturn(returnCapFromCapReviewDto);
	    mockMvc.perform(get("/employee/selectCaptains"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(returnCapFromCapReviewDto.size())))
	            .andExpect(jsonPath("$[0].empId", is(returnCapFromCapReviewDto.get(0).getEmpId())))
	            .andExpect(jsonPath("$[0].playerId", is(returnCapFromCapReviewDto.get(0).getPlayerId())))
	            .andExpect(jsonPath("$[0].name", is(returnCapFromCapReviewDto.get(0).getName())))
	            .andExpect(jsonPath("$[0].avgRating", is(returnCapFromCapReviewDto.get(0).getAvgRating())))
	            .andExpect(jsonPath("$[1].empId", is(returnCapFromCapReviewDto.get(1).getEmpId())))
	            .andExpect(jsonPath("$[1].playerId", is(returnCapFromCapReviewDto.get(1).getPlayerId())))
	            .andExpect(jsonPath("$[1].name", is(returnCapFromCapReviewDto.get(1).getName())))
	            .andExpect(jsonPath("$[1].avgRating", is(returnCapFromCapReviewDto.get(1).getAvgRating())))
	            .andExpect(jsonPath("$[2].empId", is(returnCapFromCapReviewDto.get(2).getEmpId())))
	            .andExpect(jsonPath("$[2].playerId", is(returnCapFromCapReviewDto.get(2).getPlayerId())))
	            .andExpect(jsonPath("$[2].name", is(returnCapFromCapReviewDto.get(2).getName())))
	            .andExpect(jsonPath("$[2].avgRating", is(returnCapFromCapReviewDto.get(2).getAvgRating())));
	    verify(captainService, times(1)).selectCaptains();
	    verifyNoMoreInteractions(captainService);
	}
	
	@Test
	public void getPlayersFromCapatin() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/ResponsePlayersFromCaptain.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayersFromCaptainDto> responsePlayersFromCaptainDto = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayersFromCaptainDto>>(){}.getType());
		
		when(captainService.getPlayersFromCaptain("1474f36e-39ea-11e9-93c3-28d2448d5678")).thenReturn(responsePlayersFromCaptainDto);
		mockMvc.perform(get("/admin/playersFromCaptain/{empId}","1474f36e-39ea-11e9-93c3-28d2448d5678"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[0].capName", is(responsePlayersFromCaptainDto.get(0).getCapName())))
		.andExpect(jsonPath("$[0].playerName", is(responsePlayersFromCaptainDto.get(0).getPlayerName())))
		.andExpect(jsonPath("$[0].playerRole", is(responsePlayersFromCaptainDto.get(0).getPlayerRole())))
		.andExpect(jsonPath("$[1].capName", is(responsePlayersFromCaptainDto.get(1).getCapName())))
		.andExpect(jsonPath("$[1].playerName", is(responsePlayersFromCaptainDto.get(1).getPlayerName())))
		.andExpect(jsonPath("$[1].playerRole", is(responsePlayersFromCaptainDto.get(1).getPlayerRole())))
		.andExpect(jsonPath("$[2].capName", is(responsePlayersFromCaptainDto.get(2).getCapName())))
		.andExpect(jsonPath("$[2].playerName", is(responsePlayersFromCaptainDto.get(2).getPlayerName())))
		.andExpect(jsonPath("$[2].playerRole", is(responsePlayersFromCaptainDto.get(2).getPlayerRole())));
		verify(captainService, times(1)).getPlayersFromCaptain("1474f36e-39ea-11e9-93c3-28d2448d5678");
		verifyNoMoreInteractions(captainService);
	}
	
	@Test
	public void getCaptainsList() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/ResponsePlayersFromCaptain.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseCaptainList> responseCaptainList = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseCaptainList>>(){}.getType());
		
		when(captainService.getCaptainList()).thenReturn(responseCaptainList);
	    mockMvc.perform(get("/employee/getCaptains"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(responseCaptainList.size())))
	            .andExpect(jsonPath("$[0].empId", is(responseCaptainList.get(0).getEmpId())))
	            .andExpect(jsonPath("$[0].playerId", is(responseCaptainList.get(0).getPlayerId())))
	            .andExpect(jsonPath("$[0].capId", is(responseCaptainList.get(0).getCapId())))
	            .andExpect(jsonPath("$[0].name", is(responseCaptainList.get(0).getName())))
	            .andExpect(jsonPath("$[1].empId", is(responseCaptainList.get(1).getEmpId())))
	            .andExpect(jsonPath("$[1].playerId", is(responseCaptainList.get(1).getPlayerId())))
	            .andExpect(jsonPath("$[1].capId", is(responseCaptainList.get(1).getCapId())))
	            .andExpect(jsonPath("$[1].name", is(responseCaptainList.get(1).getName())))
	            .andExpect(jsonPath("$[2].empId", is(responseCaptainList.get(2).getEmpId())))
	            .andExpect(jsonPath("$[2].playerId", is(responseCaptainList.get(2).getPlayerId())))
	            .andExpect(jsonPath("$[2].capId", is(responseCaptainList.get(2).getCapId())))
	            .andExpect(jsonPath("$[2].name", is(responseCaptainList.get(2).getName())));
	    verify(captainService, times(1)).getCaptainList();
	    verifyNoMoreInteractions(captainService);
	}
}
