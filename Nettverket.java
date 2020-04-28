package prepper;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Nettverket {
	
	private String minIp = "";
	
	public Nettverket() {
		minIp = getIp();
	}
	
	public String getMyIp() {
		return this.minIp;
	}
	
	private String getIp() {
		String ip = "NA";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			Scanner les = new Scanner(new InputStreamReader(
	                whatismyip.openStream()));
			ip = les.nextLine();
			les.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
}
