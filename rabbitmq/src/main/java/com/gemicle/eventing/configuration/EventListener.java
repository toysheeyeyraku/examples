package com.gemicle.eventing.configuration;

import com.google.common.eventbus.Subscribe;

public class EventListener {
	 
    private static int eventsHandled;
 
    @Subscribe
    public void stringEvent(String event) {
      System.out.println("EVENTED"+event);
    }
    @Subscribe
    public void f(A a) {
    	System.out.println("AAAAAAA"+a.X);
    }
}