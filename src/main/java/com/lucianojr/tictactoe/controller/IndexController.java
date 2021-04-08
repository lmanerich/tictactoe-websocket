package com.lucianojr.tictactoe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucianojr.tictactoe.model.Game;

@Controller
public class IndexController {

	@RequestMapping()
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("name") != null) {
			model.addAttribute("name", session.getAttribute("name"));
		}
		model.addAttribute("games", Game.getInstance().getAvailableGames());
		return "index";
	}
}
