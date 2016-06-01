Ext.define('Salesdemo.view.databrowsercalendar.DBCalendar', {
	extend : 'Salesdemo.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Salesdemo.view.databrowsercalendar.DBCalendarController',
	             'Salesdemo.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
