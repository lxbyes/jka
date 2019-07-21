package me.leckie.jka.multidatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

/**
 * @author Leckie
 * @version JDBCDirectTest.java, v0.1 2019/7/22 1:07
 */
public class JDBCDirectTest {

  @Test
  public void testDirectConnection() throws ClassNotFoundException, SQLException {
    // 加载驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager
        .getConnection("jdbc:mysql://127.0.0.1:3306/bytetcc_provider?useUnicode=true&characterEncoding=utf-8", "root",
            "root");
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("select name from account");
    while (rs.next()) {
      System.out.println(rs.getString("name"));
    }
    rs.close();
    statement.close();
    connection.close();
  }

}
