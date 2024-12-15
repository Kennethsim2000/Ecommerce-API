package com.example.Demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long orderId;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonBackReference
    private Customer customerId;

    @CreationTimestamp
    @Column(name="date_created", nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product productId;
}
