package com.sales.app.server.service.organization.contactmanagement;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sales.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.sales.app.shared.organization.contactmanagement.CoreContacts;
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
import com.sales.app.shared.organization.contactmanagement.Title;
import com.sales.app.server.repository.organization.contactmanagement.TitleRepository;
import com.sales.app.shared.organization.contactmanagement.Gender;
import com.sales.app.server.repository.organization.contactmanagement.GenderRepository;
import com.sales.app.shared.organization.locationmanagement.Timezone;
import com.sales.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.sales.app.shared.organization.locationmanagement.Language;
import com.sales.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.sales.app.shared.organization.contactmanagement.CommunicationData;
import com.sales.app.shared.organization.contactmanagement.CommunicationGroup;
import com.sales.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.sales.app.shared.organization.contactmanagement.CommunicationType;
import com.sales.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.sales.app.shared.organization.locationmanagement.Address;
import com.sales.app.server.repository.organization.locationmanagement.AddressRepository;
import com.sales.app.shared.organization.locationmanagement.City;
import com.sales.app.server.repository.organization.locationmanagement.CityRepository;
import com.sales.app.shared.organization.locationmanagement.State;
import com.sales.app.server.repository.organization.locationmanagement.StateRepository;
import com.sales.app.shared.organization.locationmanagement.Country;
import com.sales.app.server.repository.organization.locationmanagement.CountryRepository;
import com.sales.app.shared.organization.locationmanagement.AddressType;
import com.sales.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("CaKJsCftW3npw6rLQvlAxgmDdVwAizoWMFg7RGYC0caognwVi3");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("HEROC4EyrFDQAFHmbDEn05C55v8qmlJhC1E86UJKQ162nfyUiv");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("IB9jVmYoaShmv1XAqcHyMDDHXuCwoSTMwzOft1SZAWQNjZ7QGH");
        timezone.setTimeZoneLabel("djCCfVB1t6O2Zl1wYXdiN7RlmoCIgakRtdnYlC2LLXefFO8Ex1");
        timezone.setCities("hhK36C9hc75VzsLj2p4jb6oqVSTHeOtIO9tTqvM2ZbFkkdWz5f");
        timezone.setUtcdifference(6);
        timezone.setCountry("VB02rw1u5BcGgXFnkj4TLRj8iXuBCBBrig49knrcXLeiSVGO8F");
        Language language = new Language();
        language.setLanguageDescription("x72du5Daq23I4vmQM52AgpfjTfAzYfCIwDEbVKCHWHbrvQhjB8");
        language.setAlpha4("t4ak");
        language.setAlpha3("ca5");
        language.setLanguageType("LvEAk5fkUqnJbKoJggu4zyAA9UKCVpRZ");
        language.setLanguage("LRa1qDFxntwvx4mx6C6rUb5qFO6Go2epeyGJFJlMJkUHlDXyHP");
        language.setAlpha2("tP");
        language.setLanguageIcon("4nIByWT4b2Zvh1C0cN673Kl0l7JyKjIWtqFpUKgBBjg75WRdTN");
        language.setAlpha4parentid(6);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(116);
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464680591280l));
        corecontacts.setPhoneNumber("MSKednRyOjlFsiYpiRAe");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLastName("OkEPd9jfHY9Sqd4HWan5d1E1KsFIoHQqZDMcGVAhIKdgNAfXjT");
        corecontacts.setNativeFirstName("YsH29epepe7slOXVTd6JtZV2Yr0XvWy2rqZLJrfGRnjIYbfBqY");
        corecontacts.setNativeMiddleName("GU6UtvMoup2V8mWoYHn6eFu0vpAK5Dst9yprwDo53fEBclJtqh");
        corecontacts.setEmailId("9qGXFOZrmLSUDV1ekwX98ILxjW3wAfCkQm3sRMoSp0eudt7svW");
        corecontacts.setLastName("w2Maqwe7CfzAtfEYKONWAE1fwjKGlsyg0GnLudUHKUcs9OY7JT");
        corecontacts.setMiddleName("LKP6vDwL1D9QDNxQwN5PWV6d1TdAj44q0q3Gu1DIS1hmz1AGR9");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464680591358l));
        corecontacts.setNativeTitle("TvbycZycPDUTgnfCa1xq336vdhqZKWSWU8C5aT86t4STvvqLKx");
        corecontacts.setFirstName("dnm2whWRZkmB5ObJ9YkBV2vSyNeZizZ2NfhLlOD0xBWDkTyGXw");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("4Ofj4RtZe0yjdZz3iav4rmLKCKatRY5hThcdEUl2l5P8pAce90");
        communicationgroup.setCommGroupName("6ra0UAPlRSf4ZaIjqg1HkyW1nTVePiqJhZO0aQ4GacvCQiNtOL");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("rGDYq3qjU61lo9gQUPv8OhraCeSQ4UAsXI2BPGYflwK34TvCJv");
        communicationtype.setCommTypeName("lhK6kxQ32UeGFifPA7gg2nUM4OYmD6dn8qh2Ks5uDm4h762std");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("QwucUT6q8u");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("9ZTyi4HYFqjg82CGbPtXLLccrKmYNHLSxerURap43VfGXNAxX4");
        address.setLatitude("VQbHHFN0Z3Vyd5OU4vU1AvK4wY2erMJ28RIoJhCqHe3SsPyPJ9");
        City city = new City();
        city.setCityFlag("tG8LT1YOy23RQUn8hVFTP4ayjfIaZme9pEy39n7MIZuBYGqMGK");
        city.setCityDescription("DW55K4cKGYmLvj20ZMGeKwKb6kX34LOa1qImFVNQ7hZvN7nTBI");
        city.setCityLatitude(2);
        city.setCityName("N3lm6pK7EhEsfARPe6P6lH66riQC0vd7TIWoN9k4BsQsSlK68M");
        city.setCityLongitude(3);
        State state = new State();
        state.setStateCapital("nHzzdD9fSYJwe8F5zZEOX0fc1iP3YqA02KiiTPST4PPeNg4A7U");
        state.setStateFlag("MigUMu1yIZAchUMdT6ppy9YDyXdx7tJ1RmivgvulIjkrOlgB6e");
        state.setStateName("flFvMGiIhgk6PKdexzrv1tYkaGNxqItUTrBABmCvge6RZrvglw");
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCurrencySymbol("bCOL5Tgcvg8nOQr97zJ14xf1ahoRbbsv");
        country.setCountryCode2("Y5d");
        country.setCountryName("kcbDpicuxZaIAh0Qb8dZ7pnpDBhMyRFvrGIinuYF9RkPGoDDyR");
        country.setIsoNumeric(217);
        country.setCurrencyCode("O2Y");
        country.setCapital("0r2cX8Wmmrotd08gf7YN1mquLg28Or8l");
        country.setCountryCode1("xPF");
        country.setCountryFlag("hAlGq4KDcK2ZxRg03x7eNEXsAXAJZg4CEjAOEM5gXcx9E6ZVJc");
        country.setCapitalLatitude(5);
        country.setCurrencyName("MURXtcowm4nWwErGW1vlJplwlGrfON1t7wEbkJ8souCvxuSamV");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapital("mgbnb3WGBfHUrdjiVCTJw4Di5chqE5Rl82wg835NPinjxzWmPR");
        state.setStateFlag("Wa4uErahqb8oZd0qqYD8hNTkAmWXajok9z6jOMPu2J00feiGB1");
        state.setStateName("58mHDj4R5g5E6XjdCDRwuKCYlTlkyhCunQIBTdAiGRVQVZ6fgZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("QgTfetnH9Yyqzs1jxTXH8JgE8yCspWQa");
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(1);
        state.setStateCode(1);
        state.setStateDescription("azqSSpwxtB5PlOAEa1eIWzFqiUOSCVyvUzDMHXwz0KfpP9psQg");
        state.setStateCodeChar2("hK4BD3WF3I3ujxbMvWJu79hf5tEtFvj4");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityFlag("fJn4RtfBJI0wj10g7Q6coqcb7qsxqhbA6z784hifn84jJTUk0L");
        city.setCityDescription("j43fNDcXbytj7JJKirQOaYEnNbDjn0VL9bZKoedNJhMYETb4kC");
        city.setCityLatitude(9);
        city.setCityName("BncDzMMYuqkzb865sgSdJBxYRabOqe1xYqNcePfCpokPp9PF0J");
        city.setCityLongitude(6);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("rcYPDq0XrS5jE6AyK2E9qwowq1DI2sAX");
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("pmC6ASXFM7IwddJW0nans3c1VAdDcv84gqRFh1uTM9yiZ9UimH");
        addresstype.setAddressTypeDesc("32Cu4FQeVvl9Kx61VVDeeetu8wRHcT43aOudRcwl9ohYelLJXS");
        addresstype.setAddressTypeIcon("oM0aZ27fAg35O9lzxOAP5TmytTHVIDbCG0Xl8nx5Nfrz0mpNkZ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLongitude("sm6Ww59Iypp50NziO9DtHbEFN0qLp3Qo2xf2qGYXkzK17HOv2a");
        address.setLatitude("rNngVXQCqkNYliRATpwiz1GnVuihBypNCeaJ95xXxVS9bvJLkV");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("e5E0tHFlpnA");
        address.setAddress2("x3DeFPK0lQfdhMlaaeSHG3IeuvzRZf4XVbZcsMJjzBqrJtf0wA");
        address.setAddress1("Jr35h2sxD0lmWEajvQ73FzJYKxksKjyLsDSOHIB8Tx5mzKdf4V");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("sJ0FER");
        address.setAddress3("WuaP4mX9aW3kZOhIABb7TX7ngZpNIexeY4cpFnoFj1YBDHT5f7");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setAge(25);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1464680591692l));
            corecontacts.setPhoneNumber("EyX8ggmCvq8bdV2ObkYQ");
            corecontacts.setVersionId(1);
            corecontacts.setNativeLastName("MlZocYCIvqCTnlInKFEr82ztNNWAlsDuP31QPRRTsQsfg51VIZ");
            corecontacts.setNativeFirstName("gTSmGYRYBF94lTYxIPWxrtixQ5Mk8C4yNzIEjXKOkaOr5XjFS8");
            corecontacts.setNativeMiddleName("BxGs5rfKOq5hzkXqS5MgVUZjjYxWbRuaF2c6PsOvqNySb5HsMj");
            corecontacts.setEmailId("lpQm1iakVn0b3R1xv1bh8Qhet3SXfzvHXWbfkcDlklSxPmaJsy");
            corecontacts.setLastName("0MeHQ1lnSwTYU0IusF8YAbr8r3dHfvh2bk0JhA1m70CxoVQKLc");
            corecontacts.setMiddleName("yIVjEzOuzI2uYaIh3LvWTFq9ZzSFdGhPpJkSYSOelNxgXVtGu7");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1464680591837l));
            corecontacts.setNativeTitle("OcOQ86QzMtzn8MfU57jnYG0gfo4KuXsWjLdPPl6FRs7Zu8bLmm");
            corecontacts.setFirstName("ULCeNvrYorpdqBL4op5pAunvtAACSZzo9qiyCryN5jnTc810aJ");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "Ged4VrfzUkfS0ztV07djV7hQFH1xQ4KxWtSgK5lasstehTDaNX83G5MGyLfF25SXSGoLycBSHy5NGFDZ8PyLgyS2g5o3kaGS6LL9AlLZau9NZXH3uq0HyTWOLdizrzgoSKACN5XfCcCKOZSzRSYbCp4hFh1Jx98yfjDH8Qy1FuDfPQzxDFYrTHk6ztoi8MjS79mBPtgis4RBJ9sNWfiQ2yOp4NPfn394hA1IB2HAhk9qIxa6OuDT772YVpLvGU6VZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "yGdF22XDD7YkpKs4a0g7h0Bg0RcnIaEB99KJpFy2Ia1a5KxwPn9L5znlJW0yAQloc9Q6axBYnhPb9nwaW4c5Oxl4ECQIDoEP7BCtyUZ8tfYKk3Wpa2dEkC5qi2ReZ3I1MJs2gogogFZ98HRAZBCNKG7N7sOpgIXHT7ZuxZIas43nH6T7Ua6GI7FBZkBPfcze80vu2wu2Y6s9oPflgkxgLqwhfsKPZwFl4i2iwzgnIGQLZSTjxHyAFrBzT1MEbAeOx"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "WdlA3n7zpNIlO3eO82UBii345NVIZ6bOHxuNqcO9B7Pz9jY1f3Lr5xzE4AIY91VUjpKQunpOOcrqlfnCwIsUWnqjR6xh0kHhP9a0OIqaxpuEu6ZkXvAQjpLzTzea6HVqdzux4d0haeoNuR6n4rns1fFuMaHJuKCmLsiyOhHL0OoW2rVS8VvHcdkEAr5DEir70ZFx854omomMMHLBVwAKATEMJB1YDCDJFHg9qncnKgu4aJawG1VKtS5IU5HTMYCKW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "I0I0Pskrvo9iTe5CqohBhKJS9oSi6KUosT1yx4qF7xngIX2g9vpjYohd9A6CLbM0w"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "6DXkYbIsgAde9i361Z3NGwfi8ZuNcZPSYPVNXDrIyDJ7pdxAbLbP8TJxdJl7Dl2DlNScpDpLD54WpmH3mTF5EWrUe71TSB00Wh57g9Ubkd2WHTSuBwNE4gT0I44H4sb3MINDXcpbE5nHoY4cAAQjuSNjnHLw5Ke9cJCru5napkJFqmSNaewTvScNnfALBMIsmhDHJ8dxAOD2aVSFuExAU2sMRqyAA4GdmFsdsjM6O7hPWed12J7ryy0NySIJvfTVx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "NnobWLzeALTmuFCCt4ElwFvvALzrHHCd794zPa1mDlcm1q2jPTPPsQnlpGuX3poeEid5IYWUY720ma0rGYy4QrsMIf1xf6YAGZMLdczqiesxe61UNrwRs3IpXJM3fFFaeXKnYvRv0qw6DOHkTqIjMC07JuZVzLp56hNnlsezjX2BVnqj5nWtgW8w8wJK5ixcr0bFr9p0q1mSiKhBU8ZN4QPbvhbJdT1zxMtQLNo9hoKDI4loUMfjzJy9mHxPhiiLr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "kVDN7gJ5OEY0lC2ndo3XTNESvB4QptToiJ16i95EoQwRgnJgH94zP5XCHUAwgkrT6M2OPNxTA7DtKYdhYmy1MmjxtoRUjqQXsH8f9A0NmEk3sPu86cLwDi64jj3AvtrTJY9outhrVUNYF8GQidEibVdSlOhoqXrKVxyLjwOxmcEEiML5o9jxZrrGw8E9R7uNorkPthWqRBj0Okfuydmj1G4nuDIRtCmJwL0G9q0NPhWmNfdOeCvGE3Hy9vvlMBtzr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 163));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "mug2c5NebEmKAxaW6tSlhjNzDrYdcEaLfQVEyLSCrKy4HZKCbtRXVsRuTyqXZ1dOJgPyD40yl8sbNmWbocpTX9KshRBpz0BDS4kLx5scsOYsUL1b9C47f7Dk2mPCfeEAwhQwuu9pE6yONTiOThMqDGu0pxmtSwj9Ra6O51CACWI6rko78Tl9iVsEeDsxAj75nxOpHgguyTwRKxiFQRtRj5331wZMX6powi9eh01oeSMaP9w2CuzpX7c2FCxFuDbv4"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "d5DNSlQrDUwgIY3fH1bRo"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
