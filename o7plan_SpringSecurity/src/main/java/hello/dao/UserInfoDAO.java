package hello.dao;

import java.util.List;

import hello.model.UserInfo;

public interface UserInfoDAO {
	public UserInfo findUserInfo(String userName);
	// [USER,AMIN,..]
	public List<String> getUserRoles(String userName);

}