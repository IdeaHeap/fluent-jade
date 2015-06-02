package com.ideaheap.jade.fluent.messages;

/**
 * Created by nwertzberger on 5/17/15.
 */
public interface StringTransform <T> {
    T transform(String source) throws MessageReceiverException;
}
