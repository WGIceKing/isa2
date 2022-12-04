package com.isa.lab1.bevarage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;

import com.isa.lab1.brand.Brand;
import com.isa.lab1.brand.BrandService;
import com.isa.lab1.dto.*;

@RestController
@RequestMapping("api/brands/{id_brand}/bevarages")
public class BrandBevarageController {
    private BevarageService bevarageservice;
    private BrandService brandservice;

    @Autowired
    public BrandBevarageController(BevarageService bevarageservice, BrandService brandservice) {
        this.bevarageservice = bevarageservice;
        this.brandservice = brandservice;
    }

    @GetMapping
    public ResponseEntity<getbevaragesresponse> getbevarages(@PathVariable("id_brand") Long id) {
        Optional<Brand> brand = brandservice.find(id);
        return brand.map(value -> ResponseEntity.ok(getbevaragesresponse.entityToDtoMapper().apply(bevarageservice.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<getbevarageresponse> getbevarage(@PathVariable("id_brand") Long id_brand, @PathVariable("id") Long id) {
        return bevarageservice.find(id_brand, id)
                .map(value -> ResponseEntity.ok(getbevarageresponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postBevarage(@PathVariable("id_brand") Long id_brand, @RequestBody postbevaragerequest request, UriComponentsBuilder builder){
        Optional<Brand> brand = brandservice.find(id_brand);
        if (brand.isPresent()) {
            Bevarage bevarage = postbevaragerequest
                    .dtoToEntityMapper(brand::get)
                    .apply(request);
                    bevarage = bevarageservice.create(bevarage);
            return ResponseEntity.created(builder.pathSegment("api", "brands", "{id_brand}", "bevarages", "{id}")
                    .buildAndExpand(brand.get().getid(), bevarage.getid()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletebevarage(@PathVariable("id_brand") Long id_brand, @PathVariable("id") Long id) {
        Optional<Bevarage> bevarage = bevarageservice.find(id_brand, id);
        if (bevarage.isPresent()) {
            bevarageservice.delete(bevarage.get().getid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putbevarage(@PathVariable("id_brand") Long id_brand, @RequestBody putbevaragerequest request, @PathVariable("id") Long id) {
        Optional<Bevarage> bevarage = bevarageservice.find(id_brand, id);
        if (bevarage.isPresent()) {
            putbevaragerequest.dtoToEntityUpdater().apply(bevarage.get(), request);
            bevarageservice.update(bevarage.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}