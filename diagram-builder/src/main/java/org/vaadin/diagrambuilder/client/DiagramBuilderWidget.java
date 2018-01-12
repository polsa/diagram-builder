package org.vaadin.diagrambuilder.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class DiagramBuilderWidget extends Widget {

	static int count;
	private final int id;

	private ClientLogListener logListener;
	private List<ClientNodeEditListener> nodeEditListeners;

	// gets assigned lazily, byt the JSNIWrapper as the diagrambuilder js
	// component may be instantiated asynchronously.
	DiagramBuilderJsniWrapper wrapper;

	public DiagramBuilderWidget() {
		id = count++;

		nodeEditListeners = new ArrayList<>();

		// CSS class-name should not be v- prefixed
		setElement(Document.get().createDivElement());
		setStyleName("diagram-builder");

		getElement().setInnerHTML("<div id=\"diagram-builder-bb" + id + "\" style='height:100%'>"
				+ "<div id=\"diagram-builder-sn" + id + "\" style='height:100%'></div>" + "</div>");

	}

	void printLog(String log) {
		this.logListener.logReceived(log);
	}

	void editNode(String node) {
		for (ClientNodeEditListener c : nodeEditListeners) {
			c.editNode(node);
		}
	}

	void clearFields() {
		wrapper.clearFields();
	}

	void setConf(JavaScriptObject conf) {
		DiagramBuilderJsniWrapper.create(conf, id, this);
	}

	String getState() {
		return wrapper.toJSONStr();
	}

	public void setLogListener(ClientLogListener listener) {
		this.logListener = listener;
	}

	public void addNodeEditListener(ClientNodeEditListener l) {
		nodeEditListeners.add(l);
	}

	public void removeNodeEditListener(ClientNodeEditListener l) {
		nodeEditListeners.remove(l);
	}

	public interface ClientLogListener {
		public void logReceived(String log);
	}

	public interface ClientNodeEditListener {
		public void editNode(String node);
	}
}
