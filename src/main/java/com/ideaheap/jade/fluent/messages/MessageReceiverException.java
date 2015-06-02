package com.ideaheap.jade.fluent.messages;

import java.io.IOException;

/**
 * Created by nwertzberger on 5/30/15.
 */
public class MessageReceiverException extends Throwable {
    public MessageReceiverException(IOException e) {
        super(e);
    }
}
