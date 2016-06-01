Ext.define('Salesdemo.view.smartdevice.reportview.ReportMainViewController',{
	extend :'Ext.app.ViewController',
	alias:'controller.report-mainview',
	querycriteriaview :undefined,
	chartview :undefined,
	dataview :undefined,
	
	init : function(){
		this.reportTabPanel =  this.view.down('#reportTabPanel');
		this.querycriteriaview = this.view.down('#report-querycriteriaview');
	},
	pushFloatingButton : function(view){
		if(view){
		var body = view.body;
		
		var button = Ext.widget('button', {
			icon:'resources/appicons/ic_filter.png',
		    fixed:true,
		    height:40,
		    width:40,
		    alwaysOnTop :true,
		    scope:this,
		    margin:10,
		    constraint:true,
		    style:"box-shadow: 0px 1px 1px 1px #888888;border-top-left-radius:0px;border-bottom-left-radius:0px;border-top-right-radius:5px;border-bottom-right-radius:5px;backgroundColor:none;",
		    floating: false,
		    draggable: false,
		    renderTo: body,
		    listeners:{
		    	click :function( floatOp, width, height, eOpts ){
		    		var mainview = this.scope.view.up('report-mainview');
		    		var qView = mainview.down('report-querycriteriaview');
		    		if(qView){
		    			mainview.setActiveItem(qView);
		    		}
		    	}
		    }
		});
		button.setZIndex(29000);
		button.anchorTo(body, 'l-l',null,false,0);
		//button.anchorTo(body, 'b-b',[0,0]);
		}
		
	},
	onReportViewAfterRender :function(reportmainview){
		//this.pushFloatingButton(reportmainview);
		this.configureReportViewUI(reportmainview,false,[]);
		
	},
	configureReportViewUI :function(reportmainview,isDrillDown,criteriaColumns){
		if(reportmainview.refId){
		
		var sender = this;
		var entMask = new Ext.LoadMask({
			msg : 'Please wait...',
			target :this.view
		}).show();
		Ext.Ajax.request({
			url:AppRestUrl+'secure/dataEngineMobileService/initializeReport',
			method:'POST',
			jsonData : {
				id:reportmainview.refId,
				isDrillDown:isDrillDown,
				criteriaColumns :criteriaColumns
			},
			entMask:entMask,
			sender : sender,
			reportmainview: reportmainview,
			success:function(reponse,scope){
				var sender= scope.sender;
				
				var responseText = Ext.decode(reponse.responseText);
				var result = Ext.decode(responseText.response.data)
			    scope.entMask.hide();
			    sender.configureReportUI(scope,result,scope.reportmainview);
			},
			failure:function(reponse,scope){
				
				 scope.entMask.hide();
			}
		});
		}
	},
	onReportFilterClick :function(filterBtn){
		var querycriteriaview = this.view.down('#report-querycriteriaview');
		
		if(querycriteriaview){
			this.view.setActiveItem(querycriteriaview);
		}
	},
	onOptionBtnClick :function(opBtn){
		
		var reportCriteriaView = this.view.down('#report-querycriteriaview');
		/**Check if search criteria ui is available...**/
		if(reportCriteriaView){
			this.view.setActiveItem(reportCriteriaView);
		}
	},
	configureReportUI : function(scope,reportUIJson,reportmainview){
		/**reportmainview is passed in recusrsion..**/
		var reportTabPanel = reportmainview.down('#reportTabPanel');
		var refId = reportmainview.refId;
		reportTabPanel.removeAll();
		
		if(reportUIJson.hasDataPoint){
			var datapointview = Ext.create('Salesdemo.view.smartdevice.reportview.datapointview.DataPointView',{
				reportUIJson:reportUIJson
			});
			reportTabPanel.addDocked(datapointview);
		}
		
		if(reportUIJson.hasGrid){
			var data_json = Ext.decode(reportUIJson.gridConfig);
			
			var dataviewpanel = Ext.create('Salesdemo.view.smartdevice.reportview.dataview.DataView',{
				title : 'Data',
				iconAlign:'top',
				reportUIJson:reportUIJson
			//	icon:'resources/appicons/chart.png'
			});
			var reportGrid = dataviewpanel.down('gridpanel');
			reportGrid['gridConfig'] = data_json;
			var gridProxy = reportGrid.store.proxy
			
			gridProxy.jsonData.id = refId;
			gridProxy.extraParams.id = refId;
			
			dataviewpanel.controller.defineStore(data_json);
			
			reportTabPanel.add(dataviewpanel);
				
		}
		if(reportUIJson.hasChart){
			var chartviewpanel = Ext.create('Salesdemo.view.smartdevice.reportview.chartview.ChartView',{
				title : 'Chart',
				iconAlign:'top',
				reportUIJson:reportUIJson
			//	icon:'resources/appicons/chart.png'
			});
			
			reportTabPanel.add(chartviewpanel);
		}
		
		if(reportUIJson.searchCriteriaWidgets.length>0){
			var criteriaview = Ext.create('Salesdemo.view.smartdevice.reportview.querycriteria.QueryCriteria',{
				title : 'Search Criteria',
				reportUIJson:reportUIJson
			});
			var queryWidgetHolderPanel = criteriaview.down('#queryWidgetHolderPanel');
			queryWidgetHolderPanel.add(reportUIJson.searchCriteriaWidgets);
			reportmainview.add(criteriaview);
		}
		if(reportUIJson.hasMap){
			var mapviewpanel = Ext.create('Salesdemo.view.smartdevice.reportview.mapview.MapView',{
				title : 'Map',
				reportUIJson:reportUIJson
			});
			reportTabPanel.add(mapviewpanel);
		}
		var totalTabs = reportTabPanel.items.items.length;
		if(totalTabs >1 ){
			reportTabPanel.setActiveItem(reportTabPanel.items.items[0]);
		}else{
			reportTabPanel.tabBar.setHidden(true);
		}
	}
	
});
