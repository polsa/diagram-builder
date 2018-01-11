package org.vaadin.diagrambuilder;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Node in the diagram.
 * 
 * Names in diagram should be unique as they work as identifiers as well.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Node implements Serializable {

	private String id;
	private int[] xy = new int[] { 0, 0 };
	private String name;
	private String type;
	private String description;
	private List<Transition> transitions;

	public Node() {
	}

	public Node(String name, NodeType type, int x, int y) {
		this.name = name;
		this.type = type.getType();
		xy = new int[] { x, y };
	}

	public Node(String name, String typeId, int x, int y) {
		this.name = name;
		this.type = typeId;
		xy = new int[] { x, y };
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of name
	 *
	 * @param name
	 *            new value of name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of type
	 *
	 * @return the value of type
	 */
	public String getType() {
		return type;
	}

	public void setType(String nodeTypeId) {
		this.type = nodeTypeId;
	}

	/**
	 * Set the value of type
	 *
	 * @param type
	 *            new value of type
	 */
	public void setNodeType(NodeType type) {
		this.type = type.getType();
	}

	/**
	 * Get the value of xy
	 *
	 * @return the value of xy
	 */
	public int[] getXy() {
		return xy;
	}

	/**
	 * Set the value of xy
	 *
	 * @param xy
	 *            new value of xy
	 */
	public void setXy(int[] xy) {
		this.xy = xy;
	}

	public void setX(int x) {
		xy[0] = x;
	}

	public void setY(int y) {
		xy[1] = y;
	}

	public int getX() {
		return xy[0];
	}

	public int getY() {
		return xy[1];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
