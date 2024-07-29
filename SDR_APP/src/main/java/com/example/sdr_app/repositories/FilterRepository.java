package com.example.sdr_app.repositories;

import com.example.sdr_app.entities.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Filter,Integer> {

}
