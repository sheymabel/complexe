package Ex2;

import java.io.Serializable;

public class Compte implements Serializable{
	 private String login;
	 private  String pwd;
	 public Compte(String nom, String pwd) {
		 this.login=nom; this.pwd = pwd;}
	 public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	 public boolean equals (Object obj)
	 {  Compte u = (Compte) obj;
		 return ((u.login.equals(login)) && (u.pwd.equals(pwd))) ;}
	 public String toString() {
	    return (login + " ; pwd = " + pwd); 
	 }
}
