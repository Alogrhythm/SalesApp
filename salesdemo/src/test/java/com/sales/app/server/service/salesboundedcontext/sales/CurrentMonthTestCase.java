package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sales.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.salesboundedcontext.sales.CurrentMonthRepository;
import com.sales.app.shared.salesboundedcontext.sales.CurrentMonth;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.sales.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CurrentMonthTestCase extends EntityTestCriteria {

    @Autowired
    private CurrentMonthRepository<CurrentMonth> currentmonthRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private CurrentMonth createCurrentMonth(Boolean isSave) throws Exception {
        CurrentMonth currentmonth = new CurrentMonth();
        currentmonth.setMonthname("bi56bCs7v4KjyYr3QYhAox7V6g8GAqYPSOE2wF8KDN0IGtBhlo");
        currentmonth.setEntityValidator(entityValidator);
        return currentmonth;
    }

    @Test
    public void test1Save() {
        try {
            CurrentMonth currentmonth = createCurrentMonth(true);
            currentmonth.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            currentmonth.isValid();
            currentmonthRepository.save(currentmonth);
            map.put("CurrentMonthPrimaryKey", currentmonth._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrentMonthPrimaryKey"));
            CurrentMonth currentmonth = currentmonthRepository.findById((java.lang.Integer) map.get("CurrentMonthPrimaryKey"));
            currentmonth.setMonthname("gbToZSakGqb99CMDHC9V2mbjA4O2P9EVkbLRlnPqF0kxVD7TVr");
            currentmonth.setVersionId(1);
            currentmonth.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            currentmonthRepository.update(currentmonth);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrentMonthPrimaryKey"));
            currentmonthRepository.findById((java.lang.Integer) map.get("CurrentMonthPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrentMonthPrimaryKey"));
            currentmonthRepository.delete((java.lang.Integer) map.get("CurrentMonthPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCurrentMonth(EntityTestCriteria contraints, CurrentMonth currentmonth) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            currentmonth.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            currentmonth.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            currentmonth.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            currentmonthRepository.save(currentmonth);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "monthname", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "monthname", "YIMzTItdFRBdc3PuGNwbN6rLDkA2VCKkIUcwgonQg8VD6na5CdxmF0A17Mr2AJ5Cr"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CurrentMonth currentmonth = createCurrentMonth(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = currentmonth.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(currentmonth, null);
                        validateCurrentMonth(contraints, currentmonth);
                        failureCount++;
                        break;
                    case 2:
                        currentmonth.setMonthname(contraints.getNegativeValue().toString());
                        validateCurrentMonth(contraints, currentmonth);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
