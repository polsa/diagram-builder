package org.vaadin.diagrambuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.diagrambuilder.client.DiagramBuilderClientRpc;
import org.vaadin.diagrambuilder.client.DiagramBuilderServerRpc;
import org.vaadin.diagrambuilder.client.DiagramBuilderState;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiagramBuilder extends com.vaadin.ui.AbstractComponent {

	public interface StateCallback {

		public void onStateReceived(DiagramStateEvent event);
	}

	private StateCallback cb;

	private DiagramBuilderServerRpc rpc = new DiagramBuilderServerRpc() {

		@Override
		public void reportState(String stateJson) {
			try {
				DiagramStateEvent value = mapper.readValue(stateJson, DiagramStateEvent.class);
				value.setRawJsonString(stateJson);
				cb.onStateReceived(value);
				cb = null;
			} catch (IOException ex) {
				Logger.getLogger(DiagramBuilder.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		@Override
		public void printLog(String log) {
			DiagramBuilder.this.printLog(log);
		}

		@Override
		public void editNode(String node) {
			DiagramBuilder.this.editNode(node);
		}
	};

	public DiagramBuilder() {
		nodeEditListeners = new ArrayList<>();
		registerRpc(rpc);
	}

	protected void editNode(String json) {
		// TODO hier lege ich eine Node an wenn Sie im Modell noch nicht existiert.
		// Sollte über den SharedState gehen
		Node node = null;
		try {
			JsonNode info = mapper.readTree(json);
			System.out.println(info);
			JsonNode idInfo = info.findValue("nodeId").findValue("value");
			JsonNode nameInfo = info.findValue("nodeName").findValue("value");
			JsonNode typeInfo = info.findValue("nodeType").findValue("value");
			node = findFieldByName(nameInfo.textValue());
			System.out.println(node);
			if (node != null) {
				node.setId(idInfo.asText());
				node.setType(typeInfo.asText());
			} else {
				node = new Node();
				node.setId(idInfo.asText());
				node.setName(nameInfo.textValue());
				node.setType(typeInfo.asText());
				// TODO in fields hinzufügen
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (ServerNodeEditListener l : nodeEditListeners) {
			l.editNode(node);
		}
	}

	protected void printLog(String log) {
		logListener.receivedLog(log);
	}

	@Override
	public DiagramBuilderState getState() {
		return (DiagramBuilderState) super.getState();
	}

	private NodeType[] availableFields;
	private Node[] fields;
	private Transition[] transitions;

	public Transition[] getTransitions() {
		return transitions;
	}

	public void setTransitions(Transition... transitions) {
		this.transitions = transitions;
		markAsDirty();
	}

	public Node[] getFields() {
		return fields;
	}

	public void setFields(Node... fields) {
		this.fields = fields;
		markAsDirty();
	}

	public Node findFieldByName(String name) {
		for (Node n : fields) {
			if (n.getName().equals(name)) {
				return n;
			}
		}
		return null;
	}

	public NodeType[] getAvailableFields() {
		return availableFields;
	}

	public void setAvailableFields(NodeType... availableFields) {
		this.availableFields = availableFields;
		markAsDirty();
	}

	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	private ServerLogListener logListener;

	public void setLogListener(ServerLogListener l) {
		logListener = l;
	}

	private List<ServerNodeEditListener> nodeEditListeners;

	public void addNodeEditListener(ServerNodeEditListener l) {
		nodeEditListeners.add(l);
	}

	public void removeNodeEditListener(ServerNodeEditListener l) {
		nodeEditListeners.remove(l);
	}

	@Override
	public void beforeClientResponse(boolean initial) {
		super.beforeClientResponse(initial); // To change body of generated methods, choose Tools | Templates.
		try {
			getState().diagramJson = mapper
					.writeValueAsString(new DiagramInitState(availableFields, fields, transitions));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(DiagramBuilder.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 *
	 * @param callback
	 *            the callback to be notified when client side has reported the
	 *            current state.
	 */
	public void getDiagramState(StateCallback callback) {
		cb = callback;
		getRpcProxy(DiagramBuilderClientRpc.class).get();
	}

	public interface ServerLogListener {
		public void receivedLog(String log);
	}

	public interface ServerNodeEditListener {
		public void editNode(Node node);
	}
}
