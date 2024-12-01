package com.webz.Second.Hand.Shop;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.webz.Second.Hand.Shop.domain.Garment;
import com.webz.Second.Hand.Shop.model.GarmentEntity;
import com.webz.Second.Hand.Shop.repositories.GarmentRepository;
import com.webz.Second.Hand.Shop.services.GarmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

public class GarmentServiceUpdateTest {

    @Mock
    private GarmentRepository garmentRepository;

    @InjectMocks
    private GarmentService garmentService;

    public GarmentServiceUpdateTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateGarment_WhenGarmentExists_ShouldUpdateAndReturnSuccessMessage() {
        Integer id = 1;
        Garment request = new Garment();
        request.setDescription("New description");
        request.setSize("M");
        request.setType("Shirt");
        request.setPrice(BigDecimal.valueOf(49.99));

        GarmentEntity entity = new GarmentEntity();
        entity.setId(id);
        entity.setDescription("Old description");
        entity.setSize("L");
        entity.setType("Pants");
        entity.setPrice(BigDecimal.valueOf(39.99));

        when(garmentRepository.findById(id)).thenReturn(Optional.of(entity));
        when(garmentRepository.save(any(GarmentEntity.class))).thenReturn(entity);
        ResponseEntity<?> response = garmentService.updateGarment(id, request);

        verify(garmentRepository, times(1)).save(entity);
        assertThat(entity.getDescription()).isEqualTo(request.getDescription());
        assertThat(entity.getSize()).isEqualTo(request.getSize());
        assertThat(entity.getType()).isEqualTo(request.getType());
        assertThat(entity.getPrice()).isEqualTo(request.getPrice());
        assertThat(response.getBody()).isEqualTo("Garment updated successfully");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void updateGarment_WhenGarmentDoesNotExist_ShouldReturnNotFound() {
        Integer id = 1;
        Garment request = new Garment();
        request.setDescription("New description");

        when(garmentRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity<?> response = garmentService.updateGarment(id, request);

        verify(garmentRepository, never()).save(any());
        assertThat(response).isEqualTo(ResponseEntity.notFound().build());
    }

    @Test
    public void updateGarment_WhenGarmentExists_ShouldUpdatePartialFields() {
        Integer id = 1;
        Garment request = new Garment();
        request.setDescription("New description");

        GarmentEntity entity = new GarmentEntity();
        entity.setId(id);
        entity.setDescription("Old description");
        entity.setSize("L");
        entity.setType("Pants");
        entity.setPrice(BigDecimal.valueOf(39.99));

        when(garmentRepository.findById(id)).thenReturn(Optional.of(entity));
        when(garmentRepository.save(any(GarmentEntity.class))).thenReturn(entity);

        ResponseEntity<?> response = garmentService.updateGarment(id, request);
        verify(garmentRepository, times(1)).save(entity);
        assertThat(entity.getDescription()).isEqualTo(request.getDescription());
        assertThat(entity.getSize()).isEqualTo("L");
        assertThat(entity.getType()).isEqualTo("Pants");
        assertThat(entity.getPrice()).isEqualTo(BigDecimal.valueOf(39.99));
        assertThat(response.getBody()).isEqualTo("Garment updated successfully");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
