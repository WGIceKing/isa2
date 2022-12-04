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

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getbrandresponse{
    private Long id;
    private int estyear;
    private String name;
    private String country;

    public static Function<Brand, getbrandresponse> entityToDtoMapper(){
        return brand -> getbrandresponse.builder()
                .id(brand.getid())
                .name(brand.getname())
                .estyear(brand.getyear())
                .country(brand.getcountry())
                .build();
    }
}
