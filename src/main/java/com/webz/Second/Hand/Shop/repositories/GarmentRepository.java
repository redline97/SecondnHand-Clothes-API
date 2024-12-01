package com.webz.Second.Hand.Shop.repositories;

import com.webz.Second.Hand.Shop.model.GarmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GarmentRepository extends JpaRepository<GarmentEntity, Integer> {


    Page<GarmentEntity> findAll(Specification<GarmentEntity> garmentEntitySpecification, Pageable pageable);

    Optional<GarmentEntity> findByIdAndPublisherUsername(Integer id, String username);
}

