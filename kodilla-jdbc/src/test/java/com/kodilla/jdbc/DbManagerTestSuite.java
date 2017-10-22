package com.kodilla.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManagerTestSuite {
    @Test
    public void testConnect() throws SQLException {
        //Given
        //When
        DbManager dbManager = DbManager.getInstance();
        //Then
        Assert.assertNotNull(dbManager.getConnection());
    }

    @Test
    public void testSelectUsersAndPosts() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();

        //When
        String sqlQuery = "SELECT U.FIRSTNAME AS FIRST_NAME, U.LASTNAME AS LAST_NAME, COUNT(*) AS POSTS_NUMBER " +
                "FROM POSTS P, USERS U " +
                "WHERE P.USER_ID = U.ID " +
                "GROUP BY U.ID " +
                "HAVING COUNT(*) >= 2;";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);


        //Then
        while (rs.next()) {
            System.out.println(
                    rs.getString("FIRST_NAME") + ", " +
                            rs.getString("LAST_NAME"));
            Assert.assertEquals("Barbara", rs.getString("FIRST_NAME"));
            Assert.assertEquals("Nowak", rs.getString("LAST_NAME"));
        }

        rs.close();
        statement.close();
    }
}
