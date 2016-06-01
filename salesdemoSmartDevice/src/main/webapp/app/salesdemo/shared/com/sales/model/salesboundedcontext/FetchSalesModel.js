Ext.define('Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.FetchSalesModel', {
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
          "name": "grosssalesamt",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "netsalesamt",
          "type": "auto",
          "defaultValue": ""
     }]
});