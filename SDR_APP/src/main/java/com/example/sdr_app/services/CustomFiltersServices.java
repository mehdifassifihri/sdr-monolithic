package com.example.sdr_app.services;

import com.example.sdr_app.entities.CustomFilters;

import java.util.List;

public interface CustomFiltersServices {
    List<CustomFilters> getCustomFiltersByUserId(int userId);

    CustomFilters addCustomFilters(CustomFilters customFilters);
}