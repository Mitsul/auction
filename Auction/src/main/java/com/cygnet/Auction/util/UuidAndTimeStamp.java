/**
 * @author Mitsul Teli
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is used to get the uuid as well as time stamp as per the request
 */

package com.cygnet.Auction.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.UUIDTimer;

@Service
public class UuidAndTimeStamp {

	UUID uid = null;
	
	/**
	 * <b> Get Uuid : </b> This function returns the uuid as per the request
	 * @return String return type of the function
	 * @exception e It catches the exception if any occurs
	 */
	public String getUuid() {
		try {
			uid = Generators.timeBasedGenerator(EthernetAddress.fromInterface(),new UUIDTimer(new Random(), null)).generate();
			System.out.println("uuid = " + uid);
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		return uid.toString();
	}
	
	/**
	 * <b> Get TimeStamp : </b> This function returns the current timestamp as per the request
	 * @return Timestamp return type of the function
	 * @exception e It catches the exception if any occurs
	 */
	public Timestamp getTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}
	
}
