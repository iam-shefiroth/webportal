package jp.ac.hcs.s3a315.task.delete;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * @param num 選択したタスクのID番号
	 * @param principal ログイン情報
	 * @param model
	 * @return 検索結果-タスク情報(削除後)
	 * 
	 * 
	 */
	
	@RequestMapping("/task/delete/{task.id}")
	public String deleteTask(@PathVariable("task.id") int id,
			Principal principal,Model model){
		
		String results = null;
		System.out.println("御陀仏");
		
		/*チェック（あとでやる）
		 * if (comment == null || comment.length() >= 50 || limitday == null ) {
		 * 
		 * }
		 */
		
		
		results = "task/task";
		deleteService.deleteTask(id);
		
		TaskEntity taskEntity = deleteService.getTask(principal.getName());
		model.addAttribute("taskEntity",taskEntity);
		 
		
		return results;
	}

}
