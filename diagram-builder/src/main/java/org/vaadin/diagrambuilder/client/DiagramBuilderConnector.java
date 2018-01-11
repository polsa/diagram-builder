package org.vaadin.diagrambuilder.client;

import org.vaadin.diagrambuilder.DiagramBuilder;
import org.vaadin.diagrambuilder.client.DiagramBuilderWidget.ClientLogListener;
import org.vaadin.diagrambuilder.client.DiagramBuilderWidget.ClientNodeEditListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(DiagramBuilder.class)
public class DiagramBuilderConnector extends AbstractComponentConnector
		implements ClientLogListener, ClientNodeEditListener {

	DiagramBuilderServerRpc rpc = RpcProxy.create(DiagramBuilderServerRpc.class, this);

	public DiagramBuilderConnector() {
		getWidget().setLogListener(this);
		getWidget().addNodeEditListener(this);

		registerRpc(DiagramBuilderClientRpc.class, new DiagramBuilderClientRpc() {

			@Override
			public void get() {
				rpc.reportState(getWidget().getState());
			}
		});

	}

	// We must implement createWidget() to create correct type of widget
	@Override
	protected Widget createWidget() {
		return GWT.create(DiagramBuilderWidget.class);
	}

	// We must implement getWidget() to cast to correct type
	@Override
	public DiagramBuilderWidget getWidget() {
		return (DiagramBuilderWidget) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public DiagramBuilderState getState() {
		return (DiagramBuilderState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		JavaScriptObject conf = JsonUtils.safeEval(getState().diagramJson);
		getWidget().setConf(conf);
	}

	@Override
	public void logReceived(String log) {
		rpc.printLog(log);
	}

	@Override
	public void editNode(String node) {
		rpc.editNode(node);
	}

}
