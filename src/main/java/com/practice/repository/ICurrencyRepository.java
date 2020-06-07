package com.practice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.model.RealTimeCurrencyExRate;

@Repository
public interface ICurrencyRepository extends CrudRepository<RealTimeCurrencyExRate, UUID> {
	public Optional<RealTimeCurrencyExRate> findFirstByKeyOrderByFetchedTimeDesc(String key);
}
