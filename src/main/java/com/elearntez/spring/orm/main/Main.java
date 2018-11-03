package com.elearntez.spring.orm.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.elearntez.spring.orm.Application;
import com.elearntez.spring.orm.bean.City;
import com.elearntez.spring.orm.repository.CityRepository;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context  = new AnnotationConfigApplicationContext(Application.class);
		CityRepository repo = context.getBean(CityRepository.class);
		
		/*List<City> cities = repo.getAllCities();
		 for(City city:cities){
			 System.out.println(city);
		 }*/
		
		/*int cityCount = repo.getCityCount();
		System.out.println("The number of cities in table : "+cityCount);*/
	}

}
