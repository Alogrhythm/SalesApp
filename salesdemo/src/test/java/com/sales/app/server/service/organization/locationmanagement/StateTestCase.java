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
import com.sales.app.server.repository.organization.locationmanagement.StateRepository;
import com.sales.app.shared.organization.locationmanagement.State;
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
import com.sales.app.shared.organization.locationmanagement.Country;
import com.sales.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(11);
        country.setCurrencySymbol("FjZxP34b8Po3dwJ95OtJRmNGZdevfOQ1");
        country.setCountryCode2("d2S");
        country.setCountryName("MZDliCBPUiheWuyygHALZ1jGoLq8tydDwmuLhe6ePvCuHbCVZX");
        country.setIsoNumeric(669);
        country.setCurrencyCode("Q9H");
        country.setCapital("EQHCCXUEZDeqSSpdNojgBCsRIFyiWGcw");
        country.setCountryCode1("H1E");
        country.setCountryFlag("wuOp7fYFCVNhBc6123V0cuUNc1yKO7pRqDv5zrM95rX7tzhKTA");
        country.setCapitalLatitude(5);
        country.setCurrencyName("ZvWmOplyw8SQbKMSvAyIg4oN26P0crjfvER7g09wsivNA3kH6N");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapital("Se1jw6SLmoQWGePdoTPdQIonAwghFGV0B63MRqEoGQhC38L12d");
        state.setStateFlag("SaoVhdtQXlTabSlIN4kOnUBUDGUGRDdSYamZVogu0Ag8I9ug1z");
        state.setStateName("ewZwtiXKOJ7Od8cZ9cCKXzXYYTp9FADtt4kVio7GyBupFxdFfM");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar3("8OwWUpFDcOxxpZxK1L4I1ysCbPKkLW5U");
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(11);
        state.setStateCode(1);
        state.setStateDescription("Q9C58nZmTGUfc4y8mFTvVBJNptI70b4jHwrdrYqWKhUtuGzGp4");
        state.setStateCodeChar2("53NDBQgpEi1sgWGCw0fOrUNEYQJAsdd8");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapital("HY1ydfmN9QjW60HEjQObgZg7TUAikifgpgZGnexUFjAXsHe3qO");
            state.setStateFlag("3UUEfzIQFoIVHJ6kj0sxGQLFu9mm8ToKh9h2uZFfp9AbTC1WI2");
            state.setStateName("K3yHwtCFKyKuvaOIk5ItojAVmKpVLS8gaBtHFUPMQCIrpVWiPQ");
            state.setVersionId(1);
            state.setStateCodeChar3("sJ8rca65Iyy1xppZqLlkPmeCliJel1QP");
            state.setStateCapitalLongitude(4);
            state.setStateCapitalLatitude(3);
            state.setStateCode(1);
            state.setStateDescription("a8vQ2JMQA9yXOhzr6I9wasiXzTVp2nxqC604lYJR3jqGzOEQBJ");
            state.setStateCodeChar2("IwXxIdnmkp2ECHeNXV5nC9vE70anGIt4");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "nlisUioYka15h2mRdG4hDuxC5lJQbt50pdTbNbHWA3xs6CocYs7enftMaJMh96CY5R5S1HllimzrZbTCZ1YiBqpdNO370XIEKsJv1CMTbqPvMnYRRSC4DWc1z3qalvyhzkvjBNGnYgbQIerFBJxqwKpzhgb8RYluRBd0qRRYCKumNHWQglJmeqDEptiReJ53yw3wGNgOsMD4GUkingp85ddpiQlmvHKVta0chuxYpYUczaufzjbBIeWPqfavI4znA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "b9yc9zL6q3ABgH4TbgHJMH64JII81vS5i"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "gKbQ5xYBlF2Fpz8WVWHMPJ0kXKRiywP49"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "s3QV1O39LRLqoh0QdMU0Wd8df2NWHsJln2yhqTE9w57Pmlqfk6hHWAl3vD2Qt0qp4uPGtUbvMKjvEILAPX1mo8qoLkHtNsrLGX6nI3COkVhYeYZOH3g8oQR8XxNe31bh9ubKR1EketuKNuR0CQKIfpFrb0mDhYR3WBPsiIKXqkVY0kEs491QAWpcChXUyoyKitzQK5qwfWsbUT4SmiUBKsb9kIvA724tpoAstYGgRoYMyeAi86XanUZm9jPgLf8Gl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "yVqlwntzXshQvVdyRBTGDs6wug4Ln07BFS5hEvbZ1dgcal81i9USgaILRk7ht6Lp0SiAzFY96mwTOMD1VGS7flIwdUUBvQf5bQRT4O7JA2mIFs5kFtPwv3CmO1O6skxxv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "bF1CxxDM7ESJfC08SMwAQdP3fwfMRV7Je7eWXoOaM0IpcErV845zWNHehv1kdhMz8T4J8SClqSyALEf4GvUS4pA6TqjWBYCr5JKGJKmrsCtwKM2dHueGA3eLkqfxcBvZC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 13));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
