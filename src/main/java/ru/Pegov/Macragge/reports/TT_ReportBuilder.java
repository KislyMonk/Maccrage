/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.reports;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;
import ru.Pegov.Macragge.DB.TroubleTicket;
import ru.Pegov.Macragge.reports.models.CityModel;

/**
 *
 * @author Андрей
 */
public class TT_ReportBuilder implements ReportBuilder{
    private List<TroubleTicket> ttList = new ArrayList<>();
    private HashMap<String,CityModel> cityModels = new HashMap<>();
    private Timestamp from = null;
    private Timestamp to = null;
    
    //date from and to varibles
    private GregorianCalendar min ;
    private GregorianCalendar max ;
    
    //for remove from result, some citys must be hidden
    private List<CityModel> toRemove = new ArrayList<>();
    
    //for additional info about TT
    private List<String> repOn3LTPMGN = new ArrayList<String>();
    private List<String> timeMoreThen24h = new ArrayList<String>();
    private List<String> repOn3LTP = new ArrayList<String>();
    private Double allTimeon2ltp = 0.0;
    private Double allTimeon3ltp = 0.0;
    private int allTT = 0;
    private int allTT3LTP = 0;
    
    
    public TT_ReportBuilder(){
        this.createCityList();
    }
    
    @Override
    public void setDateRange(Timestamp from, Timestamp to) {
        this.from = from;
        this.to = to;
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
            if(cityModels.containsKey(tt.getCity())){
                CityModel cm = cityModels.get(tt.getCity());
                cm.incCountL2TP();
                allTT++;
                
                if(!tt.getStatus().equalsIgnoreCase("Завершен") || tt.getEndingDate().after(to)){
                    cm.incAtWork();
                    if(tt.getStart3LTP() != null){
                        cm.incAtWorkL3();
                    }else{
                        cm.incAtWorkL2();
                    }
                }
                
                //replace null data by same time
                if(tt.getEndingDate() == null){
                    tt.setEndingDate(to);
                }
                
                if(tt.getDesdecisionTime() == null ){
                    tt.setDesdecisionTime(tt.getOpeningDate());
                }
                
                
                long timeInWork =tt.getEndingDate().getTime() - tt.getDesdecisionTime().getTime();
                
                //count TT by timeInWork
                if(timeInWork <= 36*60*60*1000){
                    cm.incCompleateAt36h();
                }else{
                    if(timeInWork <= 48*60*60*1000){
                        cm.incCompleateAt48h();
                    }else{
                        if(timeInWork <= 72*60*60*1000){
                            cm.incCompleateAt72h();
                        }else{
                            cm.incCompleateAtMore72h();
                        }
                    }
                }
                
                //time on 2LTP
                allTimeon2ltp = allTimeon2ltp + tt.getTimeOn2LTP()/60/60;
                cm.incTotalTimeL2TP(tt.getTimeOn2LTP()/60/60);
                if(tt.getTimeOn2LTP() <= 6*60*60){
                    cm.incCompleateAt6hL2();
                }else{
                    if(tt.getTimeOn2LTP() <= 8*60*60){
                        cm.incCompleateAt8hL2();
                    }else{
                        if(tt.getTimeOn2LTP() <= 24*60*60){
                            cm.incCompleateAt24hL2();
                        }else{
                            cm.incCompleateAtMore24hL2();
                        }
                    }
                }
                
                //Time on 3LTP
                cm.incTotalTimeL3TP(tt.getTimeOn3LTP());
                allTimeon3ltp = allTimeon3ltp + tt.getTimeOn3LTP();
                if(tt.getTimeOn3LTP() > 0){
                    allTT3LTP++;
                    cm.incCountL3TP();
                    if(tt.getTimeOn3LTP() <= 36){
                        cm.incCompleateAt36hL3();
                    }else{
                        if(tt.getTimeOn3LTP() <= 48){
                            cm.incCompleateAt48hL3();
                        }else{
                            if(tt.getTimeOn3LTP() <= 72){
                                cm.incCompleateAt72hL3();
                            }else{
                                cm.incCompleateAtMore72hL3();
                            }
                        }
                    }
                }

                
                if(tt.getTimeOn3LTP() >= 24){
                    if(tt.getCity().equalsIgnoreCase("Магнитогорск (МИ)")){
                        timeMoreThen24h.add(tt.getId());
                    }
                }
                
                //count repeated
                if(!tt.getLastTTFrom().equalsIgnoreCase("")){
                    String lastTTOF = tt.getLastTTFrom().substring(
                            tt.getLastTTFrom().length()-4
                            , tt.getLastTTFrom().length()
                    );
                    
                    if(lastTTOF.equalsIgnoreCase("2ЛТП")){
                        cm.incRepeatedTTL2();
                    }else{
                        if(tt.getTimeOn3LTP() > 0){
                            cm.incRepeatedTTL3();
                            if(tt.getCity().equalsIgnoreCase("Магнитогорск (МИ)")){
                                repOn3LTPMGN.add(tt.getId());
                            }else{
                                repOn3LTP.add(tt.getId());
                            }
                        }
                    }
                }
                
                cm.incDecisionToTransfer(tt.getLastTransferOn2LTP().getTime() - tt.getDesdecisionTime().getTime());
            }
        }
        return new TT_Report();
    }
    
    private void createCityList(){
        //full list of URALs Citys
        cityModels.put("Алапаевск", new CityModel("Алапаевск","МР Урал"));
        cityModels.put("Артемовский", new CityModel("Артемовский","МР Урал"));
        cityModels.put("Березники", new CityModel("Березники","МР Урал"));
        cityModels.put("Демьянка", new CityModel("Демьянка","МР Урал"));
        cityModels.put("Дружино", new CityModel("Дружино","МР Урал"));
        cityModels.put("Екатеринбург", new CityModel("Екатеринбург","МР Урал"));
        cityModels.put("Ишим", new CityModel("Ишим","МР Урал"));
        cityModels.put("Каменск-Уральский", new CityModel("Каменск-Уральский","МР Урал"));
        cityModels.put("Камышлов", new CityModel("Камышлов","МР Урал"));
        cityModels.put("Кузино", new CityModel("Кузино","МР Урал"));
        cityModels.put("Куть-Ях", new CityModel("Куть-Ях","МР Урал"));
        cityModels.put("Нижневартовск", new CityModel("Нижневартовск","МР Урал"));
        cityModels.put("Нижний Тагил", new CityModel("Нижний Тагил","МР Урал"));
        cityModels.put("Пермь", new CityModel("Пермь","МР Урал"));
        cityModels.put("Пыть-ях", new CityModel("Пыть-ях","МР Урал"));
        cityModels.put("Реж", new CityModel("Реж","МР Урал"));
        cityModels.put("Салым", new CityModel("Салым","МР Урал"));
        cityModels.put("Серов", new CityModel("Серов","МР Урал"));
        cityModels.put("Соликамск", new CityModel("Соликамск","МР Урал"));
        cityModels.put("Сургут", new CityModel("Сургут","МР Урал"));
        cityModels.put("Тобольск", new CityModel("Тобольск","МР Урал"));
        cityModels.put("Тюмень", new CityModel("Тюмень","МР Урал"));
        cityModels.put("Чусовой", new CityModel("Чусовой","МР Урал"));
        cityModels.put("Югорск", new CityModel("Югорск","МР Урал"));
        cityModels.put("Бакал", new CityModel("Бакал","МР Ю-Урал"));
        cityModels.put("Вязовая", new CityModel("Вязовая","МР Ю-Урал"));
        cityModels.put("Еманжелинск", new CityModel("Еманжелинск","МР Ю-Урал"));
        cityModels.put("Златоуст", new CityModel("Златоуст","МР Ю-Урал"));
        cityModels.put("Карабаш", new CityModel("Карабаш","МР Ю-Урал"));
        cityModels.put("Карталы", new CityModel("Карталы","МР Ю-Урал"));
        cityModels.put("Касли", new CityModel("Касли","МР Ю-Урал"));
        cityModels.put("Копейск", new CityModel("Копейск","МР Ю-Урал"));
        cityModels.put("Коркино", new CityModel("Коркино","МР Ю-Урал"));
        cityModels.put("Кувандык", new CityModel("Кувандык","МР Ю-Урал"));
        cityModels.put("Курган", new CityModel("Курган","МР Ю-Урал"));
        cityModels.put("Кыштым", new CityModel("Кыштым","МР Ю-Урал"));
        cityModels.put("Магнитогорск", new CityModel("Магнитогорск","МР Ю-Урал"));
        cityModels.put("Новогорный", new CityModel("Новогорный","МР Ю-Урал"));
        cityModels.put("Озерск", new CityModel("Озерск","МР Ю-Урал"));
        cityModels.put("Орск", new CityModel("Орск","МР Ю-Урал"));
        cityModels.put("Полетаево", new CityModel("Полетаево","МР Ю-Урал"));
        cityModels.put("Сатка", new CityModel("Сатка","МР Ю-Урал"));
        cityModels.put("Снежинск", new CityModel("Снежинск","МР Ю-Урал"));
        cityModels.put("Троицк", new CityModel("Троицк","МР Ю-Урал"));
        cityModels.put("Чебаркуль", new CityModel("Чебаркуль","МР Ю-Урал"));
        cityModels.put("Челябинск", new CityModel("Челябинск","МР Ю-Урал"));
        cityModels.put("Южноуральск", new CityModel("Южноуральск","МР Ю-Урал"));
        cityModels.put("Оренбург (Телесот)", new CityModel("Оренбург (Телесот)","МР Ю-Урал"));
        cityModels.put("Магнитогорск (МИ)", new CityModel("Магнитогорск (МИ)","ЗАО МАГИНФО"));
    }
    
    private class TT_Report implements Report{
        List<CityModel> cityList = new ArrayList<CityModel>(cityModels.values()
                .stream()
                .sorted((cm1, cm2) -> cm1.getName().compareToIgnoreCase(cm2.getName()))
                .collect(Collectors.toList())
        );
        
        public List<CityModel> getCityList() {
            return cityList;
        }
        
        public List<String> getRepeated3LTPMGN(){
            return repOn3LTPMGN;
        }
        
        public List<String> getRepeated3LTP(){
            return repOn3LTP;
        }
        
        public List<String> getTimeMoreThen24h(){
            return timeMoreThen24h;
        }
        
        public Double getAvgTimeOn2LTP(){
            return allTimeon2ltp / allTT;
        }
        
        public Double getAvgTimeOn3LTP(){
            return allTimeon3ltp / allTT3LTP;
        }
    }
}
