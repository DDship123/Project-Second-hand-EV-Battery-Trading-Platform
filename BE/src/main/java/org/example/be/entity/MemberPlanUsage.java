package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_plan_usage")
@Data
@Getter
@Setter
public class MemberPlanUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usage_id")
    private Integer usageId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "plan_id")
    private Integer planId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "used_posts")
    private Integer usedPosts;

    private String status;
}