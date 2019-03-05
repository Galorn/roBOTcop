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

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import util.MainPageHandler;
import util.TimeUtils;

public class PageInformation {

	private static final Logger logger = LogManager.getLogger(PageInformation.class);
	private static final TimeUtils timer = new TimeUtils();
	MainPageHandler mpHandler = new MainPageHandler();
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
		    	System.out.println("run number" + i + " " + sleepTime + " seconds to sleep");
				TimeUnit.SECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	
	
	public void monitorMainPage(){
		System.out.println("monitor main Page");
		// Open a webclient
		 try (WebClient webClient = new WebClient()) {
			 	// open the supreme page shop all
			 	HtmlPage page = webClient.getPage("https://www.supremenewyork.com/shop/all");
			 	// process the page as XML
		        final String pageAsXml = page.asXml();
		        // process the page as TXT
		        final String pageAsText = page.asText();
		        // get the time as expected from the util package
		        String dateSeconds = timer.getDate();
		        // define the filename
		        String fileNameXML = "D:/temp/test" + dateSeconds + ".xml";
		        String fileNameTXT = "D:/temp/test" + dateSeconds + ".txt";
		        // write into .txt file
		        BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameTXT));
		        writer.write(pageAsText);
		        writer.close();
		        // write into .xml file
		        BufferedWriter writerXML = new BufferedWriter(new FileWriter(fileNameXML));
		        writerXML.write(pageAsXml);
		        writerXML.close();
		        System.out.println(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		        // processing text file
		        readPageFile(dateSeconds, fileNameTXT);
		        //readPageFIleAsXML(dateSeconds, fileNameXML);
		 } catch (Exception e){
			 System.out.println(e);
			 System.out.println("catch");
		 }
	}
	
	// Goal : read the .txt file (whole front page in XML) and produce a file containing the href 
	// parameters : 
		// dateSeconds : the date in Seconds that has to be sync for the name of the file 
		// fileName : file .txt whole front page 
	public void readPageFile(String dateSeconds, String fileNameTXT) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileNameTXT)); BufferedWriter writer = new BufferedWriter(new FileWriter("D:/temp/" + dateSeconds+ "txtProcess"+ ".txt"))) {
		    String line;
		    // stream the whole content of the file line by line
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
	
	public void readPageFIleAsXML(String dateSeconds, String fileNameXML) {
		try {
			
			// Initialize the Parsing 
		    SAXParserFactory spf = SAXParserFactory.newInstance();
		    spf.setNamespaceAware(true);
		    SAXParser saxParser = spf.newSAXParser();		
		    saxParser.parse(fileNameXML,mpHandler);
			 			
			} catch (Exception e) {			
			System.out.println("Exception capturée : ");			
			e.printStackTrace(System.out);
			return;
			
			}
	}
	
}
