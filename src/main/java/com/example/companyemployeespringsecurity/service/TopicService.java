package com.example.companyemployeespringsecurity.service;

import com.example.companyemployeespringsecurity.model.Topic;
import com.example.companyemployeespringsecurity.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TopicService {

    private TopicRepository topicRepository;

    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    public List<Topic> findALlByEmployeeId(int id) {
        return topicRepository.findTopicByEmployeeId(id);
    }

    public Optional<Topic> findTopicById(int id) {
        return topicRepository.findById(id);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public void deleteTopicById(int id) {
        topicRepository.deleteTopicById(id);
    }

}
