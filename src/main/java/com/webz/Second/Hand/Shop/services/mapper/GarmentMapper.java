package com.webz.Second.Hand.Shop.services.mapper;

import com.webz.Second.Hand.Shop.domain.Garment;
import com.webz.Second.Hand.Shop.domain.GarmentSearchResponse;
import com.webz.Second.Hand.Shop.model.GarmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GarmentMapper {

    public Garment toGarment(GarmentEntity entity) {
        return entity != null ?
                Garment
                        .builder()
                        .id(entity.getId())
                        .type(entity.getType())
                        .size(entity.getSize())
                        .description(entity.getDescription())
                        .price(entity.getPrice())
                        .publishedBy(entity.getPublisherUsername())
                        .build()
                : null;
    }

    public GarmentSearchResponse toProductSearchResponse(Page<GarmentEntity> page) {
        GarmentSearchResponse response = new GarmentSearchResponse();

        response.setResults(
                page.getContent().stream()
                        .map(this::toGarment)
                        .collect(Collectors.toList()));

        response.setPageIndex(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());

        return response;
    }


    public GarmentEntity toSingleEntity(Garment garment, String user) {
        return GarmentEntity
                .builder()
                .publisherUsername(user)
                .type(garment.getType())
                .size(garment.getSize())
                .description(garment.getDescription())
                .price(garment.getPrice())
                .build();
    }
}
