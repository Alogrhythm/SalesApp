package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.sales.app.shared.salesboundedcontext.sales.Retailer;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "20", comments = "Service for Retailer Master table Entity", complexity = Complexity.LOW)
public abstract class RetailerService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Retailer entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Retailer> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Retailer entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Retailer> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByDistributorcode(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
