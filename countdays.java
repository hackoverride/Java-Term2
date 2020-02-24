import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math; 

class Solution {
    public int daysBetweenDates(String date1, String date2) {
        int days = 0;
        // Assume that the days in string comes in like : "2020-01-30"
        SimpleDateFormat formatet = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date dato1 = formatet.parse(date1);
            Date dato2 = formatet.parse(date2);
            long dager = dato2.getTime() - dato1.getTime();
            float totaldager = (dager / (1000*60*60*24));
            return (int)(Math.abs(totaldager));
            
        } catch (Exception e){
            System.out.print(e);
        }
            
        
        
        return 0;
    }
}
