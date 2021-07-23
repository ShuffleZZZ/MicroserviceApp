package com.shufflezzz.ConfigManager.repository;

import com.shufflezzz.ConfigManager.models.DataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSetRepository extends JpaRepository<DataSet, Long> {
}
