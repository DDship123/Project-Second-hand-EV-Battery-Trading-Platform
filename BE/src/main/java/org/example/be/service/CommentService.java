package org.example.be.service;

import org.example.be.entity.Comment;
import org.example.be.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Comment> findAllCommentByPostId(Integer postId) {
        return commentRepository.findAllByPost_PostsId(postId);
    }
}
