package com.cygnet.Auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Auction;

@Component
public interface AuctionRepository extends JpaRepository<Auction, String> {

}
