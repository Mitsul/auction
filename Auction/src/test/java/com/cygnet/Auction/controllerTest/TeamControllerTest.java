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

import com.cygnet.Auction.controller.TeamController;
import com.cygnet.Auction.responseDto.ResponseTeamDto;
import com.cygnet.Auction.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class TeamControllerTest {

	@InjectMocks private TeamController teamController;
	@Mock private TeamService teamService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<ResponseTeamDto> responseTeamDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(teamController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/GetTeam.json"));
		jsonArray= (JSONArray)obj; 
		responseTeamDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponseTeamDto>>(){}.getType());
	
	}
	
	@Test
	public void getAllTeam() throws Exception{
		
		when(teamService.getTeam()).thenReturn(responseTeamDto);
	    mockMvc.perform(get("/employee/getTeam"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(responseTeamDto.size())))
	            .andExpect(jsonPath("$[0].teamId", is(responseTeamDto.get(0).getTeamId())))
	            .andExpect(jsonPath("$[0].teamName", is(responseTeamDto.get(0).getTeamName())))
	            .andExpect(jsonPath("$[0].capName", is(responseTeamDto.get(0).getCapName())))
	            .andExpect(jsonPath("$[1].teamId", is(responseTeamDto.get(1).getTeamId())))
	            .andExpect(jsonPath("$[1].teamName", is(responseTeamDto.get(1).getTeamName())))
	            .andExpect(jsonPath("$[1].capName", is(responseTeamDto.get(1).getCapName())))
	            .andExpect(jsonPath("$[2].teamId", is(responseTeamDto.get(2).getTeamId())))
	            .andExpect(jsonPath("$[2].teamName", is(responseTeamDto.get(2).getTeamName())))
	            .andExpect(jsonPath("$[2].capName", is(responseTeamDto.get(2).getCapName())));
	    verify(teamService, times(1)).getTeam();
	    verifyNoMoreInteractions(teamService);
	}
}
