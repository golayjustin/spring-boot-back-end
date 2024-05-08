package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_first_name", nullable=false)
    @NotBlank(message = "Cannot be blank")
    private String firstName;

    @Column(name = "customer_last_name", nullable=false)
    @NotBlank(message = "Cannot be blank")
    private String lastName;

    @Column(name = "address", nullable=false)
    @NotBlank(message = "Cannot be blank")
    private String address;

    @Column(name = "postal_code", nullable=false)
    @NotBlank(message = "Cannot be blank")
    private String postal_code;

    @Column(name = "phone", nullable=false)
    @NotBlank(message = "Cannot be blank")
    private String phone;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts = new HashSet<>();

    public void add(Cart cart) {

        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }

            carts.add(cart);
            cart.setCustomer(this);
        }
    }
}
