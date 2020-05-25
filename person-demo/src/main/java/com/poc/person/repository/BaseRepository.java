package com.poc.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.poc.person.dao.model.Person;
import com.sun.xml.bind.v2.model.core.ID;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>{

}
