package com.ideaheap.jade.fluent.messages;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by nwertzberger on 5/30/15.
 */
public class MessageReceiverFactory {
    private ObjectMapper mapper;
    private Agent agent;

    public MessageReceiverFactory(Agent agent, ObjectMapper mapper) {
        this.agent = agent;
        this.mapper = mapper;
    }

    public MessageReceiver listen(Behaviour behavoiur) {
        return new MessageReceiver(agent, behavoiur, mapper);
    }
}
