package com.elearntez.spring.orm.repository;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elearntez.spring.orm.bean.City;


@Transactional
@Repository
public class CityRepositoryImpl implements CityRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<City> getAllCities() {
		Query query = sessionFactory.getCurrentSession().createQuery("from City");
		return query.getResultList();	
	}

	public int getCityCount() {
		return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from City")
				 .uniqueResult()).intValue();
	}

	public int getCityCountByCountryCode(String countryCode) {
		String hql = "select count(*) from City where countryCode= :countryCode";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("countryCode", countryCode);
		return ((Long)query.getSingleResult()).intValue(); 
	}

}
