package com.ideaheap.jade.fluent.containers;

import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

/**
 * Created by nwertzberger on 5/17/15.
 */
public class ContainerKiller {


    public static void killContainerOf(Agent agent) {
        final AgentContainer containerController = agent.getContainerController();
        agent.doDelete();
        new Thread(() -> {
            try {
                containerController.kill();
            } catch (StaleProxyException e) {
                // We're going to eat this. I hope you didn't care about this.
                //logger.error("Had exception on killContainerOf", e);
            }
        }).start();
    }
}
