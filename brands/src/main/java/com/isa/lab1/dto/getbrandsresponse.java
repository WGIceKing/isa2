package com.isa.lab1.dto;

import com.isa.lab1.brand.Brand;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getbrandsresponse{
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class BrandEntry{
        private String name;
        private Long id;
    }

    @Singular
    private List<BrandEntry> brands;

    public static Function<Collection<Brand>, getbrandsresponse> entityToDtoMapper(){
        return brands -> {
            getbrandsresponseBuilder response = getbrandsresponse.builder();
            brands.stream()
                    .map(brand -> BrandEntry.builder()
                            .id(brand.getid())
                            .name(brand.getname())
                            .build())
                    .forEach(response::brand);
            return response.build();
        };
    }
}
