package com.ideaheap.jade.fluent.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import jade.lang.acl.ACLMessage;

/**
 * Created by nwertzberger on 6/16/15.
 */
public class Messenger {
    private final ObjectMapper objectMapper;

    public Messenger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public MessageBuilder inform() {
        return new MessageBuilder(ACLMessage.INFORM, objectMapper);
    }
}
