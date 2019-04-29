package com.cygnet.Auction.controllerTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cygnet.Auction.controller.BaseTokenController;
import com.cygnet.Auction.dto.BaseTokenDto;
import com.cygnet.Auction.service.BaseTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class BaseTokenControllerTest {

	@InjectMocks private BaseTokenController baseTokenController;
	@Mock private BaseTokenService baseTokenService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	ArrayList<BaseTokenDto> baseTokenDto;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(baseTokenController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/BaseToken.json"));
		jsonArray= (JSONArray)obj; 
		baseTokenDto = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<BaseTokenDto>>(){}.getType());
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void generateToken() throws Exception{
//		
		ObjectMapper Obj = new ObjectMapper(); 
//		Object objId = parser.parse(new FileReader("c:/json/PlayerStatId.json"));
//		JSONArray jsonArrayId = (JSONArray)objId; 
//		ArrayList<ResponsePlayer_StatDto>responsePlayer_StatDtoId = new Gson().fromJson(jsonArrayId.toString(), new TypeToken<List<ResponsePlayer_StatDto>>(){}.getType());
		
		BaseTokenDto baseTokenDtoId = new BaseTokenDto(baseTokenDto.get(0).getBaseToken());
//		Player_StatDto player_StatDto = new Player_StatDto(responsePlayer_StatDtoId.get(0).getEmpId(),responsePlayer_StatDtoId.get(0).getTotalRuns(), responsePlayer_StatDtoId.get(0).getTotalWick(), responsePlayer_StatDtoId.get(0).getManOfTheMatch());
		String jsonStr = Obj.writeValueAsString(baseTokenDtoId);
		
		when(baseTokenService.generateBaseTokenForAllPlayers(baseTokenDtoId)).thenReturn(baseTokenDtoId.toString());
		mockMvc.perform(post("/admin/generateBaseTokenForAllPlayers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().isOk());
		
		verify(baseTokenService, times(1)).generateBaseTokenForAllPlayers(org.mockito.Matchers.refEq(baseTokenDtoId));
		verifyNoMoreInteractions(baseTokenService);
	}
}
