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

import com.cygnet.Auction.controller.AuctionController;
import com.cygnet.Auction.responseDto.ResponsePlayersForBid;
import com.cygnet.Auction.service.AuctionService;
import com.cygnet.Auction.serviceImpl.PlayerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
public class AuctionControllerTest {

	@InjectMocks private AuctionController auctionController;
	@Mock private AuctionService auctionService;
	@Mock private PlayerServiceImpl playerService;
	private MockMvc mockMvc;
	
	JSONParser parser = new JSONParser();
	List<ResponsePlayersForBid> responsePlayersForBid;
	JSONArray jsonArray;
	Object obj;
	
	@Before
	public void inti() throws Exception{
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(auctionController)
                .build();
        
        obj = parser.parse(new FileReader("c:/json/ResponsePlayersForBid.json"));
		jsonArray= (JSONArray)obj; 
		responsePlayersForBid = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ResponsePlayersForBid>>(){}.getType());
	
	}
	
	@Test
	public void getAllPlayersBid() throws Exception{
		
		when(playerService.getPlayersForBid()).thenReturn(responsePlayersForBid);
	    mockMvc.perform(get("/employee/getPlayers"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$", hasSize(responsePlayersForBid.size())))
	            .andExpect(jsonPath("$[0].empId", is(responsePlayersForBid.get(0).getEmpId())))
	            .andExpect(jsonPath("$[0].playerId", is(responsePlayersForBid.get(0).getPlayerId())))
	            .andExpect(jsonPath("$[0].name", is(responsePlayersForBid.get(0).getName())))
	            .andExpect(jsonPath("$[0].lastBidderId", is(responsePlayersForBid.get(0).getLastBidderId())))
//	            .andExpect(jsonPath("$[0].lastBidderAmt", is(responsePlayersForBid.get(0).getLastBidderAmt())))
	            .andExpect(jsonPath("$[0].playerRole", is(responsePlayersForBid.get(0).getPlayerRole())))
	            .andExpect(jsonPath("$[1].empId", is(responsePlayersForBid.get(1).getEmpId())))
	            .andExpect(jsonPath("$[1].playerId", is(responsePlayersForBid.get(1).getPlayerId())))
	            .andExpect(jsonPath("$[1].name", is(responsePlayersForBid.get(1).getName())))
	            .andExpect(jsonPath("$[1].lastBidderId", is(responsePlayersForBid.get(1).getLastBidderId())))
//	            .andExpect(jsonPath("$[1].lastBidderAmt", is(responsePlayersForBid.get(1).getLastBidderAmt())))
	            .andExpect(jsonPath("$[1].playerRole", is(responsePlayersForBid.get(1).getPlayerRole())))
	            .andExpect(jsonPath("$[2].empId", is(responsePlayersForBid.get(2).getEmpId())))
	            .andExpect(jsonPath("$[2].playerId", is(responsePlayersForBid.get(2).getPlayerId())))
	            .andExpect(jsonPath("$[2].name", is(responsePlayersForBid.get(2).getName())))
	            .andExpect(jsonPath("$[2].lastBidderId", is(responsePlayersForBid.get(2).getLastBidderId())))
//	            .andExpect(jsonPath("$[2].lastBidderAmt", is(responsePlayersForBid.get(2).getLastBidderAmt())))
	            .andExpect(jsonPath("$[2].playerRole", is(responsePlayersForBid.get(2).getPlayerRole())));
	    verify(playerService, times(1)).getPlayersForBid();
	    verifyNoMoreInteractions(playerService);
	}
}
