package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterface;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "19", comments = "Repository for Material Master table Entity", complexity = Complexity.LOW)
public interface MaterialRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByBrandcode(String brandcode) throws Exception;

    public T findById(String materialcode) throws Exception;
}
