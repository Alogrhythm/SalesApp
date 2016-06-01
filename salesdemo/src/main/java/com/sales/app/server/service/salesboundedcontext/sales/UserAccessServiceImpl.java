package com.sales.app.server.service.salesboundedcontext.sales;
import org.springframework.web.bind.annotation.RestController;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.sales.app.server.repository.salesboundedcontext.sales.UserAccessRepository;
import com.sales.app.shared.salesboundedcontext.sales.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "15", comments = "Service for UserAccess Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/UserAccess")
public class UserAccessServiceImpl extends UserAccessService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private UserAccessRepository<UserAccess> userAccessrepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.UserAccess> lstuseraccess = userAccessrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("SBCSA124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        responseBean.add("data", lstuseraccess);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "findAll", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody UserAccess entity) throws Exception {
        userAccessrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "save", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<UserAccess> entity, @RequestHeader("isArray") boolean request) throws Exception {
        userAccessrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "save", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") Integer entity) throws Exception {
        userAccessrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "delete", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody UserAccess entity) throws Exception {
        userAccessrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "update", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<UserAccess> entity, @RequestHeader("isArray") boolean request) throws Exception {
        userAccessrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "update", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByUserId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.sales.app.shared.salesboundedcontext.sales.UserAccess> lstuseraccess = userAccessrepo.findByUserId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SBCSA124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        responseBean.add("data", lstuseraccess);
        Log.out.println("SBCSA124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "save", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findByRegion", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByRegion(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.sales.app.shared.salesboundedcontext.sales.UserAccess> lstuseraccess = userAccessrepo.findByRegion((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SBCSA124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        responseBean.add("data", lstuseraccess);
        Log.out.println("SBCSA124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "save", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.sales.app.shared.salesboundedcontext.sales.UserAccess lstuseraccess = userAccessrepo.findById((java.lang.Integer) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SBCSA124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "UserAccess"));
        responseBean.add("data", lstuseraccess);
        Log.out.println("SBCSA124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessServiceImpl", "save", "UserAccess");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
