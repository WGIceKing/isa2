package com.isa.lab1.dto;

import com.isa.lab1.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class putbrandrequest{
    private String name;
    private String country;
    private int year;

    public static BiFunction<Brand, putbrandrequest, Brand> dtoToEntityUpdater(){
        return (brand, request) -> {
            brand.setname(request.getName());
            brand.setyear(request.getYear());
            brand.setcountry(request.getCountry());
            return brand;
        };
    }
}
