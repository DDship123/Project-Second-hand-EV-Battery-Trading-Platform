package org.example.fe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPackageResponse {
    private int planId;
    private String name;
    private double price;
    private int duration;
    private int maxPosts;
    private String priority;

}
