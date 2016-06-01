package com.sales.app.server.service.organization.locationmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sales.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.sales.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("dQGYTWuA9fER9bYrgcpQInabvQSVYSqt8Fk99CQGdls0wXieCJ");
        timezone.setTimeZoneLabel("e28kOJ6G2wuJHR9XBw1dGxrwODp8ZmMgkwNfJOdqiexnpN2xls");
        timezone.setCities("WMGzM2ERQ39Nr7nDMTgGJKo7Qfx6j6F1zZcnBaEMfXTttIZppR");
        timezone.setUtcdifference(7);
        timezone.setCountry("9QzXnRmVHthdbheR5s5kBQPAMzcDxsXLivlS24vDNHiBmYu12m");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setGmtLabel("zCA2M58Tf3VC60g8gwDFYnPETKwU1EFW6Dp2tMQhrmPuwrivUt");
            timezone.setTimeZoneLabel("AgF98MOXP4F9bpHjQaiFXsy7pvLPkzoqadH2vvc8N7EkRn7nqF");
            timezone.setVersionId(1);
            timezone.setCities("oU7xKr0WhxNt2nBhSO9HFFebkqb75gEt97MGuRLtk7hzvXJ89c");
            timezone.setUtcdifference(5);
            timezone.setCountry("PHGhPh5Ge2ThoccrXk0Wei6ki087X5kBB4OyRWXTOiQVEAkk1C");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "M04vrUKnw3Q0JJcGWxZJISbEu2tmTHSnBIFABhnTAOGPS3KYD19uwj5LYIBWF3onHgDaKNlsTf3QN7Syqo4b316TKCOkQZOR70ZMQsoJUqOrOFxHe8WsJlyfTplJYqR4DfLen9cpUhnzbkrGYgXy6Oyo5Szhp6HUQjs0vURU4oW2Yxrqt4KB7VbblpyFL8ZMGFrK5qYJ5TMDl2xYoY2tui1B9j2rPrGFG0BYbkXFcRaOm4TKYsfNEOq2WEL9wZoDn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "OjRgdzoVckX76pwd0JPI8Go5ewpyPmBELIFtwpfbW6dxNpvg0LhmBJYZ1ihcLwI20OmKV1vYfNQW0zdDmJyzKZZN59wuWoQ0zv1Mj3ShiTcLU2AHtQoi41k4A5dh8OuRQ142Bgl6fVp0bXOgHwTp9NkHP52XdWhmpm1ylP3gmdG4WisDwBEAAOTFquuVQkMciutCOOiuN9pLiPbllCdpmjGXfWS5x5ZVnNJEO2z9SmyBgEsxJHSkzAfNpNV77ujCt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "Am4CkJkPpQCHn90GBTlrchUQ5DXQblf4lOkqqQqAg1PRGbvN134R5v4LD4V887FkowOkKVik5pB7wx58rDt5OtaAyQzIdy0DckBRlx3BtOoTw5zJB2mwQ2kqUAxiahpPptSJ65idgtKpAWQfnC0vdfzqB85jVa2ndz9LpjI7SrENYD7ioiWlrzSIqWnNpUeldS5DlQMTWaxd6o7C0JSZDyMvfBzfM2zpwTb47lLab85GOENtHVRbgrniyd9k1sGXr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "2DZXI6Bd1P0ACozNYzIU139vPB2772F7DOsDSLw6VWo1QGvo8gbCeFmAdGEwwEJI4Yq4CPfFbS7J7Iw8jBqqrY2rEsBlfbW821uhYCTNbmNHAyS64KSPNgoKNgyRZmmzrPhirOaTReh6R2yaigNa01UhHUWyV4pnvio44W0OQbcSzNIAFVdrP4x8i9K85TZnR0P2hiNxw3DsLvJFUBFohGbNMRJseiAFbnRkhEDEVP7aKkXQ0kCqJSfSEEBTxLt4x"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
