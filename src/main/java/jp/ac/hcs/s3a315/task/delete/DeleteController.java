package jp.ac.hcs.s3a315.task.delete;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.ac.hcs.s3a315.task.TaskEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DeleteController {
	
	@Autowired
	private DeleteService deleteService;
	/*
	 * 
	 * クリックしたタスク内容のIDを取得し、そのデータを削除するクラスに渡す。
	 * 
	 * 
	 * 
	 * 
	 */
	
	@PostMapping("/task/delete")
	public String deleteTask(@PathVariable int num,
			Principal principal,Model model){
		
		String results = null;
		
		/*チェック（あとでやる）
		 * if (comment == null || comment.length() >= 50 || limitday == null ) {
		 * 
		 * }
		 */
		
		
		results = "task/task";
		deleteService.deleteTask(num);
		
		TaskEntity taskEntity = deleteService.getTask(principal.getName());
		model.addAttribute("taskEntity",taskEntity);
		 
		
		return results;
	}

}
