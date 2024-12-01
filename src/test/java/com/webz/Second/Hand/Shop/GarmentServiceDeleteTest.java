package com.webz.Second.Hand.Shop;

import com.webz.Second.Hand.Shop.model.GarmentEntity;
import com.webz.Second.Hand.Shop.repositories.GarmentRepository;
import com.webz.Second.Hand.Shop.services.GarmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GarmentServiceDeleteTest {

    @Mock
    private GarmentRepository garmentRepository;

    @InjectMocks
    private GarmentService garmentService;

    public GarmentServiceDeleteTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteGarment_WhenGarmentExists_ShouldDeleteAndReturnNoContent() {

        Integer id = 1;
        String username = "reddy";
        GarmentEntity garmentEntity = new GarmentEntity();
        garmentEntity.setId(id);
        garmentEntity.setPublisherUsername(username);

        when(garmentRepository.findByIdAndPublisherUsername(id, username))
                .thenReturn(Optional.of(garmentEntity));

        ResponseEntity<?> response = garmentService.deleteGarment(id, username);

        verify(garmentRepository, times(1)).delete(garmentEntity);
        assertThat(response).isEqualTo(ResponseEntity.noContent().build());
    }

    @Test
    public void deleteGarment_WhenGarmentDoesNotExist_ShouldReturnNotFound() {
        Integer id = 1;
        String username = "user1";

        when(garmentRepository.findByIdAndPublisherUsername(id, username))
                .thenReturn(Optional.empty());

        ResponseEntity<?> response = garmentService.deleteGarment(id, username);


        verify(garmentRepository, never()).delete(any());
        assertThat(response).isEqualTo(ResponseEntity.notFound().build());
    }
}

