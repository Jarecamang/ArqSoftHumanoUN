package DataAccess.DAO;

import DataAccess.Entity.Areaofinterest;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Edwin
 */
@Stateless
public class InterestAreaDAO {

    @PersistenceContext(unitName = "TalentoHumanoPU")
    private EntityManager em;

    public Areaofinterest persist(Areaofinterest area) {
        em.persist(area);
        return area;
    }

    public List<Areaofinterest> getAllAreasOfInterest() {

        List<Areaofinterest> areaObject = null;
        Query q = em.createNamedQuery("Areaofinterest.findAll");
        try {
            areaObject = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return areaObject;
        }
    }

}
