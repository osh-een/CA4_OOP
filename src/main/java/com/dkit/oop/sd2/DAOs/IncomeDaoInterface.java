package com.dkit.oop.sd2.DAOs;

/** OOP Feb 2022
 * UserDaoInterface
 *
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User DAOs or Oracle User DAOs etc...
 *
 * Classes from the Business Layer (users of this DAO interface)
 * should use reference variables of this interface type to avoid
 * dependencies on the underlying concrete classes (e.g. MySqlUserDao).
 *
 * More sophisticated implementations will use a factory
 * method to instantiate the appropriate DAO concrete classes
 * by reading database configuration information from a
 * configuration file (that can be changed without altering source code)
 *
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */

import com.dkit.oop.sd2.DTOs.Income;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;

public interface IncomeDaoInterface {
    public List<Income> findAllIncomes() throws DaoException;

    public List<Double> findAllIncomesAndCalcutateGain() throws DaoException;

    public void addIncome() throws DaoException;

    public void deleteIncome() throws DaoException;

    public List<Double> findMonthlyIncome(int month, int year) throws DaoException;
}
