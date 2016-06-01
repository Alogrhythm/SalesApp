package com.sales.app.server.repository.appbasicsetup.usermanagement;
import com.sales.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;



public interface ArtAppNotificationTemplateRepository {

	public ArtAppNotificationTemplate findById(String templateId) throws Exception;
}
