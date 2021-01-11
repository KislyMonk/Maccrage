package DB_test;

import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Андрей
 */
public class Date_Test {
    
    public Date_Test() {
    }
    @Ignore
    @Test
     public void date_Test() {
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTimeInMillis(1559588399000L);
         System.out.println(gc.getTime());
     }
}
