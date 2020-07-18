package dtos;

import java.util.LinkedList;
import java.util.List;

public class GroupDTO extends DTOBase {

	private String name;
	private String description;
	private List<UserDTO> users;

	public GroupDTO() {
	}

	public GroupDTO(int id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
	}
	
	public GroupDTO(String name, String description) {
		super(-1);
		this.name = name;
		this.description = description;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public void addUser(UserDTO user) {
		if (users == null) {
			users = new LinkedList<UserDTO>();
		}
		this.users.add(user);
	}

	public void deleteUser(UserDTO user) {
		if (users != null) {
			users.remove(user);
		}
	}
}