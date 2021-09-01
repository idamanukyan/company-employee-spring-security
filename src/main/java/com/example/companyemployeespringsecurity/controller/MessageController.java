package com.example.companyemployeespringsecurity.controller;


import com.example.companyemployeespringsecurity.model.Employee;
import com.example.companyemployeespringsecurity.model.Message;
import com.example.companyemployeespringsecurity.security.CurrentUser;
import com.example.companyemployeespringsecurity.service.EmployeeService;
import com.example.companyemployeespringsecurity.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final EmployeeService employeeService;
    private final MessageService messageService;

    @GetMapping("/messages")
    public String messages(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Message> messages = messageService.findAllMessagesBySender(currentUser.getEmployee().getId());
        modelMap.addAttribute("messages", messages);
        return "messages";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(ModelMap modelMap, @RequestParam("id") int id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isPresent()) {
            modelMap.addAttribute("employee", employee.get());
            return "sendMessage";
        }
        return "redirect:/employees";

    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute Message message, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("id") int id, @RequestParam("text") String text) {
        if (employeeService.findEmployeeById(id).isPresent()) {
            message.setSender(currentUser.getEmployee());
            message.setSentUser(employeeService.findEmployeeById(id).get());
            message.setText(text);
            messageService.save(message);
        }
        return "redirect:/employees";
    }


}
