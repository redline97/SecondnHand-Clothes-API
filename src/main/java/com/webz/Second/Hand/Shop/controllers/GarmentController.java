package com.webz.Second.Hand.Shop.controllers;


import com.webz.Second.Hand.Shop.domain.Garment;
import com.webz.Second.Hand.Shop.domain.GarmentSearchResponse;
import com.webz.Second.Hand.Shop.services.GarmentService;
import com.webz.Second.Hand.Shop.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/clothes")
public class GarmentController {

    private final GarmentService garmentService;
    private final JwtUtil jwtTokenUtil;

    public GarmentController(GarmentService garmentService, JwtUtil jwtTokenUtil) {
        this.garmentService = garmentService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/list/{pageIndex}/{pageSize}")
    public ResponseEntity<GarmentSearchResponse> searchGarment(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "size", required = false) String size,
            @RequestParam(value = "description", required = false) String description,
            @PathVariable("pageIndex") Integer pageIndex,
            @PathVariable("pageSize") Integer pageSize
    ) {
        return garmentService.searchGarments(type, size, description, pageIndex, pageSize);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createGarment(
            HttpServletRequest request,
            @RequestBody Garment garment) {
        String username = jwtTokenUtil.extractUsername(request.getHeader("Authorization"));
        return garmentService.save(garment, username);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGarment(@PathVariable Integer id,
                                           @RequestBody Garment garmentDetails) {
        return garmentService.updateGarment(id, garmentDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGarment(
            HttpServletRequest request,
            @PathVariable Integer id) {
        String username = jwtTokenUtil.extractUsername(request.getHeader("Authorization"));
        return garmentService.deleteGarment(id, username);
    }
}
