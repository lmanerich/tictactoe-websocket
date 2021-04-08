package com.lucianojr.tictactoe.model;

public class Message {

	private String gameId;
	private String action;
	private int x;
	private int y;
	private String playerTurn;
	private String mark;
	private int[][] gameState;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(String playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	@Override
	public String toString() {
		return "{gameId:" + this.gameId + ", action:" + this.action + ", x:" + this.x + ", y:" + this.y + ", playerTurn:" + this.playerTurn + "}";
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int[][] getGameState() {
		return gameState;
	}

	public void setGameState(int[][] gameState) {
		this.gameState = gameState;
	}

}
