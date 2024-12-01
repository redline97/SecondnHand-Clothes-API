package com.webz.Second.Hand.Shop;

import com.webz.Second.Hand.Shop.domain.GarmentSearchResponse;
import com.webz.Second.Hand.Shop.model.GarmentEntity;
import com.webz.Second.Hand.Shop.repositories.GarmentRepository;
import com.webz.Second.Hand.Shop.services.GarmentService;
import com.webz.Second.Hand.Shop.services.mapper.GarmentMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GarmentServiceSearchTest {

    @Mock
    private GarmentRepository garmentRepository;

    @Mock
    private GarmentMapper garmentMapper;

    @InjectMocks
    private GarmentService garmentService;

    public GarmentServiceSearchTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void searchGarments_WithMatchingCriteria_ShouldReturnResults() {
        String type = "Shirt";
        String size = "M";
        String description = "Cotton";
        int pageIndex = 0;
        int pageSize = 2;

        GarmentEntity garment1 = new GarmentEntity();
        garment1.setId(1);
        garment1.setType("Shirt");
        garment1.setSize("M");
        garment1.setDescription("Cotton shirt");

        GarmentEntity garment2 = new GarmentEntity();
        garment2.setId(2);
        garment2.setType("Shirt");
        garment2.setSize("M");
        garment2.setDescription("Premium cotton shirt");

        Page<GarmentEntity> garmentPage = new PageImpl<>(Arrays.asList(garment1, garment2), PageRequest.of(pageIndex, pageSize), 2);
        GarmentSearchResponse response = new GarmentSearchResponse();
        when(garmentRepository.findAll((Specification<GarmentEntity>) any(), eq(PageRequest.of(pageIndex, pageSize)))).thenReturn(garmentPage);
        when(garmentMapper.toProductSearchResponse(garmentPage)).thenReturn(response);

        ResponseEntity<GarmentSearchResponse> result = garmentService.searchGarments(type, size, description, pageIndex, pageSize);
        verify(garmentRepository, times(1)).findAll((Specification<GarmentEntity>) any(), eq(PageRequest.of(pageIndex, pageSize)));
        verify(garmentMapper, times(1)).toProductSearchResponse(garmentPage);
        assertThat(result.getBody()).isEqualTo(response);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void searchGarments_WithNoMatchingCriteria_ShouldReturnEmptyResults() {
        String type = "Pants";
        String size = "L";
        String description = "Denim";
        int pageIndex = 0;
        int pageSize = 2;

        Page<GarmentEntity> emptyPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(pageIndex, pageSize), 0);
        GarmentSearchResponse emptyResponse = new GarmentSearchResponse();

        when(garmentRepository.findAll((Specification<GarmentEntity>) any(), eq(PageRequest.of(pageIndex, pageSize)))).thenReturn(emptyPage);
        when(garmentMapper.toProductSearchResponse(emptyPage)).thenReturn(emptyResponse);

        ResponseEntity<GarmentSearchResponse> result = garmentService.searchGarments(type, size, description, pageIndex, pageSize);
        verify(garmentRepository, times(1)).findAll((Specification<GarmentEntity>) any(), eq(PageRequest.of(pageIndex, pageSize)));
        verify(garmentMapper, times(1)).toProductSearchResponse(emptyPage);
        assertThat(result.getBody()).isEqualTo(emptyResponse);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void searchGarments_WithNullCriteria_ShouldReturnAllResults() {

        String type = null;
        String size = null;
        String description = null;
        int pageIndex = 0;
        int pageSize = 2;

        GarmentEntity garment1 = new GarmentEntity();
        garment1.setId(1);
        garment1.setType("Shirt");
        garment1.setSize("M");
        garment1.setDescription("Cotton shirt");

        GarmentEntity garment2 = new GarmentEntity();
        garment2.setId(2);
        garment2.setType("Pants");
        garment2.setSize("L");
        garment2.setDescription("Denim pants");

        Page<GarmentEntity> garmentPage = new PageImpl<>(Arrays.asList(garment1, garment2), PageRequest.of(pageIndex, pageSize), 2);
        GarmentSearchResponse response = new GarmentSearchResponse();

        when(garmentRepository.findAll((Specification<GarmentEntity>) any(), eq(PageRequest.of(pageIndex, pageSize)))).thenReturn(garmentPage);
        when(garmentMapper.toProductSearchResponse(garmentPage)).thenReturn(response);

        ResponseEntity<GarmentSearchResponse> result = garmentService.searchGarments(type, size, description, pageIndex, pageSize);
        verify(garmentRepository, times(1)).findAll((Specification<GarmentEntity>) any(), eq(PageRequest.of(pageIndex, pageSize)));
        verify(garmentMapper, times(1)).toProductSearchResponse(garmentPage);
        assertThat(result.getBody()).isEqualTo(response);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
