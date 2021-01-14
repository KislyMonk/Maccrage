package ru.Pegov.test.DB_test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import ru.Pegov.Macragge.reports.MostTTsByBuilding.Buildings;
import ru.Pegov.Macragge.reports.MostTTsByBuilding.MostTTsByBuildingReport;
import ru.Pegov.Macragge.reports.ReportBuilder;
import ru.Pegov.Macragge.reports.ReportBuilderFactory;
import ru.Pegov.Macragge.reports.UnknownDataExeption;

/**
 *
 * @author Андрей
 */
public class TT_By_Building_Test {
    
    public TT_By_Building_Test() {
    }
    @Ignore
    @Test
    public void showTestList() {
        HashMap<String,Object> addParametrs = new HashMap<>();
        
        addParametrs.put("city", "Магнитогорск (МИ)");
        addParametrs.put("isOnly3LTP", false);
        
        try {
            ReportBuilder rb = (new ReportBuilderFactory()).getReportBuilder("BuildingsReport");
            
            rb.setDateRange(new Timestamp(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("01/01/2019 00:00:00").getTime()),
                    new Timestamp(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("07/01/2019 00:00:00").getTime())
            );
            
            rb.setAdditionalData(addParametrs);
            
            MostTTsByBuildingReport rep = (MostTTsByBuildingReport)rb.getReport();
            
            List<Buildings> buildingsList = (List<Buildings>) rep.getReportBody();
            
            for(int i = 0; i < 10; i++){
                System.out.println(buildingsList.get(i).getName() + " : " + buildingsList.get(i).getCount());
            }
            
            System.out.println("Итого: " + buildingsList.size());
        } catch (UnknownDataExeption ex) {
            Logger.getLogger(TT_By_Building_Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TT_By_Building_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
