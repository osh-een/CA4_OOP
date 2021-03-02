package com.dkit.oop.sd2.BusinessObjects;

/** OOP 2021
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses DAOs, Data Transfer Objects (DTOs), and
 * a DaoInterface to define a contract between Business Objects
 * and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here we use one DAO per database table.
 *
 * Use the SQL script included with this project to create the
 * required MySQL user_database and user table
 */



import com.dkit.oop.sd2.DAOs.MySqlUserDao;
import com.dkit.oop.sd2.DAOs.UserDaoInterface;
import com.dkit.oop.sd2.DTOs.User;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;


public class App
{

    public static void main(String[] args)
    {
        UserDaoInterface IUserDao = new MySqlUserDao();  //"IUse..." -> "I" for Interface
//        // Notice that the userDao reference is an Interface type.
//        // This allows for the use of different concrete implementations.
//        // e.g. we could replace the MySqlUserDao with an OracleUserDao
//        // (accessing an Oracle Database)
//        // without changing anything in the Interface.
//        // If the Interface doesn't change, then none of the
//        // code below that uses the interface needs to change.
//        // The 'contract' defined by the interface will not be broken.
//        // This means that this code is independent of the code
//        // used to access the database. (Reduced coupling).
//
//        // The Business Objects require that all User DAOs implement
//        // the interface called "UserDaoInterface", as the code uses
//        // only references of the interface type to access the DAO methods.
        try
        {
            System.out.println("\nCall findAllUsers()");
            List<User> users = IUserDao.findAllUsers();

            if( users.isEmpty() )
                System.out.println("There are no Users");

            for( User user : users )
                System.out.println("User: " + user.toString());

            // test dao - with good username and password
            System.out.println("\nCall: findUserByUsernamePassword()");
            User user = IUserDao.findUserByUsernamePassword("smithj", "password");
            if(user != null)
                System.out.println("User found: " + user);
            else
                System.out.println("Username with that password not found");

            // test dao - with bad username
            user = IUserDao.findUserByUsernamePassword("madmax", "thunderdome");
            if(user != null)
                System.out.println("User found: " + user);
            else
                System.out.println("Username with that password not found");

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }



    }
}
