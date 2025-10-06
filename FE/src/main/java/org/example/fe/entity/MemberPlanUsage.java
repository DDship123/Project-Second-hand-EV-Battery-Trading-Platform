package org.example.fe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPlanUsage {
    private int usageId;
    private int memberId;
    private int planId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int usedPosts;
    private String status;

}
