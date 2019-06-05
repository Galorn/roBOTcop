package webProcessing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dto.Article;
import dto.ArticleGeneral;
import rssPackage.RSSMain;
import util.CSVFileCreator;
import util.TimeUtils;

public class PageInformation {

	private static final Logger logger = LogManager.getLogger(PageInformation.class);
	private static final TimeUtils timer = new TimeUtils();
	private CSVFileCreator csvFIleCreator = new CSVFileCreator();
	private RSSMain rssmain = new RSSMain();
	private final int VALEURMIN = 0;
	private final int VALEURMAX = 10;
	
	// Start the monitoring experience for 
		// Parameter 
		// n = seconds to monitor 
	public void startMonitoring(int secondsToMonitor) {
	    for(int i = 0; i< secondsToMonitor; i++) {
	    	// launch the monitoring of the main page and extract two list, one with sold out the other one for available
	    	// First one is sold out, second one is available
	    	String date = timer.getDate();
	    	List<Element> listsOfArticle = monitorMainPage();
	    	List<ArticleGeneral> allArticles = extractAllArticle(listsOfArticle);
	    	List <Article> articles = processAllArticles(allArticles);
	    	boolean isCSVOut = csvFIleCreator.createCSVofAllArticles(articles, date);
	    	rssmain.rssStart();
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



	/**
	 * Method that outputs all the link of articles of the main page
	 * @return
	 */
	
	public List<Element> monitorMainPage(){
		System.out.println("monitor main Page");
		Document document;
		List<Element> results = new ArrayList<Element>();
		// se connecte au site
		document = connectToPage("https://www.supremenewyork.com/shop/all");
		// liste tous les éléments de la classe inner-article (i.e. tout ce qu'il y a sur la page
		Elements elements = document.getElementsByClass("inner-article");
		List<Element> articlesGeneral = new ArrayList<Element>();
		for (Element element : elements) {
			// extrait chaque link a des articles 
			Element link = element.select("a").first();
			articlesGeneral.add(link);
		}
		results = articlesGeneral;		

		return results;

	}
	/**
	 * Classe pour process les éléments et en sortir des vrais objets 
	 * @param articles
	 * @return
	 */
	public List<ArticleGeneral> extractAllArticle(List<Element> articles) {
		
		// process all list of article and extract the link 
		List<ArticleGeneral> results = new ArrayList<ArticleGeneral>();
		for (Element article : articles) {
			// process all the articles in the list
			ArticleGeneral articleGeneral = new ArticleGeneral();
			if (!article.getElementsByClass("sold_out_tag").isEmpty()) {
				articleGeneral.setAltName(article.select("img").first().attr("alt"));
				articleGeneral.setJpgLink(article.select("img").first().attr("src"));
				articleGeneral.setLink(article.attr("abs:href"));
				articleGeneral.setSoldOut(true);
			} else {
				articleGeneral.setAltName(article.select("img").first().attr("alt"));
				articleGeneral.setJpgLink(article.select("img").first().attr("src"));
				articleGeneral.setLink(article.attr("abs:href"));
				articleGeneral.setSoldOut(false);
			}
			results.add(articleGeneral);
		}
		System.out.println(results.toString());
		return results;
	}
	
	/**
	 * Classe pour monitor une page d'article en particulier
	 * @return
	 */
	public List<Element> monitorPage(String link){
		System.out.println("monitor Page " + link);
		Document document;
		List<Element> results = new ArrayList<Element>();
		// se connecte au site
		document = connectToPage(link);
		// liste tous les éléments de la classe inner-article (i.e. tout ce qu'il y a sur la page
		System.out.println(document.toString());
		/*List<Element> articlesGeneral = new ArrayList<Element>();
		for (Element element : elements) {
			// extrait chaque link a des articles 
			Element linkPage = element.select("a").first();
			articlesGeneral.add(linkPage);
		}
		results = articlesGeneral;*/		

		return results;

	}
	
	/**
	 * Classe
	 * 
	 * @param allArticles
	 * @return
	 */
	
	
	public Boolean outputAsCSV(List<Article> allArticles) {
		// TODO Auto-generated method stub
		
		
		
		
		return true;
	}

	/**
	 *  Classe pour process tous les ArticleGeneral
	 *  
	 */
	public List<Article> processAllArticles(List<ArticleGeneral> allArticles) {
			Document document;
			List<Article> articles = new ArrayList<Article>();
			for (ArticleGeneral articleGeneral : allArticles) {
				document = connectToPage(articleGeneral.getLink());
				String nameItem = getNameOfArticle(document);
				String color = getColorOfArticle(document);
				String price = getPriceOfArticle(document);
				List<String> sizes = getSizesOfArticle(document);
				boolean isSoldOut = true;
				if (!sizes.isEmpty()) {
					isSoldOut = false;
				}
				String link = document.baseUri();
				Article article = new Article(nameItem, color, price, sizes, isSoldOut, link);
				articles.add(article);
				System.out.println(document.select("h1"));
			}
			return articles;
	}
	
	/**
	 * Classe pour trouver les tailles disponibles pour l'item
	 * 
	 */
	
	public List<String> getSizesOfArticle(Document document) {
		List<String> sizes = new ArrayList<>();
		//<select name="size" id="size"><option value="53313">Medium</option> 
		//<option value="53314">Large</option> <option value="53315">XLarge</option></select>
		
		// Peut etre sélectionner la div "details" avant de chercher les p ? 
		Elements selectElements = document.getElementsByTag("select");
		for (Element selectElement : selectElements) {
			// get all options
			Elements optionElements = selectElement.getElementsByTag("option");
			for (Element optionElement : optionElements) {
				sizes.add(optionElement.text());
			}
		}
		return sizes;
	}
	
	
	
	
	/**
	 * Classe pour trouver le prix de l'item
	 */
	public String getPriceOfArticle(Document document) {
		String price = "";
		//  <p class="price" itemprop="offers" itemscope 
		// 	itemtype="http://schema.org/Offer"><span data-currency="EUR" itemprop="price">€1,698</span></p>
		// Peut etre sélectionner la div "details" avant de chercher les p ? 
		Elements priceElements = document.getElementsByClass("price");
		for (Element priceElement : priceElements) {
			// Get the name if the item
			// <p class="style protect" itemprop="model">White</p>
			if (priceElement.attr("itemprop").equals("price")) {
				// get the inner text of the html tag if we are on the item name 
				return price =  priceElement.text(); 
			}
		}
		return price;
	}
	/**
	 * Classe pour trouver la couleur de l'item
	 */
	public String getColorOfArticle(Document document) {
		String color = "";
		// Peut etre sélectionner la div "details" avant de chercher les p ? 
		Elements pElements = document.select("p");
		for (Element pElement : pElements) {
			// Get the name if the item
			// <p class="style protect" itemprop="model">White</p>
			if (pElement.attr("itemprop").equals("model")) {
				// get the inner text of the html tag if we are on the item name 
				return color =  pElement.text(); 
			}
		}
		return color;
	}
	
	
	/**
	 * Classe pour trouver le nom de l'item 
	 * 
	 */
	public String getNameOfArticle(Document document) {
		String nameItem = "";
		Elements h1Elements = document.select("h1");
		for (Element h1Element : h1Elements) {
			// Get the name if the item
			if (h1Element.attr("itemprop").equals("name")) {
				// get the inner text of the html tag if we are on the item name 
				return nameItem =  h1Element.text(); 
			}
		}
		return nameItem;
	}

	
	/**
	 * Classe conteneur 
	 * @param link
	 * @return
	 */
	public Document connectToPage(String link) {
		Document document = null;
		try {
			document = Jsoup.connect(link).get();
		}catch(IOException e){
			e.printStackTrace();
		}
		return document;
	}
}
