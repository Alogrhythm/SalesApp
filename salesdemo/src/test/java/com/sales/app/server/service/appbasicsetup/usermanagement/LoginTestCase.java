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
import com.sales.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.Login;
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
import com.sales.app.shared.appbasicsetup.usermanagement.User;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.sales.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.sales.app.shared.appbasicsetup.usermanagement.Question;
import com.sales.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.sales.app.shared.appbasicsetup.usermanagement.UserData;
import com.sales.app.shared.organization.contactmanagement.CoreContacts;
import com.sales.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setChangePasswordNextLogin(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("bZkBwdVdJhHJlz13gNnpdJME5YGIJL5GASEIVuyhntveiUG7To");
        useraccessdomain.setDomainHelp("wGrVja2VS1qCzacHWGLiGdRngXR8KdzzNMW2QCAoOP6dDrzlPI");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("41i8HD70pstEhupkBJemfFRytJFICSvUsAsdJaQiq4qvvYKNoz");
        useraccessdomain.setDomainName("YvD6JTdDpBtGweisvpHRPTSk8IpyFb5cc3hxw6rrnYc9bICxOZ");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("yhGKhMBeJ7KbBXE1bfPNr2QpnqndoA3lhvE4ZuHLR1EaLp1yti");
        useraccesslevel.setLevelName("sroL6dQ2e3HuX9XQ1EvTiLUsptSX76nURmpFV60iZmF2J86qSf");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("ZdskoSQUIYw8hzdgizZXT4pZe95tUtyHx7lTd0RrGQxtxtfFZq");
        useraccesslevel.setLevelDescription("aBfe8jFOUs8Z2VukhlatMEjWMRGDqhG1H4TKLHe0oXoKwtoVPU");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setChangePasswordNextLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("zmoTijUUraUJkex3b1jUmB9AkMUZnO7OYNICmgr1VSvSvWa9pN");
        user.setSessionTimeout(2551);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464680603380l));
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setGenTempOneTimePassword(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464680603405l));
        user.setUserAccessCode(20320);
        user.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("LdotFMJEiN7GmzAACcqnFYHA4I1GLMxB6y6A9h2BPKVYW19NC2");
        Question question = new Question();
        question.setQuestionIcon("gd9mdJJRLv5KxIj9OCNOFhcj87Lt4BOyPS0gSNCZnpNVgAR7xM");
        question.setQuestionDetails("0Y2f7BAT4c");
        question.setLevelid(4);
        question.setQuestion("BgWgHNoKjPZdOzUYj8oQ6XbPjcp7K2Gf6D65b4ZEvpCugzgrm2");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("j9BFl4qDkl2GBE8mUKBDVxUirz5igqL8piRm0CNEmc8obPnrNd");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("PwnXS62ZDvK9LWxff99lLQXiET0sPWnPL3VjDTJWWsBIBB8sph");
        userdata.setOneTimePasswordExpiry(5);
        userdata.setOneTimePassword("s37he7HgT0dM7ZR67v5sJKUap9QzeX50");
        userdata.setLast5Passwords("Ql9FEsCBp8QlWHezECK3i8zT1GBZyKMq6lHik4BP3SlcfZCk18");
        userdata.setOneTimePasswordExpiry(2);
        userdata.setOneTimePassword("YTakAb99kFCntqz1fsZIqVu1AZIkevYO");
        userdata.setUser(user);
        userdata.setPassword("FXAonhAtHdfTRPLgpfZEE5EQ18JeHDKbrLwA9qp040wiylr1bp");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464680603632l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Title title = new Title();
        title.setTitles("8AfJ0odAvI9PJ2M5RmneXPlcxZNA7wvkhIM0qwH2B5hxUB6d4F");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("QxPbqmtimpezBDdBLJdNRWCQWGmavqW0ATa48HnqFEdUlgsiPy");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("QLe816pWVTFQVnlpgcAHmHhBpdKJrttWPOdrNItPML4ucngpdp");
        timezone.setTimeZoneLabel("Y6Z6VnQFl00GHVRQYdxB0VlPrU6W887Y6YqSsOezYnSITLmny2");
        timezone.setCities("um8HTNYdUZ8K2C40j6QUk2G6MKGAiX9nLQDJy06rv7IH8JObDZ");
        timezone.setUtcdifference(2);
        timezone.setCountry("nuKkNFdm7YMQ4dCpYGCW2wHZgxEkPBj2gGLr2ZqTB6ITuDI4xI");
        Language language = new Language();
        language.setLanguageDescription("R2Q4GUxR9dSMoS8R2nIV2AnwJfeCI05OevGTkUKaqJRHOF3qvu");
        language.setAlpha4("2qrI");
        language.setAlpha3("Ofm");
        language.setLanguageType("3Vzr1n0Hed2Od8lBsQNamS7j6sY56FQM");
        language.setLanguage("dRpVFC1vTtmt2oetx2IZTtH5UwN32Nld0EbTGBp6tSWhXo46YR");
        language.setAlpha2("Fs");
        language.setLanguageIcon("hhKYGixD1X6ldeZCAvoBa7r2jvqFilkWmqrpt8VrzETzTPRDOI");
        language.setAlpha4parentid(7);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(47);
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464680603815l));
        corecontacts.setPhoneNumber("55jB9ZoPGWikNJ1tdeFf");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLastName("4d72z2VjuVVPMOlQValngFlsqSfLGF0VqlkNMtIqmzVJk21qUx");
        corecontacts.setNativeFirstName("PNZbrssmyPWC7gIvxFa1NYns8Ftx67KJnkrrakMdqmncuEXXT3");
        corecontacts.setNativeMiddleName("CPfBJNQPPqGKhqpj5qWgTMCPKXDXjARmH4LuE3wNpGALIEM6mp");
        corecontacts.setEmailId("d7U5fpI7IzI60n1jfbxA0fxnID6dFjTPOkBaawjb45ZkbwBY2I");
        corecontacts.setLastName("6Xo1tS8OvXPT3ldYyNAI3q7t848fkayoNjYjqzA6fvrJW464XI");
        corecontacts.setMiddleName("nIkvrIBj2mVZSe22IpJJWWwL4BzzFYOrenxHoISLoQ2B6afkvA");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464680603888l));
        corecontacts.setNativeTitle("tdGbV91vSXbyX3bKJCtTXEtnDyfJYxyJ13bUy4r37aSDe52M6Y");
        corecontacts.setFirstName("rV6uzcP5wMhJhRY3LDm3IlRT1GdOEIEszQ4fpA6z9FsK5rZhJQ");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("PQJBQ9pUcpLwW4cuPA7h6MhgxbjUzcbVFwQAS4HEmiQ5fKGzML");
        communicationgroup.setCommGroupName("Vh9Dn3zg7MqwnPOljkDD8Xd1qwrzuAvsDc28vtgJ3G87KelpU9");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("nLVpdcIcoxFZd5sphZgpLeDcZLBGnSqKAcxnZk8UstosROpM5r");
        communicationtype.setCommTypeName("zhRQkX15JUmGI0iPSAfiQOAOj1hIT4cHbZXdiTeqbO2Zxp2IDc");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("fZpyl9D71f");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("cRMMSAhuJFL57kcIpNHFxYXU7i0rdxf6tM5cJM5LDiuAfKcU0l");
        address.setLatitude("fN1WhCPwes23ie8fi4wmNcJUumDyq1JD8SBchVeBX8U6GCUFfN");
        City city = new City();
        city.setCityFlag("6BfsbX199uJWqiRqHHH4nRYfzfx8KIclA9g6j3bfbYamPo5i1Z");
        city.setCityDescription("5WQ52OSdpeZUQPoPRldNUWNtgterCMTrCPFl292L2ZdenB3Oqa");
        city.setCityLatitude(10);
        city.setCityName("lU4myYRSXDqDF9O3L9sJ94b82V36VndITREoSBzxviVT8Dd1BT");
        city.setCityLongitude(3);
        State state = new State();
        state.setStateCapital("RVvNC8GQJTJGGo2ZNAwR0m06IXcREvuXKv5soJRVF0DeLxlh7L");
        state.setStateFlag("HwC57tj6Gub9ldH6tc6aEfdlbTe3pyuhD8zUUindLjJsB952Aq");
        state.setStateName("CAKepsjEeesXWkCnySs3UpbKr3M0fqZUTR17ChgbfCqhHEPQKX");
        Country country = new Country();
        country.setCapitalLongitude(11);
        country.setCurrencySymbol("RtsUD4B84WeO1w5COIQS3P8NUz2upR38");
        country.setCountryCode2("fCA");
        country.setCountryName("6AQhkPq9W5aheWksYUd9aYIQblhzctiuSX8aju4VQ9KTZORlYU");
        country.setIsoNumeric(333);
        country.setCurrencyCode("TzS");
        country.setCapital("X9hd5dcafo4R54k8bZmyf5MFUWtXMivp");
        country.setCountryCode1("VPU");
        country.setCountryFlag("3ujyqPox7P0WfCjZjh5rYazlXf6JdYC3g63TjBjKTNJCwRNhhY");
        country.setCapitalLatitude(5);
        country.setCurrencyName("mPruUYR8XPmKNHnlbgznVbyEdWHI6KcQOxrkiggCvGKvphYb7B");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapital("nDWCnAPzeKp8moRsRbbUIoMADZqtJixk5XGoa3aAd0sP5r9EHR");
        state.setStateFlag("l5blDLn6A11DqLlOrs8F8p0pyIpLhuJlGVtE1niywAEh65Wn6w");
        state.setStateName("ltueWbEhGYzbUDXmyc7ildVwZMP2I0qiYlcT2z6jxKwpfqdCcG");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("4RKSEsSr9Hmh02P4UnBil8IucnJtaKJ2");
        state.setStateCapitalLongitude(3);
        state.setStateCapitalLatitude(10);
        state.setStateCode(1);
        state.setStateDescription("ZWYko08aVmojtNUjmus2qxIEdMu0IjkoovXT92inwF7kPaZInv");
        state.setStateCodeChar2("11d8xaWRMCIY0k3qGWRHGW0Qauqh48dX");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityFlag("BUzNh8cjxcJi8XEjSgx7FxB0IWwJG7tJGSELGuGPt2aSt03SXk");
        city.setCityDescription("14TveTw9QTo41OzEWTOWplF4KACxn7x7EY08hXv0eYQVQOiC5V");
        city.setCityLatitude(10);
        city.setCityName("f1gkAAtfLbqAC9v1jyT3vHU0ITrVR2x2pifrtnxwA1NJB26Nwi");
        city.setCityLongitude(6);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("uLqmyiO1whlDTAbgb0mkpw3rqieobZNO");
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("0bZS3C1bqaLtQtLhvilv2DkgafZlEt7DXTQxUPO5X0Hq4Gsvom");
        addresstype.setAddressTypeDesc("d5AzDpeYAg7cjXTK21UoGaBhysHqt3f37tufYRX6QNrmW7U8fR");
        addresstype.setAddressTypeIcon("CYWfSMfkrKgmXs19LNcivpFvP80tpUBVnzGUbh61riJFGqhUdB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLongitude("4RF5lYlSRwbG2LUjAtb5cIe7dQvjfWIgt0oRBFqFd28NmWAIpK");
        address.setLatitude("6QC4i9mv5bCocshjUHgkqZKU0g7snEqRvETid8L36O54iFdwjr");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("pckiLVmczSq");
        address.setAddress2("TfqMVhf9AqTJyNLrqebtAji706NvLGWfci7ixpmikceeTQpZrh");
        address.setAddress1("jE4WGdRprfji4jjxlYKE15psnNblFs3NS8AnEPZEQlUEy9eGKc");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("9lDfwE");
        address.setAddress3("gfSQnXyAGZt5M3R4M8fpQasZV8ECN72BwDJXrUqwzNpp2vzlPY");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setFailedLoginAttempts(1);
        login.setLoginId("FCB6dExPZ7paggLmP3nnmZWFao9haHTKBS8Yc50mX20qAbGJXH");
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        login.setServerAuthImage("aIzA4IyysMS0mYRR8ejpcu5GCv5uihjl");
        login.setServerAuthText("2OftjZ5L1zWfcgnv");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(5);
            login.setLoginId("mSAdNc6CVHA8VBut93kf4pRk8tm1n1d5ELX0ZDPAReReWcSp1Y");
            login.setServerAuthImage("SNg3Sc8IRAGpnZU5yj0l7W5m2WmFcYWR");
            login.setServerAuthText("jycKQU2kPnqpo9vb");
            login.setVersionId(1);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "sGZINWCb08dJNIF5NBCFqNbxr78ncM7JlOSMHryx17Gf88qTeoxh42GKU6jdAsAQPkHQHbdf2rKNmKUNXsE6RtBNoG9ihlD4xZ74CagSbx5T9Yab4aZwENU6QQonaKbj3OJ6yEyPCGQCUDYnGJbEUt8uVWrTYkOaOnh83djCzjSzfn03YyH5wTrp76jjyBKf1Jjgcx3mf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "dZXN34Qyd7SdyZDX70nzIUMADQSDL6yao"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "fZgEvrkf5qop7td6l"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
