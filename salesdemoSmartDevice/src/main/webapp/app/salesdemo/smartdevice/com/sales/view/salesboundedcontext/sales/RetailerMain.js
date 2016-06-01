Ext.define('Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.RetailerMain', {
     "xtype": "retailer",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "RetailerMainController",
     "restURL": "/Retailer",
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.RetailerModel", "Salesdemo.salesdemo.smartdevice.com.sales.controller.salesboundedcontext.sales.RetailerMainController", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.DistributorModel", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.RetailerViewModel"],
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
               "itemId": "RetailerTree",
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
                    "viewModel": "RetailerViewModel",
                    "xtype": "form",
                    "displayName": "Retailer",
                    "title": "Retailer",
                    "name": "Retailer",
                    "itemId": "Retailer",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "retailername",
                         "itemId": "retailername",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Retailer",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Retailer<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "57D3EAD0-8872-4D4F-8190-59B2B71FDC9C",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "retailername",
                         "columnWidth": 1
                    }, {
                         "name": "distributorcode",
                         "itemId": "distributorcode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Distributor",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.DistributorModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Distributor<font color='red'> *<\/font>",
                         "fieldId": "F4483CDD-C6A2-481E-9714-19B83B71CA00",
                         "restURL": "Distributor",
                         "bindable": "distributorcode",
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
                         "customId": 187,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 187,
                              "customId": 33
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 187,
                              "customId": 34,
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
                              "parentId": 187,
                              "customId": 35,
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