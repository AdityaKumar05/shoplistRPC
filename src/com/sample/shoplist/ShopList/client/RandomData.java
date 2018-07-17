package com.sample.shoplist.ShopList.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("randomData")
public interface RandomData extends RemoteService {
  public ArrayList<String> getRandomData() throws IllegalArgumentException;
}