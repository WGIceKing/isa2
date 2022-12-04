package com.isa.lab1.bevarage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import com.isa.lab1.brand.Brand;
import com.isa.lab1.brand.BrandRepo;

@Service
public class BevarageService {
    
    private BevarageRepo repository;
    private BrandRepo brandrepository;

    @Autowired
    public BevarageService(BevarageRepo repo, BrandRepo brandrepository){
        this.repository = repo;
        this.brandrepository = brandrepository;
    }

    public Optional<Bevarage> find(Long id){
        return repository.findById(id);
    }

    public Optional<Bevarage> find(Long brand_id, Long id){
        Optional<Brand> brand = brandrepository.findById(brand_id);
        if(brand.isPresent()){
            return repository.findByIdAndBrand(id, brand.get());
        }else{
            return Optional.empty();
        }
    }

    public List<Bevarage> findAll(){
        return repository.findAll();
    }

    public List<Bevarage> findAll(Brand brand){
        return repository.findAllByBrand(brand);
    }

    @Transactional
    public void update(Bevarage bevarage){
        repository.save(bevarage);
    }

    @Transactional
    public Bevarage create(Bevarage bevarage){
        return repository.save(bevarage);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
