package ru.Pegov.test.DB_test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;
import ru.Pegov.Macragge.DB.*;

/**
 *
 * @author Андрей
 */
public class AddNewTT_Test {
    
    public AddNewTT_Test() {
        
    }

    @Ignore
    @Test
    public void testAdd() {
        TTManager TT_DB = new TTManagerDAO();
        TroubleTicket tt = new TroubleTicket("ТX-2251488 [О-2254381]",
                "МР Урал1",
                "Магнитогорск", 
                "АБ требуется Консультация по настройке роутера",
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                "В работе", 
                "Пегов", 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                2);
        
        assertTrue(TT_DB.addTT(tt));
    }
    @Ignore
    @Test
    public void testAddList(){
        TTManager TT_DB = new TTManagerDAO();
        List ttList = new ArrayList<TroubleTicket>();
        ttList.add(new TroubleTicket("ТX-2254515 [О-2254381]",
                "МР Урал1",
                "Магнитогорск", 
                "АБ требуется Консультация по настройке роутера",
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                "В работе", 
                "Пегов", 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                2));
        ttList.add(new TroubleTicket("ТX-1000500 [О-2254381]",
                "МР Урал2",
                "Магнитогорск", 
                "АБ требуется Консультация по настройке роутера",
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                "В работе", 
                "Пегов", 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar()).getTimeInMillis()), 
                2));
        
        assertTrue(TT_DB.addArrayTT(ttList));
    }
    @Ignore
    @Test
    public void testShow(){
        (new TTManagerDAO()).getTTs(
                new Timestamp((new GregorianCalendar(2018, 9, 28)).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar(2018, 11, 30)).getTimeInMillis()))
            .forEach(System.out::println);
    }
    @Ignore
    @Test
    public void deleteTest(){
        TTManager TT_DB = new TTManagerDAO();
        TT_DB.deleteTT("ТX-2251488 [О-2254381]");
        TT_DB.deleteTT("ТX-2254515 [О-2254381]");
        TT_DB.deleteTT("ТX-1000500 [О-2254381]");
    }
}
