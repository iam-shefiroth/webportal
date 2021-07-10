package jp.ac.hcs.s3a315.task;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.hcs.s3a315.WebConfig;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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
	
	// 挿入処理の期限日で不正取得かどうかをチェックする yyyy-MM-dd
	public static final String JUDGE_DATE = 
			"^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
	
	  //実行結果アラートタグの設定
	public static final String ALERT = null;
	  
	  //実行結果のメッセージの設定
	public static final String MESSAGE = null;
	
	@PostMapping("/task")
	public String getTask(Principal principal,Model model) {
		String returns = null;
		TaskEntity taskEntity = taskService.getTask(principal.getName());
		model.addAttribute("taskEntity",taskEntity);
		
		
		returns = "task/task";
		return returns;
	}
	
	/**
	 * 自分の全てのタスク情報をCSVファイルとしてダウンロードさせる.
	 * @param principal ログイン情報
	 * @param model
	 * @return タスク情報のCSVファイル
	 */
	@PostMapping("/task/csv")
	public ResponseEntity<byte[]> getTaskCsv(Principal principal, Model model) {

		final String OUTPUT_FULLPATH = WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV;

		log.info("[" + principal.getName() + "]CSVファイル作成:" + OUTPUT_FULLPATH);

		// CSVファイルをサーバ上に作成
		taskService.taskListCsvOut(principal.getName());

		// CSVファイルをサーバから読み込み
		byte[] bytes = null;
		try {
			bytes = taskService.getFile(OUTPUT_FULLPATH);
			log.info("[" + principal.getName() + "]CSVファイル読み込み成功:" + OUTPUT_FULLPATH);
		} catch (IOException e) {
			log.warn("[" + principal.getName() + "]CSVファイル読み込み失敗:" + OUTPUT_FULLPATH);
			e.printStackTrace();
		}

		// CSVファイルのダウンロード用ヘッダー情報設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv; charset=UTF-8");
		header.setContentDispositionFormData("filename", WebConfig.FILENAME_TASK_CSV);
		

		// CSVファイルを端末へ送信
		return new ResponseEntity<byte[]>(bytes, header, HttpStatus.OK);
	}
	

	
	  /**
	   * 
	   * タスクの追加
	   * @param comment 入力したタスク内容
	   * @param limitday 入力した期限日
	   * @param principal ログイン情報
	   * @param model
	   * 
	   * @return getTask このクラスメインメソッドに戻す
	   */
	  
	  @PostMapping("/task/insert") 
	  public String insertTask(@RequestParam(name = "comment",required = false) String comment,
			  @RequestParam(name = "limitday",required = false) String limitday,
			  @RequestParam(name = "title",required = false) String title,
			  @RequestParam(name = "priority",required = false) String priority 
			  ,Principal principal,Model model,ModelAndView mav){
		  
		  //実行結果アラートタグの設定
		  String alert = null;
			  
			  //実行結果のメッセージの設定
		  String message = null;
		  
		  //正規表現のコンパイル
		  Pattern p = Pattern.compile(JUDGE_DATE);
	  //タスク内容と期限日のチェックを行う。
		  if (comment == null || limitday == null) {
			  log.info("ELEMENT ERROR");
			  alert = "isError";
			  message = "致命的なエラーが発生しました。";
		  }else if (comment == "" || comment.length() >= 50 || 
				  limitday == "") {
			  log.info("INSERT ERROR");
			  message = "入力内容が空白又はタスク内容が５０文字以上です。";
			  alert = "isError";
		  }else if(!(p.matcher(limitday).find())) { 
			  log.info("Tampering!!");
			  alert = "isError";
			  message = "致命的なエラーが発生しました。";
		  }else {

		  //期限日をDate型に変換する 
		  Date sqlDate= Date.valueOf(limitday);
		  
		  //タスク追加情報をTaskDataクラスを利用 
		  TaskData data = taskService.createTaskData(principal.getName(),title,sqlDate,comment,priority);
		  
		  log.info("「" + principal.getName() + "」insert comment:" + comment +
		  " limitday:" + data.getLimitday()); 
		  boolean isJudge = taskService.setTask(data); 
		  if (isJudge){
			  log.info("You are SUCSESS");
			  alert = "isSucsess";
			  message = "正常に追加されました。";
		  }else {
			  log.info("You are FAILED");
			  alert = "isError";
			  message = "異常発生が起きたため、追加に失敗しました。";
		  }
	  }
		  model.addAttribute(alert,true);
		  model.addAttribute("message",message);
		  return getTask(principal,model);
	  
	  }
	  
	  
	  /**
	   * タスクの削除
	   * 
	   * @param id リンクをクリックした際に取得したID
	   * @param principal ログイン情報
	   * @param model
	   * @return このクラスメインメソッドに戻す
	   */
	  
	  @RequestMapping("/task/delete/{task.id}")
		public String deleteTask(@PathVariable("task.id") int id,
				Principal principal,Model model){
			
		  //実行結果アラートタグの設定
		  String alert = null;
			  
			  //実行結果のメッセージの設定
		  String message = null;
		  boolean isJudge = taskService.deleteTask(id);
		  if (isJudge){
			  log.info("You are SUCSESS");
			  alert = "isSucsess";
			  message = "正常に削除されました。";
		  }else {
			  log.info("You are FAILED");
			  alert = "isError";
			  message = "異常発生が起きたため、削除に失敗しました。";
			  }
		  model.addAttribute(alert,true);
		  model.addAttribute("message",message);
		  return getTask(principal,model);
	  }
}
