package com.lucianojr.tictactoe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameDetails {
	private String id;
	private String creatorName;
	private List<String> playersId;
	private String playerTurn;
	private int[][] gameState;

	public GameDetails(String name) {
		this.playersId = new ArrayList<String>();
		this.creatorName = name;
		this.gameState = new int[3][3];
	}

	public String getId() {
		return id;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getPlayersId() {
		return playersId;
	}

	public void addPlayer(String playerId) {
		this.playersId.add(playerId);
	}

	public String getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(String playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public void move(int x, int y, int player) {
		this.gameState[x][y] = player;
	}
	
	public int[][] getGameState() {
		return this.gameState;
	}
	
	public int hasWinner() {
		System.out.println(Arrays.toString(this.gameState[0]));
		System.out.println(Arrays.toString(this.gameState[1]));
		System.out.println(Arrays.toString(this.gameState[2]));
		for (int i = 0; i < 3; i++) {
			// linhas
			if (this.gameState[i][0] != 0 && this.gameState[i][0] == this.gameState[i][1] && this.gameState[i][0] == this.gameState[i][2]) {
				System.out.println("winner at row " + i);
				return this.gameState[i][0];
			}
			
			// colunas
			if (this.gameState[0][i] != 0 && this.gameState[0][i] == this.gameState[1][i] && this.gameState[0][i] == this.gameState[2][i]) {
				System.out.println("winner at column " + i);
				return this.gameState[0][i];
			}
		}

		// diagonal \
		if (this.gameState[0][0] != 0 && this.gameState[0][0] == this.gameState[1][1] && this.gameState[0][0] == this.gameState[2][2]) {
			System.out.println("winner at diagonal \\");
			return this.gameState[0][0];
		}

		// diagonal /
		if (this.gameState[0][2] != 0 && this.gameState[0][2] == this.gameState[1][1] && this.gameState[0][2] == this.gameState[2][0]) {
			System.out.println("winner at diagonal /");
			return this.gameState[0][2];
		}
		
		return 0;
	}

}
