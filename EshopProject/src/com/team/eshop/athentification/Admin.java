package com.team.eshop.athentification;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.team.eshop.files.Files;

public class Admin extends User implements Serializable {



	public Admin() {

	}

	public Admin(String fName, String sName, String user, String pass) {
		super(fName, sName, user, pass);

	}

//	public static void checkUsersData() throws ClassNotFoundException,
//			IOException {
//		
//		List<Client> c = new ArrayList<Client>();
//		c = Files.deserialize("userList.srz");
//		for (Client client : c) {
//			System.out.println(client.toString()
//					+ "\n============================\n");
//		}
//	}

	
}
