package com.cygnet.Auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Timing;

@Component
public interface TimingRepository extends JpaRepository<Timing, String> {

}
