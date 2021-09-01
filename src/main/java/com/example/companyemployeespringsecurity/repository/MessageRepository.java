package com.example.companyemployeespringsecurity.repository;

import com.example.companyemployeespringsecurity.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllBySentUserId(int id);

    List<Message> findAllBySenderId(int id);
}
