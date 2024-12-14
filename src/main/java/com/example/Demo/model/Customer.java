package com.example.Demo.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long customerId;

    private String name;
    private String address;
    private String password;


    @OneToMany(mappedBy = "customerId", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Order> order = new HashSet<>();

}

/*The key to using bidirectional relationship is to first remove the toString() method in one of the classes.
If we do not remove the toString() method, when calling toString() of customer, it will call toString of order, which has
a reference to Customer, which will then call Customer's tostring() method, leading to infinite recursion and stack
overflow.

Another thing to take note is to add the @jsonIgnore, as removing it would lead to a very nested object.
* */