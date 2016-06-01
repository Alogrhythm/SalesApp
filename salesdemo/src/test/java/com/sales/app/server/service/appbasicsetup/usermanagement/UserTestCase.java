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
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.User;
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
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.sales.app.shared.appbasicsetup.usermanagement.Question;
import com.sales.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserTestCase extends EntityTestCriteria {

    @Autowired
    private UserRepository<User> userRepository;

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

    private User createUser(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("rD8VHxCq8Jle1ifpbLG6nce48Trtp08LbJpyazsQbeLo99rhE9");
        useraccessdomain.setDomainHelp("t0QMfczGHnK038azA2NVeJfRz0nEjSEfz4Mtt0tVjnSUB4Rlve");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("pDeQAprpvBqHt5S70P67K6paCP8lybmfomX9CCUW659kibfvfC");
        useraccessdomain.setDomainName("gzGyAcydki51F4KXxCL6SSZ36uQeBr4SO6ktAQXko77iqOOMP3");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("W0igoL20yKtwxF13JTXC4uN8RjfrdaqsTqMSNWenU3iw9HPVv3");
        useraccesslevel.setLevelName("T2B55eEH3xtlQaTRd7iUN43wbhRqfoampShtfRDYpttik8O3jR");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("bVDtQl7E20IBiOuqSWRDeUOQ8B5tiedNndqxgC3kaXjK99hKGW");
        useraccesslevel.setLevelDescription("LsH8prPYOj3boQ6WLo5ED56bc9pcIJefhysVfewSiSn7lyrIGj");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        User user = new User();
        user.setChangePasswordNextLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("HkGkQRIWDUe11QCYm5GEJrey8NvjNgwSLk8ttexwtwfRJRuLNW");
        user.setSessionTimeout(1670);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464680598645l));
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setGenTempOneTimePassword(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464680598695l));
        user.setUserAccessCode(51618);
        user.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("IDl1UJ0mexJgy5HXGXoABVo7Wk4ytZpSSEcGfxtFQlN4e2BoHF");
        Question question = new Question();
        question.setQuestionIcon("ntZxJRLs4Sxpn7tYt4EubEsNgkhyuDQHRPhm6QRW9rFzMYJlDn");
        question.setQuestionDetails("aaPsO0NT3u");
        question.setLevelid(8);
        question.setQuestion("SvMdSsIe1YIfsNcQih8Kg7BjPDv51sU9y92bs41tQj7DUvkMBO");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("uOPk6iSYvPlMb9BwhF2nJhxFbbPfBGIzKXFcbFVpS5hTdphe9M");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("Va5DxPWjd54wobr8ZEsVUP6yuzJb7YIGPa3D6RmB8yKzQXjUxD");
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("j3b87Y2bbqwotyoVS3aJgDcI8dhqHyeg");
        userdata.setLast5Passwords("OoKgmf8P10mI4vDmTimnm2faaoCaUcBWi76dn0mvp4bVfq5ZiE");
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("MEjhzVcsU9mPXT05ZWX6PViyld1oin0c");
        userdata.setUser(user);
        userdata.setPassword("FieGbaqgUX0LgoVqp1RABKI9EuoH4crt59poMMqAlIe2hy2m8w");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464680598932l));
        user.setUserData(userdata);
        user.setEntityValidator(entityValidator);
        return user;
    }

    @Test
    public void test1Save() {
        try {
            User user = createUser(true);
            user.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            user.isValid();
            userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2findByuserAccessDomainId() {
        try {
            java.util.List<User> listofuserAccessDomainId = userRepository.findByUserAccessDomainId((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            if (listofuserAccessDomainId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2findByuserAccessLevelId() {
        try {
            java.util.List<User> listofuserAccessLevelId = userRepository.findByUserAccessLevelId((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            if (listofuserAccessLevelId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserPrimaryKey"));
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUser(EntityTestCriteria contraints, User user) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            user.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            user.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            user.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userRepository.save(user);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "userAccessCode", 108471));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "multiFactorAuthEnabled", 2));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "genTempOneTimePassword", 2));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "allowMultipleLogin", 2));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "isLocked", 2));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isDeleted", 2));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "changePasswordNextLogin", 2));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "passwordAlgo", "PbwoaUdNFQwDAAaouCeLjaOl0R7dWB6abwyPejpPbUtJ9YRnPXPJJAKR5KyeywTb7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "sessionTimeout", 5081));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                User user = createUser(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = user.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        user.setUserAccessCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 2:
                        user.setMultiFactorAuthEnabled(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 3:
                        user.setGenTempOneTimePassword(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 4:
                        user.setAllowMultipleLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 5:
                        user.setIsLocked(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 6:
                        user.setIsDeleted(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 7:
                        user.setChangePasswordNextLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 8:
                        user.setPasswordAlgo(contraints.getNegativeValue().toString());
                        validateUser(contraints, user);
                        failureCount++;
                        break;
                    case 9:
                        user.setSessionTimeout(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUser(contraints, user);
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
