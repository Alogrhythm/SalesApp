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
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.sales.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.sales.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.sales.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.sales.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("nlKUwvINQpfhFRRKQBZ4JsbNOWZuJ3NXDLUgSI2JqIbnJp5TQi");
        roles.setRoleHelp("TK8x3W10v2W0BHdiHebe35Nn4M13U83W2kznrQMZP3qzxnwopV");
        roles.setRoleName("sFUp7Acj12VipFAZ3ZIr5jbuST8T4VjD4EwpqtnuXc2eKYo4W9");
        roles.setRoleDescription("sTHUOR6yAB60HDHjmUJmgMqdG9vfyMs2ch93YwefbLAJ1iSMDy");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setAppType(1);
        appmenus.setMenuHead(true);
        appmenus.setMenuAction("iXNS721ecURSg7C9ukNAJlp86E45NcuRkbnLqcVHzKJOxMqRrL");
        appmenus.setUiType("jbW");
        appmenus.setAppId("CmA1qgN0wAPGbIUqoz3FFBTJW3HrTQ1rxthj9YFFSRM6Hqbj7r");
        appmenus.setMenuCommands("6LFDuq0HffWINiNTNvLLM4Nybk3xR3VvaJgpOTBChqY54SQZ2Q");
        appmenus.setMenuTreeId("K7GBIJR4p3miTeYRwDy5LxmpgHCmvHP0lke0uxI3oa6CChiq6j");
        appmenus.setMenuAccessRights(6);
        appmenus.setMenuIcon("CHqDc0eudUJUABGKz3nGXM8kxJEzy0jf7psv3Cm4nPz04xHrfg");
        appmenus.setRefObjectId("O1zxpof5JHoC5Htfq2ISqTNgAGwFDLVWOQOqc8dkgcmZy58oor");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("epodaLMIDBuclt38BRdlPuhXUZQNqXkVhQ9Pe4NDANhYTyy1S9");
        appmenus.setMenuDisplay(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsWrite(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("ObsKgciEnzCfxs604fZO8OVtYdGCoUO5fOVC5CFsaLe6QbfAVk");
            roles.setRoleHelp("A2iQ3H3UbFZQYFP7j9KLnYxjEOsi4Xk0biEQSzE8U5gUp8W85S");
            roles.setVersionId(1);
            roles.setRoleName("TwQyytzc6O2QzisvEv4KJtYxWP9Kyuv9WyCw8oU8835HaY0Lhm");
            roles.setRoleDescription("uYTMO8HE1XeUaGz1Koxer5DkU3CI7RUGkNEHpqaodGSKxA0ygw");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "5GSC030wG4C2deRw7t0iuAr37EAiBnZGnZ9ZXv04yetktF0zgSEXeBvnLC2aSqs9YRl9GK9yIYj87wLK9jLfm2n6uklryokvrnHQqdDYIGbWEwUr9UCSTyKQW2i2BOQ40unXHv7vskZyWQas61nVCzIQ3Q1nXnS4EsB2X9V6XiuVk0NVkkizhenDcuWDNmVUnh7UWol1fmaWwDHrJXDeRxlJciWU2MDDSzUagtfgqUrtB2IA4SOv9M46vFrEcMclj"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "kRqxHHnjFLt0A8Yt3xxZYJl2scHeXxbeWAFB9eDo0EZ9RVxoCbEQjQOI5J3ZMglUdVD02c9tJ1ZyyMYQHPcdd59uXvQwjqBd0zYG0bxTDC0jVRK7y9qgnYHOUxDmy6ifYifhCHhSQkeqHkJJgRD8il3c7d2PyqXY1l1JcIhMyGjVTJiX901aNcxEC8qDdaSxUXPyT5Cgu5N0uwzJyczaNKPlgl0LK7RhkOzibWXC0C6ZDAW75IZlfZoydPmGgb3Pd"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "PjD9XK8xjusf9S6TzJSo1ZA1hRtZKKNSMm8rwpD3d8zzzAiaflDrwofwd4rAcIGDp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "LHX1jxyfL29qaJxxlXlUHsowNOFJCNKWB2YB5SGeaLEIaZrtB7MWEubck7pJvgNlsSLgJHvBzJutrniRz0rSAjPNKqhwjNU5kQJ9befUUi8ezEoMpzwYcvyVokW3k2R8UrxFd5Fcxckt81hHxQgIyeQMgwfGjhWh7jaP2uFwfqvsdU9SetR84aG7IMjj4FHl73xGpRcxtHVpXoEsmTsBwy0UvwrzZWs5k6eIEDsUsKey0wMeGtIhVMhiwGRgZPBST"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
