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
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("mvIw2waMJTNPP2SB1G6H1A7rIuRv7SXdeLLLlYoY8NikUs8gf1");
        useraccesslevel.setLevelName("k0FQu0lyP7QAiypdRcJHIwppZ5jpKUzz50qNUYTsMqoippClin");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("vvF3fItyZKNCIyRiIGlhkOdY5t3GSNu9rpp34sUbr0m9FxwKrx");
        useraccesslevel.setLevelDescription("vXyz46wgm1ZSJlzMkqPjNNhZSfC8AdQnP4ckR14JsoHGZmwlt4");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelHelp("FGDUDDFV2JRVv3HaXBqVDBgm7LOh5iLS49GBC0XZXft2PNVi9Y");
            useraccesslevel.setLevelName("v5tl11VMAwWTF0uGC5RtppzEy8R0oU4nmrXWmh0PN2aQfdvk5X");
            useraccesslevel.setUserAccessLevel(77604);
            useraccesslevel.setLevelIcon("R8LXdukfHjNyIGThZjcguL3Y8a6SkYpYYvydFkhGStsA6wYk0G");
            useraccesslevel.setLevelDescription("2e1mux0FZqhLWAODlPOyDToyhEbMBthHncYyVCcXnqooq8spCM");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 157746));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "2YhbH9VNbC0iIzlAW7895LCIpKCDQcnQj6HD3nUZPPnKmHhmx5stQ20nyCNjpvHDDTEuCWeExM0cvKrAl55P3VypJrbXKEckYmyp6JGihslNyFYUTuMup2jwZ97fD1GGVqgeFHWZ2rt8MUClRCZr0e2F6GOyhzBDlZQMPcV1SfaMpNS53Hlwnw4mKacgWyHoJCE4kj1lwujjZf6gMr3ovem8Sly1VwhHXmYuv1H9UHJqTinatSpVknp0ZA4Mr7DyW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "RHDQknnaK6ee6LRYhrpVGvL7bCrmMfC1viFkLOT3zZa2fNwp6DSq8Yscq0TaygdxuiVUxxPcP7LC285P8tsdTPPq4SOsCzh7YynyRN03aUNFedAaLa0DiqtMvgujYPUYyYNGhjmsuW1z6RDO97shr4gF2uibjOaRbACr7uhWq3KCC9wRJ2jonjsLrIgJMrRLp4Giht7bkFZ11o5bLgSabwtoi0rrN7jtSjk56ng1qmJd6DRAfY65Ld3tjm985lB5K"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "U5cvpYQ0RH14O1PDjsDiAPVjGdvfeEQNb31PTFsWQeYmaeFyxH6ctF1XN0oqcYFtaxCuvDRqO8gVBHW1OBEPOiYIy02SJxqwLpC6looj0vH9z5r0tRheDln4wPN2V6sKbAQgfndDBln37u3mM3s61Eaqph5j99xorz4y5LqNgn1ZH0FHEce4wPZEENko0xjKKetjyMcBEYUy9nSzZdmBMLL5WKDQC7enruUICc5xL04ifJBWNQMcRtyHF5vJzx2T5"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
