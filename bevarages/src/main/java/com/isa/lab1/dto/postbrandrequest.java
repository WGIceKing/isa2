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
public class postbrandrequest{

    private Long id;

    public static Function<postbrandrequest, Brand> dtoToEntityMapper() {
    return request -> Brand.builder()
            .id(request.getId())
            .build();  
    }
}
