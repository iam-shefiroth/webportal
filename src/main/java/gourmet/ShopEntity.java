package gourmet;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 * 郵便番号情報検索結果の店舗情報
 * 各項目のデータ仕様については、APIの仕様を参照とする
 * １つの郵便番号に複数の住所が紐づく事もあるため、リスト構造となっている
 * - http://zipcloud.ibsnet.co.jp/doc/api
 *
 */
@Data
public class ShopEntity {
	
	/** 店舗情報のリスト*/
	private List<ShopData> shop = new ArrayList<ShopData>();

}
