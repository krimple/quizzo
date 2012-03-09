require(["dojo/_base/lang"], function(lang) {

  lang.declare("Spring.DefaultEquals", null, {
    equals : function(/*Object*/other){
      if (other.declaredClass && other.declaredClass == this.declaredClass) {
        return true;
      }else{
        return false;
      }
    }
  });

 lang.declare("Spring.ValidateAllDecoration", [Spring.AbstractValidateAllDecoration, Spring.DefaultEquals], {
  constructor : function(config) {
    this.originalHandler = null;
    this.connection = null;
    lang.mixin(this, config);
  };

  apply : function() {
    var element = lang.byId(this.elementId);
    if (!element) {
      console.error("Could not apply ValidateAll decoration.  Element with id '" + this.elementId + "' not found in the DOM.");
    } else {
      this.originalHandler = element[this.event];
      var context = this;
      element[this.event] = function(event){
        context.handleEvent(event, context);
      };
    }
    return this;
  },

  cleanup : function(){
    lang.disconnect(this.connection);
  },

  handleEvent : function(event, context){
    if (!Spring.validateAll()) {
      lang.publish(this.elementId+"/validation", [false]);
      lang.stopEvent(event);
    } else {
      lang.publish(this.elementId+"/validation", [true]);
      if(lang.isFunction(context.originalHandler)) {
        var result = context.originalHandler(event);
        if (result == false) {
          lang.stopEvent(event);
        }
      }
    }
  }
});
