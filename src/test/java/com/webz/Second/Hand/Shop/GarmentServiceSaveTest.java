package com.webz.Second.Hand.Shop;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.webz.Second.Hand.Shop.domain.Garment;
import com.webz.Second.Hand.Shop.model.GarmentEntity;
import com.webz.Second.Hand.Shop.repositories.GarmentRepository;
import com.webz.Second.Hand.Shop.services.GarmentService;
import com.webz.Second.Hand.Shop.services.mapper.GarmentMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class GarmentServiceSaveTest {

    @Mock
    private GarmentRepository garmentRepository;

    @Mock
    private GarmentMapper garmentMapper;

    @InjectMocks
    private GarmentService garmentService;

    public GarmentServiceSaveTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_ValidRequest_ShouldSaveGarmentSuccessfully() {

        Garment request = new Garment();
        request.setDescription("Test Garment");
        request.setSize("M");
        request.setType("Shirt");
        request.setPrice(BigDecimal.valueOf(19.99));

        String user = "testUser";

        GarmentEntity entity = new GarmentEntity();
        entity.setDescription("Test Garment");
        entity.setSize("M");
        entity.setType("Shirt");
        entity.setPrice(BigDecimal.valueOf(19.99));
        entity.setPublisherUsername(user);

        when(garmentMapper.toSingleEntity(request, user)).thenReturn(entity);
        when(garmentRepository.save(entity)).thenReturn(entity);
        ResponseEntity<String> result = garmentService.save(request, user);
        verify(garmentMapper, times(1)).toSingleEntity(request, user);
        verify(garmentRepository, times(1)).save(entity);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("Garment saved successfully");
    }

    @Test
    public void save_WhenExceptionOccurs_ShouldReturnInternalServerError() {
        Garment request = new Garment();
        request.setDescription("Test Garment");
        request.setSize("M");
        request.setType("Shirt");
        request.setPrice(BigDecimal.valueOf(19.99));

        String user = "testUser";

        when(garmentMapper.toSingleEntity(request, user)).thenThrow(new RuntimeException("Database error"));
        ResponseEntity<String> result = garmentService.save(request, user);
        verify(garmentMapper, times(1)).toSingleEntity(request, user);
        verify(garmentRepository, never()).save(any());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getBody()).isEqualTo("Database error");
    }
}
