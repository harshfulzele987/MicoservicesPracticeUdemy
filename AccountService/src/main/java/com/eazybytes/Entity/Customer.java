package com.eazybytes.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    @Column(name = "customer_id")
    private long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}
