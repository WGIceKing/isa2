package com.isa.lab1.bevarage;
import com.isa.lab1.brand.Brand;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BevarageRepo  extends JpaRepository<Bevarage, Long> {
    Optional<Bevarage> findByIdAndBrand(Long id, Brand brand);
    List<Bevarage> findAllByBrand(Brand brand);
}
