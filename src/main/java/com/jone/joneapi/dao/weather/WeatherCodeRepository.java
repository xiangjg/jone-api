package com.jone.joneapi.dao.weather;

import com.jone.joneapi.entity.WeatherCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherCodeRepository extends JpaRepository<WeatherCode,Integer> {

    List<WeatherCode> findByProvince(String province);
}
