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
	
	public Timestamp getTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}
	
}
