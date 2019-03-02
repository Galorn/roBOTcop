package main;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import UI.UIMain;
import configuration.StartupConfiguration;
import webProcessing.PageInformation;

public class main {
	
	

		  public static void main(String[] args){ 
		    System.out.println("test");
		    // lancement configuration
		    StartupConfiguration.initializeConfiguration();
		    // lancement proxy
		    StartupConfiguration.initializeProxy();
		    // lancement UI
		    UIMain.startupUI();
		    System.out.println("afterUI");
		    PageInformation PI = new PageInformation();
		    PI.startMonitoring(20);

		   
		  }

}
