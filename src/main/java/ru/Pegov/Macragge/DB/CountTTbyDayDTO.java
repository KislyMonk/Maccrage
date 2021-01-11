package ru.Pegov.Macragge.DB;

import java.sql.Timestamp;

/**
 * Represent one row of dounting query
 * @author Андрей
 */
public class CountTTbyDayDTO {
    String date;
    Long count;

    public String getDate() {
        return date;
    }

    public Long getCount() {
        return count;
    }
    
    public double getDateAsDouble(){
        return Double.parseDouble(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AvailableDateDTO{" + "date=" + date + ", count=" + count + '}';
    }
}
