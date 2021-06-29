package jp.ac.hcs.s3a315.zipcode;

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
public class ZipCodeEntity {
	
	/** ステータス*/
	@SuppressWarnings("unused")
	private String status;
	
	/** メッセージ*/
	@SuppressWarnings("unused")
	private String message;
	
	/** 郵便番号情報のリスト*/
	@SuppressWarnings("unused")
	private List<ZipCodeData> results = new ArrayList<ZipCodeData>();

}
