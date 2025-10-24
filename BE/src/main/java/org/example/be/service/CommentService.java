package org.example.be.service;

import org.example.be.dto.response.CommentResponse;
import org.example.be.dto.response.MemberResponse;
import org.example.be.dto.response.PostResponse;
import org.example.be.entity.Comment;
import org.example.be.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }

    public Comment updateComment(Integer id, Comment commentDetails) {
        Comment comment = getCommentById(id);
        if (comment == null) {
            return null;
        }
        comment.setCommentId(commentDetails.getCommentId());
        comment.setMember(commentDetails.getMember());
        comment.setPost(commentDetails.getPost());
        comment.setStatus(commentDetails.getStatus());
        comment.setCreatedAt(commentDetails.getCreatedAt());
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    public List<CommentResponse> findAllCommentByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findAllByPost_PostsId(postId);
        if (comments != null) {
            return comments.stream().map(c -> new CommentResponse(
                    c.getCommentId(),
                    null,
                    new MemberResponse(
                            c.getMember().getMemberId(),
                            c.getMember().getUsername(),
                            c.getMember().getAvatarUrl(),
                            c.getMember().getCreatedAt()
                    ),
                    c.getRating(),
                    c.getComment(),
                    c.getStatus(),
                    c.getCreatedAt()
            )).toList();
        }
        return null;
    }
    public List<CommentResponse> findAllCommentByStatus(String status) {
        List<Comment> comments = commentRepository.findAllByStatus(status);
        List<CommentResponse> commentResponses = new ArrayList<>();
        if (comments != null) {
            for (Comment c : comments) {
                CommentResponse commentResponse = new CommentResponse();
                commentResponse.setCommentId(c.getCommentId());
                commentResponse.setComment(c.getComment());
                commentResponse.setRating(c.getRating());
                commentResponse.setStatus(c.getStatus());
                commentResponse.setCreatedAt(c.getCreatedAt());
                MemberResponse memberResponse = new MemberResponse();
                memberResponse.setMemberId(c.getMember().getMemberId());
                memberResponse.setUsername(c.getMember().getUsername());
                commentResponse.setMember(memberResponse);
                PostResponse postResponse = new PostResponse();
                postResponse.setPostsId(c.getPost().getPostsId());
                commentResponse.setPost(postResponse);
                commentResponses.add(commentResponse);
            }
        }
        return commentResponses;
    }
}
