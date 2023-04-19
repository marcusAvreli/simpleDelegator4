package simpleDelegator4.core.annotation.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator4.api.view.ViewContainer;
import simpleDelegator4.api.view.perspective.PerspectiveConstraint;
import simpleDelegator4.application.Application;
import simpleDelegator4.common.CustomApplicationView;
import simpleDelegator4.core.view.AbstractViewContainer;

/**
 * 
 * This processor takes the annotation information of the Application and generates the needed classes at runtime.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
public class ViewsProcessor {
	private static final Logger logger = LoggerFactory.getLogger(ViewsProcessor.class);
	private Application app;
	private ViewContainer view;
	private List<ViewsProcessorWrapper> wrappers;
	
	/**
	 * @param app
	 */
	public ViewsProcessor(Application app,ViewContainer view){
		this.app = app;
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.annotation.processor.AnnotationProcessor#process()
	 */
	public void process() throws Exception {
		logger.info("process_view_started");
				wrappers  = new ArrayList<ViewsProcessorWrapper>();
			 /* ------------------- VIEW INFO ----------------- */
				Class<? extends ViewContainer> viewType = CustomApplicationView.class;
				String viewId = "myViewId";
				String iocId ="myId";
				PerspectiveConstraint constraint = PerspectiveConstraint.LEFT_MIDDLE;
				boolean isRootView = false;
				boolean isTrayView = false;
			 /* ----------------- VIEW PROCESS ---------------- */
				ViewContainer viewContainer = null;
				
				if (viewContainer == null){
					viewContainer = viewType.newInstance();
					viewContainer.setId(!viewId.equalsIgnoreCase("") ? viewId : viewContainer.getId());
				}
				wrappers.add(new ViewsProcessorWrapper(viewContainer, constraint,isRootView,isTrayView));
				logger.info("process_view_finished");
		}
	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.annotation.processor.AnnotationProcessor#getResult()
	 */
	public Object getResult(){
		return this.wrappers;
	}
	
}
