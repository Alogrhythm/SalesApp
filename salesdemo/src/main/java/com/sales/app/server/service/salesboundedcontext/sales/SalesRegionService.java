package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.sales.app.shared.salesboundedcontext.sales.SalesRegion;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "23", comments = "Service for SalesRegion Master table Entity", complexity = Complexity.LOW)
public abstract class SalesRegionService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(SalesRegion entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<SalesRegion> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(SalesRegion entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<SalesRegion> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
