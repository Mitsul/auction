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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cygnet.Auction.controller.PlayerController;
import com.cygnet.Auction.dto.PlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class PlayerControllerTest {

	@InjectMocks private PlayerController playerController;
	@Mock private PlayerService playerService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ResponsePlayerDto> responsePlayerDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(playerController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/Player.json"));
		jsonArray= (JSONArray)obj; 
		responsePlayerDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponsePlayerDto>>(){}.getType());
	
	}
	
	@Test
	public void getPlayerById() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/PlayerId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayerDto> responsePlayerDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayerDto>>(){}.getType());
		
		when(playerService.getPlayer(responsePlayerDtoId.get(0).getEmpId())).thenReturn(responsePlayerDto.get(0));
		mockMvc.perform(get("/employee/player/{empId}",responsePlayerDtoId.get(0).getEmpId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.playerId", is(responsePlayerDtoId.get(0).getPlayerId())))
		.andExpect(jsonPath("$.empId", is(responsePlayerDtoId.get(0).getEmpId())))
		 .andExpect(jsonPath("$.email", is(responsePlayerDtoId.get(0).getEmail())))
         .andExpect(jsonPath("$.name", is(responsePlayerDtoId.get(0).getName())))
         .andExpect(jsonPath("$.gender", is(responsePlayerDtoId.get(0).getGender())))
         .andExpect(jsonPath("$.isActive", is(responsePlayerDtoId.get(0).getIsActive())))
         .andExpect(jsonPath("$.prefCaptain", is(responsePlayerDtoId.get(0).getPrefCaptain())))
		.andExpect(jsonPath("$.playerRole", is(responsePlayerDtoId.get(0).getPlayerRole())));
		verify(playerService, times(1)).getPlayer(responsePlayerDtoId.get(0).getEmpId());
		verifyNoMoreInteractions(playerService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updatePlayer() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/PlayerId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayerDto> responsePlayerDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayerDto>>(){}.getType());
		
		PlayerDto playerDto = new PlayerDto(responsePlayerDtoId.get(0).getEmpId(),responsePlayerDtoId.get(0).getPrefCaptain(), responsePlayerDtoId.get(0).getIsActive(), responsePlayerDtoId.get(0).getPlayerRole());
		String jsonStr = Obj.writeValueAsString(playerDto);
		
		when(playerService.updatePlayer(playerDto)).thenReturn(playerDto.toString());
		mockMvc.perform(put("/employee/player/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(playerService, times(1)).updatePlayer(org.mockito.Matchers.refEq(playerDto));
		verifyNoMoreInteractions(playerService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addPlayerStat() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/PlayerId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayerDto>responsePlayerDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayerDto>>(){}.getType());
		
		PlayerDto playerDto = new PlayerDto(responsePlayerDtoId.get(0).getEmpId(),responsePlayerDtoId.get(0).getPrefCaptain(), responsePlayerDtoId.get(0).getIsActive(), responsePlayerDtoId.get(0).getPlayerRole());
		String jsonStr = Obj.writeValueAsString(playerDto);
		
		when(playerService.addPlayer(playerDto)).thenReturn(playerDto.toString());
		mockMvc.perform(post("/employee/player/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(playerService, times(1)).addPlayer(org.mockito.Matchers.refEq(playerDto));
		verifyNoMoreInteractions(playerService);
	}
}