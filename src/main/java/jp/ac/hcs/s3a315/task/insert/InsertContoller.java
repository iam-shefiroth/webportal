package jp.ac.hcs.s3a315.task.insert;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.hcs.s3a315.task.TaskData;
import jp.ac.hcs.s3a315.task.TaskEntity;

@Controller
public class InsertContoller {
	
	@Autowired
	private InsertService insertService;
	/**
	 * ログインしているユーザが追加したいタスク情報を挿入する。
	 * @param comment タスク内容
	 * @param limitday 期限
	 * @param principal ログイン情報
	 * 
	 */
	

	
	@PostMapping("/task/insert")
	public String insertTask(@RequestParam("comment") String comment,
			@RequestParam("limitday") String limitday,Principal principal,Model model){
		
		String results = null;
		
		//タスク追加情報をTaskDataクラスを利用
		TaskData data = new TaskData();
		System.out.println("いんさーと2");
		//期限日をDate型に変換する
		Date sqlDate= Date.valueOf(limitday);
		
		
		data.setComment(comment);
		data.setLimitday(sqlDate);
		data.setUser_id(principal.getName());
		System.out.println("いんさーと3");
		
		

		
		
		results = "task/task";
		insertService.setTask(data);
		System.out.println("いんさーと4");
		
		TaskEntity taskEntity = insertService.getTask(principal.getName());
		model.addAttribute("taskEntity",taskEntity);
		 
		
		return results;
	}

}
