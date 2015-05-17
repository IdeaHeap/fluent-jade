package com.ideaheap.jade.fluent.messages;

import jade.core.AID;

/**
 * Created by nwertzberger on 5/17/15.
 */
public interface BaseMessageReceiver<T> {
    void onMessage(AID sender, T content);
}
