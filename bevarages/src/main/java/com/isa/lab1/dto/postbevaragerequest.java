package com.isa.lab1.dto;

import com.isa.lab1.brand.Brand;
import com.isa.lab1.bevarage.Bevarage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class postbevaragerequest{
    private Long brand;
    private int popularity;
    private String name;

    public static Function<postbevaragerequest, Bevarage> dtoToEntityMapper(
        Supplier<Brand> BrandSupplier) {
    return request -> Bevarage.builder()
            .brand(BrandSupplier.get())
            .name(request.getName())
            .popularity(request.getPopularity())
            .build();  
    }
}
