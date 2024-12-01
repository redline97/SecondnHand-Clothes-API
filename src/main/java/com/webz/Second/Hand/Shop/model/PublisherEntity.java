package com.webz.Second.Hand.Shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PUBLISHER")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PublisherEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5109035762864714739L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Basic
    @Column(name = "USERNAME")
    private String username;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "FULL_NAME")
    private String fullName;

    @Basic
    @Column(name = "ADDRESS")
    private String address;
}
