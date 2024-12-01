package com.webz.Second.Hand.Shop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "GARMENT")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class GarmentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3464185611014857836L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "PUBLISHER_USERNAME")
    private String publisherUsername;

    @Basic
    @Column(name = "SIZE")
    private String size;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "TYPE")
    private String type;

    @Basic
    @Column(name = "PRICE")
    private BigDecimal price;


}
