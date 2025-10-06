package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Member;
import org.example.be.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemeberController {

    @Autowired
    private MemberService memberService;

    // ------------------- CREATE -------------------
    @PostMapping
    public ResponseEntity<ApiResponse<Member>> createMember(@RequestBody Member member) {
        ApiResponse<Member> response = new ApiResponse<>();
        try {
            Member created = memberService.createMember(member);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("timestamp", LocalDateTime.now());

            response.ok(created, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ------------------- GET ALL -------------------
    @GetMapping
    public ResponseEntity<ApiResponse<List<Member>>> getAllMembers() {
        ApiResponse<List<Member>> response = new ApiResponse<>();
        try {
            List<Member> members = memberService.getAllMembers();

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("count", members.size());
            metadata.put("timestamp", LocalDateTime.now());

            response.ok(members, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ------------------- GET BY ID -------------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Member>> getMemberById(@PathVariable Integer id) {
        ApiResponse<Member> response = new ApiResponse<>();
        try {
            Member member = memberService.getMemberById(id);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("timestamp", LocalDateTime.now());

            response.ok(member, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }
    // ------------------- UPDATE -------------------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Member>> updateMember(@PathVariable Integer id, @RequestBody Member member) {
<<<<<<< HEAD
        Member updatedMember = memberService.updateMember(id, member);
        ApiResponse<Member> response = new ApiResponse<>();
        response.ok(updatedMember);
        return ResponseEntity.ok(response);
=======
        ApiResponse<Member> response = new ApiResponse<>();
        try {
            Member updated = memberService.updateMember(id, member);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("updatedAt", LocalDateTime.now());

            response.ok(updated, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
>>>>>>> bd93a44bc1c61ab434c63e51828eba5e277be5a3
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteMember(@PathVariable Integer id) {
        ApiResponse<Boolean> response = new ApiResponse<>();
        try {
            memberService.deleteMember(id);

            response.ok();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
