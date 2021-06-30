package jp.ac.hcs.s3a315.weather;

import lombok.Data;

/**
 * 
 * 一件分の天気情報の詳細情報を取得する。
 * 各項目のデータ仕様について、APIの仕様を参照する。
 * -https://weather.tsukumijima.net/
 */

@Data
public class WeatherData2 {
	
	/**冒頭の詳細内容*/
	private String headlineText;
	
	/** メインの詳細内容*/
	private String bodyText;
	
	
	/**詳細内容*/
	private String text;

}
