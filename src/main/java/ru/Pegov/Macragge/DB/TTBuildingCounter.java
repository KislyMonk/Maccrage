package ru.Pegov.Macragge.DB;

import java.util.List;

/**
 *
 * @author Андрей
 */
public class TTBuildingCounter {
    private int clients = 1;
    private List<CountTTbyDayDTO> months = null;
    private String name;
    
    public TTBuildingCounter(String name){
        this.name = name;
    }
    
    public void incClients(){
        clients++;
    }

    public void setMonth(List<CountTTbyDayDTO> list){
        months = list;
    }

    public String getName() {
        return name;
    }

    public List<CountTTbyDayDTO> getMonths() {
        return months;
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        
        str.append(name);
        str.append("\t");
        str.append(clients);
        str.append("\t");
        
        if(!months.isEmpty()){
            for(int i = 1; i <= 12; i++){
                boolean isWrite = false;
                for(CountTTbyDayDTO m : months){
                    if(m.getDateAsDouble()== i){
                        str.append(m.getCount());
                        str.append("\t");
                        isWrite= true;
                        break;
                    }
                }
                if(!isWrite){
                    str.append(0);
                    str.append("\t");
                }
            }
        }
        
        str.append("\n");
        return str.toString();
    }
}
