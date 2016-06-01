Ext.define('Salesdemo.salesdemo.web.com.sales.view.salesboundedcontext.sales.BrandMain', {
     "xtype": "brand",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "BrandMainController",
     "restURL": "/Brand",
     "defaults": {
          "split": true
     },
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.BrandModel", "Salesdemo.salesdemo.web.com.sales.controller.salesboundedcontext.sales.BrandMainController", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CategoryModel", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.BrandViewModel"],
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
               "displayName": "Brand",
               "name": "BrandTreeContainer",
               "itemId": "BrandTreeContainer",
               "restURL": "/Brand",
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
                    "itemId": "BrandTree",
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
                    "displayName": "Brand",
                    "title": "Brand",
                    "name": "Brand",
                    "itemId": "BrandForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "brandcode",
                         "itemId": "brandcode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Brand Code",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Brand Code<font color='red'> *<\/font>",
                         "fieldId": "6A4E22D2-2615-464F-B858-E6D8AB8DD27F",
                         "minLength": "0",
                         "maxLength": "64",
                         "hidden": true,
                         "value": "",
                         "bindable": "brandcode"
                    }, {
                         "name": "branddesc",
                         "itemId": "branddesc",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Brand",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Brand<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "EBBD9D20-F4DB-448D-9A0A-F183CB4F9BA6",
                         "minLength": "0",
                         "maxLength": "64",
                         "bindable": "branddesc",
                         "columnWidth": 0.5
                    }, {
                         "name": "categoryId",
                         "itemId": "categoryId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Category",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CategoryModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Category<font color='red'> *<\/font>",
                         "fieldId": "28E25C98-047C-44F4-B190-35DAE121E1F2",
                         "restURL": "Category",
                         "bindable": "categoryId",
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
                         "fieldId": "081C0EE1-B5C1-4C2F-A83A-21E08CFF3650",
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
                         "customId": 241,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 241,
                              "customId": 432
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 241,
                              "customId": 433,
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
                              "parentId": 241,
                              "customId": 434,
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
                    "displayName": "Brand",
                    "title": "Details Grid",
                    "name": "BrandGrid",
                    "itemId": "BrandGrid",
                    "restURL": "/Brand",
                    "store": [],
                    "columns": [{
                         "header": "Brand Code",
                         "dataIndex": "brandcode",
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
                         "header": "Brand",
                         "dataIndex": "branddesc",
                         "flex": 1
                    }, {
                         "header": "Category",
                         "dataIndex": "categoryId",
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
               "displayName": "Brand",
               "title": "Brand",
               "name": "Brand",
               "itemId": "BrandForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "brandcode",
                    "itemId": "brandcode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Brand Code",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Brand Code<font color='red'> *<\/font>",
                    "fieldId": "6A4E22D2-2615-464F-B858-E6D8AB8DD27F",
                    "minLength": "0",
                    "maxLength": "64",
                    "hidden": true,
                    "value": "",
                    "bindable": "brandcode"
               }, {
                    "name": "branddesc",
                    "itemId": "branddesc",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Brand",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Brand<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "EBBD9D20-F4DB-448D-9A0A-F183CB4F9BA6",
                    "minLength": "0",
                    "maxLength": "64",
                    "bindable": "branddesc",
                    "columnWidth": 0.5
               }, {
                    "name": "categoryId",
                    "itemId": "categoryId",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Category",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CategoryModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Category<font color='red'> *<\/font>",
                    "fieldId": "28E25C98-047C-44F4-B190-35DAE121E1F2",
                    "restURL": "Category",
                    "bindable": "categoryId",
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
                    "fieldId": "081C0EE1-B5C1-4C2F-A83A-21E08CFF3650",
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
                    "customId": 241,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 241,
                         "customId": 432
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 241,
                         "customId": 433,
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
                         "parentId": 241,
                         "customId": 434,
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