/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetanalyze;

import edu.stanford.nlp.ie.demo.NERDemo;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.lang.reflect.Field;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.io.IOUtils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.IndexableFieldType;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.queryparser.xml.ParserException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.Explanation;
//import org.apache.lucene.queryparser.xml.CorePlusQueriesParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.DFISimilarity;
import org.apache.lucene.search.similarities.DFRSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.search.similarities.LMJelinekMercerSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.Similarity.SimScorer;
import org.apache.lucene.search.similarities.Similarity.SimWeight;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.BytesRef;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import info.debatty.java.stringsimilarity.Jaccard;

public class Searcher {
    static int flag=1;
    public static void main(String[] args) throws IOException, ParseException {
         
        // **************************************************************************************************

     //    BufferedReader bread =
   //      Files.newBufferedReader(Paths.get("/home/the_cook/Public/rishab/tid.txt"));
         //Files.newBufferedReader(Paths.get("/home/rishab/Desktop/mb267"));
         //BufferedReader bread =
         //Files.newBufferedReader(Paths.get("/home/rishab/Downloads/mapping.txt"));
    //     HashMap<String,String> thm= new HashMap<>();
//         HashSet<String> ths = new HashSet<>();
     //    String lin = null;
      //   while ((lin = bread.readLine()) != null) {
     //    String splitted[] = lin.split(" ");
         //arr[cnt] = split[1];
         // System.out.println(arr[cnt]);
         //cnt++;
         //System.out.println(splitted[1]);
   //      thm.put(splitted[1], splitted[0]);
//         if(ths.contains(splitted[3]))
//             System.out.println(splitted[3]);
//         ths.add(splitted[3]);
     //    }
         
       //  BufferedReader buffread =
        //         Files.newBufferedReader(Paths.get("/media/the_cook/Seagate Backup Plus Drive/2017 data/th-B-JMSMOOTHING.txt"));
           BufferedReader buffread =
                 Files.newBufferedReader(Paths.get("/home/sandip/datas/2017 data/th-B-JMS.txt"));
         HashMap<Integer,Float> pv= new HashMap<>();
         HashMap<Integer,Float> pv1= new HashMap<>();
         String lin1=null;
         int ct=1;
         while((lin1=buffread.readLine())!=null){
           String splitt[]=lin1.split(" ");
           pv.put(ct, Float.parseFloat(splitt[2]));
           pv1.put(ct, Float.parseFloat(splitt[1]));
           System.out.println(pv.get(ct)+" "+pv1.get(ct));
           ct++;
         }
         
         
         
         //System.out.println(pv.get(20)+" "+pv1.get(15)+" "+pv.size()+" "+pv1.size());
                 
         
        // System.out.println(thm.size());
        // System.out.println(thm.get("761600025856049152"));

        // **************************************************************************************************

 //       BufferedReader bran = Files
   //             .newBufferedReader(Paths.get("/home/the_cook/Public/rishab/missed_Jelinek_20_hits.txt"));

     //   HashMap<String, LinkedList<String>> hman = new HashMap<>();
    //    String linean = null;

   //     while ((linean = bran.readLine()) != null) {
   //         String splitan[] = linean.split(" ");
   //         LinkedList<String> ll = hman.get(splitan[0]);
   //         if (ll == null) {
   //             ll = new LinkedList<>();
   //             hman.put(splitan[0], ll);
  //          }
    //        ll.add(splitan[1]);
      //      hman.put(splitan[0], ll);
        //}

        // **************************************************************************************************
        File file = new File("/home/sandip/datas/2017 data/test");
        
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        // System.out.println(Arrays.toString(directories));

        HashMap<String, String> dm = new HashMap<>();
        // HashMap<String, Integer> fm = new HashMap<>();
    //    dm.put("11", "20170711");
        dm.put("29", "20170729");
        dm.put("30", "20170730");
        dm.put("31", "20170731");
        dm.put("1", "20170801");
        dm.put("2", "20170802");
        dm.put("3", "20170803");
        dm.put("4", "20170804");
        dm.put("5", "20170805");
     //   dm.put("10", "20160810");
      //  dm.put("11", "20160811");

        // fm.put("2-8", 24);
        // fm.put("3-8", 23);
        // fm.put("4-8", 23);
        // fm.put("5-8", 24);
        // fm.put("6-8", 20);
        // fm.put("7-8", 24);
        // fm.put("8-8", 18);
        // fm.put("9-9", 24);
        // fm.put("8-10", 19);
        // fm.put("11-10", 24);

       
        for(float thresh1=29f;thresh1<=30f;thresh1+=1f)
         {
             for(float thresh2=14f;thresh2<=20f;thresh2+=1f)
             {
        for(float tedious=0.1f;tedious<=1f;tedious+=500f)
        {

         //   System.out.println("for "+thresh1+" and "+thresh2);
//         FileWriter wri = new
//         FileWriter("/home/rishab/Downloads/missing_text_final.txt",true);
         
         //PrintWriter wri_text= new PrintWriter("/home/rishab/Downloads/missing_text_idForText.txt","UTF-8");
         
         
         
         
         
                Similarity similarity_used = new LMJelinekMercerSimilarity(tedious);
           
            // Similarity similarity_used = new LMJelinekMercerSimilarity(tedious);
             
        //     Similarity similarity_used = new BM25Similarity();
             //Similarity similarity_used = new LMDirichletSimilarity(1000);
                 
                 HashMap<Integer,LinkedList<String>> hmll = new HashMap<>();   
         
         
        for (int easy = 0; easy < 8; easy++) {
            JsonFactory jfactory = new JsonFactory();
            EnglishAnalyzer analyzer = new EnglishAnalyzer();
           // StandardAnalyzer analyzer = new StandardAnalyzer();
            //Analyzer analyzer = new 
            // Directory index = new RAMDirectory();
           // FileWriter writer_icheck = new FileWriter(
                      //  "/home/sandip/datas/2017output/fin_1/" +easy+ "_indexcheck.txt");
            Directory index = new SimpleFSDirectory(
                    Paths.get("/home/sandip/datas/2017output/index/day " + String.valueOf(easy + 1)));

            IndexWriterConfig config = new IndexWriterConfig(analyzer); 
           // IndexWriterConfig config = new IndexWriterConfig();
           
            config.setSimilarity(similarity_used);

            // config.setSimilarity(new LMDirichletSimilarity(1000));
            //config.setSimilarity(similarity_used);
            // config.setSimilarity(new DFRSimilarity(basicModel, afterEffect,
            // normalization))

            IndexWriter w = new IndexWriter(index, config);
           
            int totaldaytweet=0;
            int totalidaytweet=0;
            
              
            w.deleteAll();
            String date = directories[easy];
//             String date = "2";
            System.out.println(date);
            String created = dm.get(directories[easy]);
//             String created = "20160806";
            System.out.println(created);
            FileWriter writer_stat = new FileWriter(
                        "/home/sandip/datas/2017output/fin_1/"+ date + "_statq.txt");

            // System.out.println(date + " " + created);

            /*** read from file ***/
            // for(int x=1;x<=fm.get(directories[easy]);x++){
            int luch = 24;
            int en = Integer.parseInt(date);
            
            if(en==30 || en==2 || en==4)
                luch = 23;
            else 
                luch=24;
            //else if(en==29 || en==5)
              //  luch=24;
           // luch=1;  
            for (int y = 0; y <luch; y++) {
                String h = String.valueOf(y);
                //****************************   
                ArrayList<String> textlist = new ArrayList<String>();

                ArrayList<String> idlist = new ArrayList<String>();
                ArrayList<String> createdAtlist = new ArrayList<String>();

                
                String temp111 = h + ".txt";
              //  FileWriter writer_idcheck = new FileWriter(
                      //  "/home/sandip/datas/2017output/fin_1/"+ date + luch + "_idcheck.txt");
                //JsonParser jParser = jfactory
                  //      .createJsonParser(new File("/home/sandip/datas/2017 data/test/" + date + "/" +h+"f.txt"));
                
                BufferedReader br1111 = Files.newBufferedReader(Paths.get("/home/sandip/datas/2017 data/test/"+ date + "/ " +h+".txt"));

                String id1 = "", text = "", created_at = "";
                String line1 = null;
                int cnt = 0;
                 while ((line1 = br1111.readLine()) != null) {
               // System.out.println(line1.length());
               
                int tfld=0;
                if((line1.length())>3)
                {
                    cnt+=1;
                
            
                    //System.out.println("total="+cnt);
                    //JsonParser jParser = jfactory
                                         //.createJsonParser(new File("/home/sandip/datas/29/23f.txt"));
                    JsonParser jParser= jfactory.createJsonParser(line1);
                
      
                id1 = "";
                text = "";
                created_at = "";
                int ide = 0;
                boolean found=false;
                while (jParser.nextToken() != null) {
                    if(tfld < 4){
                    try{
                    // System.out.println(jParser.getCurrentToken());
                    String fieldname = jParser.getCurrentName();

        
                   // String followers = "", favourites = "", retweeted = "", id1 = "", text = "", lang = "",
                     //       created_at = "",expand_url="";
                   // String id1 = "", text = "", created_at = "";
                  //   if ("retweeted_status".equals(fieldname) || "entities".equals(fieldname)
                    //         || "quoted_status".equals(fieldname)
                      //      || "place".equals(fieldname) || "user".equals(fieldname)) {
                       // jParser.skipChildren();
                    //}

                    if ("created_at".equals(fieldname)) {

                        // current token is "name",
                        // move to next, which is "name"'s value
                        jParser.nextToken();
                        created_at = jParser.getText();
                      /*  String cre[] = created_at.split(" ");
                        if (Integer.parseInt(cre[2]) != Integer.parseInt(date)) {
                            //System.out.println(cre[2] + " " + date);
                            ide = 1;
                            continue;
                        } else {
                            ide = 0;
                        //    System.out.println(cre[2] + " " + date);
                            createdAtlist.add(created_at);
                        }*/
                        // System.out.println(cleanData(jParser.getText())); //
                        // display
                        // mkyong
                        
                        createdAtlist.add(created_at);
                        tfld+=1;
                    }
                    
                     if ("id".equals(fieldname) && ide == 0) {
                        jParser.nextToken();
                        id1 = jParser.getText();
                        // System.out.println("id:"+id1); // display 29
                        idlist.add(id1);
                        tfld+=1;
                       // System.out.print(id1);
                        //System.out.println("\n");

//                         if(thm.containsKey(id1))
//                             thm.remove(id1);
                    }
                     
                     
                     
                     if ("id_str".equals(fieldname) && ide == 0) {
                       jParser.nextToken();
                        //id1 = jParser.getText();
                        // System.out.println("id:"+id1); // display 29
                       // idlist.add(id1);
                        tfld+=1;
                        //System.out.print(id1);
                        //System.out.println("\n");

//                         if(thm.containsKey(id1))
//                             thm.remove(id1);
                    }
                     
                     

                    if ("text".equals(fieldname) && ide == 0) {

                        // current token is "name",
                        // move to next, which is "name"'s value
                        jParser.nextToken();
                        text = jParser.getText();
                        // System.out.println(cleanData(jParser.getText())); //
                        // display
                        // mkyong
                        textlist.add(cleanData(text));
                       // System.out.println(cleanData(text));
                        tfld+=1;
                    }
                    
                    

                    
                    
                    
                    }catch(IndexOutOfBoundsException e){ continue;}
                  }
                }
                //System.out.println("\n"+textlist.size()+ "," +idlist.size());
                
            }
            
          //  writer_idcheck.write(id1+ " " + text + "\n");
        }
        
        //System.out.println("\n"+textlist.size()+ "," +idlist.size());

               
                
//**************************************************

/* rishab code
// JsonParser jParser = jfactory.createJsonParser(new
                // File("/home/rishab/Downloads/btp/test/"+date+"/temp"+h));
                // JsonParser jParser = jfactory.createJsonParser(new
                // File("/home/rishab/Downloads/Trec_relavant_tweet.jsonl"));

                // loop until token equal to "}"
               // ArrayList<String> Expanded_Url = new ArrayList<>();
                //ArrayList<String> textlist = new ArrayList<String>();

               // ArrayList<String> idlist = new ArrayList<String>();

               // ArrayList<String> followerslist = new ArrayList<String>();

                //ArrayList<String> favlist = new ArrayList<String>();

               // ArrayList<String> createdAtlist = new ArrayList<String>();

                //ArrayList<String> langlist = new ArrayList<String>();

                //ArrayList<String> retweetlist = new ArrayList<String>();

                int ide = 0;
                boolean found=false;
                while (jParser.nextToken() != null) {
                    try{
                    // System.out.println(jParser.getCurrentToken());
                    String fieldname = jParser.getCurrentName();

                    String followers = "", favourites = "", retweeted = "", id1 = "", text = "", lang = "",
                            created_at = "",expand_url="";
                    
                    // if ("user".equals(fieldname)) {
                    // // System.out.println("hello");
                    // while (jParser.nextToken() != JsonToken.END_OBJECT) {
                    // // System.out.println(jParser.getText());
                    // if (jParser.getText().equals("followers_count")) {
                    // jParser.nextToken();
                    // followers = jParser.getText();
                    // followerslist.add(followers);
                    // }
                    //
                    // if (jParser.getText().equals("favourites_count")) {
                    // jParser.nextToken();
                    // favourites = jParser.getText();
                    // favlist.add(favourites);
                    // }
                    // }
                    // }
                    if ("retweeted_status".equals(fieldname) || "entities".equals(fieldname)
                             || "quoted_status".equals(fieldname)
                            || "place".equals(fieldname) || "user".equals(fieldname)) {
                        jParser.skipChildren();
                    }

                    if ("created_at".equals(fieldname)) {

                        // current token is "name",
                        // move to next, which is "name"'s value
                        jParser.nextToken();
                        created_at = jParser.getText();
                      /*  String cre[] = created_at.split(" ");
                        if (Integer.parseInt(cre[2]) != Integer.parseInt(date)) {
                            //System.out.println(cre[2] + " " + date);
                            ide = 1;
                            continue;
                        } else {
                            ide = 0;
                        //    System.out.println(cre[2] + " " + date);
                            createdAtlist.add(created_at);
                        }*/
                        // System.out.println(cleanData(jParser.getText())); //
                        // display
                        // mkyong
                        
         /*risab code
                        createdAtlist.add(created_at);

                    }

                    if ("text".equals(fieldname) && ide == 0) {

                        // current token is "name",
                        // move to next, which is "name"'s value
                        jParser.nextToken();
                        text = jParser.getText();
                        // System.out.println(cleanData(jParser.getText())); //
                        // display
                        // mkyong
                        textlist.add(cleanData(text));
                        System.out.print(text);
                    }

                    if ("id".equals(fieldname) && ide == 0) {
                        jParser.nextToken();
                        id1 = jParser.getText();
                        // System.out.println("id:"+id1); // display 29
                        idlist.add(id1);
                        System.out.print(id1);
                        System.out.println("\n");

//                         if(thm.containsKey(id1))
//                             thm.remove(id1);
                    }

                    if ("lang".equals(fieldname) && ide == 0) {
                        jParser.nextToken();
                        lang = jParser.getText();
                        // System.out.println(jParser.getText()); // display 29
                        langlist.add(lang);
                    }

                    if ("retweeted".equals(fieldname) && ide == 0) {
                        jParser.nextToken();
                        retweeted = jParser.getText();
                        // System.out.println(jParser.getText()); // display 29
                        retweetlist.add(retweeted);
                    }
                   // writer_idcheck.write(id1.trim() + " "+ text.trim()+"\n");
                    ;
         /*    if("expanded_url".equals(fieldname) && ide==0)
                    {
                        jParser.nextToken();
                        expand_url=getURLX(jParser.getText());
                        if(expand_url==null)
                        {
                            expand_url="N/A";
                        }
                        Expanded_Url.add(expand_url);
                        found=true;
                    }
                    if("timestamp_ms".equals(fieldname) && ide==0)
                    {
                        jParser.nextToken();
                        if(!found)
                        {
                            Expanded_Url.add("N/A");
                        }
                        found=false;
                        
                    }*/
                    
                /*    }catch(IndexOutOfBoundsException e){ continue;}
                   
                }*/
                
                   int fileitweet=0;                         
                   int totalfiletweet;
                   totalfiletweet=textlist.size();
                   totaldaytweet = totaldaytweet+totalfiletweet;
                   //System.err.println("Indexing started.");
                System.out.println(textlist.size()+"******"+idlist.size()+"*****"+createdAtlist.size());
                
                for (int it = 0; it < textlist.size(); it++) {
                 try{  
       //             if(thm.containsKey(idlist.get(it)))
           //         {
                        //wri_text.println(thm.get(idlist.get(it))+" "+idlist.get(it)+" "+textlist.get(it));
                        //System.out.println("hello");
             //       }
                   
                    if (textlist.get(it).split(" ").length > 0){
                        // addDoc(w, textlist.get(it), idlist.get(it),
                        // followerslist.get(it), favlist.get(it),
                        
                        // createdAtlist.get(it), langlist.get(it),
                        // retweetlist.get(it));
                      //  writer_icheck.write(idlist.get(it)+" "+textlist.get(it)+"\n");
                        addDoc(w, textlist.get(it), idlist.get(it));
                        fileitweet=fileitweet+1;
                    }
                   
                
                
                 }catch(IndexOutOfBoundsException e){ continue;}
                }
               // BufferedReader brx = Files.newBufferedReader(Paths.get("/home/rishab/Downloads/getText"));
                // HashMap<String,String> hmx = new HashMap<>();
                 //String linex = null;
                 //while ((linex = brx.readLine()) != null) {
                 //if(idlist.contains(linex))
                // {
                 //String llb=textlist.get(idlist.indexOf(linex));
                 //hmx.put(linex, llb);
                 //}
                 //}
               
               
//                 for(Map.Entry<String,String> entry : hmx.entrySet()) {
//                 wri.write(entry.getKey()+" "+entry.getValue()+" \n");
//                 }
                 
                   System.out.println(temp111 +" "+ totalfiletweet +" "+fileitweet);
                   totalidaytweet=totalidaytweet+fileitweet;
                   writer_stat.write(temp111 +" "+ totalfiletweet +" "+fileitweet+"\n");
                  // writer1.write(searcher.doc(i).get("id")+"  "+explain.toString());

               
            }
            

//             PrintWriter wri = new
//             PrintWriter("/home/rishab/Downloads/missing.txt");
//             for(Map.Entry<String,Integer> entry : thm.entrySet()) {
//             String key = entry.getKey();
//             wri.println(key);
//             }
        //     wri.close();
            System.out.println(totaldaytweet+" "+ totalidaytweet);
            writer_stat.write(date+" "+totaldaytweet+" "+ totalidaytweet);
            writer_stat.close();
            w.close();
            System.err.println("Indexing ended.");
            /*
             * String files[] = index.listAll();
             * System.out.println(index.fileLength("_0.cfe"));
             * System.out.println(index.fileLength("_0.cfs"));
             * System.out.println(index.fileLength("_0.si")); for (int file_it =
             * 0 ; file_it < files.length ; file_it++ ) {
             * System.out.println(files[file_it]); }
             */

            // **********************************************************************************************************

            //*************************for new index file***************************************
           
//            JsonFactory jfactory = new JsonFactory();
//            EnglishAnalyzer analyzer = new EnglishAnalyzer();
//            Directory index = new SimpleFSDirectory(
//                    Paths.get("/home/rishab/Downloads/btp/index0"));
//
//            IndexWriterConfig config = new IndexWriterConfig(analyzer);
//
//            // config.setSimilarity(new LMDirichletSimilarity());
//            config.setSimilarity(similarity_used);
//           
//            IndexWriter w = new IndexWriter(index, config);
//            w.deleteAll();
//           
//            JsonParser jParser = jfactory
//                    .createJsonParser(new File("/home/rishab/Downloads/TREC2015G.jsonl"));
//            // JsonParser jParser = jfactory.createJsonParser(new
//            // File("/home/rishab/Downloads/btp/test/"+date+"/temp"+h));
//            // JsonParser jParser = jfactory.createJsonParser(new
//            // File("/home/rishab/Downloads/Trec_relavant_tweet.jsonl"));
//
//            // loop until token equal to "}"
//            ArrayList<String> textlist = new ArrayList<String>();
//
//            ArrayList<String> idlist = new ArrayList<String>();
//
//            ArrayList<String> followerslist = new ArrayList<String>();
//
//            ArrayList<String> favlist = new ArrayList<String>();
//
//            ArrayList<String> createdAtlist = new ArrayList<String>();
//
//            ArrayList<String> langlist = new ArrayList<String>();
//
//            ArrayList<String> retweetlist = new ArrayList<String>();
//
//            int ide = 0;
//            while (jParser.nextToken() != null) {
//                // System.out.println(jParser.getCurrentToken());
//                String fieldname = jParser.getCurrentName();
//
//                String followers = "", favourites = "", retweeted = "", id1 = "", text = "", lang = "",
//                        created_at = "";
//
//                // if ("user".equals(fieldname)) {
//                // // System.out.println("hello");
//                // while (jParser.nextToken() != JsonToken.END_OBJECT) {
//                // // System.out.println(jParser.getText());
//                // if (jParser.getText().equals("followers_count")) {
//                // jParser.nextToken();
//                // followers = jParser.getText();
//                // followerslist.add(followers);
//                // }
//                //
//                // if (jParser.getText().equals("favourites_count")) {
//                // jParser.nextToken();
//                // favourites = jParser.getText();
//                // favlist.add(favourites);
//                // }
//                // }
//                // }
//                if ("retweeted_status".equals(fieldname) || "entities".equals(fieldname)
//                        || "extended_entities".equals(fieldname) || "quoted_status".equals(fieldname)
//                        || "place".equals(fieldname) || "user".equals(fieldname)) {
//                    jParser.skipChildren();
//                }
//
//                if ("created_at".equals(fieldname)) {
//
//                    // current token is "name",
//                    // move to next, which is "name"'s value
//                    jParser.nextToken();
//                    created_at = jParser.getText();
//                    String cre[] = created_at.split(" ");
//                        createdAtlist.add(created_at);
//                   
//                    // System.out.println(cleanData(jParser.getText())); //
//                    // display
//                    // mkyong
//
//                }
//
//                if ("text".equals(fieldname) && ide == 0) {
//
//                    // current token is "name",
//                    // move to next, which is "name"'s value
//                    jParser.nextToken();
//                    text = jParser.getText();
//                    // System.out.println(cleanData(jParser.getText())); //
//                    // display
//                    // mkyong
//                    textlist.add(cleanData(text));
//                }
//
//                if ("id".equals(fieldname) && ide == 0) {
//                    jParser.nextToken();
//                    id1 = jParser.getText();
//                    // System.out.println("id:"+id1); // display 29
//                    idlist.add(id1);
//                }
//
//                if ("lang".equals(fieldname) && ide == 0) {
//                    jParser.nextToken();
//                    lang = jParser.getText();
//                    // System.out.println(jParser.getText()); // display 29
//                    langlist.add(lang);
//                }
//
//                if ("retweeted".equals(fieldname) && ide == 0) {
//                    jParser.nextToken();
//                    retweeted = jParser.getText();
//                    // System.out.println(jParser.getText()); // display 29
//                    retweetlist.add(retweeted);
//                }
//                ;
//            }
//
//            System.out.println(textlist.size()+"******"+idlist.size()+"*****"+createdAtlist.size());
//            for (int it = 0; it < textlist.size(); it++) {
//               
//                if (textlist.get(it).split(" ").length > 5)
//                    // addDoc(w, textlist.get(it), idlist.get(it),
//                    // followerslist.get(it), favlist.get(it),
//                    // createdAtlist.get(it), langlist.get(it),
//                    // retweetlist.get(it));
//                    addDoc(w, textlist.get(it), idlist.get(it));
//
//            }
//           
//            w.close();
           
            //************************************EOF********************************************
           
            int hitsPerPage = 1000000;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            searcher.setSimilarity(similarity_used);
            System.out.println(reader.numDocs());

            // reader.numDocs();
            // searcher.doc(i).get("Tewwt id")
            // hm.put()
            // ****************************************************************
            HashMap<String, Integer> tweet_doc = new HashMap<>();
            for (int ite = 0; ite < reader.numDocs(); ite++) {
                tweet_doc.put(searcher.doc(ite).get("id"), ite);
            }
            // ********************************************************

            // 2. query
            BufferedReader br = Files.newBufferedReader(Paths.get("/home/sandip/datas/2017 data/expand-95.txt"));
            // BufferedReader br =
            // Files.newBufferedReader(Paths.get("/home/rishab/Downloads/query1.txt"));
            String line = null;
            String x = "1";

            // Jaccard j = new Jaccard();
            String arr[] = new String[95]; // changed_keep_mind
            BufferedReader br1 = Files.newBufferedReader(Paths.get("/home/sandip/datas/2017 data/tid95-2.txt"));
            // BufferedReader br1 =
            // Files.newBufferedReader(Paths.get("/home/rishab/Downloads/mapping.txt"));
            String line1 = null;
            int cnt = 0;
            while ((line1 = br1.readLine()) != null) {
                String split[] = line1.split(" ");
                arr[cnt] = split[1];
                // System.out.println(arr[cnt]);
                cnt++;

            }
            int q_count = 0;
            // int cc=0;
            // int iterate = 0; // temp
            // IndexReader se = searcher.getIndexReader(); //temp

    //        FileWriter writerx = new FileWriter(
    //                "/home/rishab/Downloads/btp/missedInResult_Jelinek0.1_0.6_normal_0.4_20_15_final_luchen.txt", true);
            // float threshhold=21.0f;
            int kem=0;
            while ((line = br.readLine()) != null) {
                // if(q_count==33 || q_count==48 || q_count==51 || q_count==45
                // || q_count==54)
                // threshhold=16.0f;
                // else
                // threshhold=24f;
                // iterate+=1; // temp
                // cc++;
                // TreeMap<Float, String> tm = new TreeMap<>();
                // System.out.println("hello");
                // ******************************************************************

  //              LinkedList<String> llx = new LinkedList<>();
//                llx = hman.get(arr[q_count]);
                // System.out.println("the size is "+llx.size());
                // writerx.println(arr[q_count]+" "+llx.size());

                // ****************************************************************
                PrintWriter writer = new PrintWriter(
                        "/home/sandip/datas/2017output/fin/" + date + "/" + x + "_output.txt", "UTF-8");

                // PrintWriter writer = new PrintWriter(
                // "/home/rishab/Downloads/smerp/" +x + "_output.txt", "UTF-8");

                PrintWriter writer_new = new PrintWriter(
                        "/home/sandip/datas/2017output/fin/" + date + "/sorted/" + x + "_output.txt", "UTF-8");
               
                FileWriter writer_test = new FileWriter(
                        "/home/sandip/datas/2017output/fin_1/"+ x + "_output.txt");
               
//                PrintWriter writer = new PrintWriter(
//                        "/home/rishab/Desktop/btp/output_notRel.txt", "UTF-8");

                // PrintWriter writer_new = new PrintWriter(
                // "/home/rishab/Downloads/smerp/sorted/" + x + "_output.txt",
                // "UTF-8");

                // PrintWriter writer_new_sum = new PrintWriter(
                // "/home/rishab/Downloads/smerp//sorted/" + x +
                // "_summarization.txt", "UTF-8");

                // PrintWriter writer_new = new
                // PrintWriter("C:\\Users\\rishabS\\Downloads\\btp\\first\\sorted\\merge\\merge.txt",
                // "UTF-8");
                // line="Saudi Arabia";
                String querystr = args.length > 0 ? args[0] : line;

                // the "title" arg specifies the default field to use
                // when no field is explicitly specified in the query.
                Query q = new QueryParser("text", analyzer).parse(querystr);
               // System.out.println(q);
                /**/
                // Explanation explain = searcher.explain(q, 0);
                // float theScore = explain.getValue();
                // System.out.println(explain.toString()+" flm");
                // System.out.println("fnhjcbdc"+explain.getDescription()+"
                // "+explain.getValue());
                
                
                //FileWriter writer1 = new FileWriter(
                  //      "/home/sandip/datas/2017output/explain1/"+ date+"/" + arr[kem]+".txt");
                 //
              //   System.out.println(reader.numDocs());
                 /*for(int i=0;i<reader.numDocs();i++){
                     
                      
                 Explanation explain = searcher.explain(q, i);
                 float theScore = explain.getValue();
                 //writer1.write(searcher.doc(i).get("id")+"  "+ searcher.doc(i).get("text")+" "+ explain.toString()+"\n");
                 writer1.write(searcher.doc(i).get("id")+"  "+ explain.toString()+"\n");

                 //System.out.println(searcher.doc(i).get("id")+"  "+explain.toString()+"\n");
           //      System.out.println("fnhjcbdc"+explain.getDescription()+" "+explain.getValue());
                 }
                 writer1.close();
                 kem++;*/
                

           
                 /**/
                // 3. search
                // int hitsPerPage = 100;
                // IndexReader reader = DirectoryReader.open(index);
                // IndexSearcher searcher = new IndexSearcher(reader);
                // searcher.setSimilarity(new BM25Similarity());
                // searcher.setSimilarity(new ClassicSimilarity());
                TopDocs docs = searcher.search(q, hitsPerPage);
                ScoreDoc[] hits = docs.scoreDocs;
                // for (int hu = 0 ; hu < 10 ; hu ++)
                // {
                // int u = hits[hu].doc;
                // Document dfs = searcher.doc(u);
                // String hy = dfs.get("text");
                // System.out.println(u+" "+hy);
                // }
                // System.out.println("the length is "+docs.totalHits+"
                // "+hits.length);
                // 4. display results
                //System.out.println("Found " + hits.length + " hits.");
                LinkedList<String> ll;
                int hmllc=Integer.parseInt(x);                //added_new
                ll=hmll.get(hmllc);
                if(ll==null)
                {
                    ll=new LinkedList<>();
                    hmll.put(hmllc, ll);
                }
                ll=hmll.get(hmllc);
                // System.out.println("hello "+hits.length+" "+ll.size());
                int jac = 0;
                int docId;

                long score_sum = 0;
                long score_count = 0;

                HashMap<String, Float> hm_sc = new HashMap<>();
                HashMap<String, String> hm_sc_data = new HashMap<>();
        //        HashMap<String, String> hm_sc_url = new HashMap<>();

                float max = 0;
                //System.out.println(hits.length);
                for (int i = 0; i < hits.length; i++) {

                    int temp = 0;
                    docId = hits[i].doc;
                    Document d = searcher.doc(docId);

                    hm_sc.put(d.get("id"), hits[i].score);
                    hm_sc_data.put(d.get("id"), d.get("text"));
          //          hm_sc_url.put(d.get("id"), d.get("expanded_url"));
                    if (hits[i].score > max)
                        max = hits[i].score;

                    // System.out.println(searcher.explain(q, docId));
                    // System.out.println("doc id :"+docId);
                    // System.out.println((i + 1) + ". " + d.get("id") + " " +
                    // hits[i].score);
                    // System.out.println(arr[q_count]);
                    // System.out.println(d.getFields().get(1));
                    // System.out.println(d.get("id")+" "+hits[i].score+"
                    // "+d.get("text"));
                   
                    writer.println(created + " " + arr[q_count] + " Q0" + " " + d.get("id") + " " + (i + 1) + " "
                            + hits[i].score + " "+d.get("text")+" IRLAB-LDRP");
                   
                    //if(thm.containsKey(d.get("id")))
                        //writer_test.println(hits[i].score);
//                    if(thm.containsKey(d.get("id")))
  //                  {
                        //System.out.println("hello");
                        //writer_test.write(d.get("id")+" "+hits[i].score+" "+getNECount(d.get("text"), querystr)+" "+getMatchURLCount(d.get("text"), querystr.toLowerCase())+"\n");
                        //writer_test.write(hits[i].score+"\n");
                        //writer_test.write(d.get("id")+" "+hits[i].score+" "+getNECount(d.get("text"), querystr)+"\n");
    //                }
                    //System.out.println(d.get("id").toString());
   
//                    if(thm.containsKey(d.get("id")))
//                    {
//                        thm.remove(d.get("id"));
//                        writer.println(d.get("id") + " "+hits[i].score + " "+ d.get("text"));
//                    }
                   
                    // writer.println(arr[q_count] + " Q0" + " " + d.get("id") +
                    // " " + (i + 1) + " "
                    // + hits[i].score + " IR-RIS");
                    // System.out.println(d.get("text")+" "+d.get("id")+"
                    // "+hits[i].score+" "+cc);
                    // writer.println(d.get("text"));
                    // double sim=0;
                    // if (hits[i].score > threshhold ) {
                    //
                    // for (int k = 0; k < ll.size(); k++) {
                    // // double sim = j.similarity(d.get("text"),
                    // // ll.get(k));
                    //
                    // sim = getJaccard(d.get("text"), ll.get(k));
                    // //if(sim==1)
                    // // System.out.println(d.get("text")+" "+sim+"*******"+
                    // ll.get(k));
                    // if (sim >= 0.4) {
                    // // System.out.println(d.get("text")+"*****************"+
                    // // ll.get(k));
                    // // System.out.println("similarity "+sim);
                    // temp = 1;
                    // break;
                    // }
                    //
                    // }
                    // if (temp == 0) {
                    // // System.out.println("sorted"+d.get("id")+"
                    // // "+d.get("text"));
                    // writer_new.println(created + " " + arr[q_count] + " Q0" +
                    // " " + d.get("id") + " "
                    // + (jac + 1) + " " + hits[i].score + " IR-RIS");
                    // //writer_new.println( arr[q_count] + " Q0" + " " +
                    // d.get("id") + " "
                    // // + (jac + 1) + " " + hits[i].score + " IR-RIS");
                    // ll.add(d.get("text"));
                    // score_sum+=hits[i].score;
                    // score_count++;
                    // //tm.put(hits[i].score, d.get("text"));
                    // jac++;
                    // }
                    // if (ll.isEmpty()) {
                    // writer_new.println(arr[q_count] + " Q0" + " " +
                    // d.get("id") + " "
                    // + (jac + 1) + " " + hits[i].score + " IR-RIS");
                    // ll.add(d.get("text"));
                    // jac++;
                    // }
                    //
                    // }

                    // *********************************************************************************************8

                    // if(llx!=null && llx.contains(d.get("id")))
                    // {
                    // writerx.write(arr[q_count]+" "+d.get("id")+"
                    // "+d.get("text")+" "+hits[i].score+" "+sim+"\n");
                    // //llx.remove(d.get("id"));
                    // }
                    // hman.put(arr[q_count], llx);
                    // *********************************************************************************************

                }

                Map<String, Float> sortedMap = sortHashMap(hm_sc);

                for (Map.Entry<String, Float> entry : sortedMap.entrySet()) {
                    int temp = 0;
                    String id = entry.getKey();
                    float score = entry.getValue();

                    // *********************************************************************************************8

//                    if (llx != null && llx.contains(id)) {
//                        writerx.write(
//                                arr[q_count] + " " + id + " " + hm_sc_data.get(id) + " " + score + " " + max + "\n");
//                        // llx.remove(d.get("id"));
//                    }
                    // hman.put(arr[q_count], llx);
                    // *********************************************************************************************
                    float t1=thresh1;
                    float t2=thresh2;
                   
                    //t2=5f;
                    //t1=9f;
                   // t2=pv.get(hmllc);
                    //t1=pv1.get(hmllc);
                   
                    //System.out.println(t2+" "+t1);
                   
//                    if(hmllc==1)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==3)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==4)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==5)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==7)
//                    {
//                        t2=4.5f;
//                        t1=7f;
//                    }
//                    if(hmllc==8)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==13)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==15)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==16)
//                    {
//                        t2=5.5f;
//                        t1=8f;
//                    }
//                    if(hmllc==17)
//                    {
//                        t2=6f;
//                        t1=6.5f;
//                    }
//                    if(hmllc==18)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==19)
//                    {
//                        t2=3f;
//                        t1=7.5f;
//                    }
//                    if(hmllc==20)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==21)
//                    {
//                        t2=6f;
//                        t1=7f;
//                    }
//                    if(hmllc==22)
//                    {
//                        t2=5.5f;
//                        t1=6f;
//                    }
//                    if(hmllc==23)
//                    {
//                        t2=5.5f;
//                        t1=6.5f;
//                    }
//                    if(hmllc==24)
//                    {
//                        t2=5.5f;
//                        t1=6.5f;
//                    }
//                    if(hmllc==28)
//                    {
//                        t2=5.5f;
//                        t1=6.5f;
//                    }
//                    if(hmllc==30)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==39)
//                    {
//                        t2=5f;
//                        t1=6f;
//                    }
//                    if(hmllc==42)
//                    {
//                        t2=4.5f;
//                        t1=7f;
//                    }
//                    if(hmllc==49)
//                    {
//                        t2=6f;
//                        t1=9f;
//                    }
//                    if(hmllc==50)
//                    {
//                        t2=6f;
//                        t1=7.5f;
//                    }
//                    if(hmllc==55)
//                    {
//                        t2=4.5f;
//                        t1=8f;
//                    }
//                   
//                    if(t2!=pv.get(hmllc))
//                    {
//                        System.out.println("wrong2 "+hmllc+" "+t2+" "+pv.get(hmllc));
//                    }
//                    if(t1!=pv1.get(hmllc))
//                        System.out.println("wrong1 "+hmllc+" "+t1+" "+pv1.get(hmllc));
                   
//                    if(hmllc==2 || hmllc==6 || hmllc==11 || hmllc==15 || hmllc==26 || hmllc==33 || hmllc==35 ||
//                            hmllc==38 || hmllc==41 || hmllc==45 || hmllc==47 || hmllc==49)
//                    {
//                        t1=t1+4;                //thresh1+4
//                        t2=t2+3;                //thresh2+3
//                    }
                   
                    if (max < t1 || score < t2)
                        continue;
                    score = score / max;

                    double sim = 0;
                    if (score > 0.4) {

                        for (int k = 0; k < ll.size(); k++) {
                            // double sim = j.similarity(d.get("text"),
                            // ll.get(k));

                            sim = getJaccard(hm_sc_data.get(id), ll.get(k));
                            // if(sim==1)
                            // System.out.println(d.get("text")+"
                            // "+sim+"*******"+ ll.get(k));
                            if (sim >= 0.4) {
                                // System.out.println(d.get("text")+"*****************"+
                                // ll.get(k));
                                // System.out.println("similarity "+sim);
                                temp = 1;
                                break;
                            }

                        }
                        if (temp == 0) {
                            // System.out.println("sorted"+d.get("id")+"
                            // "+d.get("text"));
                            writer_new.println(created + " " + arr[q_count] + " Q0" + " " + id + " " + (jac + 1) + " "
                                    + score + " IRLAB-LDRP");
                            // writer_new.println( arr[q_count] + " Q0" + " " +
                            // d.get("id") + " "
                            // + (jac + 1) + " " + hits[i].score + " IR-RIS");
                            ll.add(hm_sc_data.get(id));
                            score_sum += score;
                            score_count++;
                            // tm.put(hits[i].score, d.get("text"));
                            jac++;
                        }
                        // if (ll.isEmpty()) {
                        // writer_new.println(arr[q_count] + " Q0" + " " +
                        // d.get("id") + " "
                        // + (jac + 1) + " " + hits[i].score + " IR-RIS");
                        // ll.add(d.get("text"));
                        // jac++;
                        // }

                    }

                }
               
               

                // NavigableMap<Float,String> nm = tm.descendingMap();
                //
                // StringBuilder sb= new StringBuilder();
                //
                // for(Map.Entry<Float,String> entry : nm.entrySet()) {
                // Float key = entry.getKey();
                // String value = entry.getValue();
                //
                // System.out.println(key + " => " + value);
                // int in_len=value.split(" ").length;
                // int len=sb.toString().split(" ").length;
                // if(len+in_len<=301)
                // sb.append(" "+value);
                //
                // }
                //
                // writer_new_sum.println(sb.toString().trim());
                // sb.setLength(0);

                // reader can only be closed when there
                // is no need to access the documents any more.
                int z = Integer.parseInt(x);
                z++;
                x = String.valueOf(z);
                writer.close();
                writer_test.close();
                writer_new.close();
                // writer_new_sum.close();
                q_count++;
                double qwwq = 0;
                if (score_count != 0)
                    qwwq = score_sum / score_count;
//                System.out.println("the mean score is " + qwwq);
                // ***************************************************************************************
                // LinkedList<String> lwo= new LinkedList<>();
                // int wow=0;
                //
//                System.out.println(reader.numDocs());
//                 for(int ui=0;ui<reader.numDocs();ui++)
//                 {
//                 Explanation explain = searcher.explain(q, ui);
//                 float theScore = explain.getValue();
//                 if(thm.containsKey(searcher.doc(ui).get("id")))
//                     System.out.println(searcher.doc(ui).get("id")+" "+theScore);
//                 }
                // if(theScore>10.0)
                // {
                // for(int iz=0;iz<lwo.size();iz++)
                // {
                // double sd=getJaccard(lwo.get(iz),
                // searcher.doc(ui).get("text"));
                // if(sd>0.6)
                // {
                // wow=1;
                // break;
                // }
                // }
                // if(wow==0)
                // {
                // lwo.add(searcher.doc(ui).get("text"));
                // writerx.write(created+" "+arr[q_count]+" Q0
                // "+searcher.doc(ui).get("id")+" "+theScore+"\n");
                // }
                //
                // }
                // }

                // for(int zx=0;zx<llx.size();zx++)
                // {
                // if(tweet_doc.containsKey(llx.get(zx)))
                // {
                // Explanation explain = searcher.explain(q,
                // tweet_doc.get(llx.get(zx)));
                // float theScore = explain.getValue();
                // System.out.println(arr[q_count-1]+" "+llx.get(zx)+"
                // "+theScore+" flm");
                // //System.out.println("fnhjcbdc"+explain.getDescription()+"
                // "+explain.getValue());
                // if(theScore>=10.0)
                // writerx.write(arr[q_count-1]+" "+llx.get(zx)+"
                // "+theScore+"\n");
                //
                // }
                // }

                // ******************************************************************************************
            }
           
//            for (Map.Entry entry : thm.entrySet()) {
//                System.out.println(entry.getKey() + ", " + entry.getValue());
//            }
           
            //writerx.close();
            reader.close();

            String mergedFilePath = "/home/sandip/datas/2017output/merge/fin/merge_" + date + ".txt";
            // String mergedFilePath =
            // "/home/rishab/Downloads/smerp/merge/merge.txt";

            File mergedFile = new File(mergedFilePath);

            mergeFiles(mergedFile, date);
             }
        finalMerge(String.valueOf(tedious), String.valueOf(thresh1), String.valueOf(thresh2));
             }
         }
       
       
        //wri_text.close();
        }
            //finalMerge(String.valueOf(tedious));
        }
        // wri.close();

