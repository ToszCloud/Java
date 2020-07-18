package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtos.GroupDTO;
import dtos.UserDTO;

public class RepositoryUserGroup extends Repository implements IGroupUserRepository {

	@Override
	public void addUserToGroup(UserDTO u, GroupDTO g) {
		String insertNapis = "INSERT INTO grupy_users VALUES(?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertNapis);
			preparedStatement.setInt(1, u.getId());
			preparedStatement.setInt(2, g.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUserFromGroup(UserDTO u, GroupDTO g) {
		// TODO Auto-generated method stub
		String deleteNapis = "DELETE FROM grupy_users WHERE ((group_id=?) AND (user_id=?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteNapis);
			preparedStatement.setInt(1, g.getId());
			preparedStatement.setInt(2, u.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//select users from group?
		//select groups in which user is?
		
		
	}
}
