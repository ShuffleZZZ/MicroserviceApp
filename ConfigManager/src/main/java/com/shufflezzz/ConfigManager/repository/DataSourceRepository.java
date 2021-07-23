package com.shufflezzz.ConfigManager.repository;

import com.shufflezzz.ConfigManager.models.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {
}
