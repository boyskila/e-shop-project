package com.team.eshop.athentification;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.team.eshop.files.*;
import com.team.eshop.products.Product;
//import com.team.eshop.shoptest.TheShop;
import com.team.eshop.shoptest.TheShop;

public class User implements Serializable {

	private String fName;
	private String sName;
	private String username;
	private String password;

	// private List<User> admin = new ArrayList<User>();
	private List<Client> clients = new ArrayList<Client>();
	private List<Client> userList = new ArrayList<Client>();
	private static List<User> adminList = new ArrayList<User>();
	Iterator<User> iterator;

	public User() {

	}

	public User(String fName, String sName, String username, String password) {
		this.fName = fName;
		this.sName = sName;
		this.username = username;
		this.password = password;
	}

	// Scanner scan = new Scanner(System.in);

	// public void signInOrLogin() throws IOException, ClassNotFoundException {
	// Scanner scanner = new Scanner(System.in);
	//
	// System.out.println("---- Type 1 to create Client account ----\n---- Type 2 to Login ----");
	// int in = scanner.nextInt();
	// switch (in) {
	// case 1:
	//
	// File file = new File("userList.srz");
	// if (file.exists()) {
	//
	// setUserList(files.deserialize("userList.srz"));
	// }
	//
	// System.out.println("First name: ");
	// String firstName = scanner.next();
	// System.out.println("Second name: ");
	// String secondName = scanner.next();
	// System.out.println("Username: ");
	// String user = scanner.next();
	// System.out.println("Password: ");
	// String pass = scanner.next();
	//
	// Client newClient = new Client(firstName, secondName, user, pass);
	//
	// clients.add(newClient);
	// iterator = clients.iterator();
	//
	// while (iterator.hasNext()) {
	//
	// Client client = (Client) iterator.next();
	// getUserList().add(client);
	// files.saveFile(getUserList(), "userList.srz");
	// }
	//
	// case 2:
	// File f = new File("userList.srz");
	// if (f.exists()) {
	//
	// login(getUserList(), "userList.srz");
	//
	// } else {
	// System.out.println("Create account first");
	// TheShop.main(null);
	// }
	//
	// break;
	// default:
	// System.out.println("Create account or Login ");
	// TheShop.main(null);
	// break;
	// }
	// }

	@Override
	public String toString() {
		return "Username = " + username + "\nPassword = " + password
				+ "\nFirst name=" + fName + "\nSecond name = " + sName;
	}

	

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Client> getUpdatedList() {
		return getUserList();
	}

	public void setUpdatedList(List<Client> updatedList) {
		this.setUserList(updatedList);
	}

	public List<Client> getUserList() {
		return userList;
	}

	public void setUserList(List<Client> userList) {
		this.userList = userList;
	}

}
