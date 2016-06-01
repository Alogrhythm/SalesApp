Ext.define('Salesdemo.view.smartdevice.login.LoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.login',
	//views : [ "Login" ],
	requires : ['Salesdemo.view.smartdevice.design.drawer.main.MainPanel',
	'Salesdemo.view.smartdevice.login.ForgetPassword'],
	
	onLoginClick : function() {
		var form = this.view.down('#form1');
		// var container = this.view.container;
		var container = this.getView();
		var entMask = new Ext.LoadMask({
			msg : 'Please wait...',
			target : container
		}).show();
		var currentObject = this;
		if (form.isValid()) {
			Ext.Ajax.request({
				url : AppRestUrl+"secure/Authentication/authenticate",
				method : 'POST',
				container : container,
				maskEnable: true,
				entMask:entMask,
				jsonData : {
					"loginId" : form.getValues().loginId,
					"password" : form.getValues().password
				},
				success : function(response, scope) {
				
					var jsonResponse = Ext.JSON.decode(response.responseText);
					if (jsonResponse.response.success) {
						scope.container.removeAll();
						var _cookies = Ext.util.Cookies;
						var resp = jsonResponse.response;
						
						var userinfo = Ext.decode(resp.userinfo);
						
						var uNameValue = userinfo.firstName.concat((userinfo.hasOwnProperty('middleName')&&userinfo.middleName!=null)?(" "+userinfo.middleName):"").concat((userinfo.hasOwnProperty('lastName')&& userinfo.lastName!=null)?(" "+userinfo.lastName):"");
						
						_cookies.set('userInfo',uNameValue);
						
						if(userinfo.hasOwnProperty('XA_TID')){
							_cookies.set('XA_TID',userinfo.XA_TID);
							XA_TID = userinfo.XA_TID;
							
						}
						
						var mainViewport = Ext.getCmp('mainViewport');
						mainViewport.removeAll();
						mainViewport.add({
									xtype:"mainPanel",
									userinfo : userinfo
						});
						scope.entMask.hide();
					} 
				},
				failure : function(response, scope) {
					scope.entMask.hide();
					var jsonResponse = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert('Error', jsonResponse.response.message);

				}
			});
		}
	},
	onForgetPasswordClick :function() {
		
		var mainViewport = Ext.getCmp('mainViewport');
		mainViewport.removeAll();
		mainViewport.add({xtype:'forgetPassword'});
	}
/*	,onSessionLoginClick : function() {

		
		var form = this.lookupReference('form');
		var container = this.view.container;

		var currentObject = this;
		if (form.isValid()) {
			
			Ext.Ajax.request({
				url : "http://digitization.cloudapp.net:8080/Salesdemo/secure/Authentication/authenticate",
				method : 'POST',
				controller : currentObject,
				maskEnable: true,
				jsonData : {
					"loginId" : form.getValues().loginId,
					"password" : form.getValues().password
				},
				success : function(response, scope) {
					
					var jsonRespone = Ext.JSON.decode(response.responseText);

					if (jsonRespone.response.success) {
						if(scope.controller.view.currentController != null){
							scope.controller.view.currentController.fetchData();
						}
						form.up().close();
					} else {
						if (jsonResponse.response.changePassword == true) {
							Ext.Msg.confirm('Confirm', jsonResponse.response.message, function(id, value) {
								if (id == 'yes') {
									
									// show change password screen
									scope.container.removeAll();
									scope.container.add(Ext.create('Salesdemo.view.smartdevice.login.ChangePasswordScreen'));
								}
							}, {
								controller : this,
								scope : scope
							});
						} else {
							Ext.Msg.alert({
								title : 'Info',
								msg : jsonResponse.response.message,
								icon : Ext.MessageBox.WARNING
							});
						}
					}
				},
				failure : function(response) {
					var jsonRespone = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert('Authentication failed', jsonRespone.response.message);
				}
			});
		}
	},

	onForgetPasswordClick : function(btn, opts) {
		
		var parentpanel = this.getView();
		var form = this.view.down('#form1');
		var loginId = form.getValues().loginId;

		var entMask = new Ext.LoadMask({
			msg : 'Loading questions...',
			target : this.getView()
		}).show();
		Ext.Ajax.request({
			timeout : 180000,
			url : "http://digitization.cloudapp.net:8080/Salesdemo/secure/PasswordGenerator/findSecurityQuestions",
			headers : {isBeforeSession : true},
			method : 'POST',
			waitMsg : 'Loading questions...',
			entMask : entMask,
			jsonData : {
				"findKey" : loginId
			},
			me : this,
			success : function(response, sender) {
				
				var responseText = Ext.JSON.decode(response.responseText);
				if (responseText.response.success) {
					var data = responseText.response.data;
					var forgetPasswordWindow = Ext.create('Salesdemo.view.smartdevice.login.ForgetPasswordWindow', {
						parentPanel : parentpanel,
						listeners : {
							afterrender : function(window, opts) {
								
								window.down('#loginId').setValue(loginId);
								var secQuestionStore = window.down('#secQuestion').getStore();
								secQuestionStore.loadData(Ext.JSON.decode(data));
							},
							scope : btn.up('login').controller
						}
					});
					parentpanel.add(forgetPasswordWindow).show();
				} else {
					Ext.Msg.alert({
						title : 'Info',
						msg : responseText.response.message,
						icon : Ext.MessageBox.WARNING
					});
				}
				sender.entMask.hide();
			},
			failure : function(response, sender) {
				Ext.Msg.alert("ERROR", "Cannot connect to server");
				sender.entMask.hide();
			}
		});
	}*/
});