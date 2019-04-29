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

import com.cygnet.Auction.controller.TeamNameController;
import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.model.TeamName;
import com.cygnet.Auction.service.TeamNameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class TeamNameControllerTest {

	
	@InjectMocks private TeamNameController teamNameController;
	@Mock private TeamNameService teamNameService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<TeamName> teamName;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(teamNameController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/TeamName.json"));
		jsonArray= (JSONArray)obj; 
		teamName = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<TeamName>>(){}.getType());
	
	}
	
	@Test
	public void getAllTeamName() throws Exception{
		
		when(teamNameService.getAllTeamName()).thenReturn(teamName);
	    mockMvc.perform(get("/employee/teamName/getAll"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(teamName.size())))
	            .andExpect(jsonPath("$[0].teamNameId", is(teamName.get(0).getTeamNameId())))
	            .andExpect(jsonPath("$[0].version", is(teamName.get(0).getVersion())))
	            .andExpect(jsonPath("$[0].name", is(teamName.get(0).getName())))
	            .andExpect(jsonPath("$[1].teamNameId", is(teamName.get(1).getTeamNameId())))
	            .andExpect(jsonPath("$[1].version", is(teamName.get(1).getVersion())))
	            .andExpect(jsonPath("$[1].name", is(teamName.get(1).getName())))
	            .andExpect(jsonPath("$[2].teamNameId", is(teamName.get(2).getTeamNameId())))
	            .andExpect(jsonPath("$[2].version", is(teamName.get(2).getVersion())))
	            .andExpect(jsonPath("$[2].name", is(teamName.get(2).getName())));
	    verify(teamNameService, times(1)).getAllTeamName();
	    verifyNoMoreInteractions(teamNameService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addTeamName() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper();
		
		TeamNameDto teamNameDto = new TeamNameDto(teamName.get(0).getName());
		String jsonStr = Obj.writeValueAsString(teamNameDto);
		
		when(teamNameService.addTeamName(teamNameDto)).thenReturn(teamNameDto.toString());
		mockMvc.perform(post("/admin/teamName/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(teamNameService, times(1)).addTeamName(org.mockito.Matchers.refEq(teamNameDto));
		verifyNoMoreInteractions(teamNameService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void UpdateTeamName() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper();
		
		TeamNameDto teamNameDto = new TeamNameDto("987c9c37-37a7-11e9-aa40-28d2448d5698" ,"MI");
		String jsonStr = Obj.writeValueAsString(teamNameDto);
		
		when(teamNameService.updateTeamName(teamNameDto)).thenReturn(teamNameDto.toString());
		mockMvc.perform(put("/admin/teamName/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(teamNameService, times(1)).updateTeamName(org.mockito.Matchers.refEq(teamNameDto));
		verifyNoMoreInteractions(teamNameService);
	}
}
