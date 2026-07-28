package com.lld.BookMyShow.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseModel{
    private String name;
    private String description;
    private Long runningTime;
    private Date releaseDate;
    private Double rating;
    private List<Feature> features;
}
