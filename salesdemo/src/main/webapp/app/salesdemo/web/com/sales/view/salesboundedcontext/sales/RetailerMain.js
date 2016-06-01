Ext.define('Salesdemo.salesdemo.web.com.sales.view.salesboundedcontext.sales.RetailerMain', {
     "xtype": "retailer",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "RetailerMainController",
     "restURL": "/Retailer",
     "defaults": {
          "split": true
     },
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.RetailerModel", "Salesdemo.salesdemo.web.com.sales.controller.salesboundedcontext.sales.RetailerMainController", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.DistributorModel", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.RetailerViewModel"],
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
               "displayName": "Retailer",
               "name": "RetailerTreeContainer",
               "itemId": "RetailerTreeContainer",
               "restURL": "/Retailer",
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
                    "itemId": "RetailerTree",
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
                    "displayName": "Retailer",
                    "title": "Retailer",
                    "name": "Retailer",
                    "itemId": "RetailerForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "retailercode",
                         "itemId": "retailercode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Retailer code",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Retailer code<font color='red'> *<\/font>",
                         "fieldId": "849822E5-86EA-459E-936B-341A2984BD4E",
                         "minLength": "0",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "retailercode"
                    }, {
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
                         "columnWidth": 0.5
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
                         "fieldId": "7240B863-6CE5-43EB-B8C2-64A4DE39240C",
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
                         "customId": 159,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 159,
                              "customId": 447
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 159,
                              "customId": 448,
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
                              "parentId": 159,
                              "customId": 449,
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
                    "displayName": "Retailer",
                    "title": "Details Grid",
                    "name": "RetailerGrid",
                    "itemId": "RetailerGrid",
                    "restURL": "/Retailer",
                    "store": [],
                    "columns": [{
                         "header": "Retailer code",
                         "dataIndex": "retailercode",
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
                         "header": "Retailer",
                         "dataIndex": "retailername",
                         "flex": 1
                    }, {
                         "header": "Distributor",
                         "dataIndex": "distributorcode",
                         "renderer": "renderFormValue",
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
               "displayName": "Retailer",
               "title": "Retailer",
               "name": "Retailer",
               "itemId": "RetailerForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "retailercode",
                    "itemId": "retailercode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Retailer code",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Retailer code<font color='red'> *<\/font>",
                    "fieldId": "849822E5-86EA-459E-936B-341A2984BD4E",
                    "minLength": "0",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "retailercode"
               }, {
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
                    "columnWidth": 0.5
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
                    "fieldId": "7240B863-6CE5-43EB-B8C2-64A4DE39240C",
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
                    "customId": 159,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 159,
                         "customId": 447
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 159,
                         "customId": 448,
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
                         "parentId": 159,
                         "customId": 449,
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