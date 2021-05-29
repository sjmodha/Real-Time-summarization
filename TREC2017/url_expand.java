import java.awt.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	//https://t.co/Irfv6
	
	public static void main(String[] args) throws IOException {
		
		for(int k=0;k<100;k++)
		{
		int x=getMatchURLCount("https://t.co/Wu3UndLFRA", "https://t.co/Wu3UndLFRA");
		System.out.println(x);
		}
		
//        String shortenedUrl = "https://t.co/Wu3UndLFRA";
//        String expandedURL = repeatedExpansion(shortenedUrl);
//        //System.out.println(expandedURL);
////        String temp="";
////        for(int i=0;i<4;i++)
////        {
////        	if(expandedURL!=null)
////        		temp = expandUrl(expandedURL);
////        	//System.out.println(temp+" "+i);
////        }
////        if(temp!=null)
////        	expandedURL=temp; 
//        System.out.println(shortenedUrl + "-->" + expandedURL);
//        
//        //cleanData("Australian Olympic chief laughs off Andrew Bogut's Rio athletes' village complaints https://\\t.co/Wu3UndLFRA");
//    
//        ArrayList<String> extractedUrls = extractUrls(" with Ukraine troops: The BBC has witnessed more heavy fighting in eastern Ukraine as rebel\u2026 http://t.co/jlQn6SBRY5 https://t.co/Wu3UndLFRA http://\\t.co/E2yqADvZPu   ");
////  	http://t.co/kNxR6H3HKA    http://t.co/0jH77BRMsr
//        for (String shortURL : extractedUrls)
//        {
////            System.out.println("first method "+repeatedExpansion(shortURL));
////        	System.out.println("Short URL: "+ shortURL);
//            URLConnection urlConn = connectURL(shortURL);
//            urlConn.getHeaderFields();
//            String temp=urlConn.getURL().toString();
//            System.out.println("Second Original URL: "+ temp);
//        	
//        }
//        
////        String shortURL="http://t.co/0jH77BRMsr";
////        
////        System.out.println("Short URL: "+ shortURL);
////        URLConnection urlConn = connectURL(shortURL);
////        urlConn.getHeaderFields();
////        System.out.println("Original URL: "+ urlConn.getURL());
	
	}
     
    public static String expandUrl(String shortenedUrl) throws IOException {
        URL url = new URL(shortenedUrl);    
        // open connection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY); 
        
        // stop following browser redirect
        httpURLConnection.setInstanceFollowRedirects(false);
         
        httpURLConnection.connect();
        
        // extract location header containing the actual destination URL
        String expandedURL = httpURLConnection.getHeaderField("Location");
        
        httpURLConnection.getInputStream().close();
        
        httpURLConnection.disconnect();
        //System.out.println("inside "+expandedURL); 
        return expandedURL;
    }
    
    private static String cleanData(String x) {
		Pattern pt = Pattern.compile("[^a-zA-Z0-9@ ]");
		//String link=
		Matcher match = pt.matcher(x);
		while (match.find()) {
			String s = match.group();
			//x = x.replaceAll("\\" + s, "");
		}
		String pure = x.replaceAll("@\\p{L}+", "");
		//String pura = pure.replaceAll("http\\p{L}+", "");
		String after = pure.trim().replaceAll(" +", " ");
		
		 System.out.println(after);
		 return after;
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
    			continue;
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
	
}
