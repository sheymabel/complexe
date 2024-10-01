package Ex2;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;

public class ConnectionForm extends JFrame implements ActionListener{

	JTextField txtLogin = new JTextField();
	JTextField txtpwd = new JTextField();
	JLabel lbllogin = new JLabel ("Login");
	JLabel lblpwd = new JLabel ("Mot de passe");
	JButton btnconnecter = new JButton ("Se connecter");
	JButton btnInscrire = new JButton ("S'inscrire");
	File f = new File ("fusers.dat");
	public ConnectionForm ()
	{
		super ("Formulaire de connexion");
		setSize(350,200);
		JPanel p1 = new JPanel(new GridLayout(0,2));
		p1.add(lbllogin); p1.add(txtLogin);
		p1.add(lblpwd); p1.add(txtpwd);
		JPanel p2 = new JPanel();
		btnconnecter.addActionListener(this);
		btnInscrire.addActionListener(this);
		p2.add(btnconnecter);	p2.add(btnInscrire);
		getContentPane().add(p1);
		getContentPane().add(p2, BorderLayout.SOUTH);
		this.setLocation(500,300);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
	  JButton bt = (JButton) evt.getSource();
	  ArrayList<Compte> users = new ArrayList<Compte>(); // Create an ArrayList to store accounts

	  try {
	    FileInputStream fis = new FileInputStream(f);
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    users = (ArrayList<Compte>) ois.readObject();  // Cast the read object to ArrayList<Compte>
	    ois.close();
	  } catch (FileNotFoundException e) {
	  } catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	  }

	  if (bt.equals(btnconnecter)) {
	    String login = txtLogin.getText();
	    String password = txtpwd.getText();
	    boolean connected = false;

	    // Check if entered credentials match any existing account
	    for (Compte u : users) {
	      if (u.getLogin().equals(login) && u.getPwd().equals(password)) {
	        connected = true;
	        break;
	      }
	    }

	    if (connected) {
	      JOptionPane.showMessageDialog(this, "Connexion réussie!");
	    } else {
	    	JOptionPane.showMessageDialog(this, "Login ou mot de passe incorrect!");
	    }
	  } else if (bt.equals(btnInscrire)) {
	    String login = txtLogin.getText();
	    String password = txtpwd.getText();

	    boolean loginExists = false;
	    for (Compte u : users) {
	      if (u.getLogin().equals(login)) {
	        loginExists = true;
	        break;
	      }
	    }

	    if (loginExists) {
	      JOptionPane.showMessageDialog(this, "Login déjà utilisé!");
	    } else {
	      Compte newUser = new Compte(login, password);
	      users.add(newUser);

	      try {
	        FileOutputStream fos = new FileOutputStream(f);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(users);
	        oos.close();
	        JOptionPane.showMessageDialog(this, "Inscription réussie!");
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	}

	
	public static void main(String []argv)
	{
		new ConnectionForm();
	}
	
}
