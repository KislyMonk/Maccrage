package ru.Pegov.Macragge.reports;

/**
 * Exeption if data from user is unknown
 * @author Андрей
 */
public class UnknownDataExeption extends Exception{
    public UnknownDataExeption(String exeptionDescription){
        super(exeptionDescription);
    }
}
