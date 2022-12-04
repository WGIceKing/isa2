package com.isa.lab1.bevarage;

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

import com.isa.lab1.brand.BrandService;
import com.isa.lab1.dto.*;

@RestController
@RequestMapping("api/bevarages")
public class BevarageController{
    private BrandService brandservice;
    private BevarageService bevarageservice;

    @Autowired
    public BevarageController(BevarageService bevarageservice, BrandService brandservice){
        this.bevarageservice = bevarageservice;
        this.brandservice = brandservice;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<getbevaragesresponse> getBevarages(){
        List<Bevarage> all = bevarageservice.findAll();
        Function<Collection<Bevarage>, getbevaragesresponse> mapper = getbevaragesresponse.entityToDtoMapper();
        getbevaragesresponse resp = mapper.apply(all);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{id}")
    public ResponseEntity<getbevarageresponse> getBevarage(@PathVariable("id") Long id){
        return  bevarageservice.find(id)
                .map(val -> ResponseEntity.ok(getbevarageresponse.entityToDtoMapper().apply(val)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postBevarage(@RequestBody postbevaragerequest request, UriComponentsBuilder builder){
        Bevarage bevarage = postbevaragerequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
                bevarage = bevarageservice.create(bevarage);
        return ResponseEntity.created(builder.pathSegment("api", "bevarages", "{id}")
                .buildAndExpand(bevarage.getid()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putBevarage(@RequestBody putbevaragerequest request, @PathVariable("id") Long id){
        Optional<Bevarage> bevarage = bevarageservice.find(id);
        if (bevarage.isPresent()) {
            putbevaragerequest.dtoToEntityUpdater().apply(bevarage.get(), request);
            bevarageservice.update(bevarage.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBevarage(@PathVariable("id") Long id){
        Optional<Bevarage> bevarage = bevarageservice.find(id);
        if (bevarage.isPresent()) {
            bevarageservice.delete(bevarage.get().getid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
