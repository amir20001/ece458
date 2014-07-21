package com.ece458.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ece458.domain.Data;

public class DataMapper implements RowMapper<Data> {

	@Override
	public Data mapRow(ResultSet resultSet, int line) throws SQLException {
		Data data = new Data();
		try {
			data.map(resultSet);
		} catch (Exception e) {

		}
		return data;
	}
}