package com.sample.shoplist.ShopList.client;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RandomDataAsync {
  public void getRandomData(AsyncCallback<ArrayList<String>> callback) throws IllegalArgumentException;
}