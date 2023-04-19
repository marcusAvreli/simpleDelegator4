package simpleDelegator4.application;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator4.api.view.ViewContainer;
import simpleDelegator4.api.view.ViewContainerFrame;
import simpleDelegator4.api.view.ViewException;
import simpleDelegator4.api.view.ViewManager;
import simpleDelegator4.api.view.ViewManagerException;
import simpleDelegator4.api.view.perspective.PerspectiveConstraint;
import simpleDelegator4.common.CustomApplicationView;
import simpleDelegator4.core.annotation.processor.ViewsProcessor;
import simpleDelegator4.core.annotation.processor.ViewsProcessorWrapper;
import simpleDelegator4.core.view.DefaultViewContainer;
import simpleDelegator4.core.view.DefaultViewManager;
import simpleDelegator4.core.view.perspective.DefaultPerspective;

///https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/core/AbstractApplication.java
public abstract class AbstractApplication implements Application{
	private static final Logger logger = LoggerFactory.getLogger(AbstractApplication.class);
	private ViewContainerFrame			rootView;
	//private AbstractViewManager 				viewManager;
	private ViewManager 				viewManager;
	private List<ViewsProcessorWrapper> initViews;
	//Application
	@Override
	public void close() {
		logger.info("close called");
		this.fireClose();
	}
	
	public void fireClose() {
		ViewContainerFrame viewContainerFrame = this.getViewManager().getRootView();
		viewContainerFrame.getFrame().dispose();
	}
	//Application
	@Override
	public void setVisible(boolean visible) {
		
		JFrame frame = this.getRootView().getFrame();
		if (frame != null){
			if (visible){
				frame.setVisible(visible);
				frame.repaint();
			} else {
				frame.setVisible(visible);
			}
		}
	}
	
	//Application
	@Override
	/**
	 * @param rootView
	 */
	public void setRootView(ViewContainerFrame rootView) {
		logger.info("Setting Root View");
		
			if (this.viewManager!=null){
				this.viewManager.setRootView(rootView);
				this.rootView = rootView;
			}
		
	}
	
	public ViewContainerFrame  getRootView() {	
		logger.info("get_root_view");
		return rootView;
	}
	public AbstractApplication(){
	//	this.viewManager 			= new DefaultViewManager(this);
		this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
	}
	public AbstractApplication(ViewContainerFrame mainView){
		this();
		//this.setName(name);
		this.setRootView(mainView);
	}
	/* (non-Javadoc)
	 * @see org.viewa.core.Application#getViewManager()
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}
	public void prepare() {
		logger.info("prepare_started");
		if(null == initViews) {
			//ViewsProcessorWrapper wrapper = new ViewsProcessorWrapper(rootView,PerspectiveConstraint.LEFT_BOTTOM, true, false); 
		
			initViews = new ArrayList<ViewsProcessorWrapper>();
			ViewsProcessor processor = new ViewsProcessor(this,new CustomApplicationView());
			try {
				processor.process();
			
			initViews = List.class.cast(processor.getResult());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//initViews.add(wrapper);
		}
		logger.info("prepare_finished");
	}
	public void prepareUI(){
		logger.info("Application preparing UI!");
		if (initViews!=null){
			for (ViewsProcessorWrapper w : initViews){
				if (w.isRootView()){
					this.setRootView(ViewContainerFrame.class.cast(w.getView()));
				} else {
					logger.info("adding_just_view");
					try {
						this.getViewManager().addView(w.getView(),w.getConstraint());
					} catch (ViewException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					
					
				
			}
		}
	}
	public void initUI() {
		
			logger.info("Application UI initializing!");
			ViewManager viewManager = this.getViewManager();
			logger.info("after_view_manager");
			Component view;
			try {
				view = viewManager.arrangeViews();
			
			logger.info("after_arrange_views");
			if(null != view) {
				logger.info("view_is_not_null");
			}else {
				logger.info("view_is_null");
			}
			
			view.setVisible(true);
			} catch (ViewManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
