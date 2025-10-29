package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.*;
import org.example.fe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/home/admin")
public class AdminPageController {
    @Autowired
    private PostService postService;;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = {"", "/post-manage/{status}"})
    public String dashboard(Model model, HttpSession session,
                            @PathVariable(required = false) String status,
                            @RequestParam(name = "successMessage", required = false) String successMessage,
                            @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        if (status == null || status.isEmpty()) {
            status = "PENDING";
        }
        ApiResponse<List<PostResponse>> response = postService.getAllPostByStatus(status);
        model.addAttribute("posts", response.getPayload());
        model.addAttribute("admin", member);
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "postManage";
    }
    @GetMapping("/post-manage/detail")
    public String postDetail(Model model, HttpSession session, @RequestParam(name = "postId",defaultValue = "0") int postId) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        ApiResponse<PostResponse> postResponse = postService.getPostDetail(postId);
        PostResponse post = postResponse.getPayload();
        String status = post.getStatus();
        ApiResponse<List<PostResponse>> response = postService.getAllPostByStatus(status);
        model.addAttribute("postDetail", post);
        model.addAttribute("totalPosts", response.getPayload().size());
        model.addAttribute("posts", response.getPayload());
        model.addAttribute("admin", member);
        return "postManage";
    }

    @GetMapping("/post-manage/update-status")
    public String updatePostStatus(Model model, HttpSession session, RedirectAttributes redirectAttributes,
                                   @RequestParam(name = "postId",defaultValue = "0") int postId,
                                   @RequestParam(name = "status") String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        if (postId == 0) {
            return "redirect:/home/admin/post-manage";
        }
        ApiResponse<PostResponse> postResponse = postService.updateStatus(postId, status);
        if (postResponse.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật bài đăng thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật bài đăng thất bại.");
        }
        return "redirect:/home/admin/post-manage/PENDING";
    }

    @GetMapping("/member-manage")
    public String memberManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        List<MemberResponse> users = memberService.getUser().getPayload();
        model.addAttribute("admin", member);
        model.addAttribute("users", users);
        return "userManage";
    }
    @GetMapping("/member-manage/detail/{memberId}")
    public String memberDetail(Model model, HttpSession session, @PathVariable int memberId) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        ApiResponse<MemberResponse> memberResponse = memberService.getMemberInfo(memberId);
        MemberResponse memberDetail = memberResponse.getPayload();
        model.addAttribute("memberDetail", memberDetail);

        ApiResponse<List<ReviewResponse>> reviewResponse = reviewService.FindAllReviewBySellerId(memberId);
        List<ReviewResponse> reviews = reviewResponse.getPayload();
        model.addAttribute("reviews", reviews);

        List<MemberResponse> users = memberService.getUser().getPayload();
        model.addAttribute("users", users);
        model.addAttribute("admin", member);
        return "userManage";
    }


    @GetMapping("/member-manage/update-status")
    public String updateMemberStatus(Model model, HttpSession session, RedirectAttributes redirectAttributes,
                                     @RequestParam(name = "memberId",defaultValue = "0") int memberId,
                                     @RequestParam(name = "status") String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        if (memberId == 0) {
            return "redirect:/home/admin/member-manage";
        }
        MemberResponse memberToUpdate = new MemberResponse();
        memberToUpdate.setMemberId(memberId);
        memberToUpdate.setStatus(status);
        ApiResponse<MemberResponse> memberResponse = memberService.updateStatus(memberToUpdate);
        if (memberResponse.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật thành viên thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật thành viên thất bại.");
        }
        return "redirect:/home/admin/member-manage";
    }

    @GetMapping("/comment-review-manage")
    public String commentManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        ApiResponse<List<CommentResponse>> response = commentService.findAllCommentByStatus("PENDING");
        model.addAttribute("comments", response.getPayload());
        model.addAttribute("admin", member);
        return "commentManage";
    }
    @GetMapping("/comment-manage/update-status")
    public String updateCommentStatus(Model model, HttpSession session, RedirectAttributes redirectAttributes,
                                   @RequestParam(name = "commentId",defaultValue = "0") int commentId,
                                   @RequestParam(name = "status") String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        if (commentId == 0) {
            return "redirect:/home/admin/comment-review-manage";
        }
        ApiResponse<CommentResponse> commentResponse = commentService.updateCommentStatus(commentId, status);
        if (commentResponse.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật bình luận thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật bình luận thất bại.");
        }
        return "redirect:/home/admin/comment-review-manage";
    }

    @GetMapping("/comment-review-manage/review")
    public String reviewComments(Model model, HttpSession session)
                                 {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("admin", member);
        List<ReviewResponse> reviews = reviewService.findAllReviewByStatus("PENDING").getPayload();
        model.addAttribute("reviews", reviews);
        model.addAttribute("admin", member);
        return "commentManage";
    }
    @GetMapping("/comment-review-manage/review/update-status")
    public String updateReviewStatus(Model model, HttpSession session, RedirectAttributes redirectAttributes,
                                     @RequestParam(name = "reviewId",defaultValue = "0") int reviewId,
                                     @RequestParam(name = "status") String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        if (reviewId == 0) {
            return "redirect:/home/admin/comment-review-manage/review";
        }
        ReviewResponse reviewToUpdate = new ReviewResponse();
        reviewToUpdate.setReviewId(reviewId);
        reviewToUpdate.setStatus(status);
        ApiResponse<ReviewResponse> reviewResponse = reviewService.update(reviewToUpdate);
        if (reviewResponse.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật đánh giá thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật đánh giá thất bại.");
        }
        return "redirect:/home/admin/comment-review-manage/review";
    }

    @GetMapping("/transaction-manage")
    public String transactionManage(Model model, HttpSession session,@RequestParam(name = "successMessage", required = false) String successMessage,
                                    @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("admin", member);
        ApiResponse<List<TransactionResponse>> response = transactionService.getAllTransactions();
        model.addAttribute("transactions", response.getPayload());
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "transactionManage";
    }

    @GetMapping("/transaction-manage/update-status")
    public String updateTransactionStatus(Model model, HttpSession session, RedirectAttributes redirectAttributes,
                                   @RequestParam(name = "transactionId",defaultValue = "0") int transactionId,
                                   @RequestParam(name = "status") String status) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (member == null) {
            return "redirect:/login";
        }
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }
        if (transactionId == 0) {
            return "redirect:/home/admin/transaction-manage";
        }
        ApiResponse<TransactionResponse> transactionResponse = transactionService.updateTransactionStatus(transactionId, status);
        if (transactionResponse.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật giao dịch thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật giao dịch thất bại.");
        }
        return "redirect:/home/admin/transaction-manage";
    }

//    @GetMapping("/fee-manage")
//    public String feeManage(Model model, HttpSession session) {
//        MemberResponse member = (MemberResponse) session.getAttribute("user");
//        if (member == null) {
//            return "redirect:/login";
//        }
//        if (!member.getRole().equals("ADMIN")) {
//            return "redirect:/login";
//        }
//        model.addAttribute("admin", member);
//        return "feeManage";
//    }
}
