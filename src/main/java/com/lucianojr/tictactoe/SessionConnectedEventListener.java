package com.lucianojr.tictactoe;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class SessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {

	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {
		System.out.println("session connected");
		System.out.println(event);
	}
}
