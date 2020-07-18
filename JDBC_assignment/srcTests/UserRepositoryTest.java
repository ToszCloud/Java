import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dtos.*;
import repositories.*;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {
	
	private UserRepository uRep = new UserRepository();
	Connection connection = uRep.getConnection();
	
	UserDTO u1 = new UserDTO(1, "Kasia19", "hard_password");
	UserDTO u2 = new UserDTO(2, "Jasia19", "super_password");
	UserDTO u3 = new UserDTO(3, "Ola", "password");

	@Test
	public void add() {
		
		String testString = "SELECT * FROM users WHERE user_id = ?";
		System.out.println("Starting add function");
		uRep.add(u1);
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(testString);
			preparedStatement.setInt(1, u1.getId());
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next())
			{
				//System.out.println(res.getString("user_password"));
				Assert.assertEquals("hard_password", res.getString("user_password"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished testing add function");
		
	}

	@Test
	public void update() {
		
		String testString = "SELECT * FROM users WHERE user_id = ?";
		
		System.out.println("Starting update test function");
		
		uRep.add(u1);
		
		u1.setPassword("even_harder_password");
		
		uRep.update(u1);
		
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(testString);
			ps.setInt(1, u1.getId());
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				System.out.println(res.getString("user_password"));
				Assert.assertEquals("even_harder_password", res.getString("user_password"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	@Test
	public void delete() {
		
		uRep.add(u1);
		uRep.delete(u1);
		int count = uRep.getCount();
		assertEquals(count, 0);
		
		
	}

	@Test
	public void findById() {
		
		uRep.add(u1);
		uRep.add(u2);
		
		UserDTO result = uRep.findById(u1.getId());
		
		assertEquals(result.getId(), u1.getId());
		assertEquals(result.getLogin(), u1.getLogin());
		assertEquals(result.getPassword(), u1.getPassword());
		
		
		
	}
	
	@Test
	public void findByName() {
		
		System.out.println("Starting find by name test");
		String test = "%19%";
		
		uRep.add(u1);
		uRep.add(u2);
		uRep.add(u3);
		
		List<UserDTO> result = uRep.findByName(test);
		List<UserDTO> expected = new ArrayList<UserDTO>();
		
		expected.add(u1);
		expected.add(u2);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(result.get(i).getId(), expected.get(i).getId());
			assertEquals(result.get(i).getLogin(), expected.get(i).getLogin());
			assertEquals(result.get(i).getPassword(), expected.get(i).getPassword());
		}
		
		
		
		
		System.out.println("Finished find by name test");
	}
	
	@Test
	public void addOrUpdate() {
		
		String testString = "SELECT * FROM users WHERE user_id = ?";
		PreparedStatement ps;
		System.out.println("Starting addOrUpdate function test");
		
		
		uRep.add(u1);
		u1.setPassword("AAAA");
		uRep.addOrUpdate(u1);
		
		try {
			ps = connection.prepareStatement(testString);
			ps.setInt(1, u1.getId());
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				System.out.println(res.getString("user_password"));
				Assert.assertEquals("AAAA", res.getString("user_password"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected IUserRepository Create() {
		//UserRepository uRep = new	UserRepository();
		return uRep;
	}
	

}