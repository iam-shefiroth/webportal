package jp.ac.hcs.s3a315.task.insert;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.hcs.s3a315.task.TaskData;

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
		insertService.setTask(data);
		
		return results;
	}

}
