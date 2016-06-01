package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.sales.app.shared.salesboundedcontext.sales.CurrentMonth;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "16", comments = "Service for CurrentMonth Master table Entity", complexity = Complexity.LOW)
public abstract class CurrentMonthService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(CurrentMonth entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<CurrentMonth> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(Integer id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(CurrentMonth entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<CurrentMonth> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
