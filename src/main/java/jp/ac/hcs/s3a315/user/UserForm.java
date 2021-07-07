package jp.ac.hcs.s3a315.user;

import lombok.Data;

/**
 * 
 * フロント-バックでユーザ情報をやり取りする
 * 各項目のデータ仕様はUSerEntityを参照とする
 */

@Data
public class UserForm {
	/** ユーザID（メールアドレス）*/
	/** パスワード*/
	/** ユーザ名*/
	/** ダークモードフラグ*/
	/** 権限*/
	@NotBlank(message= "{require_check}");
	private String role;
	}

}
