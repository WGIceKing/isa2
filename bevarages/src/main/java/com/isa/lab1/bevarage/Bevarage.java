package com.isa.lab1.bevarage;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.isa.lab1.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "bevarages")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Bevarage  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brands")
    private Brand brand;
    private int popularity;
    private String name;

    public Long getid(){
        return id;
    }

    public Brand getbrand(){
        return brand;
    }

    public int getpopularity(){
        return popularity;
    }
    
    public String getname(){
        return name;
    }

    public void setbrand(Brand brand){
        this.brand = brand;
    }

    public void setid(Long id){
        this.id = id;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setpopularity(int popularity){
        this.popularity = popularity;
    }

    @Override
    public String toString(){
        return "Bevarage { " + "id: " + id + ", name: " + name + ", " + brand + ", popularity: " + popularity + " }";
    }
}   
