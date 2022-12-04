package com.isa.lab1.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
       
    private BrandRepo repository;

    @Autowired
    public BrandService(BrandRepo repo){
        this.repository = repo;
    }

    public Optional<Brand> find(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Brand create(Brand brand) {
        return repository.save(brand);
    }

    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Brand brand) {
        repository.save(brand);
    }
}
