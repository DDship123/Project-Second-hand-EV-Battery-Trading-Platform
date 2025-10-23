package org.example.be.repository;

import org.example.be.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhone(String phone);
    Optional<Member> findByUsernameAndPassword(String username, String password);
    List<Member> getMembersByStatus(String status);
    Optional<Member> findByUsername(String username);

    List<Member> findAllByRoleAndStatus(String role, String status);//Tân

    @Query("SELECT m FROM Member m WHERE m.role = :role")
    List<Member> findAllByRole(@Param("role") String role);//Tân
}
