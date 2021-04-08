package com.lucianojr.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.lucianojr.tictactoe.model.Game;
import com.lucianojr.tictactoe.model.GameDetails;

@Component
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		System.out.println("session disconnect");
		System.out.println(event);
		
		GameDetails game = Game.getInstance().getGameByPlayerId(event.getSessionId());
		Game.getInstance().removeGame(game);
		
		this.simpMessagingTemplate.convertAndSend("/queue/game-" + game.getId(), "jogador saiu");
	}
}
