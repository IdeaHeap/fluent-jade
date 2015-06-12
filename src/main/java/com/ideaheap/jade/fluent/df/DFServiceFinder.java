package com.ideaheap.jade.fluent.df;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 * Created by nwertzberger on 6/2/15.
 */
public class DfServiceFinder {
    private final Agent agent;
    private String type;

    public DfServiceFinder(Agent agent) {
        this.agent = agent;
    }

    public DfServiceFinder forServiceType(String type) {
        this.type = type;
        return this;
    }

    public AID findOne() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(type);
        dfd.addServices(sd);
        try {
            return DFService.search(agent, dfd)[0].getName();
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return null;
    }
}
