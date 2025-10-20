package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home/admin")
public class AdminPageController {
    @Autowired
    private PostService postService;;

    @GetMapping(value = {"", "/post-manage/{status}"})
    public String dashboard(Model model, HttpSession session, @PathVariable(required = false) String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        if (status == null || status.isEmpty()) {
            status = "PENDING";
        }
        ApiResponse<List<PostResponse>> response = postService.getAllPostByStatus(status);
        model.addAttribute("posts", response.getPayload());
        model.addAttribute("admin", member);
        return "postManage";
    }
    @GetMapping("/post-manage/{status}?postId={postId}")
    public String postDetail(Model model, HttpSession session, @RequestParam(name = "postId") int postId, @PathVariable String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        ApiResponse<List<PostResponse>> response = postService.getAllPostByStatus(status);
        model.addAttribute("posts", response.getPayload());
        model.addAttribute("admin", member);
        return "postManage";
    }

    @GetMapping("/member-manage")
    public String memberManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "userManage";
    }

    @GetMapping("/comment-review-manage")
    public String commentManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "commentManage";
    }

    @GetMapping("/transaction-manage")
    public String transactionManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "transactionManage";
    }
    @GetMapping("/fee-manage")
    public String feeManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "feeManage";
    }
}
