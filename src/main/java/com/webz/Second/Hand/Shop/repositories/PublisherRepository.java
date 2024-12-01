package com.webz.Second.Hand.Shop.repositories;

import com.webz.Second.Hand.Shop.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer> {
    Optional<PublisherEntity> findByUsername(String username);

}
