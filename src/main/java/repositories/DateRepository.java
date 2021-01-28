package repositories;

import entities.Date;
import java.util.*;


public class DateRepository extends GenericRepository<Date> {

    private static final String HIBERNATE_SELECT_QUERY = "from Date";

    public Date findOne(Integer id) {
        return super.findOne(id, Date.class);
    }

    public List<Date> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Date.class);
    }

}
