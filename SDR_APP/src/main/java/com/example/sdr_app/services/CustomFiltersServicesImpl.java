package com.example.sdr_app.services;

import com.example.sdr_app.entities.CustomFilters;
import com.example.sdr_app.entities.Filter;
import com.example.sdr_app.entities.User;
import com.example.sdr_app.repositories.CustomFiltersRepository;
import com.example.sdr_app.repositories.FilterRepository;
import com.example.sdr_app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomFiltersServicesImpl implements CustomFiltersServices{

    private CustomFiltersRepository customFiltersRepository;
    private FilterRepository filterRepository;
    private UserRepository userRepository;

    @Override
    public List<CustomFilters> getCustomFiltersByUserId(int userId) {
        return customFiltersRepository.getCustomFiltersByUserId(userId);
    }


    @Override
    public CustomFilters addCustomFilters(CustomFilters customFilters) {
        int userId = customFilters.getUser().getId();
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            // Set the user to the customFilters object
            customFilters.setUser(user.get());

            List<Filter> filters = filterRepository.saveAll(customFilters.getFilters());
            customFilters.setFilters(filters);
            return customFiltersRepository.save(customFilters);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }
    }
}

