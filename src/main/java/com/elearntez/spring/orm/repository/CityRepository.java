package com.elearntez.spring.orm.repository;

import java.util.List;
import com.elearntez.spring.orm.bean.City;

public interface CityRepository {
	
	List<City> getAllCities();
	
	int getCityCount();
	
	int getCityCountByCountryCode(final String countryCode);
	
}
