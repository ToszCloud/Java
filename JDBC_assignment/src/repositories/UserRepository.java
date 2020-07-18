package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtos.UserDTO;

public class UserRepository extends Repository implements IUserRepository {
	

	@Override
	public void add(UserDTO dto) {
		System.out.println("Inserting new row");
		
		if(connection != null) {
			String insertNapis = "INSERT INTO users VALUES (?,?,?)";
			
			try {
				
				PreparedStatement preparedStatement = connection.prepareStatement(insertNapis);
				preparedStatement.setInt(1, dto.getId());
				preparedStatement.setString(2, dto.getLogin());
				preparedStatement.setString(3, dto.getPassword());
				preparedStatement.executeUpdate();
				

			} catch (SQLException e) {
				//
			}
		}
	}
	
	@Override
	public void update(UserDTO dto) {
		String updateNapis = "UPDATE users SET user_password=? WHERE user_id=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateNapis);
			preparedStatement.setString(1, dto.getPassword());
			preparedStatement.setInt(2, dto.getId());
			preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addOrUpdate(UserDTO dto) {
		
		String toUse = "INSERT INTO users(user_id, user_login, user_password) VALUES(?,?,?) ON DUPLICATE KEY UPDATE user_login =?, user_password =?";
		
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(toUse);
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getLogin());
			ps.setString(3, dto.getPassword());
			
			ps.setString(4, dto.getLogin());
			ps.setString(5, dto.getPassword());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		/*
		String addOrUpdate = "INSERT INTO users(user_login, user_password) VALUES (?, ?)";
		String updateNapis = "UPDATE users SET user_login=?, user_password=? WHERE user_id=?";
		//String addUpdateNapis = "INSERT INTO users(user_id, user_login, user_password) VALUES(?,?,?) ON DUPLICATE KEY UPDATE group_id = group_id+1";
		*/
				
				
				/*
		try {
			
			
			if(dto.getId() == -1) {
				String addOrUpdate = "INSERT INTO users(user_login, user_password) VALUES (?, ?)";
			}
			
			
			
			PreparedStatement preparedStatement = connection.prepareStatement(addUpdateNapis);
			preparedStatement.setInt(1, dto.getId());
			preparedStatement.setString(2, dto.getLogin());
			preparedStatement.setString(3, dto.getPassword());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
	}

	@Override
	public void delete(UserDTO dto) {
		String deleteNapis = "DELETE FROM users WHERE user_login=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteNapis);
			preparedStatement.setString(1, dto.getLogin());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public UserDTO findById(int id) {
		String selectNapis = "SELECT * FROM users WHERE user_id =?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectNapis);
			preparedStatement.setInt(1, id);
			ResultSet res = preparedStatement.executeQuery();
			if(res.next()) {
				int thisid = res.getInt("user_id");
				String login = res.getString("user_login");
				String password = res.getString("user_password");
				UserDTO user = new UserDTO(thisid, login, password);
				return user;
			} else {
				System.out.println("No user with this ID");
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public int getCount() {
		String countString = "SELECT COUNT(*) FROM users";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(countString);
			ResultSet res = preparedStatement.executeQuery();
			if(res.next()) {
				int res1 = res.getInt(1);
				return res1;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
		
	}

	@Override
	public boolean exists(UserDTO dto) {
		String existsString = "SELECT EXISTS(SELECT * FROM users WHERE user_id=?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(existsString);
			preparedStatement.setInt(1, dto.getId());
			
			System.out.println(preparedStatement.toString());
			//ResultSet res = preparedStatement.executeQuery();
			ResultSet res = preparedStatement.executeQuery();
			
			if(res.next()) {
				int res1 = res.getInt(1);
				if(res1 == 1) {
					return true;
				} else {
					return false;
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}	

	@Override
	public List<UserDTO> findByName(String username) {
		
		String selectNapis = "SELECT * FROM users WHERE user_login LIKE ?";
		List<UserDTO> list = new ArrayList<UserDTO>();
		
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectNapis);
			preparedStatement.setString(1, username);
			
			System.out.println(preparedStatement.toString());
			
			ResultSet res = preparedStatement.executeQuery();
			while(res.next()) {
				int id = res.getInt("user_id");
				String login = res.getString("user_login");
				String password = res.getString("user_password");
				UserDTO user = new UserDTO(id, login, password);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<UserDTO> selectAll() {
		String selectAll = "SELECT * FROM users";
		List<UserDTO> list = new ArrayList<UserDTO>();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectAll);
			
			System.out.println(preparedStatement.toString());
			
			ResultSet res = preparedStatement.executeQuery();
			while(res.next()) {
				int id = res.getInt("user_id");
				String login = res.getString("user_login");
				String password = res.getString("user_password");
				UserDTO user = new UserDTO(id, login, password);
				list.add(user);
				
			}
		} catch(SQLException e) {
		
		}
		return list;
	}

}
