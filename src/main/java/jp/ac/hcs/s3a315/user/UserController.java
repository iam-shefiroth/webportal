package jp.ac.hcs.s3a315.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	 * @return 検索結果-タスク情報
	 */
	
	@GetMapping("/user/list")
	public String getUser(Principal principal,Model model) {
		UserEntity userEntity = userService.getTask();
		
		model.addAttribute("userEntity",userEntity);
		
		return "user/userList";
	}
	

}
