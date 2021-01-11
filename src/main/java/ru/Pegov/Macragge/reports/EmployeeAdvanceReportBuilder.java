package ru.Pegov.Macragge.reports;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;
import ru.Pegov.Macragge.DB.TroubleTicket;

/**
 *
 * @author Андрей
 */
public class EmployeeAdvanceReportBuilder implements ReportBuilder{
    List<TroubleTicket> ttList;
    HashMap<String, EmployeeReportBuilder.Employee> result = new HashMap<>();
    
    @Override
    public void setDateRange(Timestamp from, Timestamp to) {
        TTManager ttm = new TTManagerDAO();
        
        ttList = ttm.getTTs(from, to);
    }

    @Override
    public void setAdditionalData(HashMap<String, Object> data) throws UnknownDataExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Report getReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
