package com.company;

import java.io.*;
import java.util.*;
import java.net.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Desktop;

import java.util.Stack;

/*
***** This is what outputs for the second out put ******** 
0.html Sun Sep 24 15:17:30 EDT 2017http://em.efollett.com)
1.html Sun Sep 24 15:17:30 EDT 2017https://secure.touchnet.com)
2.html Sun Sep 24 15:17:30 EDT 2017https://www.linkedin.com)
3.html Sun Sep 24 15:17:30 EDT 2017http://www)
4.html Sun Sep 24 15:17:30 EDT 2017https://www.payexpo.com)
5.html Sun Sep 24 15:17:30 EDT 2017http://www.bkstr.com)
6.html Sun Sep 24 15:17:30 EDT 2017http://www.addthis.com)
7.html Sun Sep 24 15:17:30 EDT 2017http://www.twitter.com)
8.html Sun Sep 24 15:17:30 EDT 2017https://support.google.com)

//there's more lines for the output
*/

public class Main {
    //Queue for BFS

    public static void main(String[] args) throws IOException {
        try {
            BFSCrawler.bfs();
            BFSCrawler.displayResults();

        } catch (IOException e) {
            System.out.println("IOException caught : " + e);
        }

        // The name of the file to open.
        String fileName = "test.txt.rtf";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");


            BFSCrawler object = new BFSCrawler();

            object.writeToFile(fileName);


        }
    }
}

public class DFS{
    
    Stack s = new Stack();
    static Set<String> marked = new HashSet<>();
    static String root = "http://www.spelman.edu";
    static String regex = "http[s]*://(\\w+\\.)*(\\w+)";
    
    int count = 0;


    static Queue<String> q = new LinkedList<>();

    //URLs already visited
    s.push(root);
    while(!s.isEmpty()){
        
        String first = q.top();
        
        q.top();
        
        for 
        
        
        
        
        
    }
    
    
}

public class BFSCrawler {


    int count = 0;


static Queue<String> q = new LinkedList<>();

    //URLs already visited
    static Set<String> marked = new HashSet<>();

    //URL Pattern regex
    static String regex = "http[s]*://(\\w+\\.)*(\\w+)"; //the regular expression pattern to extract web site links from html content downloaded form a web page

    //Start from here
    static String root = "http://www.spelman.edu";

    //BFS Routine
    public static void bfs() throws IOException {
        q.add(root);
        while (!q.isEmpty()) {
            String s = q.poll();//retrieves and removes the first element of list
            //System.out.println(s);

            //Find only almost 100 websites.
            if (marked.size() > 100) return;

            boolean ok = false;
            URL url = null;
            BufferedReader br = null;

            while (!ok) {
                try {
                    url = new URL(s);
                    br = new BufferedReader(new InputStreamReader(url.openStream()));
                    ok = true;
                } catch (MalformedURLException e) {
                    System.out.println("\nMalformedURL : " + s + "\n");
                    //Get next URL from queue
                    s = q.poll();
                    ok = false;
                } catch (IOException e) {
                    System.out.println("\nIOException for URL : " + s + "\n");
                    //Get next URL from queue
                    s = q.poll();
                    ok = false;
                }
            }

            StringBuilder sb = new StringBuilder();

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            s = sb.toString();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                String w = matcher.group();

                if (!marked.contains(w)) {
                    marked.add(w);
                    System.out.println("Site : " + w);
                    q.add(w);
                }
            }
        }
    }


    public void writeToFile(String filename) {

        FileWriter writer;
        try {
            writer = new FileWriter(filename);
            marked.forEach(a -> {
                try {
                    String temp = count + ".html " + new Date() +  a + ")\n";
                    //display to console
                    System.out.println(temp);
                    //save to file
                    writer.write(temp);
                } catch (IOException e) {
                   // System.err.println(e.getMessage());
                }
                count++;
            });
            writer.close();
        } catch (IOException e) {
            //System.err.println(e.getMessage());
        }
    }


    //Display results from SET marked
    public static void displayResults() {
        System.out.println("\n\nResults: ");
        System.out.println("\nWeb sites crawled : " + marked.size() + "\n");
        for (String s : marked) {
            System.out.println(s);
        }
    }
}



