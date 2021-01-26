package repositories;

import entities.Event;
import java.util.*;


public class EventRepository extends GenericRepository<Event> {

    private static final String HIBERNATE_SELECT_QUERY = "from Event";

    public Event findOne(Integer id) {
        return super.findOne(id, Event.class);
    }

    public List<Event> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Event.class);
    }

}
