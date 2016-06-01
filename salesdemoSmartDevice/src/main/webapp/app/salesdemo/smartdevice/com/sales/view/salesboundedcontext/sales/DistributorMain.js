Ext.define('Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.DistributorMain', {
     "xtype": "distributor",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "DistributorMainController",
     "restURL": "/Distributor",
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.DistributorModel", "Salesdemo.salesdemo.smartdevice.com.sales.controller.salesboundedcontext.sales.DistributorMainController", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.SalesRegionModel", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.DistributorViewModel"],
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
               "itemId": "DistributorTree",
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
                    "viewModel": "DistributorViewModel",
                    "xtype": "form",
                    "displayName": "Distributor",
                    "title": "Distributor",
                    "name": "Distributor",
                    "itemId": "Distributor",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "distributorname",
                         "itemId": "distributorname",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Distributor",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Distributor<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "608717C1-6E1E-4760-A39C-51BD4008A378",
                         "minLength": "0",
                         "maxLength": "64",
                         "bindable": "distributorname",
                         "columnWidth": 1
                    }, {
                         "name": "regioncode",
                         "itemId": "regioncode",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Region",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.SalesRegionModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Region<font color='red'> *<\/font>",
                         "fieldId": "88C7453A-D308-4859-AF32-C1224374033B",
                         "restURL": "SalesRegion",
                         "bindable": "regioncode",
                         "columnWidth": 1
                    }, {
                         "name": "longitude",
                         "itemId": "longitude",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Longitude",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Longitude<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "906BA69B-90EA-441F-A914-18C13AF84A1A",
                         "minValue": "-9223372000000000000",
                         "maxValue": "9223372000000000000",
                         "bindable": "longitude",
                         "columnWidth": 1
                    }, {
                         "name": "lattitude",
                         "itemId": "lattitude",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Latitude",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Latitude<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "2269B074-5CDB-4615-9A04-774A41CBDF3F",
                         "minValue": "-9223372000000000000",
                         "maxValue": "9223372000000000000",
                         "bindable": "lattitude",
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
                         "customId": 135,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 135,
                              "customId": 815
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 135,
                              "customId": 816,
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
                              "parentId": 135,
                              "customId": 817,
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