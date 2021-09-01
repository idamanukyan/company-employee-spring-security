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
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class TopicController {
    private final TopicService topicService;
    private final CommentService commentService;


    @GetMapping("/topics")
    public String getTopics(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Topic> topics = topicService.findALlByEmployeeId(currentUser.getEmployee().getId());
        modelMap.addAttribute("topics", topics);
        return "topics";
    }

    @GetMapping("/addTopic")
    public String addTopic() {
        return "addTopic";
    }

    @PostMapping("/addTopic")
    public String addTopic(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute Topic topic) {
        topic.setCreatedDate(new Date());
        topic.setEmployee(currentUser.getEmployee());
        topicService.save(topic);
        return "redirect:/topics";

    }

    @GetMapping("/topic/{id}")
    @Transactional
    public String oneTopic(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Topic> topic = topicService.findTopicById(id);
        List<Comment> comments = commentService.findAllCommentsByTopicId(id);
        modelMap.addAttribute("topic", topic);
        modelMap.addAttribute("comments", comments);
        return "topic";
    }

    @GetMapping("/deleteTopic0")
    public String deleteTopic(@RequestParam("id") int id) {
        topicService.deleteTopicById(id);
        return "redirect:/employees";
    }
}
