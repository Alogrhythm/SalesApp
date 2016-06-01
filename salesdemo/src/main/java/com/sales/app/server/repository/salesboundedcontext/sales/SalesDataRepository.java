package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterface;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "25", comments = "Repository for SalesData Transaction table", complexity = Complexity.MEDIUM)
public interface SalesDataRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(Integer id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByChannelId(String channelId) throws Exception;

    public List<T> findByReatilercode(String reatilercode) throws Exception;

    public List<T> findByMaterialcode(String materialcode) throws Exception;

    public List<T> findByBrandcode(String brandcode) throws Exception;

    public List<T> findByCategoryId(String categoryId) throws Exception;

    public T findById(Integer autoid) throws Exception;
}
