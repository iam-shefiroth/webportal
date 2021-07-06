package jp.ac.hcs.s3a315.gourmet;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GourmetController {
	
	@Autowired
	private GourmetService gourmetService;
	/**
	 * キーワードからヒットする店舗情報を表示する。
	 * @param keyword 検索するキーワード
	 * @param principal ログイン情報
	 * @param model
	 * @return ヒット結果
	 */
	
	@PostMapping("/gourmet")
	public String getGourmet(@RequestParam("shopname") String keyword,
			Principal principal,Model model) {
		String returns = "gourmet/gourmet";
		
		//北海道に固定（後に変更あり？）
		String large_service_area = "SS40";
		
		//キーワードを元にAPIで検索する。
		log.info(principal.getName() + "inputwords :" + keyword + 
				" and choise Large_area" + large_service_area);
		ShopEntity shopEntity = gourmetService.findGourmet(keyword,large_service_area);
		
		System.out.println(shopEntity);
		model.addAttribute("shopEntity",shopEntity);
		model.addAttribute("keyword",keyword);
		
		return returns;
	}

}
