Ext.define('Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.SalesRegionMain', {
     "xtype": "salesRegion",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "SalesRegionMainController",
     "restURL": "/SalesRegion",
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.SalesRegionModel", "Salesdemo.salesdemo.smartdevice.com.sales.controller.salesboundedcontext.sales.SalesRegionMainController", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.SalesRegionViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "margin": 5,
     "tabBar": {
          "hidden": true
     },
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "treepanel",
               "customWidgetType": "vdTree",
               "title": "List",
               "useArrows": true,
               "rowLines": true,
               "columnLines": true,
               "rootVisible": false,
               "itemId": "SalesRegionTree",
               "listeners": {
                    "select": "treeClick"
               },
               "tbar": [{
                    "xtype": "triggerfield",
                    "customWidgetType": "vdTriggerField",
                    "width": "90%",
                    "height": "35",
                    "emptyText": "Search",
                    "triggerCls": "",
                    "listeners": {
                         "change": "onTriggerfieldChange",
                         "buffer": 250
                    }
               }, "->", {
                    "xtype": "tool",
                    "type": "refresh",
                    "tooltip": "Refresh Tree Data",
                    "handler": "onTreeRefreshClick"
               }],
               "region": "south",
               "height": "100%",
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false
          }, {
               "region": "center",
               "layout": "border",
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "viewModel": "SalesRegionViewModel",
                    "xtype": "form",
                    "displayName": "SalesRegion",
                    "title": "SalesRegion",
                    "name": "SalesRegion",
                    "itemId": "SalesRegion",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "regionname",
                         "itemId": "regionname",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Region",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Region<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "7E588860-5CA5-4EEA-B36C-7C3D8B6C807D",
                         "minLength": "0",
                         "maxLength": "64",
                         "bindable": "regionname",
                         "columnWidth": 1
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 342,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 342,
                              "customId": 731
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 342,
                              "customId": 732,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 342,
                              "customId": 733,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {
                              "margin": "0 5 0 5",
                              "flex": 1,
                              "height": 30
                         }
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }]
          }]
     }]
});