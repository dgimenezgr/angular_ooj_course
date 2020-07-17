/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyzer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public class UniqueIPTester {
    public static void main(String[] args) {
        LogAnalyzer la = new LogAnalyzer();
//        la.readFile("data/short-test_log");
//        la.readFile("data/weblog1_log");
        la.readFile("data/weblog2_log");
//        la.readFile("data/weblog3-short_log");
        
        int uniqueIPs = la.countUniqueIPs();
        
        System.out.println("There are " + uniqueIPs + " unique IPs.");
        
        System.out.println("");
//        System.out.println("Entries with status code higher than 200:");
//        System.out.println("-----------------------------------------");
//        la.printAllHigherThanNum(400);
//
//        System.out.println("");
        System.out.println("Entries in date:");
        System.out.println("-----------------------------------------");
        
        ArrayList<String> visitsOnDay = la.uniqueIPVisitsOnDay("sep 24");
        for (int k = 0; k < visitsOnDay.size(); k++) {
            System.out.println(visitsOnDay.get(k));
        }
        System.out.println("");
        System.out.println("Entries with status code between two values:");
        System.out.println("-----------------------------------------");
        System.out.println(la.countUniqueIPsInRange(200, 299));

//        System.out.println("");
//
//        System.out.println("");
        System.out.println("Visits by IP:");
        System.out.println("-----------------------------------------");
        
        HashMap<String,Integer> visitsOnIP = la.countVisitsPerIP();
        for (String key : visitsOnIP.keySet()) {
            System.out.println("IP " + key + " visited " + visitsOnIP.get(key) +  " times.");
        }
        System.out.println("");
        System.out.println("Most visits per IP were " + la.mostNumberVisitsByIP(visitsOnIP));
        System.out.println("-----------------------------------------");
        System.out.println("");
        System.out.println("IPs that visited the most:");
        System.out.println("-----------------------------------------");
        
        ArrayList<String> IPsMostVisits = la.iPsMostVisits(visitsOnIP);
        for (int k = 0; k < IPsMostVisits.size(); k++) {
            System.out.println(IPsMostVisits.get(k));
        }
        System.out.println("");
        
        System.out.println("IPs by day:");
        System.out.println("-----------------------------------------");
        
        HashMap<String, ArrayList<String>> IPsByDay = la.iPsForDays();
        for (String record : IPsByDay.keySet()) {
            System.out.println(record + ":");
            System.out.println("---");
            ArrayList<String> ipsThisDay = IPsByDay.get(record);
            for (int k = 0; k < ipsThisDay.size(); k++) {
                System.out.println(ipsThisDay.get(k));
            }
            System.out.println("");
        }
        
        System.out.println("");
        
        System.out.println("Day with most visits:");
        System.out.println("-----------------------------------------");
        System.out.println(la.dayWithMostIPVisits(IPsByDay));
        
        System.out.println("");
        
        System.out.println("IP most visited this day:");
        System.out.println("-----------------------------------------");
        System.out.println(la.iPsWithMostVisitsOnDay(IPsByDay, "sep 29"));
        
    }
}
