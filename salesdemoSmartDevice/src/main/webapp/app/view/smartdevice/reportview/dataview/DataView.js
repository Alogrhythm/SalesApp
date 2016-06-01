Ext.define('Salesdemo.view.smartdevice.reportview.dataview.DataView', {
	extend : 'Ext.panel.Panel',
	requires:['Salesdemo.view.smartdevice.reportview.dataview.DataViewController','Salesdemo.view.smartdevice.reportview.dataview.DataDetailViewModel'],
	xtype : 'report-dataview',
	itemId : 'report-dataview',
	controller:'report-dataview',
	layout:'border',
	style:{
		background: '#ffffff'
	},
	margin:'5 0 0 0',
	items : [
	    {
	    	xtype: 'grid',
	    	region:'west',
	    	hideHeaders:true,
	    	itemId:'report-dataview-list',
	    	width:'100%',
	    	//margin:'5 0 0 0',
	    	bodyStyle:{
	    		background: '#fff'
	    	},
	    	//cls:'report-list-view-cls',
	    	rowLines:true,
	    	features: [{ftype:'grouping'}],
	    	emptyText: '<div align="center"><img src="resources/appicons/ic_no_result.png"/><div align="center" style="align:middle;font-size:14px;font-weight:bold;color:#838b8b;">No data found!</div></div>',
	    	viewConfig:{

	    		getRowClass: function(record, rowIndex, rowParams, store) 
					{  
						var reportgrid = this.up('grid');
			        	if(reportgrid.gridConfig.hasOwnProperty('rowHighlight')){
			        	var rowArray= reportgrid.gridConfig.rowHighlight;

			        	for(var i=0;i<rowArray.length;i++ )
			        	{
			        		
			        		if(rowArray[i].parameterType=="range"){
			        			if(rowArray[i].to!=""){
				        			if(record.get(rowArray[i].name)>=parseInt(rowArray[i].from)&& record.get(rowArray[i].name)<=parseInt(rowArray[i].to) )
					        			
					        			return rowArray[i].styleCss;
				        		}else{
				        			if(record.get(rowArray[i].name)>=parseInt(rowArray[i].from))
					        			return rowArray[i].styleCss;
				        		}
			        		}
			        		else{
			        			if(record.get(rowArray[i].name).toString()==rowArray[i].equalTo)
				        			return rowArray[i].styleCss;
			        		}
			        	}
			        	}else{
			        		return "";
			        	}
					}
			},
	    	store:{
	    		autoLoad: true,
	            autoSync: true,
	            pageSize:20,
	            proxy: {
                    type: "ajax",
                    url: AppRestUrl+"secure/dataEngineMobileService/getGridDataForMobile",
                    method:"POST",
                    jsonData : {id:""},
					extraParams: {id:""},
                    paramsAsJson:true,
    	            actionMethods: {
                         read: "POST"
                    },
                    headers: {
                         "Content-Type": "application/json;"
                    },
                    reader: {
                         type: "json",
                         rootProperty: "response.data",
                    	 totalProperty : 'response.total'
				},
				writer : {
					type : 'json'
				}
               }
	    	},
	    	listeners:{
	    		boxready:"onDataListAfterRender",
	    		itemclick:'onDataListItemClick'
	    	}
	    },
	    {
	    	xtype: 'form',
	    	region:'center',
	    	autoScroll: true,
	    	itemId:'report-dataview-detail',
	    	layout:{
	    		type:'vbox',
	    		align:'stretch'
	    	},
	    	defaults:{
	    		margin:'10 20 0 20'
	    	},
	    	viewModel:{
	    		type:'report-dataview-detail-viewmodel'
	    	},
	    	header : {
				titlePosition : 1,
				style:{
					background:'#ffffff'
				},
				items : [{ 
		               xtype: 'button',
		               style:{
	                	   borderRadius:'55%',
	                	   borderWidth:'0px'
	                   },
		               scale:'medium',
		               icon:'resources/appicons/ic_back.png',
		               margin:5,
		               listeners:{
		                  click:'onBackClick'
		               }
					   }
		               ]
			},
			listeners:{
				hide : 'onDataDetailViewHide'
			}
	    },
	    {
	    	xtype: 'form',
	    	region:'east',
	    	itemId:'report-dataview-drilldown',
	    	hideHeaders:true,
	    	hidden:true,
	    	width:'100%',
	    	title:'Drill Down Report',
	    	autoScroll:true,
	    	layout:{type:'vbox',align:'stretch'},//,pack:'center'},
	    	//margin:'5 0 0 0',
	    	style:{
	    		background: '#ffffff'
	    	},
	    	defaults:{
	    		margin:10,
	    		labelAlign:'top'
	    	},
	    	/*features: [{ftype:'grouping'}],
	    	plugins:[{
      			ptype: 'rowexpander',
      			rowBodyTpl: ['{report_name}']
   			}],*/
	    	header : {
				titlePosition : 1,
				style:{
					background:'#ffffff'
				},
				items : [{ 
		               xtype: 'button',
		               style:{
	                	   borderRadius:'55%',
	                	   borderWidth:'0px'
	                   },
		               scale:'medium',
		               icon:'resources/appicons/ic_back.png',
		               margin:5,
		               listeners:{
		                  click:'onDrillDownBackClick'
		               }
					   }
		               ]
			},
			items:[{
				xtype:"combo",
				itemId:"drilldown-reports-combo",
				editable:false,
				//height:3,
				fieldLabel:"",
				emptyText:"Select drill down report",
				delimiter:"",
				displayField: "report_name",
				valueField: "report_id",
				listeners:{
					select: 'onDrillDownReportSelect'
				}
			},
			{
       			xtype: 'checkboxgroup',
       			itemId:'columns-chk-group',
        		fieldLabel: '',
        		delimiter:"",
        		columns: 1,
        		vertical: true,
        		defaults:{
	    				margin:10,
	    		}
        
    		}
    		],
    		buttons:[{
				xtype:'button',
				text:'OK',
				flex:1,
				height:38,
				listeners:{
						click:'onDrillDownBtnClick'
				}
			}],
			listeners:{
				hide : 'onDrillDownViewHide'
			}
			
	    }
	]
	
});