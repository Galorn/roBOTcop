package webProcessing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PageInformation {

	private static final Logger logger = LogManager.getLogger(PageInformation.class);
	public static void getPageTitle(String url){
		System.out.println("JJJ");
		 try (WebClient webClient = new WebClient()) {
			 	HtmlPage page = webClient.getPage("http://www.google.com");
		        System.out.println(page.getTitleText());
		        final String pageAsXml = page.asXml();
		        final String pageAsText = page.asText();
		        System.out.println(pageAsXml);
		        System.out.println(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		 } catch (Exception e){
			 System.out.println(e);
			 System.out.println("catch");
		 }
	}
}
