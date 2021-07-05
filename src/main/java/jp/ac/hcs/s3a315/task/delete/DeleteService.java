package jp.ac.hcs.s3a315.task.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import jp.ac.hcs.s3a315.task.TaskEntity;
import jp.ac.hcs.s3a315.task.TaskRepository;

@Service
public class DeleteService {
	
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
	 * 受け渡ったIDからSQLを削除する。
	 * @param num sqlのID番号
	 */
	
	public void deleteTask(int num) {
		
		try {
			taskRepository.deleteOne(num);
			
		}catch (DataAccessException e){
			e.printStackTrace();
		}
		
	}
	

	
	

}
