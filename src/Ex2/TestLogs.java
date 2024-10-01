package Ex2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.util.ArrayList;

public class TestLogs {
	public static void main (String argv[])  	
	
	{      System.out.println ("Journal des connexions:");  
		
	 ArrayList<Connection> connections = new ArrayList<Connection>();

	 try {
         FileInputStream fis = new FileInputStream("Journal.dat");
         ObjectInputStream ois = new ObjectInputStream(fis);
         connections = (ArrayList<Connection>) ois.readObject();  
         ois.close();
     } catch (FileNotFoundException e) {
         System.out.println("Aucun historique de connexion trouvé.");
     } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
     }
	}
	//sirlation constucteur 
}
