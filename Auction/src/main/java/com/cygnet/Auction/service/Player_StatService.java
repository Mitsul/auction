package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.Player_StatDto;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.responseDto.ResponsePlayer_StatDto;

public interface Player_StatService {
	
	public String adminAddPlayerStat(Player_StatDto player_StatDto);
	public ResponsePlayer_StatDto adminGetPlayerStat(String empId);
	public List<ResponseEmployeeDto> adminGetAllEmp();
	public String updatePlayerStat(Player_StatDto player_StatDto);
	public List<ResponsePlayer_StatDto> adminPlayerStatGetAll();
}
