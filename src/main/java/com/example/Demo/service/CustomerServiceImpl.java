package com.example.Demo.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.exception.CustomerNotFoundException;
import com.example.Demo.model.Customer;
import com.example.Demo.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository repository;

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Customer deleteById(Long customerId) throws CustomerNotFoundException {
        Customer deletedCustomer = repository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Unable to find customer based on customer Id provided"));
        repository.deleteById(customerId);
        return deletedCustomer;
    }

    @Override
    public Customer findById(Long customerId) {
        Customer customer = repository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Unable to find customer based on customer Id provided"));
        return customer;
    }

    @Transactional
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
