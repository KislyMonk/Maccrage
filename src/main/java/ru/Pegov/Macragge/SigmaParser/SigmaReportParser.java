/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.SigmaParser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.Pegov.Macragge.DB.TTManager;
import ru.Pegov.Macragge.DB.TTManagerDAO;
import ru.Pegov.Macragge.DB.TroubleTicket;
import ru.Pegov.Macragge.tools.ColumnDeterminant;

/**
 *
 * @author Андрей
 */
public class SigmaReportParser {
    //uniform format of date
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private List<TroubleTicket> tts = new ArrayList<TroubleTicket>();
    
    //need automatic determine column position by name
    private ColumnDeterminant cd = null;
    
    //resulting counters
    private int added = 0;
    private int notAdd = 0;
    
    public SigmaReportParser(){
        
    }
    
    public List<TroubleTicket> getTTsList(InputStream is){
        
        try{
            XSSFWorkbook sourceBook = new XSSFWorkbook(is);
            XSSFSheet sheet = sourceBook.getSheetAt(0);
            cd = new ColumnDeterminant(sheet.getRow(0));
            sheet.removeRow(sheet.getRow(0)); //delete title row
            
             //create models
            StreamSupport.stream(sheet.spliterator(), false)
                    .forEach(r ->{
                        try{
                            this.parseRow(r);
                        }catch(IllegalStateException e){
                            System.out.println("ru.Pegov.Macragge.SigmaParser.SigmaReportParser.setInputStream()" + e + " - "+ r.getRowNum());
                        }
                    });
        } catch (IOException ex) {
            System.err.println("[" + (new GregorianCalendar()).toZonedDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu")) + 
                    "]ru.Pegov.Macragge.SigmaParser.SigmaReportParser can't parse file, IOException");
        }

        return tts;
    }

