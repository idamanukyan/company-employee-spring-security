package com.example.companyemployeespringsecurity.service;

import com.example.companyemployeespringsecurity.model.Comment;
import com.example.companyemployeespringsecurity.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findAllCommentsByTopicId(int id) {
        return commentRepository.findAllByTopicId(id);
    }

}
