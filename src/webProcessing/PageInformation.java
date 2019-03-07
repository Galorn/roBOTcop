package webProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import dto.Article;
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

		Document document;
		try {
			// se connecte au site
			document = Jsoup.connect("https://www.supremenewyork.com/shop/all").get();
			// liste tous les éléments de la classe inner-article (i.e. tout ce qu'il y a sur la page
			Elements elements = document.getElementsByClass("inner-article");
			List<Element> articlesSoldOut = new ArrayList<Element>();
			List<Element> articlesEnStock = new ArrayList<Element>();
			for (Element element : elements) {
				// extrait chaque link a des articles 
				Element link = element.select("a").first();
				System.out.println(link.toString());
				// Vérifié si element sold out 
				if (!link.getElementsByClass("sold_out_tag").isEmpty()) {
					System.out.println("found sold out tag");
					// ajoute les articles sold out
					articlesSoldOut.add(link);
				} else {
					articlesEnStock.add(link);
				}
				String relHref = link.attr("href"); // URL relative 
				String[] relHrefSplit = relHref.split("/");
				System.out.println(relHref);
				System.out.println(relHrefSplit[2]);
				System.out.println(relHrefSplit[relHrefSplit.length -1]);
				String absHref = link.attr("abs:href"); // URL complete
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Classe pour process les éléments et en sortir des vrais objets 
	 * @param articles
	 * @return
	 */
	public List<Article> processList(List<Element> articles) {
		
		for (Element article : articles) {
			
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//
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
