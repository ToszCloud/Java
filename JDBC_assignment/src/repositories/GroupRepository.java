package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtos.GroupDTO;

public class GroupRepository extends Repository implements IGroupRepository {

	@Override
	public void add(GroupDTO dto) {
		
		
		String updateIDHelper = "SELECT MAX(group_id) FROM grupy";
		String insertNapis = "INSERT INTO grupy(group_id ,group_name, group_descr) VALUES(?, ?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertNapis);
			preparedStatement.setInt(1, dto.getId());
			preparedStatement.setString(2, dto.getName());
			preparedStatement.setString(3, dto.getDescription());
			preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(GroupDTO dto) {
		String updateNapis = "UPDATE grupy SET group_name=?, group_descr=? WHERE group_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateNapis);
			preparedStatement.setString(1, dto.getName());
			preparedStatement.setString(2, dto.getDescription());
			preparedStatement.setInt(3, dto.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addOrUpdate(GroupDTO dto) {
		
		String toUse = "INSERT INTO grupy(group_id, group_name, group_descr) VALUES(?,?,?) ON DUPLICATE KEY UPDATE group_name =?, group_descr =?";
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(toUse);
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getDescription());
			
			ps.setString(4, dto.getName());
			ps.setString(5, dto.getDescription());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void delete(GroupDTO dto) {
		//"DELETE FROM grupy WHERE group_id=?"
		String deleteNapis = "DELETE FROM grupy WHERE group_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteNapis);
			preparedStatement.setInt(1, dto.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public GroupDTO findById(int id) {
		String selectNapis = "SELECT * FROM grupy WHERE group_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectNapis);
			preparedStatement.setInt(1, id);
			ResultSet res = preparedStatement.executeQuery();
			
			if(res.next()) {
				int res1 = res.getInt("group_id");
				String res2 = res.getString("group_name");
				String res3 = res.getString("group_descr");
				GroupDTO g = new GroupDTO(res1, res2, res3);
				return g;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public int getCount() {
		String countString = "SELECT COUNT(*) FROM grupy";
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
	public boolean exists(GroupDTO dto) { ////////////???????????????????????????????????????????????????
		String statement = "SELECT EXISTS(SELECT * FROM grupy WHERE group_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(statement);
			preparedStatement.setInt(1, dto.getId());
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
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<GroupDTO> findByName(String name) {////////////////////////////////FIX TO SAME AS IN USER IM SO DEAD INSIDE
		String selectStatement = "SELECT * FROM grupy WHERE group_name LIKE ?";
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, name);
			
			System.out.println(preparedStatement.toString());
			
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("group_id");
				String name2 = res.getString("group_name");
				String descr = res.getString("group_descr");
				GroupDTO g = new GroupDTO(id, name2, descr);
				list.add(g);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
		// TODO Auto-generated method stub
	}

	@Override
	public List<GroupDTO> selectAll() {
		String selectStatement = "SELECT * FROM grupy";
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			
			System.out.println(preparedStatement.toString());
			 
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("group_id");
				String name2 = res.getString("group_name");
				String descr = res.getString("group_descr");
				GroupDTO g = new GroupDTO(id, name2, descr);
				list.add(g);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}

}
