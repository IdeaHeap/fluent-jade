package com.ideaheap.jade.fluent.df;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public Optional<AID> findOne() {
        Set<AID> all = findAll();
        if (all.size() > 0) {
            return Optional.of(findAll().iterator().next());
        } else {
            return Optional.empty();
        }
    }

    public Set<AID> findAll() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(type);
        dfd.addServices(sd);
        Set<AID> aids = new HashSet<>();
        try {
            for (DFAgentDescription desc : DFService.search(agent, dfd)) {
                aids.add(desc.getName());
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return aids;
    }
}
