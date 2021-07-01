package jp.ac.hcs.s3a315.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	/**
	 * 
	 * @param principal ログイン情報
	 * @param model
	 * @return 検索結果-天気予報
	 */
	
	@PostMapping("/weather")
	public String getWeather(Principal principal,Model model) {
		String returns = null;
		TaskEntity TaskEntity = taskService.getTask(principal.getName());
		model.addAttribute("TaskEntity",TaskEntity);
		
		returns = "task/task";
		return returns;
	}

}
