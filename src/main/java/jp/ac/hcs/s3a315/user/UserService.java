package jp.ac.hcs.s3a315.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ情報を配列に入れる操作を行う。

 */
@Transactional
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	/**
	 * 登録しているユーザ情報を抽出する。
	 * @return userEntity
	 */

	public UserEntity getTask() {
		
		//ユーザーエンティティの作成
		UserEntity userEntity;
		try {
			userEntity = userRepository.selectAll();
			
		}catch(DataAccessException e){
			e.printStackTrace();
			userEntity = null;
		}
		
		return userEntity;
	}

}
