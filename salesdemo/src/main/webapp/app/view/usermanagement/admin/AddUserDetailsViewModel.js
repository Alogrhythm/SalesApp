Ext.define('Salesdemo.view.usermanagement.admin.AddUserDetailsViewModel',
{
	extend : 'Ext.app.ViewModel',
	
	alias : 'viewmodel.addUserModel',

	model: "AddUserDataModel",
	 
	requires:['Salesdemo.model.AddUserDataModel'],

});