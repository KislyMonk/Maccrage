/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.reports;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;
import ru.Pegov.Macragge.DB.TroubleTicket;

/**
 * Create a report that definition are buildings, with the most complaints
 * @author Андрей
 */
public class MostTTsByBuilding implements ReportBuilder{
    private List<TroubleTicket> ttList = new ArrayList<>();
    private Map<String,Buildings> result = new HashMap<>();
    private String city = "Магнитогорск (МИ)";
    private boolean isOnly3LTP = false;
    private Timestamp from = null;
    private Timestamp to = null;
    
    /**
     * Default constructor
     */
    public MostTTsByBuilding() {
        
    }

    /**
     * Set date range to define report
     * @param from
     * @param to
     */
    @Override
    public void setDateRange(Timestamp from, Timestamp to) {
        this.from = from;
        this.to = to;
        
        
    }

    /**
     * Not uses at this report
     * @param data
     * @throws UnknownDataExeption
     */
    @Override
    public void setAdditionalData(HashMap<String, Object> data) throws UnknownDataExeption{
        if(data.containsKey("city")){
            city = (String)data.get("city");
        }else{
            throw  new UnknownDataExeption("parametr city is miss");
        }
        
        if(data.containsKey("isOnly3LTP")){
            isOnly3LTP = (boolean)data.get("isOnly3LTP");
        }else{
            throw  new UnknownDataExeption("parametr isOnly3LTP is miss");
        }
    }

    /**
     * Create and return report object
     * @return
     */
    @Override
    public Report getReport() {
        
        if(from != null && to != null){
            TTManager ttm = new TTManagerDAO();
        
            ttList = ttm.getTTs(from, to)
                    .stream()
                    .filter((tt) -> tt.getCity().equalsIgnoreCase(city))
                    .filter(tt -> tt.getTimeOn3LTP() != null)
                    .collect(Collectors.toList());
        }
        
        for(TroubleTicket tt : ttList ){
            String address = tt.getAddress();
            
            if(address.length() != 0){
                address = address.replaceFirst(
                        address.substring(address.lastIndexOf(","), address.length()),
                        "");
                
                if(result.containsKey(address)){
                    result.get(address).incTT(tt);
                }else{
                    result.put(address, new Buildings(address, tt));
                }
            }
        }
        
        for(int i = 0; i<10;i++){
            
        }
        
        return new MostTTsByBuildingReport();
    }
    
    /**
     * Report object 
     */
    public class MostTTsByBuildingReport implements Report{
        
        public Object getReportBody() {
            
            List<Buildings> outRes = result.values()
                    .stream()
                    .sorted(Comparator.comparingInt(Buildings::getCount).reversed())
                    .collect(Collectors.toList());
            
            for(int i = 0; i < 10; i++){
                System.out.println(outRes.get(i).getName());
                outRes.get(i).TTList.stream().forEach(tt -> {System.out.println("\t" + tt.getClientId() + "\t" + tt.getId() + "\t" + tt.getReason());});
            }
            
            return  outRes;
        }
    }
    
    public class Buildings{
        private String name = "";
        private List<TroubleTicket> TTList = new ArrayList<>();
        
        public Buildings(String name, TroubleTicket tt){
            this.name = name;
            TTList.add(tt);
        }
        
        public void incTT(TroubleTicket tt){
            TTList.add(tt);
        }
        
        public Integer getCount(){
            return TTList.size();
        }

        public String getName() {
            return name;
        }

        public List<TroubleTicket> getTTList() {
            return TTList;
        }
        
        
    }
}
