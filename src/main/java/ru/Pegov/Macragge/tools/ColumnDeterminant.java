/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.tools;

import java.util.stream.StreamSupport;
import org.apache.poi.ss.usermodel.Row;

/**
 * ColumnDeterminant need to determinant column index.
 * @author Андрей
 */
public class ColumnDeterminant {
    private final String IDname = "ID";
    private final String MRname = "МР";
    private final String CityName = "Город";
    private final String ReasonName = "Процесс";
    private final String DesTimeName = "Решение о переводе на 2ЛТП";
    private final String[] OpeningDateName = new String[] {"Дата открытия ТТ", "Дата открытия"};
    private final String[] EndingDateName = new String[] {"Дата закрытия ТТ", "Дата закрытия"};
    private final String StatusName = "Статус";
    private final String EmployeeName = "Последний ответственный";
    private final String Start3LTPDateName = "Посл. перевод на 3ЛТП";
    private final String End3LTPDateName = "Посл. перевод с 3ЛТП";
    private final String On2LTPTimeName = "Общ. время ТТ в ЗО 2 ЛТП";
    private final String On3LTPTimeName = "Общ. время ТТ в ЗО 3 ЛТП";
    private final String RepeatedCountName = "Кол-во ТТ тех. кат. по аб. за 30 дн.";
    private final String LastTTFromName = "Пред. обращ-е закрыто на";
    private final String AppealOpeningDateName = "Дата открытия обращения";
    private final String LastTransferOn2LTPDateName = "Посл. перевод на 2ЛТП";
    private final String ClientIDname = "Номер договор";
    private final String AddressName = "Адрес подкл";
    private final String CommentName = "Описание проблемы";
    private final String Service = "2ЛТП Услуга";
    private final String Group2LTP = "2ЛТП Группа";
    private final String Subgroup2LTP = "2ЛТП Подгруппа";
    private final String Reason2LTP = "2ЛТП Причина";
    private final String SubReason2LTP = "2ЛТП Подпричина";
    private final String Group3LTP = "3ЛТП Группа";
    private final String Subgroup3LTP = "3ЛТП Подгруппа";
    private final String Reason3LTP = "3ЛТП Причина";
    private final String SubReason3LTP = "3ЛТП Подпричина";

     /**
     * return index of column with name "ID"
     */
    public int ID;
    
    /**
     * return index of column with name "МР"
     */
    public int MR;

    /**
     * return index of column with name "Город"
     */
    public int CITY;

    /**
     * return index of column with name "Процесс"
     */
    public int REASON;

    /**
     * return index of column with name "Решение о переводе на 2ЛТП"
     */
    public int DESTIME;

    /**
     * return index of column with name "Дата открытия"
     */
    public int OPENING_DATE;

    /**
     * return index of column with name "Дата закрытия"
     */
    public int ENDING_DATE;

    /**
     * return index of column with name "Статус"
     */
    public int STATUS;

    /**
     * return index of column with name " Последний ответственный"
     */
    public int EMPLOYEE;

    /**
     * return index of column with name "Посл. перевод на 3ЛТП"
     */
    public int START_3LTP_DATE;

    /**
     * return index of column with name "Посл. перевод с 3ЛТП"
     */
    public int END_3LTP_DATE;

    /**
     * return index of column with name "Общ. время ТТ в ЗО 2 ЛТП"
     */
    public int ON_2LTP_TIME;

    /**
     * return index of column with name "Общ. время ТТ в ЗО 3 ЛТП"
     */
    public int ON_3LTP_TIME;

    /**
     * return index of column with name "Кол-во ТТ тех. кат. по аб. за 30 дн."
     */
    public int REPEATED_COUNT;

    /**
     * return index of column with name "Пред. обращ-е закрыто на"
     */
    public int LAST_TT_FROM;
    
    /**
     * return index of column with name "Дата открытия обращения"
     */
    public int APPEAL_OPENNING_DATE;
    
    /**
     * return index of column with name "Посл. перевод на 2ЛТП"
     */
    public int LAST_TRANSFER_ON_2LTP_DATE;
    
    /**
     * return index of column with name "Номер договор"
     */
    public int CLIENT_ID;
    
    /**
     * return index of column with name "Адрес подкл"
     */
    public int ADDRESS;
    
    /**
     * return index of column with name "Описание проблемы"
     */
    public int COMMENT;

    /**
     * return index of column with name "2ЛТП Услуга"
     */
    public int SERVICE ;

    /**
     * return index of column with name "2ЛТП Группа"
     */
    public int GROUP2LTP ;

    /**
     * return index of column with name "2ЛТП Подгруппа"
     */
    public int SUBGROUP2LTP ;

    /**
     * return index of column with name "2ЛТП Причина"
     */
    public int REASON2LTP ;

    /**
     * return index of column with name "2ЛТП Подпричина"
     */
    public int SUBREASUN2LTP ;

    /**
     * return index of column with name "3ЛТП Группа"
     */
    public int GROUP3LTP ;

    /**
     * return index of column with name "3ЛТП Подгруппа"
     */
    public int SUBGROUP3LTP ;

    /**
     * return index of column with name "3ЛТП Причина"
     */
    public int REASON3LTP ;

    /**
     * return index of column with name "3ЛТП Подпричина"
     */
    public int SUBREASUN3LTP ;
    
    /**
     * 
     * @param row is a object of org.apache.poi.ss.usermodel.Row, that contain titele row
     */
    public ColumnDeterminant(Row row){
        StreamSupport.stream(row.spliterator(), false).forEach(cell -> {
            if(cell.getStringCellValue().equalsIgnoreCase(IDname)){
               ID = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(MRname)){
                MR = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(CityName)){
                CITY = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(ReasonName)){
                REASON = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(DesTimeName)){
                DESTIME = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(OpeningDateName[0]) ||
                    cell.getStringCellValue().equalsIgnoreCase(OpeningDateName[1]) ){
                OPENING_DATE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(EndingDateName[0]) ||
                    cell.getStringCellValue().equalsIgnoreCase(EndingDateName[1])){
                ENDING_DATE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(StatusName)){
                STATUS = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(EmployeeName)){
                EMPLOYEE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Start3LTPDateName)){
                START_3LTP_DATE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(End3LTPDateName)){
                END_3LTP_DATE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(On2LTPTimeName)){
                ON_2LTP_TIME = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(On3LTPTimeName)){
                ON_3LTP_TIME = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(RepeatedCountName)){
                REPEATED_COUNT = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(LastTTFromName)){
                LAST_TT_FROM = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(AppealOpeningDateName)){
                APPEAL_OPENNING_DATE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(LastTransferOn2LTPDateName)){
                LAST_TRANSFER_ON_2LTP_DATE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(ClientIDname)){
                CLIENT_ID = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(AddressName)){
                ADDRESS = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(CommentName)){
                COMMENT = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Service)){
                SERVICE = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Group2LTP)){
                GROUP2LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Subgroup2LTP)){
                SUBGROUP2LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Reason2LTP)){
                REASON2LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(SubReason2LTP)){
                SUBREASUN2LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Group3LTP)){
                GROUP3LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Subgroup3LTP)){
                SUBGROUP3LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(Reason3LTP)){
                REASON3LTP = cell.getColumnIndex();
            }
            if(cell.getStringCellValue().equalsIgnoreCase(SubReason3LTP)){
                SUBREASUN3LTP = cell.getColumnIndex();
            }
        });
    }
}
