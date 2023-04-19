package simpleDelegator4.application;

import simpleDelegator4.api.view.ViewContainerFrame;
import simpleDelegator4.api.view.ViewManager;

public interface Application {
public void close();
public void setVisible(boolean visible);
public abstract void setRootView(ViewContainerFrame rootView);
public ViewManager getViewManager();
public void prepare();
public void prepareUI() ;
public void initUI() ;
}
