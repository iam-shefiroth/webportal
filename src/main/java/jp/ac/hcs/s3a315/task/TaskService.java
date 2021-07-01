package jp.ac.hcs.s3a315.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *タスク情報を配列に追加する操作を行う
 */

@Transactional
@Service
public class TaskService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * ログイン中のidからtaskデータを抽出する。
	 * @return taskEntity
	 */
	
	public TaskEntity getTask(String name) {
		
		//タスクエンティティの作成
		TaskEntity taskEntity = new TaskEntity();
		
		//タスクリポジトリ―（SQL取得）の作成
		TaskRepository taskRepository = new TaskRepository();
		
		
		return taskEntity;
	}

}
