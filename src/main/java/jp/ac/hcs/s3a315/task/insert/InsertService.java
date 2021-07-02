package jp.ac.hcs.s3a315.task.insert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.hcs.s3a315.task.TaskData;
import jp.ac.hcs.s3a315.task.TaskRepository;

/**
 * 受け渡った情報をSQLに挿入する操作を行う
 */

@Transactional
@Service
public class InsertService {
	
	@Autowired
	TaskRepository taskRepository;
	/**
	 * @param data タスクデータ追加情報
	 */

	public void setTask(TaskData data) {
		
		try {
			taskRepository.insertOne(data);
			
		}catch (DataAccessException e){
			e.printStackTrace();
		}
		
	}
	
	


}
