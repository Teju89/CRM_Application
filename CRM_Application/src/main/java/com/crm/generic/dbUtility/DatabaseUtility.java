package com.crm.generic.dbUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crm.generic.entity.Organization;

public class DatabaseUtility {

	private Connection connection;
	private PreparedStatement preparedStatement;

	public void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() throws Exception {
		connection.close();
	}

	public List<Organization> getAllOrganization() throws SQLException {
		String query = "Select * From Organization";
		preparedStatement = connection.prepareStatement(query);
		ResultSet executeQuery = preparedStatement.executeQuery();
		List<Organization> organizations = new ArrayList<Organization>();
		if (executeQuery.isBeforeFirst()) {
			while (executeQuery.next()) {
				Organization organization = new Organization();
				organization.setOrgName(executeQuery.getString("OrgName"));
				organization.setIndustry(executeQuery.getString("industry"));
				organization.setType(executeQuery.getString("type"));
				organizations.add(organization);
			}
			return organizations;
		}
		return new ArrayList<Organization>();
	}
}
