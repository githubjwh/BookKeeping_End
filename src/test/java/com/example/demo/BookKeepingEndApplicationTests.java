package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookKeepingEndApplicationTests {
	@Autowired
	DataSource dataSource;
	@Test
	void contextLoads() {
		Connection conn;
		try {
			conn = dataSource.getConnection();
			System.err.println(conn);
			}catch (SQLException e) {
				e.printStackTrace();
				}
	}
}
