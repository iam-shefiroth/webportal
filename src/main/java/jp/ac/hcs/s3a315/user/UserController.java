package jp.ac.hcs.s3a315.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登録してるユーザ一覧を取得する。
	 * 
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 */
	
	@GetMapping("/user/list")
	public String getUser(Model model) {
		UserEntity userEntity = userService.getTask();
		
		System.out.println("reading");
		model.addAttribute("userEntity",userEntity);
		
		return "user/userList";
	}
	
	/**
	 * ユーザ登録画面（管理者用）を表示する
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面
	 */
	@GetMapping("/user/insert")
	public String setUser(UserForm form,Model model) {
		return "user/insert";
	}
	
	/**
	 * 一件分のユーザ情報をデータベースに登録する
	 * @param form 登録するユーザ情報（パスワードは平文）
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @model model
	 * @return ユーザ一覧画面
	 */
	@PostMapping("/user/insert")
	public String setUser(@ModelAttribute @Validated UserForm form,BindingResult bindingResult,
			Principal principal,Model model) {
		
		//userform→userdataメソッドに置換する
		UserData data = userService.chengeMethod(form);
		
		//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return setUser(form,model);
		}else {
			boolean isJudge = userService.insertUser(data);
			if (isJudge) {
				log.info("UserAccount Registeird Sucsess");
			}else {
				log.info("UserAccount Registeird Failed");	
			}
			
			System.out.println("isnerted");
			return getUser(model);
			
		}
		
		
	}
	
	/**
	 * ユーザ詳細情報画面を表示する
	 * @param user_id 検索するユーザID
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ詳細情報画面
	 */
	
	@GetMapping("/user/detail/{id}")
	public String getUserData(@PathVariable ("id") String user_id,Principal principal,Model model) {
		//正規表現
		
		//後に必須チェックと妥当性チェックを書く。
		if (user_id == null) {
			log.info("NO DATE!!");
			return getUser(model);
			/* }else if () { */
			
		}
		UserData data = userService.getUserOne(user_id);
		model.addAttribute("userData",data);
		return "user/detail";
	}
	

}
