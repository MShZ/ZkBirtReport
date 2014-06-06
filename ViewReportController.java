package ru.ufa.zkbirt;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ViewReportController extends SelectorComposer<Window>{

	/**
	 * @author Shamil' Mustafin
	 */
	private static final long serialVersionUID = 1L;
	@Wire("#year") private Textbox year;
	
	
	@Listen("onClick = #showReport")
	public void showReport() {
		String yearValue = year.getText().trim();
		if (yearValue.equals("")) {
			showNotify("Requires input values");
			return;
		} else {
		   String json = getJSONForReport(yearValue);
		 //The transfer of control in the JS code
		   Clients.evalJavaScript("runReportI(" + json + ")");
		}  
	}
	
	private void showNotify(String msg) {
		 Clients.showNotification(msg,"warning",null,null,0);
    }

	//Generated a JSON string to send in the JS code
	private String getJSONForReport(String yearValue) {
		  String json = "";
		 
		      json = "{\"elements\":[";
		      
		      json = json + "{" +
		                        "\"" + "year"  + "\"" + ":" + "\"" + yearValue + "\"" +
		                        "},";
		      
		      json = json + "]}";
		    return json;
	 }
	
}
