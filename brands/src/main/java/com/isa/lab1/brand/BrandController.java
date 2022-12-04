package com.isa.lab1.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.isa.lab1.dto.*;

@RestController
@RequestMapping("api/brands")
public class BrandController{
    private BrandService brandservice;

    @Autowired
    public BrandController(BrandService brandservice){
        this.brandservice = brandservice;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<getbrandsresponse> getBrands(){
        List<Brand> all = brandservice.findAll();
        Function<Collection<Brand>, getbrandsresponse> mapper = getbrandsresponse.entityToDtoMapper();
        getbrandsresponse resp = mapper.apply(all);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{id}")
    public ResponseEntity<getbrandresponse> getBrand(@PathVariable("id") Long id){
        return  brandservice.find(id)
                .map(val -> ResponseEntity.ok(getbrandresponse.entityToDtoMapper().apply(val)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postbevarage(@RequestBody postbrandrequest request, UriComponentsBuilder builder){
        Brand brand = postbrandrequest
                .dtoToEntityMapper()
                .apply(request);
                brand = brandservice.create(brand);
        return ResponseEntity.created(builder.pathSegment("api", "brands", "{id}")
                .buildAndExpand(brand.getid()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putBrand(@RequestBody putbrandrequest request, @PathVariable("id") Long id){
        Optional<Brand> brand = brandservice.find(id);
        if (brand.isPresent()) {
            putbrandrequest.dtoToEntityUpdater().apply(brand.get(), request);
            brandservice.update(brand.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("id") Long id){
        Optional<Brand> brand = brandservice.find(id);
        if (brand.isPresent()) {
            brandservice.delete(brand.get().getid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
