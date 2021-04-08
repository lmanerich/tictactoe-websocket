package com.lucianojr.tictactoe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucianojr.tictactoe.model.GameDetails;

@Controller
public class TestController {

	@RequestMapping("/test")
	public String test(Model model, HttpSession session) {
		return "test";
	}

	@RequestMapping(value = "/test/novo", produces = MediaType.APPLICATION_JSON_VALUE)
	public GameDetails newGame() {
		GameDetails game = new GameDetails("asd");
		game.setId("123123");
		return game;
	}
}
