package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

import dto.Article;

public class CSVFileCreator {

	/**
	 * Classe pour output un fichier csv
	 * @param articles
	 * @param date
	 * @return
	 */
	public boolean createCSVofAllArticles(List<Article> articles, String date) {
		
		 // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File("D:/output/temp/" + date + "_Articles.csv"); 
	    try( FileWriter outputfile = new FileWriter(file);
		    CSVWriter writer = new CSVWriter(outputfile, ';'
		    ,CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER
		    , CSVWriter.DEFAULT_LINE_END);
	    		  ) { 
	    	// Parcours tous les soldOut
	    	for (Article article : articles) {
		        // adding header to csv 
		        String[] header = {"SoldOut"}; 
		        writer.writeNext(header); 
	    		if (article.isSoldOut()){
	    			// Plusieurs size de dispo
	    			if (article.getSizes().size()>1) {
	    				for (String size : article.getSizes()) {
	    					String[] line = { article.getName(), article.getColor(), article.getPrice(),size, article.getLink() };
	    	    	        writer.writeNext(line); 
	    				}
	    			} else {
    					String[] line = { article.getName(), article.getColor(), article.getPrice(),article.getSizes().get(0), article.getLink() }; 
    	    	        writer.writeNext(line); 
	    			}
	    		}
	    	}
	    	
	    	// Parcours tous les available
	    	for (Article article : articles) {
		        // adding header to csv 
		        String[] header = {"Available"}; 
		        writer.writeNext(header); 
	    		if (!article.isSoldOut()){
					if (article.getSizes().size()>1) {
	    				for (String size : article.getSizes()) {
	    					String[] line = { article.getName(), article.getColor(), article.getPrice(),size, article.getLink() };
	    	    	        writer.writeNext(line); 
	    				}
					} else {
    					String[] line = { article.getName(), article.getColor(), article.getPrice(),article.getSizes().get(0), article.getLink() }; 
    	    	        writer.writeNext(line); 
	    			}
    			}
	    	}
	    } catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
		return true;
	}
	
	
	
}
