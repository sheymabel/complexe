package Ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Connexion implements Serializable{
	private Compte user;
	private  String date;
	public  static String fileLog="FileLogs3.dat"; 
	public Connexion (Compte u) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Set user and date
        this.user = u;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(now);

        // Optionally append to existing logs
        ArrayList<Connexion> logs = readLogsFromFile();  // Read existing logs if file exists
        logs.add(this);  // Add the new connection
        writeLogsToFile(logs);  // Write the updated logs back to the file
    }

    private void writeLogsToFile(ArrayList<Connexion> logs) throws FileNotFoundException, IOException {
    	try (FileOutputStream fos = new FileOutputStream(fileLog);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
               oos.writeObject(logs);
           }		
	}

    private ArrayList<Connexion> readLogsFromFile() throws IOException, ClassNotFoundException {
        ArrayList<Connexion> logs = new ArrayList<>();
        File logFile = new File(fileLog);

        if (logFile.exists()) {
            try (FileInputStream fis = new FileInputStream(logFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                logs = (ArrayList<Connexion>) ois.readObject();
            }
        }
        return logs;
    }

	 
	  @Override
	 public String toString() {
		 return("User: " + user.getLogin() +  " Connection Date: " + date+   "\n");
	 }
}
