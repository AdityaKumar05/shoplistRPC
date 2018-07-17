package com.sample.shoplist.ShopList.server;

import java.util.ArrayList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sample.shoplist.ShopList.client.RandomData;

public class RandomDataImpl extends RemoteServiceServlet implements RandomData {
private static final long serialVersionUID = 1L;
public ArrayList<String> getRandomData() {

    //Start
    String ws= "randomItem";
    ArrayList<String> arr= new ArrayList<String>();
    for (int j=1;j<=50;j++){
    	arr.add(ws+j);
    	}
    return arr;
//End
}
}