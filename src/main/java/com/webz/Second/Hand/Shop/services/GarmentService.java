package com.webz.Second.Hand.Shop.services;

import com.webz.Second.Hand.Shop.domain.Garment;
import com.webz.Second.Hand.Shop.domain.GarmentSearchResponse;
import com.webz.Second.Hand.Shop.model.GarmentEntity;
import com.webz.Second.Hand.Shop.repositories.GarmentRepository;
import com.webz.Second.Hand.Shop.services.mapper.GarmentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarmentService {

    private final GarmentRepository garmentRepository;
    private final GarmentMapper garmentMapper;

    public GarmentService(GarmentRepository garmentRepository, GarmentMapper garmentMapper) {
        this.garmentRepository = garmentRepository;
        this.garmentMapper = garmentMapper;
    }

    @Transactional(
            value = "transactionManager",
            rollbackFor = {Exception.class},
            readOnly = true
    )
    public ResponseEntity<GarmentSearchResponse> searchGarments(String type, String size, String description,
                                                                Integer pageIndex, Integer pageSize) {
        Page<GarmentEntity> garments = garmentRepository.findAll(
                garmentSearchSpecification(type, size, description),
                PageRequest.of(pageIndex, pageSize)
        );
        return ResponseEntity.ok(garmentMapper.toProductSearchResponse(garments));
    }

    private Specification<GarmentEntity> garmentSearchSpecification(String type, String size, String description) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(type)) {
                predicates.add(builder.equal(root.get("type"), type));
            }

            if (StringUtils.isNotBlank(size)) {
                predicates.add(builder.equal(root.get("size"), size));
            }

            if (StringUtils.isNotBlank(description)) {
                predicates.add(builder.like(root.get("description"), "%" + description + "%"));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Transactional(
            value = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> save(Garment request, String user) {
        try {
            GarmentEntity entityToTransform = garmentMapper.toSingleEntity(request, user);
            garmentRepository.save(entityToTransform);
            return ResponseEntity.ok("Garment saved successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(
            value = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<?> updateGarment(Integer id, Garment request) {
        GarmentEntity entity = garmentRepository.findById(id).orElse(null);

        if (entity != null) {
            if (request.getDescription() != null) {
                entity.setDescription(request.getDescription());
            }
            if (request.getSize() != null) {
                entity.setSize(request.getSize());
            }
            if (request.getType() != null) {
                entity.setType(request.getType());
            }
            if (request.getPrice() != null) {
                entity.setPrice(request.getPrice());
            }
            garmentRepository.save(entity);
            return ResponseEntity.ok("Garment updated successfully");
        }
        return ResponseEntity.notFound().build();
    }


    @Transactional(
            value = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<?> deleteGarment(Integer id, String username) {
        Optional<GarmentEntity> garmentEntity = garmentRepository.findByIdAndPublisherUsername(id, username);
        if (garmentEntity.isPresent()) {
            garmentRepository.delete(garmentEntity.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

