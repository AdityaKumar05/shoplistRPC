package com.sample.shoplist.ShopList.client;

import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ShopList implements EntryPoint {
	int maxPage=5;
    TextBox listItemTB= new TextBox();
    Button showAll= new Button("Show All");
    Button random=new Button("Randomly Populate");
    Button showSelected= new Button("Show Selected");
    Button srchBtn=new Button("Search");
    Button deleteAll=new Button("Delete");
    FlowPanel divList=new FlowPanel();
    FlexTable listH=new FlexTable();
    FlexTable listB=new FlexTable();
    Label empty= new Label();
    DialogBox dgSearch=new DialogBox();
    DialogBox dgSelect=new DialogBox();
    FlexTable listSelect=new FlexTable();
    FlexTable listSearch=new FlexTable();
    Button closeSelect =new Button("Close");
    Button closeSearch = new Button("Close");
    FlowPanel select=new FlowPanel();
    FlowPanel search = new FlowPanel();
    HorizontalPanel panel= new HorizontalPanel();
    HorizontalPanel setPager= new HorizontalPanel();
    ArrayList<String> name=new ArrayList<String>();
    ArrayList<String> done=new ArrayList<String>();
    Anchor prev=new Anchor();
    Anchor laast=new Anchor();
    Anchor first=new Anchor();
    Label curPage=new Label();
    Anchor a1=new Anchor();
    Anchor a2=new Anchor();
    Anchor a3=new Anchor();
    int index;
    Anchor next=new Anchor();
    ScrollPanel scrollPanel = new ScrollPanel();
    private RandomDataAsync randomData = (RandomDataAsync) GWT.create(RandomData.class); 

public void onModuleLoad() {
	a1.setText("1");
	a2.setText("2");
	a3.setText("3");
	dgSearch.setText("Search Results");
	search.add(listSearch);
	search.add(closeSearch);
	dgSearch.setWidget(search);
	dgSelect.setText("Showing Selected");
	select.add(listSelect);
	select.add(closeSelect);
	dgSelect.setWidget(select);
	first.setText("First");
	laast.setText("Last");
	setPager.add(first);
	setPager.add(prev);
	setPager.add(a1);
	setPager.add(a2);
	setPager.add(a3);
	setPager.add(next);
	setPager.add(laast);
	prev.setText("Prev");
	curPage.setText("1");					//
	next.setText("Next");				//
    scrollPanel.add(listB);				//
    scrollPanel.addStyleName("testDiv");
    listH.setText(0,0,"NAME/Value");
    listH.setText(0,1,"SELECTED");
    divList.add(listH);
    divList.add(scrollPanel);
    divList.add(empty);
    panel.add(curPage);
    RootPanel.get("test").add(random);
    RootPanel.get("test").add(listItemTB);
    RootPanel.get("test").add(srchBtn);
    RootPanel.get("test").add(divList);
    RootPanel.get("test").add(setPager);
    RootPanel.get("test").add(panel);
    RootPanel.get("test").add(showAll);
    RootPanel.get("test").add(showSelected);
    RootPanel.get("test").add(deleteAll);
    listItemTB.getElement().setPropertyString("placeholder","Press Enter to ADD");

    listItemTB.addKeyUpHandler(new KeyUpHandler(){
        public void onKeyUp(KeyUpEvent evt){
        	 if(evt.getNativeKeyCode()==KeyCodes.KEY_ENTER )
             {addList();    
             listItemTB.setText("");}
        	 else{
           	    updateList(Integer.parseInt(curPage.getText()));
             }
      	  }});
      	  
closeSearch.addClickHandler(new ClickHandler(){
    public void onClick(ClickEvent evt){
    dgSearch.hide();
    }
 });

closeSelect.addClickHandler(new ClickHandler(){
    public void onClick(ClickEvent evt){
     dgSelect.hide();
    }
 });

deleteAll.addClickHandler(new ClickHandler(){
    public void onClick(ClickEvent evt){
      listB.removeAllRows();
      curPage.setText(""+1);
      name.clear();
      done.clear();
    }
 });

prev.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		changePrevious();
		int x=Integer.parseInt(curPage.getText());
		if(x-1>0)
		{
			a1.setText(""+(x-1));
			a2.setText(""+(x));
			a3.setText(""+(x+1));
		}
	}
});

