package com.clearwateranalytics.quiz.pojo.dto;

import com.clearwateranalytics.quiz.pojo.entities.MessageEntity;

import java.util.ArrayList;
import java.util.List;

public class ChatRequestDto {
    private String model;
    private List<MessageEntity> messages;
    private int n;
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        this.n = prompt.length();
        this.messages = new ArrayList<>();
        this.messages.add(new MessageEntity("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
