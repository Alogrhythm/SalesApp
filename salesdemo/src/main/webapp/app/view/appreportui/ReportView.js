Ext.define('Salesdemo.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Salesdemo.view.appreportui.ReportViewController',
	            'Salesdemo.view.appreportui.datagrid.DataGridPanel',
	            'Salesdemo.view.appreportui.datagrid.DataGridView',
	            'Salesdemo.view.appreportui.querycriteria.QueryCriteriaView',
	            'Salesdemo.view.appreportui.chart.ChartView',
	            'Salesdemo.view.appreportui.datapoint.DataPointView',
	            'Salesdemo.view.googlemaps.map.MapPanel',
	            'Salesdemo.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
