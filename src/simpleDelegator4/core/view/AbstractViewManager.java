package simpleDelegator4.core.view;

import java.awt.Container;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator4.api.view.ViewContainer;
import simpleDelegator4.api.view.ViewException;
import simpleDelegator4.api.view.ViewManager;
import simpleDelegator4.api.view.ViewManagerException;
import simpleDelegator4.api.view.perspective.PerspectiveConstraint;
import simpleDelegator4.application.Application;

/**
 * A default implementation of View Manager. It manages the views added to the application
 * and those which are removed from the application. It also is resposible of launching
 * the views lifecycle and add them to the current perspective. 
 * 
 * It's also responsible for keeping the visual part of the application stable.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/view/AbstractViewManager.java
public abstract class AbstractViewManager implements ViewManager
{
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractViewManager.class);
	private Application 				application;
	//private ViewContainer view;
	private Map<Object,ViewContainer> 	views;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	public void addView(ViewContainer view,PerspectiveConstraint constraint) throws ViewException 
	{	 
		logger.info("add_view_started");
		Map<Object,ViewContainer> 				viewContainerCollection = this.getViews();
		ViewContainer 							viewContainer 			= viewContainerCollection.get(view.getId());		
		Application								app						= this.getApplication();
		//ViewModelManager						modelManager			= app.getViewModelManager();
		//ViewControllerDispatcher				controllerDispatcher	= app.getControllerDispatcher();
		//Map<String,ViewModel>					model					= null;
		//Map<String,ViewModel>					viewModel				= null;
		//Map<String,List<ViewController<?,?>>>	controllers 			= null;
		String									viewId					= view.getId();
	 /* Views must have their ids and these ids must not be repeated */
		logger.info("viewId:"+viewId);
		logger.info("viewContainer:"+viewContainer);
		if (viewId!=null && viewContainer == null)
		{
		//	model 		= modelManager.getViewModelMap(viewId);
		//	viewModel	= view.getViewModelMap();
		//	controllers = controllerDispatcher.getViewControllers(view);
		 /* Then application instance is injected in the view */
			view.setApplication(app);
		 /* The view is added to the application holder */
			this.getViews().put(viewId,view);
		 /* The view lifecycle begins */
		//	if (viewModel!=null){
		//		model.putAll(viewModel);
		//	}
			//view.setViewModelMap(model);
		 /* This view can already have some controllers, if so the manager adds the dispatcher controllers*/
			//if (view.getViewControllerMap()!=null){
			//	view.getViewControllerMap().putAll(controllers);
		 /* Otherwise the dispatcher sets the controllers */
		//	} else {
		//		view.setViewControllerMap(controllers);
		//	}
			// TODO refactoring
			logger.info("before_ad_view:"+view.getId());
			if (!view.getId().equals("FRAME")){
			this.getPerspective().addView(view,constraint);
			}
			//updates listeners that view is started
			view.viewInit();
		 /* And finally the view is added to the current perspective */
			
		} else {
			throw new ViewException(viewId == null ? "view must have an id" : "View is already added");
		}
		logger.info("add_view_finished");
	}
	public void addView(ViewContainer view) throws ViewException {
		this.addView(view,null);
	}
	/**
	 * Default constructor. It creates a new List where the views are
	 * added.
	 */
	public AbstractViewManager(){
		this.views = new HashMap<Object,ViewContainer>();		
	}
	public Map<Object, ViewContainer> getViews() {
		return this.views;
	}
	

	public Container arrangeViews()  throws ViewManagerException
	{
		logger.info("arrange_views_started");
		Map<Object,ViewContainer> cviews 					= new HashMap<Object, ViewContainer>();
		Collection<ViewContainer> viewContainerCollection 	= this.getViews().values();

		if(null !=viewContainerCollection) {
			logger.info("viewContainerCollection_is_not_null: ");
		}else {
			logger.info("viewContainerCollection_is_null");
		}
		/* ViewManager and Perspectives can make different decisions about its views so
		  * it is mandatory to create different view collections. */
			for (ViewContainer view : viewContainerCollection){
				cviews.put(view.getId(), view);
			}
			
			this.getPerspective().setViews(cviews);
			return this.getPerspective().arrange();
	}
}
