package com.webz.Second.Hand.Shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Garment implements Serializable {


    private int id;
        private String publishedBy;
        private String size;
        private String description;
        private String type;
        private BigDecimal price;
}
