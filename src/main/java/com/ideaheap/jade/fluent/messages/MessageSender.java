package com.ideaheap.jade.fluent.messages;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

/**
 * This class is literally just here to deal with the final crap inside of the jade Agent.
 * Created by nwertzberger on 6/11/15.
 */
public class MessageSender {

    private final Agent agent;

    public MessageSender(Agent agent) {
        this.agent = agent;
    }

    public void send(ACLMessage message) {
        agent.send(message);
    }
}
