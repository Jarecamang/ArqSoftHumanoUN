/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Contract;
import DataAccess.Entity.User;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
@Stateless
public class ContractDAO implements Serializable {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Contract persist(Contract contract) {
        //em = emf.createEntityManager();
        try {
            em.persist(contract);
            return contract;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Contract edit(Contract contract, int contractPosition) {
        return null;
    }

    public int getAmountOfSalariesEquals(double salary) {

        Query q = em.createNamedQuery("Contract.findBySalary");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesSmallerThan(double salary) {

        Query q = em.createNamedQuery("Contract.findBySalarySmallerThan");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesBiggerThan(double salary) {

        Query q = em.createNamedQuery("Contract.findBySalaryBiggerThan");
        q.setParameter("salary", salary);
        return q.getResultList().size();

    }

    public int getAmountOfSalariesBetween(double salarySmaller, double salaryBigger) {
        if (salarySmaller == 1000000) {
            return getAmountOfSalariesSmallerThan(salaryBigger) - getAmountOfSalariesSmallerThan(salarySmaller) + getAmountOfSalariesEquals(salarySmaller) + getAmountOfSalariesEquals(salaryBigger);
        } else {
            return getAmountOfSalariesSmallerThan(salaryBigger) - getAmountOfSalariesSmallerThan(salarySmaller) + getAmountOfSalariesEquals(salaryBigger);
        }
    }

    public Contract getUserContract(User id) {
        Query q = em.createNamedQuery("Contract.findByfkuserID");
        q.setParameter("fkuserID", id);
        Contract cont = (Contract) q.getSingleResult();
        return cont;
    }
}
