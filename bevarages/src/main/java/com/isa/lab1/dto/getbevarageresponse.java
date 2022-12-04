package com.isa.lab1.dto;

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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class getbevarageresponse{
    
    private Long id;
    private String brand;
    private int popularity;
    private String name;

    public static Function<Bevarage, getbevarageresponse> entityToDtoMapper(){
        return bevarage -> getbevarageresponse.builder()
                .id(bevarage.getid())
                .brand(bevarage.getbrand().getname())
                .name(bevarage.getname())
                .popularity(bevarage.getpopularity())
                .build();
    }
}