next.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		changeNext();
		int x=Integer.parseInt(curPage.getText());
		if(x+1<=maxPage && x!=1)
		{
			a1.setText(""+(x-1));
			a2.setText(""+(x));
			a3.setText(""+(x+1));
		}
	}
});

a1.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		if(name.size()!=0){
			int x=Integer.parseInt(a1.getText());
			if(!(x-1<=0)){
				gotoPage(x);
				curPage.setText(""+x);
				a1.setText(""+(x-1));
				a2.setText(""+(x));
				a3.setText(""+(x+1));
			}else{
				gotoPage(x);
			}
		}
		else{
			empty.setText("Nothing to See here!");
		}
	}
});

a2.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		int x=Integer.parseInt(a2.getText());
		if(name.size()!=0){
		gotoPage(x);
		curPage.setText(""+x);}	}
});

a3.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		if(name.size()!=0){
		int x=Integer.parseInt(a3.getText());
		
		if(!(x+1>maxPage)){
			gotoPage(x);
			curPage.setText(""+x);
			a1.setText(""+(x-1));
			a2.setText(""+(x)); 
			a3.setText(""+(x+1));
		}else{
			gotoPage(x);
		}
		}
		else{
			empty.setText("Nothing to See here!");
		}
	}
});

first.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		gotoPage(1);
		curPage.setText(""+1);	}
});

laast.addClickHandler(new ClickHandler(){
	public void onClick(ClickEvent evt){
		if(name.size()!=0){
		gotoPage(maxPage);
		curPage.setText(""+maxPage);}	}
});

random.addClickHandler(new ClickHandler(){
    public void onClick(ClickEvent evt){
        getDataForList();
     }
});

showAll.addClickHandler(new ClickHandler(){
 public void onClick(ClickEvent evt){
      updateList(1);
      a1.setText("1");
  	  a2.setText("2");
  	  a3.setText("3");
        
    }
});

showSelected.addClickHandler(new ClickHandler(){
  public void onClick(ClickEvent evt){
	  curPage.setText("1");
	  a1.setText("1");
	  a2.setText("2");
	  a3.setText("3");
	  if(name.size()==0)
		  empty.setText("Nothing To See Here!");
	  else
		  empty.setText("");
    listSelect.removeAllRows();
    int count=0;
    for(int i=0;i<name.size();i++)
    {
      CheckBox cb=new CheckBox();
      if(done.get(i).equalsIgnoreCase("true"))
      {
        cb.setValue(true);
        listSelect.setWidget(count,1,cb);
        listSelect.setText(count++,0,name.get(i));
      }
    }dgSelect.center();
    dgSelect.show();
  }
});

srchBtn.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent evt){
    	  ArrayList<String> Sdone=new ArrayList<String>();
    	    ArrayList<String> searchResult=new ArrayList<String>();
          if(listItemTB.getText()!=""){
        	  listSearch.removeAllRows();
		  String str=listItemTB.getText();
    	  if(name.size()!=0){
    		  for(int i=0;i<name.size();i++){
        		  if(name.get(i).contains(str)){
        			  searchResult.add(name.get(i));
        			  Sdone.add(done.get(i));
        		  }
        		  updateSearch(searchResult,Sdone);
        		  }
    	  }
    	  
       
     }}}
);

listB.addClickHandler(new ClickHandler() { 
    @Override 
    public void onClick(ClickEvent event) {  
         int rowIndex = listB.getCellForEvent(event).getRowIndex();
         setDone(rowIndex,Integer.parseInt(curPage.getText()));
    } 
   }); 

listSelect.addClickHandler(new ClickHandler() { 
    @Override 
    public void onClick(ClickEvent event) {  
         int rowIndex = listSelect.getCellForEvent(event).getRowIndex();
         setDoneSelect(rowIndex);
    } 
   }); 

