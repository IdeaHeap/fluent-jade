package com.ideaheap.jade.fluent.df;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 * Created by nwertzberger on 6/1/15.
 */
public class DfServiceBuilder {
    private Agent agent;
    private DFAgentDescription dfd;

    public DfServiceBuilder(Agent agent) {
        this.agent = agent;
        dfd = new DFAgentDescription();
        dfd.setName(agent.getAID());
    }

    public DfServiceBuilder addService(String type, String name) {
        ServiceDescription desc = new ServiceDescription();
        desc.setType(type);
        desc.setName(name);
        dfd.addServices(desc);
        return this;
    }

    public void register() {
        try {
            DFService.register(agent, dfd);
        } catch (FIPAException e) {
        }
    }
}
