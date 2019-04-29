package com.cygnet.Auction.controllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.cygnet.Auction.controller.Player_StatController;
import com.cygnet.Auction.dto.Player_StatDto;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.responseDto.ResponsePlayer_StatDto;
import com.cygnet.Auction.service.Player_StatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@WebMvcTest(value = Player_StatController.class, secure = false)
@ContextConfiguration(classes=AuctionApplicationTests.class)
public class Player_StatControllerTest {

	@InjectMocks private Player_StatController player_StatController;
	@Mock private Player_StatService player_StatService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ResponsePlayer_StatDto> responsePlayer_StatDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(player_StatController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/PlayerStat.json"));
		jsonArray= (JSONArray)obj; 
		responsePlayer_StatDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponsePlayer_StatDto>>(){}.getType());
	
	}
	
	@Test
	public void getAllPlayerStat() throws Exception{
		
		when(player_StatService.adminPlayerStatGetAll()).thenReturn(responsePlayer_StatDto);
	    mockMvc.perform(get("/employee/player/playerStat/getAll"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(responsePlayer_StatDto.size())))
	            .andExpect(jsonPath("$[0].playerStatId", is(responsePlayer_StatDto.get(0).getPlayerStatId())))
	            .andExpect(jsonPath("$[0].empId", is(responsePlayer_StatDto.get(0).getEmpId())))
	            .andExpect(jsonPath("$[0].name", is(responsePlayer_StatDto.get(0).getName())))
	            .andExpect(jsonPath("$[0].gender", is(responsePlayer_StatDto.get(0).getGender())))
	            .andExpect(jsonPath("$[0].totalRuns", is(responsePlayer_StatDto.get(0).getTotalRuns())))
	            .andExpect(jsonPath("$[0].totalWick", is(responsePlayer_StatDto.get(0).getTotalWick())))
	            .andExpect(jsonPath("$[0].manOfTheMatch", is(responsePlayer_StatDto.get(0).getManOfTheMatch())))
	            .andExpect(jsonPath("$[1].playerStatId", is(responsePlayer_StatDto.get(1).getPlayerStatId())))
	            .andExpect(jsonPath("$[1].empId", is(responsePlayer_StatDto.get(1).getEmpId())))
	            .andExpect(jsonPath("$[1].name", is(responsePlayer_StatDto.get(1).getName())))
	            .andExpect(jsonPath("$[1].gender", is(responsePlayer_StatDto.get(1).getGender())))
	            .andExpect(jsonPath("$[1].totalRuns", is(responsePlayer_StatDto.get(1).getTotalRuns())))
	            .andExpect(jsonPath("$[1].totalWick", is(responsePlayer_StatDto.get(1).getTotalWick())))
	            .andExpect(jsonPath("$[1].manOfTheMatch", is(responsePlayer_StatDto.get(1).getManOfTheMatch())));
	    verify(player_StatService, times(1)).adminPlayerStatGetAll();
	    verifyNoMoreInteractions(player_StatService);
	}
	
	@Test
	public void getAllPlayers() throws Exception{
		
		Object objEmp = parser.parse(new FileReader("c:/json/PlayerStatGetAllEmp.json"));
		jsonArray= (JSONArray)objEmp; 
		ArrayList<ResponseEmployeeDto> responseEmployeeDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponseEmployeeDto>>(){}.getType());
		
		when(player_StatService.adminGetAllEmp()).thenReturn(responseEmployeeDto);
		
	    mockMvc.perform(get("/admin/playerStat/getAllEmp"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(responseEmployeeDto.size())))
	            .andExpect(jsonPath("$[0].empId", is(responseEmployeeDto.get(0).getEmpId())))
	            .andExpect(jsonPath("$[0].email", is(responseEmployeeDto.get(0).getEmail())))
	            .andExpect(jsonPath("$[0].name", is(responseEmployeeDto.get(0).getName())))
	            .andExpect(jsonPath("$[0].gender", is(responseEmployeeDto.get(0).getGender())))
	            .andExpect(jsonPath("$[1].empId", is(responseEmployeeDto.get(1).getEmpId())))
	            .andExpect(jsonPath("$[1].email", is(responseEmployeeDto.get(1).getEmail())))
	            .andExpect(jsonPath("$[1].name", is(responseEmployeeDto.get(1).getName())))
	            .andExpect(jsonPath("$[1].gender", is(responseEmployeeDto.get(1).getGender())));
	    verify(player_StatService, times(1)).adminGetAllEmp();
	    verifyNoMoreInteractions(player_StatService);
	}
	
	@Test
	public void getPlayerStatById() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/PlayerStatId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayer_StatDto> responsePlayer_StatDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayer_StatDto>>(){}.getType());
		
		when(player_StatService.adminGetPlayerStat(responsePlayer_StatDtoId.get(0).getEmpId())).thenReturn(responsePlayer_StatDto.get(1));
		mockMvc.perform(get("/employee/player/playerStat/{empId}",responsePlayer_StatDtoId.get(0).getEmpId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.playerStatId", is(responsePlayer_StatDtoId.get(0).getPlayerStatId())))
		.andExpect(jsonPath("$.empId", is(responsePlayer_StatDtoId.get(0).getEmpId())))
		 .andExpect(jsonPath("$.name", is(responsePlayer_StatDtoId.get(0).getName())))
         .andExpect(jsonPath("$.gender", is(responsePlayer_StatDtoId.get(0).getGender())))
         .andExpect(jsonPath("$.totalRuns", is(responsePlayer_StatDtoId.get(0).getTotalRuns())))
         .andExpect(jsonPath("$.totalWick", is(responsePlayer_StatDtoId.get(0).getTotalWick())))
         .andExpect(jsonPath("$.manOfTheMatch", is(responsePlayer_StatDtoId.get(0).getManOfTheMatch())));
		verify(player_StatService, times(1)).adminGetPlayerStat(responsePlayer_StatDtoId.get(0).getEmpId());
		verifyNoMoreInteractions(player_StatService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updatePlayerStat() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/PlayerStatId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayer_StatDto> responsePlayer_StatDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayer_StatDto>>(){}.getType());
		
		Player_StatDto player_StatDto = new Player_StatDto(responsePlayer_StatDtoId.get(0).getEmpId(),responsePlayer_StatDtoId.get(0).getTotalRuns(), responsePlayer_StatDtoId.get(0).getTotalWick(), responsePlayer_StatDtoId.get(0).getManOfTheMatch());
		String jsonStr = Obj.writeValueAsString(player_StatDto);
		
		when(player_StatService.updatePlayerStat(player_StatDto)).thenReturn(player_StatDto.toString());
		mockMvc.perform(put("/admin/playerStat/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(player_StatService, times(1)).updatePlayerStat(org.mockito.Matchers.refEq(player_StatDto));
		verifyNoMoreInteractions(player_StatService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addPlayerStat() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/PlayerStatId.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<ResponsePlayer_StatDto>responsePlayer_StatDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayer_StatDto>>(){}.getType());
		
		Player_StatDto player_StatDto = new Player_StatDto(responsePlayer_StatDtoId.get(0).getEmpId(),responsePlayer_StatDtoId.get(0).getTotalRuns(), responsePlayer_StatDtoId.get(0).getTotalWick(), responsePlayer_StatDtoId.get(0).getManOfTheMatch());
		String jsonStr = Obj.writeValueAsString(player_StatDto);
		
		when(player_StatService.adminAddPlayerStat(player_StatDto)).thenReturn(player_StatDto.toString());
		mockMvc.perform(post("/admin/playerStat/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(player_StatService, times(1)).adminAddPlayerStat(org.mockito.Matchers.refEq(player_StatDto));
		verifyNoMoreInteractions(player_StatService);
	}
}