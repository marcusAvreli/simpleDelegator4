package simpleDelegator4.common;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import simpleDelegator4.core.view.DefaultViewContainer;


public class CustomApplicationView  extends DefaultViewContainer{

	private JLabel lblCa;
	public static final String ID = "ControllerTestViewId";	

	public CustomApplicationView() {
		super(ID,new TestPanel());
		
	}
	

}
