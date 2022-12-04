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
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class putbevaragerequest{
    private String name;
    private int popularity;

    public static BiFunction<Bevarage, putbevaragerequest, Bevarage> dtoToEntityUpdater(){
        return (bevarage, request) -> {
            bevarage.setname(request.getName());
            bevarage.setpopularity(request.getPopularity());
            return bevarage;
        };
    }
}
