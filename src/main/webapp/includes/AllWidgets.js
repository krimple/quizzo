dojo.provide("includes.AllWidgets");

require(["dijit/dijit"],
    ["dojo/parser"],
    ["dijit/layout/ContentPane"],
    ["dijit/form/Form"],
    ["dijit/form/TextBox"],
    ["dijit/form/Textarea"]),
    ["dijit/form/Button"],
    ["dijit/Dialog"],
    ["dijit/form/SimpleTextarea"],
    ["dijit/Editor"],
        function(dijit_dijit, dojo_parser, content_pane, form_form,
                 text_box, text_area, button, dialog, simple_text_editor, rich_editor){
            alert("hi mommy");
        }
);

require(["dojo"], function(dojo){
    Spring.initialize;
});

