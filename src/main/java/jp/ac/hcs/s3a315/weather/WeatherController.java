package jp.ac.hcs.s3a315.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WeatherController{

	@Autowired
	private WeatherService weatherService;
	/**
	 * 
	 * @param weather 選択した地域（初期は札幌、後に全地域対応可能）
	 * @param principal ログイン情報
	 * @param model
	 * @return 検索結果-天気予報
	 */
	
	@PostMapping("/weather")
	public String getWeather(@RequestParam("weather") String weather,
			Principal principal,Model model) {
		String returns = null;
		System.out.println(weather + "番号");
		if (weather == "000000") {
			log.info("choise miss");
			returns = "index";
		}else {
			WeatherEntity weatherEntity = weatherService.getWeather(weather);
			model.addAttribute("weatherEntity",weatherEntity);
			
			log.info("「" + principal.getName() + "」choise area to wrather : " + weather);
			returns = "weather/weather";
		}
		return returns;
	}
}
