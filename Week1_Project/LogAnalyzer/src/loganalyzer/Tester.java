/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyzer;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public static void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/weblog2_log");
        
        System.out.println("IPs únicas: ");
        System.out.println(analyzer.countUniqueIPs());

        System.out.println("IPs únicas en rango 400,499: ");
        System.out.println(analyzer.countUniqueIPsInRange(400,499));
        
        System.out.println("IPs únicas el día 27 sep: ");
        System.out.println(analyzer.uniqueIPVisitsOnDay("sep 27"));
        
        HashMap<String,Integer> counts = analyzer.countVisitsPerIP();
        System.out.println("Mayor número de visitas por IP:");
        System.out.println(analyzer.mostNumberVisitsByIP(counts));
        // analyzer.printAll();
        
        System.out.println("IP con más visitas:");
        System.out.println(analyzer.iPsMostVisits(counts));
        
        System.out.println("Día con más visitas de IP:");
        HashMap dayWithMostIpVis = analyzer.iPsForDays();
        System.out.println(analyzer.dayWithMostIPVisits(dayWithMostIpVis));
        
        System.out.println("IP con más visitas el día sep 29:");
        System.out.println(analyzer.iPsWithMostVisitsOnDay(dayWithMostIpVis, "sep 29"));
    }
    
    public static void main(String[] args) {
        testLogAnalyzer();
    }
}
