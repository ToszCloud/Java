package repositories;
import java.util.List;

import dtos.*;

public interface IGroupUserRepository {
	
	
	
	void addUserToGroup(UserDTO u , GroupDTO g);
	void deleteUserFromGroup(UserDTO u, GroupDTO g);
	//List<GroupDTO> selectUserGroups(UserDTO u);
	//List<UserDTO> selectUsersFromGroup(GroupDTO g);
	

}
