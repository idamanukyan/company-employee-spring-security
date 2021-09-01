package com.example.companyemployeespringsecurity.repository;

import com.example.companyemployeespringsecurity.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Optional<Topic> findTopicById(int id);

    List<Topic> findTopicByEmployeeId(int id);

    void deleteTopicById(int id);

}
