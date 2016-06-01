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
import com.sales.app.server.repository.salesboundedcontext.sales.CurrentMonthRepository;
import com.sales.app.shared.salesboundedcontext.sales.CurrentMonth;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "16", comments = "Service for CurrentMonth Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/CurrentMonth")
public class CurrentMonthServiceImpl extends CurrentMonthService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CurrentMonthRepository<CurrentMonth> currentMonthrepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.CurrentMonth> lstcurrentmonth = currentMonthrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("SBCSA124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        responseBean.add("data", lstcurrentmonth);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "findAll", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody CurrentMonth entity) throws Exception {
        currentMonthrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "save", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<CurrentMonth> entity, @RequestHeader("isArray") boolean request) throws Exception {
        currentMonthrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "save", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") Integer entity) throws Exception {
        currentMonthrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "delete", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody CurrentMonth entity) throws Exception {
        currentMonthrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "update", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<CurrentMonth> entity, @RequestHeader("isArray") boolean request) throws Exception {
        currentMonthrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("SBCSA123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "update", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.sales.app.shared.salesboundedcontext.sales.CurrentMonth lstcurrentmonth = currentMonthrepo.findById((java.lang.Integer) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("SBCSA124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "CurrentMonth"));
        responseBean.add("data", lstcurrentmonth);
        Log.out.println("SBCSA124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrentMonthServiceImpl", "save", "CurrentMonth");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
