package com.cygnet.Auction.controllerTest;

import static org.hamcrest.Matchers.hasSize;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cygnet.Auction.controller.Captain_ReviewController;
import com.cygnet.Auction.dto.Captain_ReviewDto;
import com.cygnet.Auction.responseDto.ResponseGetCaptainReview;
import com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto;
import com.cygnet.Auction.service.Captain_ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class Captain_ReviewControllerTest {

	@InjectMocks private Captain_ReviewController captain_ReviewController;
	@Mock private Captain_ReviewService captain_ReviewService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ResponsePlayersWithCapPrefDto> responsePlayersWithCapPrefDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(captain_ReviewController)
                .build();

	}
	
	@Test
	public void getAllPlayerStat() throws Exception{
		
		obj = parser.parse(new FileReader("c:/json/PlayerWithCapPref.json"));
		jsonArray= (JSONArray)obj; 
		responsePlayersWithCapPrefDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponsePlayersWithCapPrefDto>>(){}.getType());
		
		when(captain_ReviewService.getCaptainPrefList()).thenReturn(responsePlayersWithCapPrefDto);
	    mockMvc.perform(get("/employee/captainReview/getAllCaptainPrefList"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(responsePlayersWithCapPrefDto.size())))
	            .andExpect(jsonPath("$[0].playerId", is(responsePlayersWithCapPrefDto.get(0).getPlayerId())))
	            .andExpect(jsonPath("$[0].empId", is(responsePlayersWithCapPrefDto.get(0).getEmpId())))
	            .andExpect(jsonPath("$[0].name", is(responsePlayersWithCapPrefDto.get(0).getName())))
	            .andExpect(jsonPath("$[1].playerId", is(responsePlayersWithCapPrefDto.get(1).getPlayerId())))
	            .andExpect(jsonPath("$[1].empId", is(responsePlayersWithCapPrefDto.get(1).getEmpId())))
	            .andExpect(jsonPath("$[1].name", is(responsePlayersWithCapPrefDto.get(1).getName())))
	            .andExpect(jsonPath("$[2].playerId", is(responsePlayersWithCapPrefDto.get(2).getPlayerId())))
	            .andExpect(jsonPath("$[2].empId", is(responsePlayersWithCapPrefDto.get(2).getEmpId())))
	            .andExpect(jsonPath("$[2].name", is(responsePlayersWithCapPrefDto.get(2).getName())));
	    verify(captain_ReviewService, times(1)).getCaptainPrefList();
	    verifyNoMoreInteractions(captain_ReviewService);
	}
	
	@Test
	public void getReviewId() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/PlayerStatId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponseGetCaptainReview> responseGetCaptainReviewId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponseGetCaptainReview>>(){}.getType());
		
		when(captain_ReviewService.getReview("0f45bdfe-39ea-11e9-852a-28d2448d5678")).thenReturn(responseGetCaptainReviewId.get(0));
		mockMvc.perform(get("/employee/captainReview/getReview/{empId}","0f45bdfe-39ea-11e9-852a-28d2448d5678"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.playerId", is(responseGetCaptainReviewId.get(0).getPlayerId())))
		.andExpect(jsonPath("$.capRevId", is(responseGetCaptainReviewId.get(0).getCapRevId())))
		 .andExpect(jsonPath("$.playerName", is(responseGetCaptainReviewId.get(0).getPlayerName())))
         .andExpect(jsonPath("$.captainName", is(responseGetCaptainReviewId 	.get(0).getCaptainName())))
         .andExpect(jsonPath("$.rating", is(responseGetCaptainReviewId.get(0).getRating())));
		verify(captain_ReviewService, times(1)).getReview("0f45bdfe-39ea-11e9-852a-28d2448d5678");
		verifyNoMoreInteractions(captain_ReviewService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void giveCaptainReview() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/CaptainReview.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<Captain_ReviewDto> captain_ReviewDto = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<Captain_ReviewDto>>(){}.getType());
		
		String jsonStr = Obj.writeValueAsString(captain_ReviewDto.get(0));
		
		when(captain_ReviewService.giveReview(captain_ReviewDto.get(0))).thenReturn(captain_ReviewDto.toString());
		mockMvc.perform(post("/employee/captainReview/giveReview")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(captain_ReviewService, times(1)).giveReview(org.mockito.Matchers.refEq(captain_ReviewDto.get(0)));
		verifyNoMoreInteractions(captain_ReviewService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updatePlayerStat() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/CaptainReview.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<Captain_ReviewDto> captain_ReviewDto = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<Captain_ReviewDto>>(){}.getType());
		
//		Player_StatDto player_StatDto = new Player_StatDto(captain_ReviewDto.get(0).getEmpId(),responsePlayer_StatDtoId.get(0).getTotalRuns(), responsePlayer_StatDtoId.get(0).getTotalWick(), responsePlayer_StatDtoId.get(0).getManOfTheMatch());
		String jsonStr = Obj.writeValueAsString(captain_ReviewDto.get(0));
		
		when(captain_ReviewService.updateReview(captain_ReviewDto.get(0))).thenReturn(captain_ReviewDto.get(0).toString());
		mockMvc.perform(put("/employee/captainReview/updateReview")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(captain_ReviewService, times(1)).updateReview(org.mockito.Matchers.refEq(captain_ReviewDto.get(0)));
		verifyNoMoreInteractions(captain_ReviewService);
	}
}