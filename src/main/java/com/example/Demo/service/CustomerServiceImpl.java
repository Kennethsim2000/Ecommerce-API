package com.example.Demo.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.model.Customer;
import com.example.Demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository repository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer deleteById(Long customerId) {
        Optional<Customer> deletedCustomer = repository.findById(customerId);
        if(deletedCustomer.isEmpty()) {
            return null;
        }
        repository.deleteById(customerId);
        return deletedCustomer.get();
    }

    @Override
    public Customer findById(Long customerId) {
        Optional<Customer> customer = repository.findById(customerId);
        return customer.orElse(null);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer initialCustomer = repository.findById(customer.getCustomerId()).orElse(null);
        if(initialCustomer == null) {
            return null;
        }
        try {
            Field[] fields = customer.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(customer);
                if(value != null) {
                    field.set(initialCustomer, value);
                }
            }
        } catch(Exception e) {
            System.out.println("trouble updating customer");
        }
        repository.save(initialCustomer);
        return initialCustomer;
    }


}
