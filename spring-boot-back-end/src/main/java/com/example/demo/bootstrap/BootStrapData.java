package com.example.demo.bootstrap;

import com.example.demo.dao.*;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() < 5) {
            addCustomer("Alvin", "Chipmunk", "111 First Customer Street", "11111", "111-111-1111", 2L);
            addCustomer("Simon", "Chipmunk", "222 Second Customer Street", "22222", "222-222-2222", 3L);
            addCustomer("Theodore", "Chipmunk", "333 Third Customer Street", "33333", "333-333-3333", 4L);
            addCustomer("Dave", "Seville", "444 Fourth Customer Street", "44444", "444-444-4444", 5L);
            addCustomer("Brittany", "Chipette", "555 Fifth Customer Street", "55555", "555-555-5555", 6L);
        }
    }

    private void addCustomer(String firstName, String lastName, String address, String postalCode, String phone, Long divisionId) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setPostal_code(postalCode);
        customer.setPhone(phone);
        Division division = divisionRepository.findById(divisionId).orElse(null);
        customer.setDivision(division);
        customerRepository.save(customer);
    }
}