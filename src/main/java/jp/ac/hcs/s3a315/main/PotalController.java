package jp.ac.hcs.s3a315.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PotalController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
