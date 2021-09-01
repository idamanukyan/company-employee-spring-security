package com.example.companyemployeespringsecurity.controller;

import com.example.companyemployeespringsecurity.model.Comment;
import com.example.companyemployeespringsecurity.model.Topic;
import com.example.companyemployeespringsecurity.security.CurrentUser;
import com.example.companyemployeespringsecurity.service.CommentService;
import com.example.companyemployeespringsecurity.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class CommentController {

    private final TopicService topicService;
    private final CommentService commentService;

    @GetMapping("/comments")
    public String comments(ModelMap modelMap, @RequestParam("id") int id) {
        Optional<Topic> topic = topicService.findTopicById(id);
        List<Comment> comments = commentService.findAllCommentsByTopicId(id);
        modelMap.addAttribute("topic", topic.get());
        modelMap.addAttribute("comments", comments);
        return "comments";
    }

    @GetMapping("/addComment")
    public String addComment(ModelMap modelMap, @RequestParam("id") int id) {
        Optional<Topic> topic = topicService.findTopicById(id);
        if (topic.isPresent()) {
            modelMap.addAttribute("topic", topic.get());
            return "addComment";
        }
        return "redirect:/topics";
    }

    @PostMapping("/addComment")
    public String addComment(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute Comment comment, @RequestParam("id") int id) {
        Optional<Topic> topic = topicService.findTopicById(id);
        if (topic.isPresent()) {
            comment.setTopic(topic.get());
            comment.setCreatedDate(new Date());
            comment.setEmployee(currentUser.getEmployee());
            commentService.save(comment);
            return "redirect:/comments?id=" + id;
        }
        return "redirect:/topics";
    }
}
