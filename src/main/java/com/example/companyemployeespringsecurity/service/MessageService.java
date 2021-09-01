package com.example.companyemployeespringsecurity.service;

import com.example.companyemployeespringsecurity.model.Message;
import com.example.companyemployeespringsecurity.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public List<Message> findAllMessagesBySentUser(int id) {
        return messageRepository.findAllBySentUserId(id);
    }

    public List<Message> findAllMessagesBySender(int id) {
        return messageRepository.findAllBySenderId(id);
    }

}
