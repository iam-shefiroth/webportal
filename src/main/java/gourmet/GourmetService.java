package gourmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * 店舗情報を操作する
 * リクルート社のグルメサーチAPIを活用する
 *- http://webservice.recruit.co.jp/hotpepper/gourmet/v1/
 */
@Transactional
@Service
public class GourmetService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** APIキー*/
	private static final String API_KEY = "7ee5e8d9aa08642d";
	
	/** グルメサーチAPI リクエストURL*/
	private static final String URL = 
			"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/search?key={API_KEY}"
			+ "&name={keyword}&large_service_area={large_service_area}&format=json";
	
	/**
	 * 指定したキーワードに紐づく店舗情報を取得する。
	 * @param keyword
	 * 
	 */

}
