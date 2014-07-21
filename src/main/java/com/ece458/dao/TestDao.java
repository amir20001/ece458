package com.ece458.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ece458.domain.Data;
import com.ece458.mapper.DataMapper;

@Repository
public class TestDao {

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public Data getip(String domainName) {
		String sql = "select * from data where domain_name= ?";

		Data data = null;
		try {
			data = jdbcTemplate.queryForObject(sql,
					new Object[] { domainName }, new DataMapper());
		} catch (Exception e) {
			// No user was found with the specified id, return null
			return null;
		}
		return data;
	}

	public List<Data> getAll() {
		String sql = "select * from data;";

		List<Data> rooms = new ArrayList<Data>();
		try {
			rooms = jdbcTemplate.query(sql, new DataMapper());
			return rooms;
		} catch (Exception e) {
			return rooms;
		}
	}

}
