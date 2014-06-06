package ru.ufa.zkbirt;


import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Window;

public class MainController extends SelectorComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire("#treeReports") private Tree treeReports;
	@Wire("#mainTabs")	private Tabbox mainTabs;
	@Wire("#mainTabs > tabs") 	private Tabs tabs; 
	@Wire("#mainTabs > tabpanels") 	private Tabpanels tabpanels;
	
	private void setTabValue(String url, String nameReport) {
		Tab tab = new Tab(nameReport);
		tab.setClosable(true);
		tab.setSelected(true);
		tabs.appendChild(tab);
		
		Tabpanel tabpanel = new Tabpanel();
		Include include = new Include(url);
		tabpanel.appendChild(include);
		tabpanels.appendChild(tabpanel);
	}
	
	@Listen("onClick = #viewReport")
	public void showReport() {
		if (treeReports.getSelectedItem() != null) {
			String url = treeReports.getSelectedItem().getValue();
			String label = treeReports.getSelectedItem().getLabel();
			setTabValue(url, label);
		} else {
			showNotify("Select the report");
			return;
		}
	}
	
	private void showNotify(String msg) {
		 Clients.showNotification(msg,"warning",null,null,0);
    }
}
