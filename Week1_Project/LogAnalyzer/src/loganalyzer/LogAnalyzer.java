/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyzer;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.text.SimpleDateFormat;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
        FileResource resource = new FileResource(filename);
         
        WebLogParser parser = new WebLogParser();
         
        for (String line : resource.lines()) {
            LogEntry tmp = parser.parseEntry(line);
            records.add(tmp);
        }
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }

        int counts = uniqueIPs.size();
        
        return counts;
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }


    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> entriesInDay = new ArrayList<String>();

        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            Date entryDate = le.getAccessTime();
            
            SimpleDateFormat format = new SimpleDateFormat("LLL dd");
            
            String entryDateFormatted = format.format(entryDate);
            
            if (entryDateFormatted.equals(someday.toLowerCase()) && !entriesInDay.contains(ipAddr)) {
                entriesInDay.add(ipAddr);
            }
        }
    
        return entriesInDay;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();

        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if ((statusCode >= low && statusCode <= high) && (!uniqueIPsInRange.contains(ipAddr))) {
                uniqueIPsInRange.add(ipAddr);
            }
        }
        
        return uniqueIPsInRange.size();
    }
    
    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                int currCount = counts.get(ip);
                counts.put(ip, currCount+1);
            }
        }
        
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
        int mostVisits = 0;
        
        for (String key : counts.keySet()) {
            int thisVisits = counts.get(key);
            if (thisVisits > mostVisits) {
                mostVisits = thisVisits;
            }
        }
        
        return mostVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
        int mostVisits = mostNumberVisitsByIP(counts);
        ArrayList<String> mostVisitIps = new ArrayList<String>();
        
        for (String key : counts.keySet()) {
            int thisVisits = counts.get(key);
            if (thisVisits == mostVisits) {
                mostVisitIps.add(key);
            }
        }
        
        return mostVisitIps;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String, ArrayList<String>>();

        SimpleDateFormat format = new SimpleDateFormat("LLL dd");
        
        for (LogEntry record : records) {
           Date thisDate = record.getAccessTime();
           String thisDateStr = format.format(thisDate);
           if (!ipsForDays.containsKey(thisDateStr)) {
               ArrayList<String> newIp = new ArrayList<String>();
               newIp.add(record.getIpAddress());
               ipsForDays.put(thisDateStr, newIp);
           } else {
               ArrayList<String> tmp = ipsForDays.get(thisDateStr);
               tmp.add(record.getIpAddress());
               ipsForDays.put(thisDateStr, tmp);
           }
        }
        
        return ipsForDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsByDay) {
        int visitCount = 0;
        String dayWithMostVisits = "";
        
        for (String record : ipsByDay.keySet()) {
            int thisDayVisitCount = ipsByDay.get(record).size();
            if (thisDayVisitCount > visitCount) {
                visitCount = thisDayVisitCount;
                dayWithMostVisits = record;
            }
        }
        
        return dayWithMostVisits;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsByDay, String date) {
        
        HashMap<String,Integer> countsThisDay = new HashMap<String,Integer>();
        ArrayList<String> visitsThisDay = ipsByDay.get(date);
        
        // Get HashMap of IPs in this day and count
        for (int k = 0; k < visitsThisDay.size(); k++) {
            String ip = visitsThisDay.get(k);
            if (!countsThisDay.containsKey(ip)) {
                countsThisDay.put(ip, 1);
            } else {
                int currCount = countsThisDay.get(ip);
                countsThisDay.put(ip, currCount+1);
            }
        }
        
        // Get max count
        int maxCounts = 0;
        
        for (String record : countsThisDay.keySet()) {
            int thisCount = countsThisDay.get(record);
            if (thisCount > maxCounts) {
                maxCounts = thisCount;
            }        
        }

        ArrayList<String> mostVisitsThisDay = new ArrayList<String>();
        // Get ArrayList of IPs with this count
        for (String record : countsThisDay.keySet()) {
            int thisCount = countsThisDay.get(record);
            if (thisCount == maxCounts) {
                mostVisitsThisDay.add(record);
            }        
        }
        
        return mostVisitsThisDay;
    }
}
