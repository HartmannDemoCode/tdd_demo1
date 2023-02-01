package facade;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DBFacadeTest {
    Connection con = null;

    @BeforeEach
    void setUp() {
        System.out.println("TESTINNNNGGGG");
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            con.prepareStatement("DELETE FROM `startcode_test`.`usertable`").executeUpdate();

            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5, "Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    public void test()  {
        System.out.println("Testing database connection, to see first name");
        String sql = "SELECT fname FROM startcode_test.usertable";
        try  {
            Connection connection = con = DBconnector.connection();
            ResultSet set = con.prepareStatement(sql).executeQuery();
            set.next();
            String name = set.getString("fname");
            System.out.println("NAME::::::::: = " + name);
            assertEquals("Hans", name);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        assertTrue(true);
    }

}