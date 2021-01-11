/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.DB;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 *
 * @author Андрей
 */
public class TTManagerDAO implements TTManager{

    @Override
    public boolean addTT(TroubleTicket tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(tt);
            tx.commit();
        }catch (HibernateException e) {
            
            System.err.println("[" + (new Timestamp((new GregorianCalendar().getTimeInMillis()))) + 
                    "]ru.Pegov.Macragge.DB.TTManagerDAO can't add TT: "
                    + tt);
            
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close(); 
        }
        return true;
    }

    @Override
    public boolean addArrayTT(List<TroubleTicket> ttList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ttList.stream().forEach((TroubleTicket tt) ->{
                session.saveOrUpdate(tt);
            });
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close(); 
        }
        return true;
    }

    @Override
    public List<TroubleTicket> getTTs(Timestamp from, Timestamp to) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<TroubleTicket> result = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(TroubleTicket.class);
            result = cr.add(Restrictions.or(
                            Restrictions.between("openingDate", from, to), 
                            Restrictions.between("endingDate", from, to))
                    ).list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close(); 
        }
        
        return result;
    }
    
    @Override
    public boolean deleteTT(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            TroubleTicket tt;
            tx = session.beginTransaction();
            tt = (TroubleTicket)session.load(TroubleTicket.class, id);
            session.delete(tt);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close(); 
        }
        return true;
    }
    
    @Override
    public List<CountTTbyDayDTO> getCountByDays(Timestamp from, Timestamp to){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria cr = session.createCriteria(TroubleTicket.class);
        cr.add(Restrictions.or(
                            Restrictions.between("openingDate", from, to), 
                            Restrictions.between("endingDate", from, to))
                    );
        
        ProjectionList projList = Projections.projectionList(); 
        projList.add(Projections.sqlGroupProjection(
                "date({alias}.endingdate) as date",
                "date({alias}.endingdate)",
                new String[]{"date"},
                new Type[]{StandardBasicTypes.STRING}
        
        ));
        
        projList.add(Projections.count("endingDate"), "count");
        cr.setProjection(projList);
        
        return cr.setResultTransformer(Transformers.aliasToBean(CountTTbyDayDTO.class))
                .list();
    }
    
    @Override
    public Timestamp getLastClosedDate(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria cr = session.createCriteria(TroubleTicket.class)
                .setProjection(Projections.max("endingDate"));
        
        return (Timestamp) cr.uniqueResult();
    }
    
    public List<CountTTbyDayDTO> getCountTTByAddressByMonth(String address,int year){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Timestamp from = new Timestamp(new GregorianCalendar(year, 0, 1).getTimeInMillis());
        Timestamp to = new Timestamp(new GregorianCalendar(year, 11, 31).getTimeInMillis());
        
        Criteria cr = session.createCriteria(TroubleTicket.class);
        cr.add(Restrictions.like("address","%" + address + "%"));
        cr.add(Restrictions.between("openingDate", from, to));
        
        ProjectionList projList = Projections.projectionList(); 
        projList.add(Projections.sqlGroupProjection(
                "EXTRACT(MONTH FROM openingDate) as date",
                "EXTRACT(MONTH FROM openingDate)",
                new String[]{"date"},
                new Type[]{StandardBasicTypes.STRING}
        
        ));
        
        projList.add(Projections.count("openingDate"), "count");
        cr.setProjection(projList);
        
        List<CountTTbyDayDTO> result = cr.setResultTransformer(Transformers.aliasToBean(CountTTbyDayDTO.class))
                .list();
        session.close();
        return result;
    }
    
        public List<TTBuildingCounter> getCountTTByAddressByMonth(Collection<TTBuildingCounter> buildingCounters,int year){
        List<TTBuildingCounter> result = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Timestamp from = new Timestamp(new GregorianCalendar(year, 0, 1).getTimeInMillis());
        Timestamp to = new Timestamp(new GregorianCalendar(year, 11, 31).getTimeInMillis());
        
        for(TTBuildingCounter ttCounte : buildingCounters){
            Criteria cr = session.createCriteria(TroubleTicket.class);
            cr.add(Restrictions.like("address","%" + ttCounte.getName() + ",%"));
            cr.add(Restrictions.between("openingDate", from, to));

            ProjectionList projList = Projections.projectionList(); 
            projList.add(Projections.sqlGroupProjection(
                    "EXTRACT(MONTH FROM openingDate) as date",
                    "EXTRACT(MONTH FROM openingDate)",
                    new String[]{"date"},
                    new Type[]{StandardBasicTypes.STRING}

            ));

            projList.add(Projections.count("openingDate"), "count");
            cr.setProjection(projList);

            ttCounte.setMonth(cr.setResultTransformer(Transformers.aliasToBean(CountTTbyDayDTO.class))
                    .list());
            
            if(ttCounte.getMonths().isEmpty()){
                Criteria cr2 = session.createCriteria(TroubleTicket.class);
                cr2.add(Restrictions.like("address","%" + ttCounte.getName().replaceAll("/", " корп ") + "%"));
                cr2.add(Restrictions.between("openingDate", from, to));
                cr2.setProjection(projList);

                ttCounte.setMonth(cr2.setResultTransformer(Transformers.aliasToBean(CountTTbyDayDTO.class))
                        .list());
            }
            
            result.add(ttCounte);
            System.out.println(result.size() + "/" + buildingCounters.size());
            
            
        }
        
        
        session.close();
        return result;
    }
}
