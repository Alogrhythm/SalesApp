Ext.define('Salesdemo.view.smartdevice.design.drawer.main.MainPanel', {
    extend : 'Ext.panel.Panel',
    xtype : 'mainPanel',
    requires : ['Salesdemo.view.googlemaps.map.MapPanel','Salesdemo.view.smartdevice.searchengine.SearchEngineMainView','Salesdemo.view.smartdevice.design.drawer.main.MainPanelController','Salesdemo.view.smartdevice.design.resource.AppFormsList'],
    userinfo:{},
    id:'mainPanel',
    controller:'mainPanel',
    
    layout: {
        type: 'border'
    },

    items: [
            {
                region : 'west',
                itemId : 'westPanel',
                id:'westPanel',
                split : false,
                width : '80%',
                height:'100%',
                xtype : 'appFormsList',
                collapsible:false,
                hidden:true,
                title:"",
                autoScroll:true,
                style:{
                    background:"#ffffff"
                },
                header : {
        			titlePosition : 1,
                    title:{style:"color:#ffffff"},
        			cls:"appHeaderCls",
        			items : [ {
        				xtype : 'image',
        				src : 'resources/appicons/logo.png',
        				id : 'userImg',
        				height : 50,
        				width : 50,
        				margin : '0 10 0 0'
        			}],
        			tools:[{ 
                               xtype: 'button',
                               text: '',
                               scale:'large',
                               icon:'resources/appicons/ic_drawer_collapse.png',
                               cls:'appHeaderBtn',
                               margin:'0 0 0 10',
                               listeners:{
                                  click:'onDrawerBtnClick'
                               }
                   }]
        		}
            },{
                region : 'center',
                itemId : 'centerPanel',
                id:'centerPanel',
                title:'',
                layout:'fit',
                style:{
                    background:"#ffffff"
                },
                header:{
                         titlePosition:1,
                        title:{style:"color:#ffffff"},
        			cls:"appHeaderCls",
                         tools:[{
                     		xtype:'textfield',
                     		id:"searchTxt",
                     		itemId:'searchTxt',
                     		emptyText:'Search',
                     		margin:'0 5 0 5',
                            width:'18%',
                            height:40,
                            hidden:false,
                     		listeners:{
                     			focus : function(searchTxt){
                     				this.setWidth('50%');
                     			},
                     			blur : function(searchTxt){
                                    this.setWidth('18%');
                                }
                     		}
                         },{
                             xtype: 'button',
                             id:"smartSearchBtn",
                             text:'',
                             margin:'0 5 0 5',
                             scale:'large',
                             hidden:false,
                           cls:'appHeaderBtn',
                             icon:'resources/appicons/ic_search.png',
                             listeners : {
                     			click : 'onSmartSearchClick'
                     		 }
                             
                          },{
                            xtype: 'button',
                            text:'',
                            scale:'large',
                            arrowVisible:false,
                            cls:'appHeaderBtn',
                            icon:'resources/appicons/ic_action_navigation_more_vert.png',
                            arrowAlign:'bottom',
                            menu:{
                                items:[{
                                    xtype:'menuitem',
                                    height:35,
                                    text:'Logout',
                                    listeners:{
                                        click:'onLogoutMenuItemClick'
                                    }
                                }]
                            }
                         }],
                         items:[
                         { 
                               xtype: 'button',
                               text: '',
                               cls:'appHeaderBtn',
                               scale:'large',
                               icon:'resources/appicons/ic_drawer_button.png',
                               margin:'0 10 0 0',
                               listeners:{
                                  click:'onDrawerBtnClick'
                               }
                         }
                         
                         ]
                },
                items:[
                    {
                        xtype:'panel',
                        title:'',
                        id:'appMainTabPanel',
                        layout:'fit',
                        itemId:'appMainTabPanel'
                    }
                ]

            }
    ],
    listeners:{
        afterrender:'onMainPanelAfterRender'
    }

});
