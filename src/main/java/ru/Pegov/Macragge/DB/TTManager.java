package ru.Pegov.Macragge.DB;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Андрей
 */
public interface TTManager {

    /**
     * Add or update single TT
     * @param tt - TroubleTicket that need to add|update
     * @return
     */
    boolean addTT(TroubleTicket tt);

    /**
     * Add or update a lot of TT
     * @param ttList - TroubleTickets that need to add|update
     * @return
     */
    boolean addArrayTT(List<TroubleTicket> ttList);

    /**
     * Return list of TroubleTickets. That opened, closed or stay in work, for period from to
     * @param from
     * @param to
     * @return List of TroubleTicket
     */
    List<TroubleTicket> getTTs(Timestamp from, Timestamp to);
    
    /**
     * Delete a single TroubleTicket by ID
     * @param id - String
     * @return boolean true - deleted, false - not 
     */
    public boolean deleteTT(String id);
    
    /**
     * 
     * @param from
     * @param to
     * @return
     */
    public List<CountTTbyDayDTO> getCountByDays(Timestamp from, Timestamp to);
    
    /**
     * 
     * 
     * @return last closed Date
     */
    public Timestamp getLastClosedDate();
}
