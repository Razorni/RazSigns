package me.razorni.dev.handler;

import me.razorni.dev.*;

public class Handler
{
    private RazMain instance;
    
    public Handler(final RazMain instance) {
        this.instance = instance;
    }
    
    public void enable() {
    }
    
    public void disable() {
    }
    
    public RazMain getInstance() {
        return this.instance;
    }
}
