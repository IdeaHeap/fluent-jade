package com.ideaheap.jade.fluent.df;

import jade.core.Agent;

/**
 * Created by nwertzberger on 6/11/15.
 */
public class DfServiceFinderFactory {

    private final Agent agent;

    public DfServiceFinderFactory(Agent agent) {
        this.agent = agent;
    }

    public DfServiceFinder find() {
        return new DfServiceFinder(agent);
    }
}
