package org.vaadin.diagrambuilder;

public class Translations {

	// addNode: 'Objekt hinzuf&uuml;gen',
	// cancel: 'Abbrechen',
	// close: 'Schliessen',
	// deleteConnectorsMessage: 'Die Verbindung(en) wirklich l&ouml;schen?',
	// deleteNodesMessage: 'Die Objekte wirklich l&ouml;schen?',
	// propertyName: 'Eigenschaft',
	// save: 'Speichern',
	// settings: 'Einstellungen',
	// value: 'Wert'

	private String addNode;
	private String cancel;
	private String close;
	private String deleteConnectorsMessage;
	private String deleteNodesMessage;
	private String propertyName;
	private String save;
	private String settings;
	private String value;

	public static Translations getDefault() {
		Translations t = new Translations();
		t.setAddNode("Objekte");
		t.setCancel("Abbrechen");
		t.setClose("Schliessen");
		t.setDeleteConnectorsMessage("Ausgewählte Verbindungen wirklich löschen?");
		t.setDeleteNodesMessage("Ausgewählte Knoten wirklich löschen?");
		t.setPropertyName("Eigenschaft");
		t.setSave("Speichern");
		t.setSettings("Eigenschaften");
		t.setValue("Wert");
		return t;
	}

	public String getAddNode() {
		return addNode;
	}

	public void setAddNode(String addNode) {
		this.addNode = addNode;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getDeleteConnectorsMessage() {
		return deleteConnectorsMessage;
	}

	public void setDeleteConnectorsMessage(String deleteConnectorsMessage) {
		this.deleteConnectorsMessage = deleteConnectorsMessage;
	}

	public String getDeleteNodesMessage() {
		return deleteNodesMessage;
	}

	public void setDeleteNodesMessage(String deleteNodesMessage) {
		this.deleteNodesMessage = deleteNodesMessage;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