listSearch.addClickHandler(new ClickHandler() { 
    @Override 
    public void onClick(ClickEvent event) {  
         int rowIndex = listSearch.getCellForEvent(event).getRowIndex();
         setDoneSearch(rowIndex);
    } 
   }); 

}
void setDoneSelect(int r){
	String str=listSelect.getText(r,0);
	int index=0;
	for(int i=0;i<name.size();i++){
		if(name.get(i).equalsIgnoreCase(str)){
			index=i;
			break;
		}
	}
	  if(done.get(index).equalsIgnoreCase("false"))
	      done.set(index,"true");
	    else
	      done.set(index, "false");
	    updateList(Integer.parseInt(curPage.getText()));
}
void setDoneSearch(int r){
	String str=listSearch.getText(r,0);
	int index=0;
	for(int i=0;i<name.size();i++){
		if(name.get(i).equalsIgnoreCase(str)){
			index=i;
			break;
		}
	}
	  if(done.get(index).equalsIgnoreCase("false"))
	      done.set(index,"true");
	    else
	      done.set(index, "false");
	    updateList(Integer.parseInt(curPage.getText()));
}

  void changeNext(){
	  gotoPage(Integer.parseInt(curPage.getText())+1);
	}
  
  
  void changePrevious(){
	  gotoPage(Integer.parseInt(curPage.getText())-1);
	}
  
  void gotoPage(int c){
		
		if(c <= maxPage && c > 0){
			//Paging Code
			updateList(c);
		}
	}
  
  void updateSearch(ArrayList<String> sr,ArrayList<String> sd){
	  if(sr.size()==0)
		  empty.setText("Nothing to see here!");
	  else{
		  empty.setText("");
	  listSearch.removeAllRows();
	  for(int i=0;i<sr.size();i++)
	    {
	      CheckBox cb=new CheckBox();
	      boolean b=sd.get(i).equalsIgnoreCase("true")?true:false;
	      cb.setValue(b);
	      listSearch.setWidget(i,1,cb);
	      listSearch.setText(i,0,sr.get(i));
	    } 	
	  dgSearch.center();
	  dgSearch.show();
	  }
  }
  
  void getDataForList(){
	  empty.setText("");
    randomData.getRandomData(new AsyncCallback<ArrayList<String>>() {
      public void onFailure(Throwable caught) {
        Window.alert("RPC to getRandomData() failed.");
      }
      public void onSuccess(ArrayList<String> result) {
        
        	name.clear();
        	done.clear();
            for(int i=0;i<result.size();i++)
              {
                  name.add(result.get(i));
                  done.add("false");
              } 
       for(int i=0;i<10;i++){
    	   CheckBox cb=new CheckBox();
           listB.setWidget(i,1,cb);
         listB.setText(i,0,name.get(i));

       }
      }
    });
  }
  
  public void setDone(int i,int currentPage){
    i=((currentPage-1)*10)+i;
	  if(done.get(i).equalsIgnoreCase("false"))
      done.set(i,"true");
    else
      done.set(i, "false");
    updateList(Integer.parseInt(curPage.getText()));
  }
  
  public void addList()
  {
	  if(maxPage*10<name.size()+1)
		  maxPage++;
	  listB.removeAllRows();
    if(name.contains(listItemTB.getText()))
      return;
    if(listItemTB.getText()!=""){
      name.add(listItemTB.getText());
      done.add("false");
    }
    listItemTB.setText("");
    //ADDING ELEMENTS TO FLEXTABLE
    for(int i=0;i<name.size();i++)
    {
      CheckBox cb=new CheckBox();
      listB.setWidget(i,1,cb);
      listB.setText(i,0,name.get(i));
    }
    updateList(Integer.parseInt(curPage.getText()));
  }

  public void updateList(int page){
	  int count=0;	  
if(name.size()==0)
		  empty.setText("Nothing to see here!");
	  else{
		  curPage.setText(""+page);
		  empty.setText("");
	  listB.removeAllRows();
    for(int i=((page-1)*10);i<name.size() && i<page*10;i++)
    {
      CheckBox cb=new CheckBox();
      boolean b=done.get(i).equalsIgnoreCase("true")?true:false;
      cb.setValue(b);
      listB.setWidget(count,1,cb);
      listB.setText(count++,0,name.get(i));
    }
  } 
  }
}
