/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.reports;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;
import ru.Pegov.Macragge.DB.TroubleTicket;

/**
 *
 * @author Андрей
 */
public class EmployeeReportBuilder  implements ReportBuilder{
    List<TroubleTicket> ttList;
    HashMap<String, Employee> result = new HashMap<>();

    @Override
    public void setDateRange(Timestamp from, Timestamp to) {
        TTManager ttm = new TTManagerDAO();
        
        ttList = ttm.getTTs(from, to);
    }

    /**
     * Not uses at this report
     * @deprecated 
     * @param data
     * @throws UnknownDataExeption
     */
    @Override
    public void setAdditionalData(HashMap<String, Object> data) throws UnknownDataExeption{
        throw new UnknownDataExeption("This method is not supported at this report");
    }

    @Override
    public Report getReport() {
        
        
        for(TroubleTicket tt : ttList){
            if(result.containsKey(tt.getEmployee())){
                result.get(tt.getEmployee()).incCount();
            }else{
                result.put(tt.getEmployee(), new Employee(tt.getEmployee()));
            }
        }
        
        return new EmployeeReport();
    }
    
    class EmployeeReport implements Report{

        
        public Object getReportBody() {
            result.remove("");
            return result.values()
                    .stream()
                    .sorted(Comparator.comparingInt(Employee::getCount).reversed())
                    .collect(Collectors.toList());
        }
            
    }
    
    public class Employee{
        String name = "";
        Integer count = 1;
        
        public Employee (String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Integer getCount() {
            return count;
        }
        
        public void incCount(){
            count++;
        }
        
        public int compareToName(Employee o) {
            return name.compareTo(o.getName());
        }
        
        public int compareToCount(Employee o) {
            return count.compareTo(o.getCount());
        }
        
    }
}
