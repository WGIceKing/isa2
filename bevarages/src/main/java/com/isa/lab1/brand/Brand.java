package com.isa.lab1.brand;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.InheritanceType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
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
@Table(name = "brands")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Brand implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private int estyear;
    private String country;

    public Long getid(){
        return id;
    }

    public String getname(){
        return name;
    }

    public String getcountry(){
        return country;
    }

    public int getyear(){
        return estyear;
    }

    public void setcountry(String country){
        this.country = country;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setid(Long id){
        this.id = id;
    }

    public void setyear(int year){
        this.estyear = year;
    }

    @Override
    public String toString(){
        return "Brand { " + "id: "+ id +", name: " + name + ", established: " + estyear + ", country: " + country + " }";
    }
}
