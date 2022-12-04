package com.isa.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import com.isa.lab1.brand.*;
import com.isa.lab1.bevarage.*;

@Component
public class Datainitialize {

    private final BevarageService bevarageservice;
    private final BrandService brandservice;

    @Autowired
    public Datainitialize(BevarageService bevarageservice, BrandService brandservice){
        this.bevarageservice = bevarageservice;
        this.brandservice = brandservice;
    }

    @PostConstruct
    public synchronized void Initialize(){

        Brand brand = Brand.builder()
            .id(1L)
            .build();

        brandservice.create(brand);

        Bevarage bevarage = Bevarage.builder()
            .brand(brand)
            .name("Fanta")
            .popularity(9)
            .build();

        bevarageservice.create(bevarage);
        bevarageservice.findAll().forEach(System.out::println);
    }
}
