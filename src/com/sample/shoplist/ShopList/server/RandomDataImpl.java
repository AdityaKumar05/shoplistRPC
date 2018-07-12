package com.sample.shoplist.ShopList.server;

import java.util.ArrayList;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sample.shoplist.ShopList.client.RandomData;


public class RandomDataImpl extends RemoteServiceServlet implements
    RandomData {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ArrayList<String> getRandomData() {

    //Start
    String alphabet= "abcdefghijklmnopqrstuvwxyz";
    ArrayList<String> arr= new ArrayList<String>();
    String s = "";
    for (int j=0;j<50;j++){
      Random random = new Random();
      int randomLen = 1+random.nextInt(5);
      for (int i = 0; i < randomLen; i++) {
          char c = alphabet.charAt(random.nextInt(26));
          s+=c;
      }
      arr.add(s);
      s="";
    }
    return arr;
//End
}
}