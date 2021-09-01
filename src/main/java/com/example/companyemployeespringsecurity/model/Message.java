package com.example.companyemployeespringsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "sent_user_id")
    private Employee sentUser;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Employee sender;
}
