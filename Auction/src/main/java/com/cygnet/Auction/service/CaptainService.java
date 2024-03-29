/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Captain
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.responseDto.ResponseCaptainList;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;
import com.cygnet.Auction.responseDto.ResponseString;

public interface CaptainService {

	public List<ReturnCapFromCapReviewDto> selectCaptains();
	public String addCaptains(int size);
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptain(String empId);
	public List<ResponseCaptainList> getCaptainList();
	public String checkEmpAsCap(String empId);
	public List<ReturnCapFromCapReviewDto> selectCaptainsTimeStamp(ReportDto reportDto);
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptainTimestamp(ReportDto reportDto);
	public ResponseString getEmployeeByCapId(String capId);
}
