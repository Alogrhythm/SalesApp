package com.sales.app.server.repository.appbasicsetup.usermanagement;
import com.sales.app.server.repository.core.SearchInterface;
import com.spartan.server.interfaces.LoginSessionDataRepository;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for SessionData Transaction table", complexity = Complexity.MEDIUM)
public interface SessionDataRepository<T> extends SearchInterface, LoginSessionDataRepository {
}
