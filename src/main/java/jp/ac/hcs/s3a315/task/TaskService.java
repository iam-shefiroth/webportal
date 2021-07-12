package jp.ac.hcs.s3a315.task;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *タスク情報を配列に入れる操作を行う
 */

@Transactional
@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	/**
	 * ログイン中のidからtaskデータを抽出する。
	 * @param name (user_id)
	 * @return taskEntity
	 */
	
	public TaskEntity getTask(String name) {
		
		//タスクエンティティの作成
		TaskEntity taskEntity;
		try {
			//タスクリポジトリ―（SQL取得）の作成
			taskEntity = taskRepository.selectAll(name);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			taskEntity = null;
		}
		
		return taskEntity;
	}
	
	/**
	 * @param data タスクデータ追加情報
	 * @return sql処理結果（成功：true,失敗：false）
	 */

	public boolean setTask(TaskData data) {
		int result;
		try {
			result = taskRepository.insertOne(data);
		}catch (DataAccessException e){
			e.printStackTrace();
			result = 0;
		}
		
		return result > 0;
	}
	
	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskRepository.tasklistCsvOut(user_id);
	}

	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 */
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
	
	/**
	 * 受け渡ったIDからSQLを削除する。
	 * @param num sqlのID番号
	 * @return sql処理結果（成功：true,失敗：false）
	 */
	
	public boolean deleteTask(int num) {
		int result;
		try {
			result = taskRepository.deleteOne(num);
			
		}catch (DataAccessException e){
			e.printStackTrace();
			result = 0;
		}
		
		return result > 0;
		
	}
	
	/**
	 * タスク情報をtaskDataに挿入する
	 *@param title
	 *@param limitday
	 *@param comment
	 *@param priority
	 *
	 *@return taskData
	 */
	TaskData createTaskData(String user_id,String title,Date limitday,String comment,String prioryty) {
		TaskData taskData = new TaskData();
		
		//タスクデータを設定する
		taskData.setUser_id(user_id);
		taskData.setTitle(title);
		taskData.setLimitday(limitday);
		taskData.setComment(comment);
		
		taskData.setPriority(Priority.IdOf(prioryty));
		return taskData;
	}

}
