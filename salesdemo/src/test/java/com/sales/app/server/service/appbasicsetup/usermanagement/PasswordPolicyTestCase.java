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
import com.sales.app.server.repository.appbasicsetup.usermanagement.PasswordPolicyRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
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
public class PasswordPolicyTestCase extends EntityTestCriteria {

    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordpolicyRepository;

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

    private PasswordPolicy createPasswordPolicy(Boolean isSave) throws Exception {
        PasswordPolicy passwordpolicy = new PasswordPolicy();
        passwordpolicy.setMinPwdLength(4);
        passwordpolicy.setMinSmallLetters(3);
        passwordpolicy.setMaxPwdLength(11);
        passwordpolicy.setPolicyName("jR53WUt8rTjdxuD4y9ru8NxofFswnBKUWK9BzNBLWFUp5Wep5W");
        passwordpolicy.setMinCapitalLetters(3);
        passwordpolicy.setMinSpecialLetters(2);
        passwordpolicy.setMinNumericValues(2);
        passwordpolicy.setAllowedSpecialLetters("826WjdWcdypfP9fEtwUUT6WcHD48HeGRSXMIF5qyyuwnhPf1fd");
        passwordpolicy.setPolicyDescription("i8OEnaANoiay3YdoWE1aw9agnI53ZfRjuLFa0rDImidG7VqYFb");
        passwordpolicy.setEntityValidator(entityValidator);
        return passwordpolicy;
    }

    @Test
    public void test1Save() {
        try {
            PasswordPolicy passwordpolicy = createPasswordPolicy(true);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordpolicy.isValid();
            passwordpolicyRepository.save(passwordpolicy);
            map.put("PasswordPolicyPrimaryKey", passwordpolicy._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            PasswordPolicy passwordpolicy = passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
            passwordpolicy.setMinPwdLength(7);
            passwordpolicy.setMinSmallLetters(7);
            passwordpolicy.setMaxPwdLength(16);
            passwordpolicy.setPolicyName("jfrJWJYCxCuV0Fg8GxU9ZkCiMm4FPNQzeNkWxFeSYJLFfDFzhw");
            passwordpolicy.setMinCapitalLetters(4);
            passwordpolicy.setMinSpecialLetters(8);
            passwordpolicy.setMinNumericValues(10);
            passwordpolicy.setAllowedSpecialLetters("DkI9c5A0DeeV54zJOFGErj4qpcIDkENfqTNJlDmH2aAuMbxQOC");
            passwordpolicy.setPolicyDescription("OOKu6aJl5nPtT6F0oLZH8RcX8WFya0X0O4VkNgyXMAGtPb6bnj");
            passwordpolicy.setVersionId(1);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordpolicyRepository.update(passwordpolicy);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.delete((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordPolicy(EntityTestCriteria contraints, PasswordPolicy passwordpolicy) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordpolicyRepository.save(passwordpolicy);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "policyName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "policyName", "RyYGZp00CmSc4ClT8H3aAJMOj8gsSABnufsg27GIfo9RiJbILCUFfh8POjl3uXbkOHuUcRd9USJJu1fTZEzcww46rE0UTTFWJz6XkvD7F9q4sJ7GgIfHPuoNs3ZQIYJHNNB49n0YINbvP3HjrpwEiDnMMdZVoycKAW6xCpnLvk2y6II3kg90RcyQo0vxe7ex0RzxgXvECeSlw7LTMJwz9STRm9XrR18k7YYvoZa9mtCiJ64GBdklUd8p9aK68oMgl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "policyDescription", "dHtxoACXug0iQVoEtwuKg3v4rR9vnwX2F8CtwuRC50QNWDB1b2gozFvhCOi3iBXdO3VJBlHnXRzxUsze3nWxL5Z58DCILLyvJ3rl4L1nxm99w4or4FsWurnEHr6zjz1UDycmHOdU6SbAZir1iYyux7cKyF0sXQkTYvOS4Dx0HDgDMR3EWtV6NGq3GnbYfOJeDNvyFMz7EzDuDfaPB9Yh4ov3YecheX3P7srBedRLQ78XZoqHhTb9grsQm4EwwP2Ib"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "maxPwdLength", 37));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "minPwdLength", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "minPwdLength", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "minCapitalLetters", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "minSmallLetters", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "minNumericValues", 17));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "minSpecialLetters", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "allowedSpecialLetters", "Zjc1NsMWobV6vZSr35aaUpa5vjXv7eUO1SXMcYm3oWSdinZFHfVAyKv73bL0TAjbIEsyyFXoT9O6njHVQEaA9otopx7PqgWny8ehHYHrFZwcRFMNhkTWjl4qa7wrZ6TOWkevxFWfuVIiXrofVKv2RdKRNpdD1Fo9khZosFoluzDWx2v2YlQwMhJqHVjoz7dpPKAKHIZG6GzQLaL8w3jwyxjXGuHrCSa4yw9IEgEJbkOP23C2u7XzVLBTnNBWk2mTX"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PasswordPolicy passwordpolicy = createPasswordPolicy(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordpolicy.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 2:
                        passwordpolicy.setPolicyName(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 3:
                        passwordpolicy.setPolicyDescription(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 4:
                        passwordpolicy.setMaxPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 6:
                        passwordpolicy.setMinPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 7:
                        passwordpolicy.setMinCapitalLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 8:
                        passwordpolicy.setMinSmallLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 9:
                        passwordpolicy.setMinNumericValues(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 10:
                        passwordpolicy.setMinSpecialLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 11:
                        passwordpolicy.setAllowedSpecialLetters(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
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
