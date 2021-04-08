package com.lucianojr.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private static Game instance;

	private List<GameDetails> availableGames;

	private Game() {
		this.availableGames = new ArrayList<GameDetails>();
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public GameDetails createGame(String name) {
		if (this.availableGames.size() >= 10) {
			this.availableGames.clear();
		}

		GameDetails game = new GameDetails(name);
		game.setId(String.valueOf(System.currentTimeMillis()));
		this.availableGames.add(game);
		return game;
	}

	public List<GameDetails> getAvailableGames() {
		return this.availableGames;
	}
	
	public void removeGame(GameDetails game) {
		this.availableGames.remove(game);
	}

	public GameDetails getGameByPlayerId(String id) {
		GameDetails game = null;
		for (GameDetails gameDetails : this.availableGames) {
			for (String playerId : gameDetails.getPlayersId()) {
				if (playerId.equals(id)) {
					game = gameDetails;
					break;
				}
			}
		}
		
		return game;
	}

	public GameDetails getGame(String id) {
		GameDetails game = null;
		for (GameDetails gameDetails : this.availableGames) {
			if (gameDetails.getId().equals(id)) {
				game = gameDetails;
				break;
			}
		}
		
		return game;
	}

	public void joinGame(String id) {
		GameDetails game = this.getGame(id);

		if (game != null) {
			this.availableGames.remove(game);
		} else {
			throw new RuntimeException("Jogo não encontrado");
		}
	}
}
