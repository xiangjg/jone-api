package com.jone.joneapi.dao.weather;

import com.jone.joneapi.entity.WeatherCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherCodeRepository extends JpaRepository<WeatherCode,Integer> {
}
