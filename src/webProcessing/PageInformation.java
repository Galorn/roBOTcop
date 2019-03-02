package webProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import util.TimeUtils;

public class PageInformation {

	private static final Logger logger = LogManager.getLogger(PageInformation.class);
	private static final TimeUtils timer = new TimeUtils();
	private final int VALEURMIN = 0;
	private final int VALEURMAX = 10;
	
	// Start the monitoring experience for 
		// Parameter 
		// n = seconds to monitor 
	public void startMonitoring(int secondsToMonitor) {
	    for(int i = 0; i< secondsToMonitor; i++) {
	    	// launch the monitoring
	    	monitorMainPage();
	    	// get sleepTime
		    try {
		    	Random r = new Random();
		    	int sleepTime = VALEURMIN + r.nextInt(VALEURMAX - VALEURMIN);
		    	System.out.println(sleepTime + " seconds to sleep");
				TimeUnit.SECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	
	
	public void monitorMainPage(){
		System.out.println("JJJ");
		 try (WebClient webClient = new WebClient()) {
			 	HtmlPage page = webClient.getPage("https://www.supremenewyork.com/shop/all");
		        System.out.println(page.getTitleText());
		        final String pageAsXml = page.asXml();
		        final String pageAsText = page.asText();
		        System.out.println(pageAsXml);
		        String dateSeconds = timer.getDate();
		        String fileName = "D:/temp/test" + dateSeconds + ".txt";
		        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		        writer.write(pageAsXml);
		        writer.close();
		        System.out.println(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		        // processing text file
		        readPageFile(dateSeconds, fileName);
		 } catch (Exception e){
			 System.out.println(e);
			 System.out.println("catch");
		 }
	}
	
	
	public void readPageFile(String dateSeconds, String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)); BufferedWriter writer = new BufferedWriter(new FileWriter("D:/temp/testLINK" + dateSeconds + ".txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	if (line.contains("href=\"/shop/")){
		    		writer.write(line);
		    		writer.write(System.lineSeparator());
		    	}
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
}
