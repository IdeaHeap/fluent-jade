package com.ideaheap.jade.fluent.messages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;

/**
 * Created by nwertzberger on 5/5/15.
 */
public class MessageReceiver {
    private final Agent agent;
    private final Behaviour behavior;
    private ObjectMapper mapper;

    MessageReceiver(Agent agent, Behaviour behavior, ObjectMapper mapper) {
        this.agent = agent;
        this.behavior = behavior;
        this.mapper = mapper;
    }

    private <T> void forMessage(
        BaseMessageReceiver<T> receiver,
        StringTransform<T> transform) throws MessageReceiverException {
        ACLMessage message = agent.receive();
        if (message != null) {
            try {
                if (message.getContent() == null) {
                    receiver.onMessage(message.getSender(), null);
                } else {
                    receiver.onMessage(
                        message.getSender(),
                        transform.transform(message.getContent())
                    );
                }
            } catch (MessageReceiverException e) {
                throw new MessageReceiverException("Caught exception from " + message.getSender()
                                                                                     .getLocalName(), e );
            }
        } else {
            behavior.block();
        }
    }

    public void forInteger(BaseMessageReceiver<Integer> contentReceiver) throws
        MessageReceiverException {
        forMessage(contentReceiver, (content) -> Integer.valueOf(content));
    }

    public void forDouble(BaseMessageReceiver<Double> contentReceiver) throws
        MessageReceiverException {
        forMessage(contentReceiver, (content) -> Double.valueOf(content));
    }

    public void forString(BaseMessageReceiver<String> contentReceiver) throws
        MessageReceiverException {
        forMessage(contentReceiver, (content) -> (content));
    }

    public void forLong(BaseMessageReceiver<Long> contentReceiver) throws MessageReceiverException {
        forMessage(contentReceiver, (content) -> Long.valueOf(content));
    }

    public void forBoolean(BaseMessageReceiver<Boolean> contentReceiver) throws
        MessageReceiverException {
        forMessage(contentReceiver, (content) -> Boolean.valueOf(content));
    }

    public <T> void forClass(Class<T> clazz, BaseMessageReceiver<T> contentReceiver) throws
        MessageReceiverException {
        forMessage(
            contentReceiver, (content) -> {
                try {
                    return mapper.readValue(content, clazz);
                } catch (IOException e) {
                    throw new MessageReceiverException(e);
                }
            }
        );
    }

    public <T> void forListOf(Class<T> clazz, BaseMessageReceiver<List<T>> contentReceiver) throws
        MessageReceiverException {
        forMessage(
            contentReceiver, (content) -> {
                try {
                    return mapper.readValue(
                        content,
                        mapper.getTypeFactory().constructCollectionType(List.class, clazz)
                    );
                } catch (IOException e) {
                    throw new MessageReceiverException(e);
                }
            }
        );
    }
}
