package repositories;

import entities.Description;
import java.util.*;


public class DescriptionRepository extends GenericRepository<Description> {

    private static final String HIBERNATE_SELECT_QUERY = "from Description";

    public Description findOne(Integer id) {
        return super.findOne(id, Description.class);
    }

    public List<Description> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Description.class);
    }

}
