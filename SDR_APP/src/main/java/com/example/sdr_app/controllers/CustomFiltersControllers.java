package com.example.sdr_app.controllers;

import com.example.sdr_app.entities.CustomFilters;
import com.example.sdr_app.services.CustomFiltersServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filters")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CustomFiltersControllers {

    private final CustomFiltersServicesImpl customFiltersServices;

    @GetMapping("/{userId}")
    ResponseEntity<List<CustomFilters>> getFiltersByUserId(@PathVariable int userId) {
        List<CustomFilters> filtersByUserId = customFiltersServices.getCustomFiltersByUserId(userId);
        return ResponseEntity.ok(filtersByUserId);
    }

    @PostMapping()
    public ResponseEntity<CustomFilters> addCustomFilter(@RequestBody CustomFilters customFilters) {
        CustomFilters customFilters1 = customFiltersServices.addCustomFilters(customFilters);
        return new ResponseEntity<>(customFilters1, HttpStatus.CREATED);
    }
}
