//package testJava;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.junit.Assert;
//
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//public class Test {
//    private static final Logger logger = LogManager.getLogger(Test.class);
//
//	@org.junit.Test
//	public void homePage() throws Exception {
//	    try (final WebClient webClient = new WebClient()) {
//	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
//	        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
//
//	        final String pageAsXml = page.asXml();
//	        Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
//
//	        final String pageAsText = page.asText();
//	        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
//	    }
//	}
//}
