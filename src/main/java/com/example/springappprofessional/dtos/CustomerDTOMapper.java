package com.example.springappprofessional.dtos;

import com.example.springappprofessional.models.Customer;

import java.util.function.Function;

public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getGender(),
                customer.getAge(),
                customer.getProfileImageId()
        );
    }
}