    private void parseRow(Row r) {
        TroubleTicket tt = new TroubleTicket();
        
        //can't be null
        tt.setId(r.getCell(cd.ID).getStringCellValue());
        tt.setCity(r.getCell(cd.CITY).getStringCellValue());
        tt.setMR(r.getCell(cd.MR).getStringCellValue());
        tt.setStatus(r.getCell(cd.STATUS).getStringCellValue());
        tt.setEmployee(r.getCell(cd.EMPLOYEE).getStringCellValue());
        tt.setReason(r.getCell(cd.REASON).getStringCellValue());
        
        tt.setOpeningDate(this.fromRowToSQLTime(r, cd.OPENING_DATE));
        tt.setAppealOpening(this.fromRowToSQLTime(r, cd.APPEAL_OPENNING_DATE));
        tt.setLastTransferOn2LTP(this.fromRowToSQLTime(r, cd.LAST_TRANSFER_ON_2LTP_DATE));
        tt.setEndingDate(this.fromRowToSQLTime(r, cd.ENDING_DATE));
        tt.setTimeOn2LTP(getL2TPTime(r));
        
        if(tt.getAppealOpening() == null
                || tt.getOpeningDate() == null
                || tt.getLastTransferOn2LTP() == null
                || tt.getId().equalsIgnoreCase("")
                || tt.getMR().equalsIgnoreCase("")
                || tt.getReason().equalsIgnoreCase("")
                || tt.getCity().equalsIgnoreCase("")
                ){
            
            notAdd++;
            return;
        }
        
        //can't be null, but can be -0
        try{
            tt.setClientId(this.fromRowToInt(r, cd.CLIENT_ID));
        }catch(ParseException | NumberFormatException ex){
            tt.setClientId(-0);
        }
        
        //can be null
        tt.setDesdecisionTime(this.fromRowToSQLTime(r, cd.DESTIME));
        tt.setStart3LTP(this.fromRowToSQLTime(r, cd.START_3LTP_DATE));
        tt.setLastTTFrom(r.getCell(cd.LAST_TT_FROM).getStringCellValue());
        
        try {    
            tt.setRepeatedCount(this.fromRowToInt(r, cd.REPEATED_COUNT));
        } catch (ParseException ex) {
            Logger.getLogger(SigmaReportParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tt.setEnd3LTP(this.fromRowToSQLTime(r, cd.END_3LTP_DATE));
        tt.setTimeOn3LTP(getL3TPTime(r));
        tt.setAddress(r.getCell(cd.ADDRESS).getStringCellValue());
        tt.setComment(r.getCell(cd.COMMENT).getStringCellValue());
        tt.setService(r.getCell(cd.SERVICE).getStringCellValue());
        tt.setGroup2ltp(r.getCell(cd.GROUP2LTP).getStringCellValue());
        tt.setSubgroup2ltp(r.getCell(cd.SUBGROUP2LTP).getStringCellValue());
        tt.setReason2ltp(r.getCell(cd.REASON2LTP).getStringCellValue());
        tt.setSubreason2ltp(r.getCell(cd.SUBREASUN2LTP).getStringCellValue());
        tt.setGroup3ltp(r.getCell(cd.GROUP3LTP).getStringCellValue());
        tt.setSubgroup3ltp(r.getCell(cd.SUBGROUP3LTP).getStringCellValue());
        tt.setReason3ltp(r.getCell(cd.REASON3LTP).getStringCellValue());
        tt.setSubreason3ltp(r.getCell(cd.SUBREASUN3LTP).getStringCellValue());
        
        
        
        tts.add(tt);
    } 
    
    public int getAdded() {
        return tts.size();
    }

    public int getNotAdd() {
        return notAdd;
    }
    
    private Timestamp stringToSQLDate (String date_str) throws ParseException{        
        return new Timestamp(SDF.parse(date_str).getTime());
    }
    
    private Integer fromRowToInt (Row r, int i) throws ParseException{
        try{
            return Integer.parseInt(r.getCell(i).getStringCellValue());
        }catch(IllegalStateException e){
            return (int) r.getCell(i).getNumericCellValue();
        }
    }
    
    private Timestamp fromRowToSQLTime(Row r, int i){
        try{
            return this.stringToSQLDate(r.getCell(i).getStringCellValue());
        }catch(IllegalStateException e){
            return new Timestamp(r.getCell(i).getDateCellValue().getTime());
        }catch(ParseException ex){
            if(i != cd.DESTIME && i != cd.START_3LTP_DATE && i != cd.END_3LTP_DATE){
                if(i == cd.DESTIME){
                    return this.fromRowToSQLTime(r, cd.ENDING_DATE);
                }
                if(i == cd.ENDING_DATE){
                    return null;
                }
                System.err.println("[" + (new GregorianCalendar()).toZonedDateTime().format(DateTimeFormatter.ofPattern("d-MMM-uuuu HH:ss"))  
                    +"]SigmaReportParser: Some cells are don't parsed, at row number: " 
                    + r.getRowNum()
                    + " TT num: "
                    + r.getCell(cd.ID).getStringCellValue()
                    + " field num:"
                    + i);
            }
            return null;
        }
        
    }
    //return life time of TT om L3TP
    private Integer getL3TPTime(Row row){
        try{
            return ((Double) row.getCell(cd.ON_3LTP_TIME).getNumericCellValue()).intValue();
        }catch(IllegalStateException e){
            try{
                return ((Double) Double.parseDouble(row.getCell(cd.ON_3LTP_TIME).getStringCellValue())).intValue();
            }catch(NumberFormatException ex){
                return null;
            }
        }
    }
    
    private Long getL2TPTime(Row row){
        String rawStr;
        
        //Report is changed, and at 2ltp time now have format 0.0 hours
        try{
            rawStr = row.getCell(cd.ON_2LTP_TIME).getStringCellValue();
        }catch(java.lang.IllegalStateException ex){
            //report changed, again, how it excel time type. Day format.
            double rawTime = row.getCell(cd.ON_2LTP_TIME).getNumericCellValue();
            return (long) (rawTime*24*60*60); //excel time type   
        }
        
        //Not removed, becouse all can changed back, ver 1.0
        String hourStr = "00";
        String minuteStr = "00";
        String secondStr = "00";
        
        try{
            hourStr = rawStr.substring(0, 2);
            minuteStr = rawStr.substring(3, 5);
            secondStr = rawStr.substring(6);
        }catch(StringIndexOutOfBoundsException e){
            System.err.println("Can't get subsntring at getL2TPTime(Row row): " + rawStr);
            return 0L;
        }
        
        try{
            return Long.parseLong(hourStr)*60*60 + Long.parseLong(minuteStr)*60 + Long.parseLong(secondStr);
        }catch(NumberFormatException e){
            System.err.println("cant parse string at getL2TPTime(Row row): " + rawStr);
            return 0L;
        }
        
    }
}
