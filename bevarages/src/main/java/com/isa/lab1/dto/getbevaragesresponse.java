package com.isa.lab1.dto;

import com.isa.lab1.bevarage.Bevarage;
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
public class getbevaragesresponse{
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class BevarageEntry{
        private Long id;
        private String name;
    }
    @Singular
    private List<BevarageEntry> bevarages;

    public static Function<Collection<Bevarage>, getbevaragesresponse> entityToDtoMapper(){
        return bevarages -> {
            getbevaragesresponseBuilder resp = getbevaragesresponse.builder();
            bevarages.stream()
                    .map(bevarage -> BevarageEntry.builder()
                            .id(bevarage.getid())
                            .name(bevarage.getname())
                            .build())
                    .forEach(resp::bevarage);
            return resp.build();
        };
    }
}
