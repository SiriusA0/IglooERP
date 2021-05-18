package com.igloo.mail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

	@GetMapping("/mail")
	public String read(Model model) {

		model.addAttribute("title_section", 6);

		return "mail/mail";
	}

}
