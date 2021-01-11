package DB_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Ignore;
import org.junit.Test;
import ru.Pegov.Macragge.DB.CountTTbyDayDTO;
import ru.Pegov.Macragge.DB.TTBuildingCounter;
import ru.Pegov.Macragge.DB.TTManagerDAO;

/**
 *
 * @author Андрей
 */
public class LoadFileTest {
    
    public LoadFileTest() {
    }  
    @Ignore
    @Test
     public void hello() {
        HashMap<String,TTBuildingCounter> count = new HashMap<>();
         
     try{
            XSSFWorkbook sourceBook = new XSSFWorkbook(new FileInputStream(new File("D:\\123.xlsx")));
            XSSFSheet sheet = sourceBook.getSheetAt(0);
            sheet.removeRow(sheet.getRow(0)); //delete title row
            
             //create models
            StreamSupport.stream(sheet.spliterator(), false)
                    .forEach(r ->{
                        try{
                            String str = r.getCell(0).getStringCellValue();
                            String adrSource = str.substring(
                                    str.indexOf("г.Магнитогорск")+ 17,
                                    str.lastIndexOf(",")
                            );
                            String adr = adrSource.replaceAll("  ", " ");
                            adr = adr.replaceFirst("\\.", ". ");
                            adr = adr.replaceFirst(" ,", ",");
                            
                            if(adr.matches(".*\\d\\s\\d.*")){
                                adr = adr.substring(0, adr.length()-2)
                                        + "/"
                                        + adr.substring(adr.length()-1);
                            }
                            
                            if(count.putIfAbsent(adr, new TTBuildingCounter(adr)) != null){
                                count.get(adr).incClients();
                            }
                        }catch(IllegalStateException e){
                            System.out.println("ru.Pegov.Macragge.SigmaParser.SigmaReportParser.setInputStream()" + e + " - "+ r.getRowNum());
                        }catch(StringIndexOutOfBoundsException e){
                            System.err.println("String ERROR at" + r.getCell(0).getStringCellValue());
                        }
                    });
        } catch (IOException ex) {
            System.err.println("[" + (new GregorianCalendar()).toZonedDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu")) + 
                    "]ru.Pegov.Macragge.SigmaParser.SigmaReportParser can't parse file, IOException");
        }
        
        TTManagerDAO ttm = new TTManagerDAO();
        
        System.out.println(count.size());
        try(FileWriter writer = new FileWriter("D:\\123.txt", false)){
            List<TTBuildingCounter> bc = ttm.getCountTTByAddressByMonth(count.values(), 2019);
            
                bc.forEach(c -> {
                    try {
                        writer.append(c.toString());
                    } catch (IOException ex) {
                        Logger.getLogger(LoadFileTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            writer.flush();
        }catch(IOException e){
            
        }
        
     }
}
