package DB_test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import ru.Pegov.Macragge.DB.TTManagerDAO;

/**
 *
 * @author Андрей
 */
public class SingleTest {
    
    public SingleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Ignore
    @Test
    public void hello() {
        TTManagerDAO ttm = new TTManagerDAO();
        ttm.getCountTTByAddressByMonth("Октябрьская, д. 5/1", 2019).forEach(System.out::println);
    }
}
