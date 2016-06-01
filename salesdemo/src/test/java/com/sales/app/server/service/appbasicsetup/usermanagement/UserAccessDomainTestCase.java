package com.sales.app.server.service.appbasicsetup.usermanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sales.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("cGS8ThIYKSYndFYGI7wal7TertDbpHhV1dHoLaEF5cOxNSp9k9");
        useraccessdomain.setDomainHelp("dQMdJ9kTiMWRFfO2SMWy4GYmUZO95Oy8kA82DY0N1m7OpU8m5O");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("On6XIdvVrAGftkxMh3SnDfuG0YS78P0WiTvN9IYlq3lxJFhFZM");
        useraccessdomain.setDomainName("k4tqadkJ058EQA6zjsLZu8hHsXSYKuVhuj7LbzUWHaVEWZWwJx");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainIcon("R9S6wp0s9QXVa5q1c5Mh8kFv6nq1bIRN2LYvUQRjdEkFY3jIou");
            useraccessdomain.setDomainHelp("p12FqOtYz6rJsgnas5KGxzj58K4ejDH0guhnqLCWt0Z7cLsiTk");
            useraccessdomain.setUserAccessDomain(50765);
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainDescription("yAi1drEaYRRFsDa0w8hgo3BzjIDIfnOlrRCRN9N6E1B2iY56xS");
            useraccessdomain.setDomainName("pc00SDfRhnDhserxBqWFsoPP7bWDHB6MQV92f3hK6Fdfzxa6DP");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 166148));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "hEahlclqwAbzBeaS9o6JNLOZU70kt2joKzYwXZn1Oy0onCblqYatuVrvwVFV4TX2Ry1ANq7rTMMH331rxKa23aD6ted3E2cCLXSCRn372xL0Pnnfuo9LF0yr6BUyeBgfLmowVitHHcOHlQnzWhr4BgQP6xFtxV3mmN2vXDlIuPLog30QOjqejg7Lx9bRMcmCcvsxUgafXWqLCMeT4CF330q8J539wiZmbfNKNxIdCEmQ7pE1zzYREt6ylVMkhKBsw"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "WLCPxeVBwDvVrMzMXhxKND0g8JMtwnNYBJayokChpYrUQQPXvCTPYW1e83cyqP8m89UzkH03q3mucUqWuRgOjjYqBBIigbAeFSVHMdSoLYgq6TtyCPzna8SLLCZZYFwkQja3yhJ41iIc6uDCiysK5nrXVaVBNPwW3xkI8a05JP3gEmjcAW3FNxc4gabv8L1L6CIdFYolHZHCE6Cb54zie6wzvIiVFfsaLk8vHbbf617FLazJwsMjgNwjwZGFshk27"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "yKAEbTCPirSJHQCo5lUERs8KULY1sECXmLaUt6vYYGkjDTc01ieZ9VuQxQjGwMq5V6aT5NPEoVr0xpUwfWwaDdh5TDd1fsK0HZQ5uc12K5QBnzFCawdzCG7OUgVEudjh2OEW8PHNnhQ0APXVUCCafkIORYS8oKDr9bpCck6VntRyYgj2IeQB7ECCYL2d0DzRA4dDHFd3Cdl9bQCQ8liqUPJJqP5aiiStzTBwOTc37tAAo9q0ccza91H2RxLl7ArSR"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
