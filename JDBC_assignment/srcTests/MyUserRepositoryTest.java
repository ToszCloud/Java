import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Test;

import dtos.UserDTO;
import repositories.UserRepository;

class MyUserRepositoryTest  {

	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/

	@Test
	void testUserRepository(){

		//Repository test
		UserRepository uRepository = new UserRepository();
		uRepository.getConnection();
		uRepository.beginTransaction();

		
		//test: add, addOrUpdate, selectAll
		UserDTO u1 = new UserDTO(1, "Kasia19", "hard_password");
		UserDTO u2 = new UserDTO(2, "Jolly15", "creative_password");
		UserDTO u3 = new UserDTO(3, "Kasia15", "impossible_password");
		UserDTO u4 = new UserDTO(4, "Molly19", "super_password");
		UserDTO u5 = new UserDTO(5, "Kamila9", "nice_password");
		UserDTO u6 = new UserDTO(6, "Kami99", "good_password");
		UserDTO u7 = new UserDTO(7, "Kami22", "great_password");
		UserDTO u100 = new UserDTO(100, "KKKK", "MMMMM");
		
		uRepository.add(u1);
		uRepository.add(u2);
		uRepository.add(u3);
		uRepository.add(u4);
		uRepository.add(u5);
		uRepository.addOrUpdate(u6);
		uRepository.add(u7);
		
		List<UserDTO> selectAllList;
		selectAllList = uRepository.selectAll();
		
		List<UserDTO> testAllList = new ArrayList<UserDTO>();
		testAllList.add(u1);
		testAllList.add(u2);
		testAllList.add(u3);
		testAllList.add(u4);
		testAllList.add(u5);
		testAllList.add(u6);
		testAllList.add(u7);
		
		for(int i = 0; i < 7; i++) {
			assertEquals(selectAllList.get(i).getId(),testAllList.get(i).getId());
			assertEquals(selectAllList.get(i).getLogin(),testAllList.get(i).getLogin());
			assertEquals(selectAllList.get(i).getPassword(),testAllList.get(i).getPassword());
		}

		//uRepository.getCount() test
		int countTest = uRepository.getCount();
		assertEquals(countTest, 7);
		
		
		//update test, find by id
		u1.setPassword("even_better_password");
		uRepository.update(u1);
		UserDTO idChangedPassword = uRepository.findById(u1.getId());
		assertEquals(u1.getPassword(), idChangedPassword.getPassword());
		
		
		//findByName 
		
		String test = "%15%";
		List<UserDTO> expectedResult = new ArrayList<UserDTO>();
		expectedResult.add(u2);
		expectedResult.add(u3);
		
		List<UserDTO> findByName = uRepository.findByName(test);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(expectedResult.get(i).getId(),findByName.get(i).getId());
			assertEquals(expectedResult.get(i).getLogin(),findByName.get(i).getLogin());
			assertEquals(expectedResult.get(i).getPassword(),findByName.get(i).getPassword());
		}
		
		
		//exists test
		
		boolean test100 = uRepository.exists(u100);
		assertEquals(test100, false);
		
		
		boolean test200 = uRepository.exists(u1);
		assertEquals(test200, true);
		
		
		
		uRepository.rollbackTransaction();
		
		//uRepository.commitTransaction();
	}

	private void assertEquals(int id, int id1) {
	}

}
