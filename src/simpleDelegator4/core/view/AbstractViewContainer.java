package simpleDelegator4.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;

import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator4.api.view.ViewContainer;
import simpleDelegator4.api.view.ViewException;
import simpleDelegator4.application.Application;
//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/view/AbstractViewContainer.java
public abstract class AbstractViewContainer implements ViewContainer
{

	private static final Logger logger = LoggerFactory.getLogger(AbstractViewContainer.class);
	//private List<ViewActionDescriptor> 				actionDescriptors;
	private Application 							application;
	//private List<Delegator>							delegators;
	private Image									iconImage;
	private String 									id;
	private JToolBar								jToolBar;
	
	//private ResourceBundle							messageBundle;
	//private Map<String,List<Component>> 			namedComponents;
	private JRootPane 								rootPane;
	private String									title;
	//private List<ViewContainerEventController> viewContainerEventControllers;
	//private Map<String,List<ViewController<?,?>>> 	viewControllerMap;
	//private Map<String,ViewModel>					viewModelMap;
	//private Map<String,Object>	
	public Container getContentPane() {
		return this.getRootPane().getContentPane(); 
	}
	
								
	
	/* (non-Javadoc)
	 * @see javax.swing.RootPaneContainer#getRootPane()
	 */
	public JRootPane getRootPane() {
		if (this.rootPane == null){
			logger.info("root_pane_is_null");
			this.rootPane = new JRootPane();
			this.rootPane.setName("ROOTPANE");
		}else {
			logger.info("root_pane_is_not_null");
		}
		return this.rootPane;
	}
	public Application getApplication() {
		logger.info("get_application_called");
		return application;
	}
	public void setApplication(Application application) {
		logger.info("set_application_called");
		this.application = application;
	}
	public String getId() {
		logger.info("get_id:"+id);
		return id;
	}
	public void setId(String id) {
		logger.info("set_id:"+id);
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setRootPane(JRootPane rootPane) {
		this.rootPane = rootPane;
	}
	public Component getGlassPane() {
		return this.getRootPane().getGlassPane();
	}
	public void setGlassPane(Component glassPane) {
		this.getRootPane().setGlassPane(glassPane);
	}
	/* (non-Javadoc)
	 * @see javax.swing.RootPaneContainer#setLayeredPane(javax.swing.JLayeredPane)
	 */
	public void setLayeredPane(JLayeredPane layeredPane) {
		this.getRootPane().setLayeredPane(layeredPane);
	}
	/* (non-Javadoc)
	 * @see javax.swing.RootPaneContainer#getLayeredPane()
	 */
	public JLayeredPane getLayeredPane() {
		return this.getRootPane().getLayeredPane();
	}
	/* (non-Javadoc)
	 * @see javax.swing.RootPaneContainer#setContentPane(java.awt.Container)
	 */
	public void setContentPane(Container contentPane) {
		this.getRootPane().setContentPane(contentPane);
	}
	/* (non-Javadoc)
	 * @see javax.swing.RootPaneContainer#getContentPane()
	 */
	public AbstractViewContainer(String id){
		this();
		this.setId(id);
	}
	public AbstractViewContainer(){
		super();
		this.getContentPane().setLayout(new BorderLayout());
		//this.viewContainerEventControllers = new ArrayList<ViewContainerEventController>();
	}
	
	/* (non-Javadoc)
	 * @see org.viewa.view.View#viewInit()
	 */
	public void viewInit() throws ViewException {
		
			logger.info("Initializing view "+this.getClass().getName());
		
		//TODO refactor
		if (this.getContentPane()!=null) this.getContentPane().setName("contentPane");
		
		final ViewContainer thisContainer = this; 
		
		 {
		
		}
		
	}
	
}
