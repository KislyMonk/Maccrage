package ru.Pegov.test.DB_test;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.Ignore;
import ru.Pegov.Macragge.DB.*;


/**
 *
 * @author Андрей
 */
public class GetAllTest {
    private int errCount = 0;
    public GetAllTest() {
    }
    
    protected class City{
        String cityName = "";
        long timeon2LTP = 0L;
        int count = 0;
        int count3LTP = 0;
        long timeon3LTP = 0;
        int count2LTP6h = 0;
        int count3LTP36h = 0;
        public City(String cityName){
            this.cityName = cityName;
        }
        @Ignore
        @Override
        public String toString(){
            return cityName + " \t"
                    + (((double)timeon2LTP/1000/60/60)/count) + " \t"
                    + count + " \t"
                    + count3LTP + " \t"
                    + (((double)timeon3LTP/1000/60/60)/count3LTP) + " \t"
                    + count2LTP6h + " \t"
                    + count3LTP36h ;
                    
        }
    }
    
    private void makeCity(City c, TroubleTicket tt){
        
        if(tt.getDesdecisionTime() == null){
            tt.setDesdecisionTime(tt.getOpeningDate());
        }
        
        if(tt.getStart3LTP() != null && tt.getEnd3LTP() != null){
                    c.count3LTP++;
                    c.timeon3LTP +=  (tt.getEnd3LTP().getTime() - tt.getStart3LTP().getTime());
                    c.timeon2LTP += (tt.getStart3LTP().getTime() - tt.getDesdecisionTime().getTime());
                    
                    if(((tt.getEnd3LTP().getTime() - tt.getStart3LTP().getTime()))
                            /1000/60/60 < 36){
                        c.count3LTP36h++;
                    }
                    
                    if(((tt.getStart3LTP().getTime() - tt.getDesdecisionTime().getTime()))
                            /1000/60/60 < 6){
                        c.count2LTP6h++;
                    }
                }else{
                    c.timeon2LTP += (tt.getEndingDate().getTime() - tt.getDesdecisionTime().getTime());
                    if(((tt.getEndingDate().getTime() - tt.getDesdecisionTime().getTime()))
                            /1000/60/60 < 6){
                        c.count2LTP6h++;
                    }
                }
    }
    @Ignore
     @Test
     public void hello() {
        List<TroubleTicket> tts = (new TTManagerDAO()).getTTs(
                new Timestamp((new GregorianCalendar(2018, 3, 0)).getTimeInMillis()), 
                new Timestamp((new GregorianCalendar(2018, 11, 30)).getTimeInMillis()));
        
        HashMap<String, City> cityMap = new HashMap<>();
        
        tts.forEach(tt -> {
            if(cityMap.containsKey("Итого")){
                City c = cityMap.get("Итого");
                c.count++;
                
                try{
                    makeCity(c, tt);
                }catch(NullPointerException e){
                    errCount++;
                    System.out.println(tt);
                }
                
                
            }else{
                City c =new City("Итого");
                c.count++;
                
                try{
                    makeCity(c, tt);
                }catch(NullPointerException e){
                    errCount++;
                    System.out.println(tt);
                }
                
                cityMap.put("Итого", c);
            }
        });
        
         System.out.println("ErrCount = " + errCount);
         
         System.out.println( "Город" + " \t"
                    + "Среднее время 2ЛТП ч." + " \t"
                    + "Количество 2ЛТП" + " \t"
                    + "Количество 3ЛТП" + " \t"
                    + "Среднее время 3ЛТП ч." + " \t"
                    + "Менее 6 часов 2ЛТП" + " \t"
                    + "Менее 36 часов 3ЛТП");
        for(Map.Entry<String,City> entry : cityMap.entrySet()){
            System.err.println(entry.getValue());
        }
     }
}
