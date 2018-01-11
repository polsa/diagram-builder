
package org.vaadin.diagrambuilder.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *
 */
public class DiagramBuilderJsniWrapper extends JavaScriptObject {

	protected DiagramBuilderJsniWrapper() {
	};

	public static native void create(JavaScriptObject conf, int id, DiagramBuilderWidget widget)
	/*-{
	  $wnd.YUI().use(
	    'aui-diagram-builder',
	    function(Y) {
	        
	      Y.DiagramBuilder.prototype._onNodeEdit = function(event) {
		      var instance = this;
		    
			  // Only enable editing if the double clicked node is inside the node
		      // contentBox.
		      if (!event.target.ancestor('.diagram-node-content', true)) {
		          return;
		      }
		
		      var diagramNode = Y.Widget.getByNode(event.currentTarget);
		
		      if (diagramNode) {
		      	  instance.editNode(diagramNode);
		      	  var nodeInfo = {
		      	  	nodeId: diagramNode._state.data.id,
		      	  	nodeName: diagramNode._state.data.name,
		      	  	nodeType: diagramNode._state.data.type
		      	  };
		      	  widget.@org.vaadin.diagrambuilder.client.DiagramBuilderWidget::editNode(Ljava/lang/String;)(JSON.stringify(nodeInfo));
		      }
			  console.debug("Edit node: " + diagramNode);
		  };
	
	      conf.boundingBox =  '#diagram-builder-bb' + id;
	      conf.srcNode = '#diagram-builder-sn' + id;
	        
	      var trs = conf.transitions;
	
	      var diagramBuilder = new Y.DiagramBuilder(conf).render();
	      
	      diagramBuilder.connectAll(trs);
	      
	      widget.@org.vaadin.diagrambuilder.client.DiagramBuilderWidget::wrapper = diagramBuilder;
	      
	    }
	  );
	 }-*/;

	public final native JavaScriptObject toJSON() /*-{
													return this.toJSON();
													}-*/;

	public final native String toJSONStr() /*-{
											return JSON.stringify(this.toJSON());
											}-*/;

}
