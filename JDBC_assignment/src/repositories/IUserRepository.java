package repositories;

import java.util.List;

import dtos.*;

public interface IUserRepository extends IRepository<UserDTO> {

	List<UserDTO> findByName(String username);
	List<UserDTO> selectAll();
}