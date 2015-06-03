package com.ideaheap.jade.fluent.messages;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.ideaheap.jade.fluent.messages.MessageBuilder.inform;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by nwertzberger on 5/17/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Agent.class)
public class MessageReceiverTest {

    @Mock
    private Agent agent;

    @Mock
    private Behaviour behaviour;


    @Test
    public void messageReceiver_canReceiveMessages() throws Exception, MessageReceiverException {
        Mockito.when(agent.receive()).thenReturn(inform().withContent("meh").build());
        new MessageReceiver(agent, behaviour, new ObjectMapper()).forString((sender, content) -> {
            assertEquals("meh", content);
        });
    }

    @Test
    public void messageReceiver_blocksOnNull() throws Exception {
        Mockito.when(agent.receive()).thenReturn(null);
        new MessageReceiver(agent, behaviour, new ObjectMapper()).forString((sender, content) -> {
            fail();
        });
        Mockito.verify(behaviour).block();
    }

}
