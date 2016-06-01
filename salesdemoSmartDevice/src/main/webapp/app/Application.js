/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('Salesdemo.Application', {
	extend : 'Ext.app.Application',

	name : 'Salesdemo',

requires : ["Ext.button.*","Ext.container.*","Ext.dashboard.*","Ext.dd.*","Ext.dom.*","Ext.event.*","Ext.flash.*","Ext.form.*","Ext.fx.*","Ext.grid.*","Ext.layout.*","Ext.menu.*","Ext.panel.*","Ext.picker.*","Ext.plugin.*","Ext.resizer.*","Ext.rtl.*","Ext.selection.*","Ext.slider.*","Ext.sparkline.*","Ext.state.*","Ext.tab.*","Ext.tip.*","Ext.toolbar.*","Ext.tree.*","Ext.util.*","Ext.view.*","Ext.window.*","Ext.Action","Ext.Component","Ext.ComponentLoader","Ext.ElementLoader","Ext.EventManager","Ext.FocusManager","Ext.Img","Ext.LoadMask","Ext.ProgressBar","Ext.ProgressBarWidget","Ext.ZIndexManager","Salesdemo.view.fw.component.DateFields","Salesdemo.view.fw.component.CustomCheckBoxGroup","Salesdemo.view.fw.component.CustomRadioGroup","Salesdemo.view.fw.component.DateTimeField","Salesdemo.view.fw.component.DateTimePicker","Salesdemo.view.fw.component.FileUploadComponent","Salesdemo.view.fw.component.Grids","Salesdemo.view.fw.component.ListViewBaseView","Salesdemo.view.fw.DateRange","Ext.data.Model","Ext.app.ViewModel","Salesdemo.view.smartdevice.reportview.ReportMainView","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.MaterialMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.SalesRegionMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.ChannelMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.CurrentUI","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.CurrentMonthMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.DistributorMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.CategoryMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.RetailerMain","Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.BrandMain"],
	
	views : [ ],

	controllers : [],

	stores : [],

	launch : function() { }

});

Ext.Ajax.timeout = 180000; 
var sessionTimeOutFlag = false;

/**isMultiForm**/var isMultiTab = true;

Ext.Ajax.on('beforerequest', function(connection, options, eOpts) {

	if (options.maskEnable) {
		if (options.maskEle != null) {
			options.maskEle.mask('Request processing...');
		} else {
			Ext.getBody().mask('Request processing...');
		}
	}
});

Ext.Ajax.on('requestcomplete', function(conn, response, options, eOpts) {
		
	try {
		if (options.maskEnable) {
			if (options.maskEle != null) {
				options.maskEle.unmask();
			} else {
				Ext.getBody().unmask();
			}
		}
	} catch(e) {
		console.log("requestcomplete >> e -- " +e);
	}
});

Ext.Ajax.on('requestexception', function(conn, response, options, eOpts) {
	
	try{
		var responseText = Ext.JSON.decode(response.responseText);
		if (responseText.alarm) {
			if (responseText.alarm.alarmID == "ABSAA254905401" && !sessionTimeOutFlag) {
				sessionTimeOutFlag = true;
				var loginWindow = Ext.create('Salesdemo.view.login.SessionLogin').center();
				loginWindow.currentRequest = options;
				loginWindow.show();
			}
		}
	} catch(e){ 
		console.log("requestexception >> e -- " +e);
	}
	
	try{
		if (options.maskEnable) {
			if (options.maskEle != null) {
				options.maskEle.unmask();
			} else {
				Ext.getBody().unmask();
			}
		}
	} catch(e1) {
		console.log("requestexception >> e1 -- " +e1);
	}
});
