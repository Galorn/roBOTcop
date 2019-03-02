package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {
	
	    public String getDate() {
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
	        System.out.println( sdf.format(cal.getTime()) );
	        
	        return sdf.format(cal.getTime());
	    }

}