        // ***************************************************************
        // for(Entry<String, LinkedList<String>> entry : hman.entrySet()) {
        // String key = entry.getKey();
        // //wri.println(key);
        // LinkedList<String> ll = new LinkedList<>();
        // ll=hman.get(key);
        // for(int i=0;i<ll.size();i++)
        // {
        // System.out.println(key+" "+ll.get(i));
        // }
        // }
        // *******************************************************
//    }

    // private static void addDoc(IndexWriter w, String title, String id, String
    // followers_count, String favourites_count,
    // String created_at, String lang, String retweeted) throws IOException {
    // Document doc = new Document();
    // // Field title_field = new Field("title" , title ,
    // // Field.Store.YES,Field.);
    // doc.add(new TextField("text", title, Field.Store.YES));
    //
    // // use a string field for isbn because we don't want it tokenized
    // doc.add(new StringField("id", id, Field.Store.YES));
    // doc.add(new StringField("follwers_count", followers_count,
    // Field.Store.YES));
    // doc.add(new StringField("favourites_count", favourites_count,
    // Field.Store.YES));
    // doc.add(new StringField("created_at", created_at, Field.Store.YES));
    // // if(!url.isEmpty())
    // // doc.add(new StringField("url", id, Field.Store.YES));
    // doc.add(new StringField("lang", lang, Field.Store.YES));
    // doc.add(new StringField("retweeted", retweeted, Field.Store.YES));
    // w.addDocument(doc);
    // }
    
