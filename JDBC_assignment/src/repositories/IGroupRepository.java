package repositories;

import java.util.List;
import dtos.*;

public interface IGroupRepository extends IRepository<GroupDTO> {

	List<GroupDTO> findByName(String name);
	List<GroupDTO> selectAll();
}