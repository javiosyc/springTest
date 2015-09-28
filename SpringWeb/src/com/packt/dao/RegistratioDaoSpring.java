package com.packt.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class RegistratioDaoSpring implements RegistrationDao {

	private final JdbcTemplate jdbcTemplate;

	public RegistratioDaoSpring(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		new DatabaseManager().go(null);
	}

	@Override
	public boolean isExistingUserId(String userId) {
		return jdbcTemplate.queryForInt(
				"SELECT count(*) FROM user_data where userId=?",
				new Object[] { userId }) > 0;
	}

	@Override
	public void create(String userId, String password, String firstName,
			String lastName) {
		int rowCount = jdbcTemplate.update(
				"insert into user_data values (?,?,?,?)", new Object[] {
						userId, password, firstName, lastName });
		if (rowCount != 1) {
			throw new RuntimeException("Database update row count should be 1");
		}

	}

}
