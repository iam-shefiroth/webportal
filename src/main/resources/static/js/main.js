/**
 * main.htmlのJavaスクリプト
 */


//zipcodeの入力チェック
function zip_check(){
	if (zip.zipcode.value == ""){
		//条件に一致する場合(メールアドレスが空の場合)
		alert("郵便番号が未入力です。");    //エラーメッセージを出力
		return false;    //送信ボタン本来の動作をキャンセルします
	}else{
		//条件に一致しない場合(メールアドレスが入力されている場合)
		return true;    //送信ボタン本来の動作を実行します
	}
}

//weatherの改ざんチェック
function weather_check(){
	const judge = ".*[0-9]{6}.*";
	if (weather.weather.value == ""){
		alert("選択してください");
		return false; 
	}else if (!weather.weather.value.match(judge)){
		alert("この不正パラメータは使用できません：" + weather.weather.value);
		return false;
	}else {
		return true;
	}
}