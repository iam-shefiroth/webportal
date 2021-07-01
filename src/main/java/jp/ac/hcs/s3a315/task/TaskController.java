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
	 * @return 検索結果-タスク情報
	 */
	
	@PostMapping("/task")
	public String getTask(Principal principal,Model model) {
		String returns = null;
		TaskEntity taskEntity = taskService.getTask(principal.getName());
		model.addAttribute("taskEntity",taskEntity);
		
		returns = "task/task";
		return returns;
	}

}
