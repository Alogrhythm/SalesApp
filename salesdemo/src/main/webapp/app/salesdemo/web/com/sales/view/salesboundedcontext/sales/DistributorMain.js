Ext.define('Salesdemo.salesdemo.web.com.sales.view.salesboundedcontext.sales.DistributorMain', {
     "xtype": "distributor",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "DistributorMainController",
     "restURL": "/Distributor",
     "defaults": {
          "split": true
     },
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.DistributorModel", "Salesdemo.salesdemo.web.com.sales.controller.salesboundedcontext.sales.DistributorMainController", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.SalesRegionModel", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.DistributorViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Distributor",
               "name": "DistributorTreeContainer",
               "itemId": "DistributorTreeContainer",
               "restURL": "/Distributor",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "DistributorTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
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
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Distributor",
                    "title": "Distributor",
                    "name": "Distributor",
                    "itemId": "DistributorForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "distributorcode",
                         "itemId": "distributorcode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Distributor code",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Distributor code<font color='red'> *<\/font>",
                         "fieldId": "626B2656-E98C-488F-BD47-1982C236840A",
                         "hidden": true,
                         "value": "",
                         "bindable": "distributorcode"
                    }, {
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
                         "columnWidth": 0.5
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
                         "columnWidth": 0.5
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
                         "columnWidth": 0.5
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
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "618DF644-82CB-4BE4-B0EE-493778E4AF01",
                         "bindable": "versionId",
                         "hidden": true
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
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 941,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 941,
                              "customId": 458
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 941,
                              "customId": 459,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 941,
                              "customId": 460,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Distributor",
                    "title": "Details Grid",
                    "name": "DistributorGrid",
                    "itemId": "DistributorGrid",
                    "restURL": "/Distributor",
                    "store": [],
                    "columns": [{
                         "header": "Distributor code",
                         "dataIndex": "distributorcode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Distributor",
                         "dataIndex": "distributorname",
                         "flex": 1
                    }, {
                         "header": "Region",
                         "dataIndex": "regioncode",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "Longitude",
                         "dataIndex": "longitude",
                         "flex": 1
                    }, {
                         "header": "Latitude",
                         "dataIndex": "lattitude",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Distributor",
               "title": "Distributor",
               "name": "Distributor",
               "itemId": "DistributorForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "distributorcode",
                    "itemId": "distributorcode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Distributor code",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Distributor code<font color='red'> *<\/font>",
                    "fieldId": "626B2656-E98C-488F-BD47-1982C236840A",
                    "hidden": true,
                    "value": "",
                    "bindable": "distributorcode"
               }, {
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
                    "columnWidth": 0.5
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
                    "columnWidth": 0.5
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
                    "columnWidth": 0.5
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
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "618DF644-82CB-4BE4-B0EE-493778E4AF01",
                    "bindable": "versionId",
                    "hidden": true
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
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 941,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 941,
                         "customId": 458
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 941,
                         "customId": 459,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 941,
                         "customId": 460,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});