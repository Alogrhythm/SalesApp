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
import com.sales.app.server.repository.organization.locationmanagement.AddressRepository;
import com.sales.app.shared.organization.locationmanagement.Address;
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
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        City city = new City();
        city.setCityFlag("rUv50Vu5LOBE9mXXEtGbsut8ArhXyL8uX8D9Hr4XnD9e3Mj6fc");
        city.setCityDescription("zEf7eqnAxPs9w4kWmhTZU9MY0JwYSlaxaB1bXQr2FhTd05SmF6");
        city.setCityLatitude(8);
        city.setCityName("7T3nAD1tmDa3Fq4VR7u0AhekQaSQ9jQnyCVTXAMyjZppM142wy");
        city.setCityLongitude(6);
        State state = new State();
        state.setStateCapital("rSIzMLa7k4zEQcQKzeYuCW3XoeseFeUyKvV5FJdKHUSe5tXc8S");
        state.setStateFlag("fqXyJcmoLaj2VbFWQpnIUqgpHaSy7lcj8FL55SclaSvZNe3KHc");
        state.setStateName("LIJkbHaDOe0kMlZcr1SZD6aRbCfXcwt2uN6EdhuzEpl45fO7eE");
        Country country = new Country();
        country.setCapitalLongitude(3);
        country.setCurrencySymbol("a2nDcfoMzWFnzHCmSbbVl9gyRIjUig8Q");
        country.setCountryCode2("JFk");
        country.setCountryName("w8jUP8f8a74hZxEEcmw8ED6cIdNKy6INnfYZDn9XQN1Unek93k");
        country.setIsoNumeric(360);
        country.setCurrencyCode("IO8");
        country.setCapital("dzSYNPwsafDJaxRwBsboS2mFINny0LUx");
        country.setCountryCode1("LHO");
        country.setCountryFlag("vsHu3Jqzle6Xfuu0tL2Ws0DdVcNFT9Gie5el38hAkQDr44qQev");
        country.setCapitalLatitude(6);
        country.setCurrencyName("AwxEcia8XfgjX8KrFLSVcy8Ch7E66cv0h3YcOvCT6wvamw7cQM");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapital("gLPSfFS8eTP9arhrPt9b9i4K4Nrivv60pr3khmAbbqyxsImjfe");
        state.setStateFlag("NodMZ5zTb7GWzrUIAzynBYeXIJ4xb8Kzqh2QUamHeuDWTvoH6Y");
        state.setStateName("OcPm07uFOAclHIHH4u1NBAAN7IGCpgipWzhHU2Zk0e3ftudxwp");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("VERe3MFD8GLmI4ADPiYNw9FTVWG3XxK2");
        state.setStateCapitalLongitude(8);
        state.setStateCapitalLatitude(3);
        state.setStateCode(2);
        state.setStateDescription("XGAZ0BuHeAcnbOsyDe6UFebFel4XjxrNqPKsD9cKUjzWR7Ir0d");
        state.setStateCodeChar2("xHfurS6gikIQOPIX94JkwAgFzV7WHK8Q");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityFlag("2NVtKcvLUfHy8gC4Su0bfOXZmiXayODSVfAmtshbIEQFQIeYsV");
        city.setCityDescription("7UnUqzPbls3dQPEp3WPTpu8AOBFWF0VWARi1GuZufrr3uIvXRZ");
        city.setCityLatitude(7);
        city.setCityName("7tp6JaheAYVtafsS2Utqmq2SOKuwfpItxdKgJkO3X1xeJ5tnTh");
        city.setCityLongitude(10);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("vNuAqJ6XcoknrFJeUrVUJrxyttw4EwcX");
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("FYmPRKwAg1dXHxg56phcZtW2B6Cbakhuv8DwLOVAQcEeXcrVde");
        addresstype.setAddressTypeDesc("rEQlG3rDTIzusEYEOt1LhTWmNhkKQivVtb1QLuOpKP2twmu5Cg");
        addresstype.setAddressTypeIcon("HL6F4ZjnfOoIRMckxo16vzXVwL8mRGvnhLkJ1N84GYjBmsSFAP");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setLongitude("QqJODiUONreAMtdmxBxOpcmMLMBGKUG9bf3QTgABQyCOnAtzRj");
        address.setLatitude("hfLhYGIFRtmKj5LN8PZScjByYy0rL0VI1jDKqRWPHSMRnhi2Ir");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("Lx6zz4qX4zL");
        address.setAddress2("Fz5mPeG1iKrobblYzKOac7gQGZNPctAZAn3IIXgWbdO5S5WrTe");
        address.setAddress1("6ERVcJtRjPMiN2l8n5ZCMaqwRaVl53NAIBy9us5EUMxqRCRc3V");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("jWpmVM");
        address.setAddress3("IC6tfshfn8GlVwTOVh9oT8wb6JivjKhCLrUaYKHnZiB8oJpKn3");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLongitude("pMwDQveYKDEzr1v4qCFJhSoWv2e76uFqbVCbnleT0N9rgHYX0G");
            address.setLatitude("A2Lwo3m5xfj4XO796XCzpx06sgEy6Mkf6gJTsxsxNv8eeMbBKr");
            address.setAddressLabel("s9onnN7QciX");
            address.setVersionId(1);
            address.setAddress2("iZqK6CmTtaxqDiHdbH5qWnH9EpQhHSvyF0hOIbRw1mMlW6xfkK");
            address.setAddress1("FeeOT71BCiowhp2R4iDf8vQG5tUoYYaiuOYaeH9QamLG8C57KD");
            address.setZipcode("ry2TMK");
            address.setAddress3("VM7R9PD9CpVBwnvncEJdcdjU01HVALCQVrFs7FBHiLly8CwRuC");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "0p6BLsSSJrLs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "dWkH4Es3foJDZt5JI6Y98ZOzEmsBFYnSwlFVWQqVU6SnuivRatZlgqZkh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "41KljQ6u3yPL0Z15ESliX5aISua2TM6N7NpNZlxuEuLP97jQ2kub6b315"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "d6pjbodeD1nPNlDw8dT22mwQmAzytDxVyBLmbjEiwKlkM9grNc5gDSGMu"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "RyprK7U"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "NGsPqf5CHu7P8F0IJgUcRX1i69FgNt2tfv6kIwZOjjz3ygmVol0YgaVLIwFoFvFxr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "3mtF5NR9eNgDENKe2ddOcM9830kmRxuPSUwbwHbLY1THjtN1rOYPU5Won3JDRfOo6"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
