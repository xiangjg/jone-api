package com.jone.joneapi.dao.weather;

import com.jone.joneapi.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

}
