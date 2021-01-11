/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_test;

import java.sql.Timestamp;
import static java.sql.Timestamp.from;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;
import ru.Pegov.Macragge.DB.TroubleTicket;

/**
 *
 * @author Андрей
 */
public class DB_TEST {
    
    public DB_TEST() {
    }
    
    
    @Test
    public void random3LTP() {
        TTManagerDAO ttm = new TTManagerDAO();
        
        try {
            ttm.getCountByDays(
                    new Timestamp(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("07/01/2019 00:00:00").getTime()),
                    new Timestamp(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("07/01/2019 00:00:00").getTime())
            );
        } catch (ParseException ex) {
            Logger.getLogger(DB_TEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void lastDate(){
        TTManagerDAO ttm = new TTManagerDAO();
        
        Timestamp ts = ttm.getLastClosedDate();
        
        System.out.println("DB_test.DB_TEST.lastDate(): " + ts.toString());
        
        assertNotNull(ts);
    }
}
