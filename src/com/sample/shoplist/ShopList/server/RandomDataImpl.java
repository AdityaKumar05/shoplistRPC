package com.sample.shoplist.ShopList.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sample.shoplist.ShopList.client.RandomData;

public class RandomDataImpl extends RemoteServiceServlet implements RandomData {

private static final long serialVersionUID = 1L;

public ArrayList<String> getRandomData() {

    //Start
    String alphabet= "abc";
    ArrayList<String> arr= new ArrayList<String>();
    for (int j=0;j<50;j++){
    	arr.add(alphabet+j);
    	}
    return arr;
//End
}
}