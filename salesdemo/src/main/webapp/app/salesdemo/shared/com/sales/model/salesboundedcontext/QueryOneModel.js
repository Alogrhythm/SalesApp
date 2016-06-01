Ext.define('Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.QueryOneModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "distributorname",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "regionname",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "netsalesamt",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "grosssalesamt",
          "type": "auto",
          "defaultValue": ""
     }]
});