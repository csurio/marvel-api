package com.siman.assestment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siman.assestment.repository.entity.SearchLog;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {

}
