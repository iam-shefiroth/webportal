package jp.ac.hcs.ks.task;

import java.util.Date;

import lombok.Data;

/**
 * 一件分のタスク情報
 */

@Data
public class TaskData {
	
	/**
	 * タスクID
	 * 主キー、SQLにて自動採番
	 */
	private int id;
	
	/**
	 * ユーザID（メールアドレス）
	 * Userテーブルの主キーと紐づく、ログイン情報から取得
	 */
	private String user_id;
	
	/**
	 * 優先度
	 * 大、中、小の三種類
	 */
	private Priority priority;
	
	/**
	 * 件名
	 * 必須入力
	 */
	private String title;
	
	/**
	 * コメント
	 * 必須入力
	 */
	private String comment;
	
	/**
	 * 期限日
	 * 必須入力
	 */
	private Date limitday;

}



/**
 * 優先度のEnumクラス
 */
enum Priority{
	HIGH("HIGH","大"),
	MIDDLE("MIDDLE","中"),
	LOW("LOW","小");
	/** ID*/
	private String id;
	
	/** 値*/
	private String value;
	
	/** コンストラクタ*/
	Priority(String id,String value){
		this.id = id;
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * IDから合致したPriority型を返却する
	 * @param id
	 * @return Priority
	 */
	
	public static Priority IdOf(String id) {
		for (Priority priority : values()) {
			if(priority.getId().equals(id)) {
				return priority;
			}
		}
		throw new IllegalArgumentException("指定されたIDのPriorityが存在しません");
	}
}
