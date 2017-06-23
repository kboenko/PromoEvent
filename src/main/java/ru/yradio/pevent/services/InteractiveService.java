package ru.yradio.pevent.services;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import ru.yradio.pevent.ctrls.DataViewController;
import ru.yradio.pevent.domain.tEntity;
import ru.yradio.pevent.utils.StringUtils;

public class InteractiveService {
    
    private static final Logger log = Logger.getLogger(DataViewController.class);
    
    private static Properties readProps() {
        Properties propTbl = new Properties();
        log.debug("Trying to read DB properties from file...");
        try {
            ClassLoader cl =Thread.currentThread().getContextClassLoader();
            InputStream inTbl = cl.getResourceAsStream("database.properties");
            propTbl.load(inTbl);
            log.debug("DB properties has been succesfully read!");
            
        }catch (IOException e){
            log.error("SOMETHING WRONG: " + e);
        }
        return propTbl;
    }
    
    private static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        log.debug("Connecting to DB...");
        Properties props = readProps();
        String drivers = props.getProperty("jdbc.driverClassName");
        if (drivers!=null) System.setProperty("jdbc.driverClassName", drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        Class.forName("org.postgresql.Driver");
        log.debug("Connection is established!");
        return DriverManager.getConnection(url, username, password);
    }
    
    public static List<String> getPoints() throws SQLException, ClassNotFoundException, IOException {
        log.debug("Getting points of prize taking...");
        Properties propTbl = readProps();
        List<String> points = StringUtils.convertStrings2UTF8(new ArrayList<>(Arrays.asList(propTbl.getProperty("prize.taking.points").split(":"))));
        if(points.isEmpty())
        {
            log.error("No points of prize taking has been found!");
        }
        return points;
    }
        
    public static List<tEntity> getEntities() throws SQLException, ClassNotFoundException, IOException {
        log.debug("Getting data from DB...");
        Properties propTbl = readProps();
        String tablename = propTbl.getProperty("jdbc.tablename");
        String first = "SELECT id, date, callerid, prize, prize_taken from ";
        String entityQuery = first + tablename + " WHERE prize IS NOT NULL AND answer_status = TRUE ORDER BY id";
                        
        try(Connection c = getConnection();
              PreparedStatement pSt = c.prepareStatement(entityQuery);
               ResultSet resSet = pSt.executeQuery(); ){
            
            ArrayList<tEntity> entities = new ArrayList<>();
            while (resSet.next()){
                Long id = resSet.getLong(1);
                String date = resSet.getString(2);
                String callerid = resSet.getString(3);
                String prize = resSet.getString(4);
                String prize_taken = resSet.getString(5);
                entities.add(new tEntity(id,date,callerid,prize,prize_taken));
            }
            if(entities.isEmpty())
            {
                log.error("No data has been taken or DB is empty!");
            }
            return entities;
        }
    }
    
    public static void updateEntity(int id, String opt) throws SQLException, ClassNotFoundException, IOException {
        if(!opt.isEmpty())
        {
            log.debug("Updating data: id = " + id + ", address = " + opt);
            Properties propTbl = readProps();
            
            byte[] addrWIN1251 = opt.getBytes();
            String address = new String(addrWIN1251,"UTF-8");
                        
            String tablename = propTbl.getProperty("jdbc.tablename");
            Date d = new Date();
            SimpleDateFormat f1 = new SimpleDateFormat("dd.MM.yy, HH:mm");
            String newStr = "Выдано: " + address  + ", " + f1.format(d);
            String updQuery = "UPDATE " + tablename + " SET prize_taken = '" + newStr + "' WHERE id = " + id;

            Connection c = getConnection();
            PreparedStatement pSt = c.prepareStatement(updQuery);
            pSt.executeUpdate();
            log.debug("Data has been successfully updated.");
        }
    }
        
}
