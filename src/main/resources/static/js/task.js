/**
 * 
 */
function delete_verification(){
	var result = window.confirm("このタスクを削除してもよろしいですか？");
	if (result) {
		return true;
	} else {
		return false;
	}
}
