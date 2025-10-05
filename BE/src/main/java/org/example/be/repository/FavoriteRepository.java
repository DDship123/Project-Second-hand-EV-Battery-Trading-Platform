package org.example.be.repository;

import org.example.be.entity.Favorite;
import org.example.be.entity.Member;
import org.example.be.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query("SELECT f.post FROM Favorite f WHERE f.member = :member ORDER BY f.favoritesId DESC")
    List<Post> findLatestPostsByMember(@Param("member") Member member);
}
