package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "favorites")
@Entity
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_id")
    private int favoritesId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // FK

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;   // FK

}
