package repositories;

import entities.Time;
import java.util.*;


public class TimeRepository extends GenericRepository<Time> {

    private static final String HIBERNATE_SELECT_QUERY = "from Time";

    public Time findOne(Integer id) {
        return super.findOne(id, Time.class);
    }

    public List<Time> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Time.class);
    }

}