    private static void addDoc(IndexWriter w, String title, String id) throws IOException 
    {
        Document doc = new Document();
        // Field title_field = new Field("title" , title ,
        // Field.Store.YES,Field.);
        doc.add(new TextField("text", title, Field.Store.YES));
       // doc.add(new TextField("expanded_url", expanded_url, Field.Store.YES));
        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("id", id, Field.Store.YES));
        w.addDocument(doc);
    }


    private static String cleanData(String x) {
        Pattern pt = Pattern.compile("[^a-zA-Z0-9@ ]");
        Matcher match = pt.matcher(x);
        while (match.find()) {
            String s = match.group();
            //x = x.replaceAll("\\" + s, "");
        }
        String pure = x.replaceAll("@\\p{L}+", "");
        //String pura = pure.replaceAll("http\\p{L}+", "");
        String after = pure.trim().replaceAll(" +", " ");
        return after;
        // System.out.println(after);
    }

    public static void mergeFiles(File mergedFile, String fold_name) {
        FileWriter fstream = null;
        BufferedWriter out = null;
        try {
            fstream = new FileWriter(mergedFile, false);
            out = new BufferedWriter(fstream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (File f : getAllLogFiles(fold_name)) {
            // System.out.println("merging: " + f.getName());
            FileInputStream fis;
            try {
                fis = new FileInputStream(f);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                String aLine;
                int temp = 0;
                while ((aLine = in.readLine()) != null) {
                    if (temp == 0) {
                        // 3System.out.println(aLine);
                        temp++;
                    }
                    out.write(aLine);
                    out.newLine();
                }

                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File[] getAllLogFiles(String fold) {
        File folder = new File("/home/sandip/datas/2017output/fin/" + fold + "/sorted/");
        File[] files = folder.listFiles();

        return files;
    }

    public static double getJaccard(String a, String b) {
        HashMap<String, Integer> map = new HashMap();
        String array1[] = a.trim().split(" ");

        for (int i = 0; i < array1.length; i++) {
            if (map.containsKey(array1[i])) {
                map.put(array1[i], map.get(array1[i]) + 1);
            } else {
                map.put(array1[i], 1);
            }
        }

        String array2[] = b.split(" ");
        float intersection = 0;
        float union = array1.length + array2.length;

        for (int i = 0; i < array2.length; i++) {
            if (map.containsKey(array2[i])) {
                int val = map.get(array2[i]);
                if (val == 1) {
                    map.remove(array2[i]);
                } else {
                    map.put(array2[i], val - 11);
                }
                intersection++;
            }
        }

        return intersection / (union - intersection);
    }

    public static void makeSummarization() {
        TreeMap<Double, String> tm = new TreeMap<>();

        NavigableMap<Double, String> nm = tm.descendingMap();

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Double, String> entry : nm.entrySet()) {
            Double key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + " => " + value);
            int in_len = value.split(" ").length;
            int len = sb.toString().split(" ").length;
            if (len + in_len <= 6)
                sb.append(" " + value);

        }

        System.out.println(sb.toString().trim());
    }

    public static double customSimilarity(String x, String y, String z, float w1, float w2) {
        HashSet<String> hs1 = new HashSet<>();
        int l1 = y.split(" ").length;
        String temp1[] = y.split(" ");
        for (int i = 0; i < l1; i++)
            hs1.add(temp1[i]);

        HashSet<String> hs2 = new HashSet<>();
        int l2 = z.split(" ").length;
        String temp2[] = z.split(" ");
        for (int i = 0; i < l2; i++) {
            if (!hs1.contains(temp2[i]))
                hs2.add(temp2[i]);
        }

        int count1 = 0;
        int count2 = 0;

        int l = x.split(" ").length;
        String temp[] = x.split(" ");
        for (int i = 0; i < l; i++) {
            if (hs1.contains(temp[i]))
                count1++;

            if (hs2.contains(temp[i]))
                count2++;
        }

        return ((count1 * w1) * (count2 * w2) * l1) / l;
    }

    public static Map<String, Float> sortHashMap(HashMap<String, Float> unsortedMap) {
        Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    public static void mergeFiles(File mergedFile) {
        FileWriter fstream = null;
        BufferedWriter out = null;
        try {
            fstream = new FileWriter(mergedFile, false);
            out = new BufferedWriter(fstream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (File f : getAllLogFiles()) {
            System.out.println("merging: " + f.getName());
            FileInputStream fis;
            try {
                fis = new FileInputStream(f);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                String aLine;
                int temp = 0;
                while ((aLine = in.readLine()) != null) {
                    if (temp == 0) {
                        // 3System.out.println(aLine);
                        temp++;
                    }
                    out.write(aLine);
                    out.newLine();
                }

                in.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File[] getAllLogFiles() {
        File folder = new File("/home/sandip/datas/2017output/merge/fin");
        File[] files = folder.listFiles();

        return files;
    }

    public static void finalMerge(String x, String thresh1, String thresh2) {
        String mergedFilePath = "/home/sandip/datas/2017output/result/jm/jm091117_"+x+" "+thresh1+"_"+thresh2+".txt";
        // FileReader reader=new
        // FileReader("C:\\Users\\rishabS\\Downloads\\btp\\first\\sorted\\1_output.txt");
        // File[] files = new File[2];
        // files[0] = new File(sourceFile1Path);
        // files[1] = new File(sourceFile2Path);

        // System.out.println(files[0].listFiles());

        File mergedFile = new File(mergedFilePath);

        // mergeFiles(files, mergedFile);
        mergeFiles(mergedFile);
       
    }
   
   
    public static String expandUrl(String shortenedUrl) throws IOException {
        URL url = new URL(shortenedUrl);   
        // open connection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
       
        // stop following browser redirect
        httpURLConnection.setInstanceFollowRedirects(true);
        
        // extract location header containing the actual destination URL
        String expandedURL = httpURLConnection.getHeaderField("Location");
        httpURLConnection.disconnect();
        //System.out.println("inside "+expandedURL);
        return expandedURL;
    }
   
   
    public static ArrayList<String> extractUrls(String text)
    {
        ArrayList<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }
   
    public static String repeatedExpansion(String url) throws IOException
    {
        String temp=url;
        for(int i=0;i<4;i++)
        {
            temp=expandUrl(url);
            if(temp!=null)
                url=temp;
        }
        return url;
    }
   
    public static URLConnection connectURL(String strURL) {
        URLConnection conn =null;
        try {
            URL inputURL = new URL(strURL);
            conn = inputURL.openConnection();
            int test = 0;

        }catch(MalformedURLException e) {
            System.out.println("Please input a valid URL");
        }catch(IOException ioe) {
            System.out.println("Can not connect to the URL");
        }
        return conn;
    }
   
    public static String separate(String link)
    {
        String a[]=link.split("//");
        String x=a[a.length-1];
        x=x.replaceAll("-", " ");
        x=x.replaceAll("\\.", " ");
        x=x.replaceAll("/", " ");
        x=x.replaceAll("\\?", " ");
        x=x.replaceAll("=", " ");
        x=x.replaceAll("\\+", " ");
        x=x.replaceAll("_", " ");
        x=x.replaceAll("&", " ");
        return x;
    }
   
 /*   public static HashSet<String> getNamedEntities(String x) {
        NERDemo nd = new NERDemo();
        LinkedList<String> ll = new LinkedList<>();
        HashSet<String> hs = new HashSet<>();
        try {
            ll = nd.getNouns(x);
            for(int i=0;i<ll.size();i++)
                hs.add(ll.get(i));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hs;

    } */
   
  /*  public static int getNECount(String tweet, String query)
    {
        HashSet<String> hs1=getNamedEntities(tweet);
//        for(String s:hs1)
//            System.out.println("tweet "+s);
       
//        System.out.println("q "+query);
        HashSet<String> hs2=getNamedEntities(query);
//        for(String s1:hs2)
//            System.out.println("query "+s1);
       
       
        int count=0;
        for(String s:hs1)
        {
            if(hs2.contains(s))
                count++;
        }
       
        return count;
    } */
   
    public static int getMatchURLCount(String tweet, String query) throws IOException
    {
        //System.out.println(tweet+" "+query);
        int count=0;
        HashSet<String> hs1=new HashSet<>();
        ArrayList<String> urls=extractUrls(tweet);
        for(int i=0;i<urls.size();i++)
            System.out.println(urls.get(i));
        for(int i=0;i<urls.size();i++)
        {
            String temp1=urls.get(i);
            if(temp1.length()<=8)
            {
                System.out.println("less than");
                continue;
            }
            URLConnection urlConn = connectURL(urls.get(i));
            urlConn.getHeaderFields();
            //System.out.println("Second Original URL: "+ urlConn.getURL());
            String temp=urlConn.getURL().toString();
            String expand= separate(temp);
            System.out.println(expand);
            String e[]=expand.split(" ");
            for(int j=0;j<e.length;j++)
            {
                //if(e[j]!=null)
                hs1.add(e[j]);
               
            }
        }
        for(String s:hs1)
        {
            if(query.contains(s.toLowerCase()))
                count++;
        }
       
        return count;
    }

    private static String getURLX(String text) throws MalformedURLException, IOException {
        System.out.println("URL data fetching started." +flag); 
        URL url = new URL(text);
        try{
    URLConnection con = url.openConnection();
    InputStream in = con.getInputStream();
    String encoding = con.getContentEncoding();  // ** WRONG: should use "con.getContentType()" instead but it returns something like "text/html; charset=UTF-8" so this value must be parsed to extract the actual encoding
    encoding = encoding == null ? "UTF-8" : encoding;
//    String body = IOUtils.toString(in, encoding);
 //     String tex=  body.replaceAll("<a.*?>","");
  //    tex=tex.replaceAll("</a>","");
//System.out.println(body);
String regex ="<p>(.*?)</p>";
String info="";
//List<String> reg = getAllMatches(text,regex);
//Matcher m = Pattern.compile(regex).matcher(tex);
  //      while(m.find()) {
            
    //        info+=m.group(1);
     //       info+="\n";
            
     //   }
        System.out.println("URL data fetching ended."+flag);
        flag++;
        return info;
        }catch(Exception e){ return null;}
        
    }

}

class ValueComparator implements Comparator {
    Map map;

    public ValueComparator(Map map) {
        this.map = map;
    }

    public int compare(Object keyA, Object keyB) {
        Comparable valueA = (Comparable) map.get(keyA);
        Comparable valueB = (Comparable) map.get(keyB);
        return valueB.compareTo(valueA);
    }
   
   

}