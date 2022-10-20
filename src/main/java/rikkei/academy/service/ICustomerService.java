package rikkei.academy.service;

import java.util.List;

import rikkei.academy.model.Customer;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void deleteById(Long id);

    void save(Customer customer);
}
