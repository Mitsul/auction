/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Player
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.PlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayersForBid;

public interface PlayerService {

	public String addPlayer(PlayerDto playerDto);
	public ResponsePlayerDto getPlayer(String empId);
	public String updatePlayer(PlayerDto playerDto);
	public List<ResponsePlayersForBid> getPlayersForBid(int i);
}
