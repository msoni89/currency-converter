package com.practice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.enums.Frequency;
import com.practice.model.DashboardData;

@Repository
public interface IDashboardRepository extends CrudRepository<DashboardData, UUID> {
	public Optional<DashboardData> findFirstByKeyAndFrequency(String key, Frequency frequency);

	public List<DashboardData> deleteByKey(String key);
}
