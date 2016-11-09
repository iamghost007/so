$(document).ready(function(e) { 
	$('.modal-dialog').css({height:'90%', border: 0, padding: 0, 'max-height':'90%'}); 
	$('.modal-content').css({height:'90%', 'max-height':'90%'}); 
}); 

(function(BpmnViewer, $) {

  // create viewer
  var bpmnViewer = new BpmnViewer({
    container: '#canvas'
  });


  // import function
  function importXML(xml) {

    // import diagram
    bpmnViewer.importXML(xml, function(err) {

      if (err) {
        return console.error('could not import BPMN 2.0 diagram', err);
      }

      var canvas = bpmnViewer.get('canvas'),
          overlays = bpmnViewer.get('overlays');


      // zoom to fit full viewport
      canvas.zoom('fit-viewport');

      // attach an overlay to a node
      overlays.add('approveOrder', 'note', {
        position: {
          bottom: 0,
          right: 0
        },
        html: '<div class="diagram-note">审核人：飞飞</div>'
      });

      // add marker
      canvas.addMarker('approveOrder', 'needs-discussion');
    });
  }


  // load external diagram file via AJAX and import it
  //$.get('diagram.bpmn', importXML, 'text');
  $.get('/simple/product-orders.v1.bpmn', importXML, 'text');


})(window.BpmnJS, window.jQuery);

jQuery.extend({
	viewBpmn : function(module) {
		  //$.get('product-orders.v1.bpmn', importXML, 'text');
		  $("#bpmnModal").modal("show");
	}
});
