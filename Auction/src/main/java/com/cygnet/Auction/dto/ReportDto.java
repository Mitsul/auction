package com.cygnet.Auction.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

	private String id;
	private String empId;
	private Date startDate;
	private Date endDate;
}
