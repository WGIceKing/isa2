package com.isa.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import com.isa.lab1.brand.Brand;
import com.isa.lab1.brand.BrandService;

@Component
public class Datainitialize {

    private final BrandService brandservice;

    @Autowired
    public Datainitialize (BrandService brandservice){
        this.brandservice = brandservice;
    }

    @PostConstruct
    public synchronized void Initialize(){

        Brand brand = Brand.builder()
            .id(1L)
            .name("CocaCola")
            .estyear(1949)
            .country("USA")
            .build();

        brandservice.create(brand);
    } 
}
