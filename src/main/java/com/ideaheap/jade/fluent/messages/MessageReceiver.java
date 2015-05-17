package com.ideaheap.jade.fluent.messages;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by nwertzberger on 5/5/15.
 */
public class MessageReceiver {
    private final Agent agent;
    private final Behaviour behavior;

    private MessageReceiver(Agent agent, Behaviour behavior) {
        this.agent = agent;
        this.behavior = behavior;
    }

    public static MessageReceiver listen(Agent agent, Behaviour behavior) {
        return new MessageReceiver(agent, behavior);
    }

    private <T> void forMessage(BaseMessageReceiver<T> receiver, StringTransform<T> transform) {
        ACLMessage message = agent.receive();
        if (message != null) {
            receiver.onMessage(message.getSender(), transform.transform(message.getContent()));
        } else {
            behavior.block();
        }
    }

    public void forInteger(BaseMessageReceiver<Integer> contentReceiver) {
        forMessage(contentReceiver, (content) -> Integer.valueOf(content));
    }
    public void forDouble(BaseMessageReceiver<Double> contentReceiver) {
        forMessage(contentReceiver, (content) -> Double.valueOf(content));
    }
    public void forString(BaseMessageReceiver<String> contentReceiver) {
        forMessage(contentReceiver, (content) -> (content));
    }
    public void forLong(BaseMessageReceiver<Long> contentReceiver) {
        forMessage(contentReceiver, (content) -> Long.valueOf(content));
    }
    public void forBoolean(BaseMessageReceiver<Boolean> contentReceiver) {
        forMessage(contentReceiver, (content) -> Boolean.valueOf(content));
    }
}
