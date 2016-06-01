Ext.define('Salesdemo.view.smartdevice.MainViewport', {
		extend:'Ext.container.Viewport',
		bodyPadding : 5,
		closable : false,
		xtype : 'mainViewport',
		id:'mainViewport',
		autoDestroy : true,
		requires : ['Ext.ux.SlidingPager','Salesdemo.view.fw.DateRange','Salesdemo.view.smartdevice.login.Login'],
		layout:'fit',
		title:'Viewport',
		items:[{
			xtype:'login'
		}]
});