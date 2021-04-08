package com.lucianojr.tictactoe.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.lucianojr.tictactoe.model.Game;
import com.lucianojr.tictactoe.model.GameDetails;
import com.lucianojr.tictactoe.model.Message;

@Controller
public class GameController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@RequestMapping("novo")
	public String index(@RequestParam("nome") String name, Model model, HttpSession session) {
		session.setAttribute("name", name);
		GameDetails game = Game.getInstance().createGame(name);
		return "redirect:/game/" + game.getId();
	}

	@RequestMapping("/game/sair")
	public String quit(Model model, HttpSession session) {
		return "redirect:/";
	}

	@RequestMapping("/game/{gameId}")
	public String game(@PathVariable String gameId, Model model, HttpSession session) {
		GameDetails game = Game.getInstance().getGame(gameId);

		if (game != null) {
			model.addAttribute("gameId", gameId);
			return "game";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado");
		}
	}

	@MessageMapping("/websocket")
	public void send(Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		String sessionId = headerAccessor.getSessionId();

		System.out.println("Received message");
		System.out.println(message);
		System.out.println(sessionId);

		GameDetails game = Game.getInstance().getGame(message.getGameId());
		System.out.println(game);

		Message reply = new Message();
		reply.setGameId(message.getGameId());
		if ("connected".equals(message.getAction())) {
			game.addPlayer(sessionId);
			if (game.getPlayersId().size() == 2) {
				Random r = new Random();
				int randomPlayer = r.nextInt(2);
				System.out.println("starting with player " + randomPlayer);
				game.setPlayerTurn(game.getPlayersId().get(randomPlayer));

				reply.setAction("start");
				reply.setPlayerTurn(game.getPlayerTurn());
			} else {
				reply.setAction("waiting_player_2");
			}
		}
		if ("play".equals(message.getAction())) {
			game.move(message.getX(), message.getY(), "X".equals(message.getMark()) ? 1 : 2);
			int winner = game.hasWinner();

			reply.setMark(message.getMark());
			reply.setX(message.getX());
			reply.setY(message.getY());
			reply.setGameState(game.getGameState());
			if (winner > 0) {
				Game.getInstance().removeGame(game);
				reply.setAction("winner");
			} else {
				reply.setAction("move");
				for (String playerId : game.getPlayersId()) {
					if (playerId != game.getPlayerTurn()) {
						game.setPlayerTurn(playerId);
						reply.setPlayerTurn(playerId);
						break;
					}
				}
			}
		}

		System.out.println("Sending reply");
		System.out.println(reply);
		this.simpMessagingTemplate.convertAndSend("/queue/game-" + message.getGameId(), reply);
	}
}
