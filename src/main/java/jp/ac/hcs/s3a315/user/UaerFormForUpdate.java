package jp.ac.hcs.s3a315.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * アップデータようにパスワード、ダークモード、権限のチェックを外したUｓerForm
 * その他の内容はUserFormに準じる
 */

@Data
public class UaerFormForUpdate {
	
	/** ユーザID（メールアドレス）*/
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String user_id;
	
	/** パスワード*/
	private String password;
	
	/** ユーザ名*/
	@NotBlank(message = "{require_check}")
	private String user_name;
	
	/** ダークモードフラグ　妥当性が分からない（false_checkを利用する。）*/
	private boolean darkmode;
	
	/** 権限*/
	private String role;

}
