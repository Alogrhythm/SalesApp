package com.sales.app.server.service.appbasicsetup.userrolemanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sales.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.sales.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAppType(1);
        appmenus.setMenuHead(true);
        appmenus.setMenuAction("muo8Cl87Dp4Xx0DL3bvLloQVTd02ZPNmEUMdryDCxerHLHgwRD");
        appmenus.setUiType("lSX");
        appmenus.setAppId("pZUyCPVEwZ0xnWQGPBRw6QT3qX1a3qtB53EOgdkOA1eekhhnwS");
        appmenus.setMenuCommands("AlRTQRnz7rX2Ti4kJGQ1TSRzFcx6PgMUd8UDyw6Vo26q0oqjL1");
        appmenus.setMenuTreeId("PEhRcWFgUc6sWKFsIXkp9fBG1ZoBDwrDt5Kpp4fh0bUTy0I0F2");
        appmenus.setMenuAccessRights(7);
        appmenus.setMenuIcon("Ez9j4OQw2JqF8zmuVcCRlQWKiNSYv7AFntT7ALkQMyGh69peVl");
        appmenus.setRefObjectId("qY65sMCcfjGogaJ5BNyEC1wQVK8K3gDeaFJVInLTz9lQpGnCuG");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("iKoj25xWw2eOC7cCOb7GloPL7lQRjcyF9ZgVwmjJRlFIGCMjqg");
        appmenus.setMenuDisplay(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setVersionId(1);
            appmenus.setAppType(1);
            appmenus.setMenuAction("KqLgSJsE7kGFR38OJyNt4Wfmd9Sl2kG5HexUPhv0KgIDuuDiUR");
            appmenus.setUiType("PeW");
            appmenus.setAppId("6bzuN8qNcaYbUgRGjjKJr8awUuMLpcYtp5lyitXO60uf89ABn8");
            appmenus.setMenuCommands("5ODF9HZ5fho1yFRdaMumrjMXgdW17oIGgbWm2jkAVcrygfKRdn");
            appmenus.setMenuTreeId("uKCWs2hDpfpjtpZ76cPlYuXSr6lcIDRlvteiOFuUHC4zaLnARg");
            appmenus.setMenuAccessRights(11);
            appmenus.setMenuIcon("G3gamxs1Li31Inw0uo6t9tqJqy18lnxjsHciPl5GhfiJbuLc4F");
            appmenus.setRefObjectId("Rvv6nt8n685BI6f8Sd1GzhFcEhHmjeuqKDN0zBocT6tc4Edl66");
            appmenus.setMenuLabel("9TANs0btxr7MMO6k4q6h79T9kBB3G8Vjnfe1DV1jCazGEvjYD0");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "bq23qRUBN2onXLqd6a2V4yY8sBuCnMQQhApuz9UoQPBmi47vAbroOEcRzcq7ie2PrSuZ4orEmhJmZQGpqjftqsvwFwiqnh62YKAPRexWVc1gsJc65zbB38wD6i7RZhd5IZhwzS5pr9DwjlZJA0r05nok6AYpl8oZ6LZ4zOCrHApaGoyQVRogwFwH9WOLbGMeKma3ma1KfL2B6kHhPrwz8UzEUpFh5bjGXXa0hDFjXr2CmxSZk5G8lu3DJ8iODpgDG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "sxu5dj5uUQTbxgPx5coyMV6Cc1k5MWePXlPj7C5bmpqpoxt50zrp9EF8xCBUYoOb9tx0Ju77VDJw4EEg8K7Wn4cMMLEvddSRftqTfUnWpavAttD7m1d8G6apHCf23ZPOxvVYBxCUHNTcnGyhyoITYm3PWrXzjv5ejKjJVBl3ZsrBSmb1LgijD62l4tNmfklsYAMMExvYxj5ED7bMixf9gpW8JcxbOP9dyeO1GvEJ4KE3gonAsDULi81Q1ORLuTid5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "O0Su2MPjJp62FVZwqFnHr5KlcJoITR76UzFYiYSF0EtOfE65Au4QCl9ubIU0rSY7gjdKtq3EdVwer0Iu7E0DP0ABbCeowpq1v0ONIzbFvECrlyaqbYd1bkptdx04vDUwH6YprsqIUkCse9DQXMQCLMSfpBFBtjbFToShTlFDycP7VZ7jFmhH9N4cZSR2xknYNleSiHddg8tIhjyUpxoqznRKWWgjLgb47ZSIwcVCnA4zz9NFt9MfdmD6CcaGi3KmY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "Mz8IxZ4dKkoqZ4Xrj5YAdW6KqpUJCxXyBxjEC0cB9kzuuHu853ZpLMt32s6NQkOHj"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "ErvSjnDgVG87qriJ0seGlTsLNsHpe6VslwzAoOe9o6ee2sNmdpDjXx237sDVmsecMzJ0W0uzHUa4oAXUgyrVst32IuUWQEyj1XGDgxAygsaqbetMn9WwQ3kUY75w78T618gneoVWld9ctVJNfQhYNq3PMT5BJJyQSoSMV1Ha9nsdB3aRYBByz8WB2rXyBfNlc33T8BMCmRAXeMn88Cm9yxpAAlrd2wMVtoEn73LlMxEMKfHY4g1BaOV1aUkPVlVxO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "swrn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "WhFAEXso53aM5jZHQ0L3CpdvKWf5ZZV9S8cGq6Bp13LqeVdHxy37VTz28E7qY4zj4ZnilhbzYsJNz6ZhjlikVh60wZMkvXcOXaPMVvtLiEU1PprOZ35Po4fsnBhRaeJFuK6bWhictl42qtxcP1GgscyEVAgm1jbDMvocy0Jo6tMu6WfEfQCM70NG0QxNyUIvuMcq7BYUspBAf7foO6z5fNnABGTihQ0H3uIbh8NZkdTy5HXDkcxWcg9nhAPpOp45E"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "zdDAADu0LNyksB8IE748czk7MfkfiHcARd2iq0EvPZhEto4g9Jsv97m2K5Qi4PDqX2DGbOlS5vzBI0M7nKsaS8PuswX94yfXDzHQs5LIuiFZhcUtTGgHY72BfUkir9sOxDP5VaLdRqjgCZUDKPdTITImnZbhvxzA1wTBZLqxskdIvdpwSXrqJQnQ4OI0RUzLNUz3B0hbAS5cV84NDyCBsy0cURmHqHqWpqbt0zygoyzhcPCBoXTczcsQ3ChJPvvFz"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
