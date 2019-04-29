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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import com.cygnet.Auction.controller.PlayerRoleController;
import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;
import com.cygnet.Auction.service.PlayerRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PlayerRoleController.class, secure = false)
@ContextConfiguration(classes=AuctionApplicationTests.class)
public class PlayerRoleControllerTest {

	
	@InjectMocks private PlayerRoleController playerRoleController;
	@Mock private PlayerRoleService playerRoleService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<PlayerRole> playerRole;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(playerRoleController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/PlayerRole.json"));
		jsonArray= (JSONArray)obj; 
		playerRole = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<PlayerRole>>(){}.getType());
	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllPlayerRole() throws Exception{

		    when(playerRoleService.getAllPlayerRole()).thenReturn(jsonArray);
		    mockMvc.perform(get("/employee/playerRole/getAll"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(playerRole.size())))
		            .andExpect(jsonPath("$[0].playerRoleId", is(playerRole.get(0).getPlayerRoleId())))
		            .andExpect(jsonPath("$[0].name", is(playerRole.get(0).getName())))
		            .andExpect(jsonPath("$[1].playerRoleId", is(playerRole.get(1).getPlayerRoleId())))
		            .andExpect(jsonPath("$[1].name", is(playerRole.get(1).getName())));
		    verify(playerRoleService, times(1)).getAllPlayerRole();
		    verifyNoMoreInteractions(playerRoleService);
	}
	
	@Test
	public void getPlayerRoleById() throws Exception{
		
		Object objId = parser.parse(new FileReader("c:/json/PlayerRoleById.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<PlayerRole> playerRoleId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<PlayerRole>>(){}.getType());
		
		when(playerRoleService.getPlayerRole(playerRole.get(0).getPlayerRoleId())).thenReturn(playerRole.get(0));
		mockMvc.perform(get("/employee/playerRole/get/{id}",playerRole.get(0).getPlayerRoleId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.playerRoleId", is(playerRoleId.get(0).getPlayerRoleId())))
		.andExpect(jsonPath("$.name", is(playerRoleId.get(0).getName())));
		verify(playerRoleService, times(1)).getPlayerRole(playerRoleId.get(0).getPlayerRoleId());
		verifyNoMoreInteractions(playerRoleService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addPlayerRole() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		PlayerRoleDto playerRoledto = new PlayerRoleDto("All Rounder");
		String jsonStr = Obj.writeValueAsString(playerRoledto);
		when(playerRoleService.addPlayerRole(playerRoledto)).thenReturn(jsonStr);
		mockMvc.perform(post("/admin/playerRole/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(playerRoleService, times(1)).addPlayerRole(org.mockito.Matchers.refEq(playerRoledto));
		verifyNoMoreInteractions(playerRoleService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void updatePlayerRole() throws Exception{
		
		ObjectMapper Obj = new ObjectMapper(); 
		Object objId = parser.parse(new FileReader("c:/json/PlayerRoleById.json"));
		JSONArray jsonArrayId = (JSONArray)objId; 
		ArrayList<PlayerRole> playerRoleId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<PlayerRole>>(){}.getType());
		
		PlayerRoleDto playerRoleDto = new PlayerRoleDto(playerRoleId.get(0).getPlayerRoleId(),playerRoleId.get(0).getName());
		String jsonStr = Obj.writeValueAsString(playerRoleDto);
		
		when(playerRoleService.updatePlayerRole(playerRoleDto)).thenReturn(playerRoleDto.toString());
		mockMvc.perform(put("/admin/playerRole/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(playerRoleService, times(1)).updatePlayerRole(org.mockito.Matchers.refEq(playerRoleDto));
		verifyNoMoreInteractions(playerRoleService);
	}
	
}