import dtos.*;
import repositories.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

//import eu.glowacki.utp.assignment10.dtos.DTOBase;
//import eu.glowacki.utp.assignment10.repositories.IRepository;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

	private TRepository _repository;

	@Before
	public void before() {
		_repository = Create();
		if (_repository != null) {
			_repository.beginTransaction();
		}
	}

	@After
	public void after() {
		if (_repository != null) {
			_repository.rollbackTransaction();
		}
	}

	protected abstract TRepository Create();
	
	public Connection getConnection() {
		Connection connection = _repository.getConnection();
		return connection;

	}
	
	
	
}