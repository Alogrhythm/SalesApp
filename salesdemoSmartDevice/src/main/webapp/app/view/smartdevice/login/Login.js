Ext.define('Salesdemo.view.smartdevice.login.Login', {
		extend:'Ext.panel.Panel',
		bodyPadding : 5,
		closable : false,
		xtype : 'login',
		autoDestroy : true,
		requires : ['Salesdemo.view.smartdevice.login.LoginController'],
		controller : 'login',
		 style:{
		    	background:'#fff'
		    },
		    layout:{type: 'fit',
				align:'center',
				pack:'center'
			},
			items:[
	{
		xtype:'form',
		itemId : 'form1',
		title:'',
		margin:'0 30 0 30',
		layout:{
			type: 'vbox',
			align:'center',
			pack:'center'
		},
		defaults:{
        	width:'100%'
		},
		items:[
            {
            	xtype:'image',
            	height:100,
            	width:100,
            	margin:20,
            	src:'images/logo.png'
            },
            {
				xtype : 'textfield',
				itemId : 'loginId',
				name : 'loginId',
				emptyText:'Username',
				height:38,
				enableKeyEvents : true,
				fieldStyle:{
					fontSize:'16px',
					color:'#585858',
					fontWeight:'bold'
				}
				
			}, {
				xtype : 'textfield',
				itemId : 'password',
				name : 'password',
				height:38,
				inputType : 'password',
				emptyText:'Password',
				enableKeyEvents : true,
				fieldStyle:{
					fontSize:'16px',
					color:'#585858',
					fontWeight:'bold'
				}
				
			},{
				xtype:'button',
				text : 'Login',
				width:'100%',
				height:38,
				margin:'5 0 0 0',
				style:{
					background:'',
					borderWidth:'0px',
					borderRadius:'0px'
				},
				listeners : {
					click : 'onLoginClick'
				}
			},{
				xtype:'button',
				text: "<span style = 'color:#DBA901;font-size: 16px;'><u>Forgot Password?</u></span>",
				width:250,
				height:35,
        		style:{
				    "borderColor": "#ffffff",
            		"background": "#ffffff",
            		"borderRadius": "0px",
            		"borderStyle": "solid"
				},
				listeners : {
					click : 'onForgetPasswordClick'
				}
			},{
				html:'<div align="center" style="font-size:10px;">Powered by <b>Appli<span style="color:red;">Fire</span></b> @ 2016</div>'
			}]
	}
            ]
});