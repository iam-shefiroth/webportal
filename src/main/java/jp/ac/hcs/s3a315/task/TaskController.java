package jp.ac.hcs.s3a315.task;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	/**
	 * ログインしているユーザのタスク情報を取得する。
	 * 
	 * @param principal ログイン情報
	 * @param model
	 * @return 検索結果-タスク情報
	 */
	
	@PostMapping("/task")
	public String getTask(Principal principal,Model model) {
		String returns = null;
		System.out.println("たすく「");
		TaskEntity taskEntity = taskService.getTask(principal.getName());
		model.addAttribute("taskEntity",taskEntity);
		
		returns = "task/task";
		return returns;
	}
	@PostMapping("/insert")
	public String insertTask(@RequestParam("commmet") String comment,
			@RequestParam("limitday") String limitday,Principal principal){
		//タスク追加情報をTaskDataクラスを利用
		TaskData data = new TaskData();
		System.out.println("いんさーと「");
		//期限日をDate型に変換する
		Date sqlDate= Date.valueOf(limitday);
		
		
		data.setComment(comment);
		data.setLimitday(sqlDate);
		data.setUser_id(principal.getName());
		
		String results = null;
		results = "task/task";
		taskService.setTask(data);
		
		return results;
	}

}
