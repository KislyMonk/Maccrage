package ru.Pegov.Macragge.reports;

import java.sql.Timestamp;
import java.util.HashMap;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;

/**
 *
 * @author Андрей
 */
public class LastClosedDateBuilder implements ReportBuilder{
    Timestamp result = null;
    
    /**
     * 
     * @param from
     * @param to
     */
    @Override
    public void setDateRange(Timestamp from, Timestamp to) {
        
    }

    /**
     * @deprecated 
     * @param data
     * @throws UnknownDataExeption
     */
    @Override
    public void setAdditionalData(HashMap<String, Object> data) throws UnknownDataExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return Max date from closed TT
     */
    @Override
    public Report getReport() {
        TTManager ttm = new TTManagerDAO();
        return new MaxDate(ttm.getLastClosedDate());
    }
    
    protected class MaxDate implements Report{
        Timestamp maxDate = null;
        
        public MaxDate(Timestamp maxDate){
            this.maxDate = maxDate;
        }

        public Timestamp getMaxDate() {
            return maxDate;
        }
        
        public Long gettMaxDateLong(){
            return maxDate.getTime();
        }
    }
}
