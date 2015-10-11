package com.example.subhransumishra.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.utils.SystemProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "userApi",
        version = "v1",
        resource = "user",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.subhransumishra.example.com",
                ownerName = "backend.myapplication.subhransumishra.example.com",
                packagePath = ""
        )
)
public class UserEndpoint {

    private static final Logger logger = Logger.getLogger(UserEndpoint.class.getName());
    String url = "";

    public Void initdb() {
        try {
            if (SystemProperty.environment.value() ==
                    SystemProperty.Environment.Value.Production) {
                // Connecting from App Engine.
                // Load the class that provides the "jdbc:google:mysql://"
                // prefix.
                try {
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                url = "jdbc:google:mysql://papypalhack:milkmoney/milk_money?user=root";
            } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } // Connecting from an external network.
                url = "jdbc:mysql://173.194.232.121:3306/milk_money?user=paypal&password=paypal";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method gets the <code>User</code> object associated with the specified <code>id</code>.
     *
     * @param phone The id of the object to be returned.
     * @return The <code>User</code> associated with <code>id</code>.
     */

    @ApiMethod(name = "getUser")
    public User getUser(@Named("phone") String phone) {
        initdb();
        Connection conn = null;
        User act = new User();
        logger.info("Calling getUser method");

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "SELECT `password` FROM `user` WHERE home_phone='" + phone + "';";

        ResultSet rest = null;
        try {
            if (conn != null) {
                rest = conn.createStatement().executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String tmpstr2 = null;
        try {
            if (rest != null) {
                if (rest.next()) {
                    tmpstr2 = rest.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("The user is " + tmpstr2);
        try {
            if (rest != null) {
                System.out.println(rest);
                act.setPassword(rest.getString("password"));
                act.setRole_id(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return act;
    }

    @ApiMethod(name = "insertEvent")
    public void insertEvent(@Named("name") String name, @Named("Desc") String desc) {
        logger.info("Calling insertEvent method");

        initdb();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int randomNum = 1 + (int)(Math.random()*999999);
        String query = "INSERT INTO `event` (`id`,`title`, `description`,`date_time`, `num_rsvped`, `num_attended`, `after_report`) VALUES ('"+randomNum+"','"+name+"','"+desc+"','"+"2515-10-11 00:00:00"+"','"+2+"','"+3+"','"+"You"+"');";
        System.out.println(query);
        //INSERT INTO `milk_money`.`event` (`title`, `description`, `date_time`) VALUES ('b', 's', '43');

        ResultSet rest = null;
        try {
            if (conn != null) {
                conn.createStatement().execute(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}