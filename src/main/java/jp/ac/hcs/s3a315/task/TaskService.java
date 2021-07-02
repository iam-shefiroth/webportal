package jp.ac.hcs.s3a315.task;

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
	
public void setTask(TaskData data) {
		
		try {
			taskRepository.insertOne(data);
			
		}catch (DataAccessException e){
			e.printStackTrace();
		}
		
	}

}
