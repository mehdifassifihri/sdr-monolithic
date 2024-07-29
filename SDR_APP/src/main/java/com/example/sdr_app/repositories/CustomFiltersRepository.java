package com.example.sdr_app.repositories;

import com.example.sdr_app.entities.CustomFilters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFiltersRepository extends JpaRepository<CustomFilters,Integer> {
    List<CustomFilters> getCustomFiltersByUserId(int userId);
}
