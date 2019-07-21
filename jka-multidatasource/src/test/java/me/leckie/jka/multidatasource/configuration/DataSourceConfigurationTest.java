package me.leckie.jka.multidatasource.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version DataSourceConfigurationTest.java, v0.1 2019/7/22 0:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataSourceConfigurationTest {

  @Autowired
  @Qualifier("accountDataSource")
  private DataSource dataSource;

  @Test
  public void testConnection() throws SQLException {
    Connection connection = dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("select * from account");
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      System.out.println(resultSet.getString("name"));
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();
  }
}