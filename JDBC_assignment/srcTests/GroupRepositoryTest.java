

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import repositories.*;
import dtos.*;


public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {
	
	private GroupRepository gRep = new GroupRepository();
	Connection connection = gRep.getConnection();
	
	GroupDTO g1 = new GroupDTO(1, "Admins", "Descr1");
	GroupDTO g2 = new GroupDTO(2, "Users", "Descr2");
	GroupDTO g3 = new GroupDTO(3, "Mods", "Descr3");
	GroupDTO g4 = new GroupDTO(4, "Admin2", "Descr4");

	@Test
	public void add() {
		
		System.out.println("Starting test add function");
		
		gRep.add(g1);
		
		String helper = "SELECT * FROM grupy WHERE group_id=?";
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(helper);
			ps.setInt(1, g1.getId());
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				//System.out.println(res.getString("user_password"));
				assertEquals("Admins", res.getString("group_name"));
				Assert.assertEquals("Descr1", res.getString("group_descr"));
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("Finished test add function");
	}

	@Test
	public void update() {
		
		System.out.println("Starting update add function");
		
		gRep.add(g1);
		g1.setDescription("Opis1");
		gRep.update(g1);
		
		String helper = "SELECT * FROM grupy WHERE group_id=?";
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(helper);
			ps.setInt(1, g1.getId());
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				assertEquals(res.getString("group_descr"), g1.getDescription());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Finished update add function");
	}
	
	@Test
	public void addOrUpdate() {
		
		System.out.println("Starting addOrUpdate test");
		
		gRep.add(g1);
		gRep.addOrUpdate(g1);
		g1.setDescription("Opis2");
		
		String helper = "SELECT * FROM users WHERE user_id = ?";
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(helper);
			ps.setInt(1, g1.getId());
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				assertEquals(res.getString("group_descr"), g1.getDescription());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Finished addOrUpdate test");
		
		
		
	}

	@Test
	public void delete() {
		
		gRep.add(g1);
		gRep.delete(g1);
		int count = gRep.getCount();
		assertEquals(count, 0);
		
	}

	@Test
	public void findById() {
		gRep.add(g1);
		gRep.add(g2);
		
		GroupDTO result = gRep.findById(g1.getId());
		
		assertEquals(result.getId(), g1.getId());
		assertEquals(result.getName(), g1.getName());
		assertEquals(result.getDescription(), g1.getDescription());
	}
	
	@Test
	public void findByName() {
		System.out.println("Starting find by name test");
		String target = "%Admin%";
		
		gRep.add(g1);
		gRep.add(g2);
		gRep.add(g3);
		gRep.add(g4);
		
		List<GroupDTO> result = gRep.findByName(target);
		List<GroupDTO> expected = new ArrayList<GroupDTO>();
		
		expected.add(g1);
		expected.add(g4);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(result.get(i).getId(), expected.get(i).getId());
			assertEquals(result.get(i).getName(), expected.get(i).getName());
			assertEquals(result.get(i).getDescription(), expected.get(i).getDescription());
		}
		
		
	}

	@Override
	protected IGroupRepository Create() {
		return gRep;
	}
}