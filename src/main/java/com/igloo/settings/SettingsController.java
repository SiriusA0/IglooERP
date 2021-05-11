package com.igloo.settings;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.igloo.order.model.Order;

@Controller
public class SettingsController {
	
	
	
	@GetMapping("/settings")
    public String settings() {

       

        return "settings/settings";
    }

}
