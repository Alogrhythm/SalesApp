package com.sales.app.server.service.salesboundedcontext.sales;
import com.sales.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.sales.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.sales.app.server.repository.salesboundedcontext.sales.SalesDataRepository;
import com.sales.app.shared.salesboundedcontext.sales.SalesData;
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
import com.sales.app.shared.salesboundedcontext.sales.Material;
import com.sales.app.server.repository.salesboundedcontext.sales.MaterialRepository;
import com.sales.app.shared.salesboundedcontext.sales.Brand;
import com.sales.app.server.repository.salesboundedcontext.sales.BrandRepository;
import com.sales.app.shared.salesboundedcontext.sales.Category;
import com.sales.app.server.repository.salesboundedcontext.sales.CategoryRepository;
import com.sales.app.shared.salesboundedcontext.sales.Retailer;
import com.sales.app.server.repository.salesboundedcontext.sales.RetailerRepository;
import com.sales.app.shared.salesboundedcontext.sales.Distributor;
import com.sales.app.server.repository.salesboundedcontext.sales.DistributorRepository;
import com.sales.app.shared.salesboundedcontext.sales.SalesRegion;
import com.sales.app.server.repository.salesboundedcontext.sales.SalesRegionRepository;
import com.sales.app.shared.salesboundedcontext.sales.Channel;
import com.sales.app.server.repository.salesboundedcontext.sales.ChannelRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class SalesDataTestCase extends EntityTestCriteria {

    @Autowired
    private SalesDataRepository<SalesData> salesdataRepository;

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

    private SalesData createSalesData(Boolean isSave) throws Exception {
        Material material = new Material();
        material.setMaterialdesc("eCz3QHPrzylapUxQEuytFcTTg7WDIer76cyMO6j7mYw4k8LOcw");
        Brand brand = new Brand();
        Category category = new Category();
        category.setCategoryInformation("4boTQTXMeLVwVerbLoHrxPSfFUOdPC64BQKl4Qa1VPvtfjIlXI");
        Category CategoryTest = new Category();
        if (isSave) {
            CategoryTest = categoryRepository.save(category);
            map.put("CategoryPrimaryKey", category._getPrimarykey());
        }
        brand.setCategoryId((java.lang.String) CategoryTest._getPrimarykey()); /* ******Adding refrenced table data */
        brand.setBranddesc("SaEqnUXfIh8NafA05GhUedLfoVt1gUrJAcaxEqPl1R40MEP2UD");
        Brand BrandTest = new Brand();
        if (isSave) {
            BrandTest = brandRepository.save(brand);
            map.put("BrandPrimaryKey", brand._getPrimarykey());
        }
        material.setMaterialdesc("SDKmQI6aIcgzOAyFniMiC2gZpAlEM1SsonJ6hViYEL2Zlj4e4Z");
        material.setBrandcode((java.lang.String) BrandTest._getPrimarykey()); /* ******Adding refrenced table data */
        Material MaterialTest = new Material();
        if (isSave) {
            MaterialTest = materialRepository.save(material);
            map.put("MaterialPrimaryKey", material._getPrimarykey());
        }
        Retailer retailer = new Retailer();
        retailer.setRetailername("QC8sig8W1A1CirREOdzYpDMLhwxyg4xa0w00vaoGha2FEZsObR");
        Distributor distributor = new Distributor();
        distributor.setLattitude(8900.0d);
        distributor.setDistributorname("rF5yFq8TWt9VdEB98xKk76PyfgM8h4G0Gd1Av09wMud7FBcxel");
        SalesRegion salesregion = new SalesRegion();
        salesregion.setRegionname("YQmieFxsZdLrWmFxQ6dfImUQc2qkSEEDgz4MxC8zB0FpY7Vn0a");
        SalesRegion SalesRegionTest = new SalesRegion();
        if (isSave) {
            SalesRegionTest = salesregionRepository.save(salesregion);
            map.put("SalesRegionPrimaryKey", salesregion._getPrimarykey());
        }
        distributor.setLattitude(-4100.0d);
        distributor.setDistributorname("wr58Tl7KqbcmAyUuJg8mahfsOKzkaU94iChU3rRhvvEvGD658b");
        distributor.setRegioncode((java.lang.String) SalesRegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        distributor.setLongitude(-6400.0d);
        Distributor DistributorTest = new Distributor();
        if (isSave) {
            DistributorTest = distributorRepository.save(distributor);
            map.put("DistributorPrimaryKey", distributor._getPrimarykey());
        }
        retailer.setRetailername("GV3uSoqaR3iM61ZgC677tPUspnFRxg931nNstDfHbYtfnIeaUR");
        retailer.setDistributorcode((java.lang.String) DistributorTest._getPrimarykey()); /* ******Adding refrenced table data */
        Retailer RetailerTest = new Retailer();
        if (isSave) {
            RetailerTest = retailerRepository.save(retailer);
            map.put("RetailerPrimaryKey", retailer._getPrimarykey());
        }
        Channel channel = new Channel();
        channel.setChannelInformation("q4fmXkC2GtxCgN2Q8MTq2PB7t45NGIlEo6ipdwYjtkAq9nogdX");
        Channel ChannelTest = new Channel();
        if (isSave) {
            ChannelTest = channelRepository.save(channel);
            map.put("ChannelPrimaryKey", channel._getPrimarykey());
        }
        SalesData salesdata = new SalesData();
        salesdata.setMaterialcode((java.lang.String) MaterialTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setNetsalesamt(6700.0d);
        salesdata.setSalesqty(4200.0d);
        salesdata.setBrandcode((java.lang.String) BrandTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setSalesmonth(2147483647);
        salesdata.setRetailername("ZiRtWDGLFIJrxdfsfFX3RRs5kt4hcJnbK2i7t4stOMc7lVQhxn");
        salesdata.setGrosssalesamt(3500.0d);
        salesdata.setReatilercode((java.lang.String) RetailerTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setSalesyear(2147483647);
        salesdata.setChannelId((java.lang.String) ChannelTest._getPrimarykey()); /* ******Adding refrenced table data */
        salesdata.setMaterialdescription("UU7fD5W7aIdiyViUUObsXt37nAkrBnjugekj6e0FNW4Xtf0x7U");
        salesdata.setSalesinvoicenbr("uijKvspnMTmr6mBnUFHXWjotR5HgdO7tLKyiVmAkYIzOlfeE6m");
        salesdata.setBranddescription("l");
        salesdata.setCategoryId((java.lang.String) CategoryTest._getPrimarykey());
        salesdata.setSalesdate(new java.sql.Date(123456789));
        salesdata.setEntityValidator(entityValidator);
        return salesdata;
    }

    @Test
    public void test1Save() {
        try {
            SalesData salesdata = createSalesData(true);
            salesdata.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            salesdata.isValid();
            salesdataRepository.save(salesdata);
            map.put("SalesDataPrimaryKey", salesdata._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private MaterialRepository<Material> materialRepository;

    @Autowired
    private BrandRepository<Brand> brandRepository;

    @Autowired
    private CategoryRepository<Category> categoryRepository;

    @Autowired
    private RetailerRepository<Retailer> retailerRepository;

    @Autowired
    private DistributorRepository<Distributor> distributorRepository;

    @Autowired
    private SalesRegionRepository<SalesRegion> salesregionRepository;

    @Autowired
    private ChannelRepository<Channel> channelRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            SalesData salesdata = salesdataRepository.findById((java.lang.Integer) map.get("SalesDataPrimaryKey"));
            salesdata.setNetsalesamt(-9700.0d);
            salesdata.setSalesqty(9360.0d);
            salesdata.setSalesmonth(2147483647);
            salesdata.setRetailername("Oxsp1n52R7gMe38piNXeAL2KF6cZPEjZJLrBE2P4EWaWaJjhNu");
            salesdata.setGrosssalesamt(2800.0d);
            salesdata.setSalesyear(2147483647);
            salesdata.setMaterialdescription("aebw9kxAEIPhMGCfxXCFLST601lQvw5s4d3glujv2PzoOSbnqy");
            salesdata.setVersionId(1);
            salesdata.setSalesinvoicenbr("s7RnkFf2GFamoeLki6iXvjjriitMHh9Nqlxd2xukq6yumzqBbl");
            salesdata.setBranddescription("s");
            salesdata.setSalesdate(new java.sql.Date(123456789));
            salesdata.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            salesdataRepository.update(salesdata);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBymaterialcode() {
        try {
            java.util.List<SalesData> listofmaterialcode = salesdataRepository.findByMaterialcode((java.lang.String) map.get("MaterialPrimaryKey"));
            if (listofmaterialcode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBybrandcode() {
        try {
            java.util.List<SalesData> listofbrandcode = salesdataRepository.findByBrandcode((java.lang.String) map.get("BrandPrimaryKey"));
            if (listofbrandcode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            salesdataRepository.findById((java.lang.Integer) map.get("SalesDataPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByreatilercode() {
        try {
            java.util.List<SalesData> listofreatilercode = salesdataRepository.findByReatilercode((java.lang.String) map.get("RetailerPrimaryKey"));
            if (listofreatilercode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBychannelId() {
        try {
            java.util.List<SalesData> listofchannelId = salesdataRepository.findByChannelId((java.lang.String) map.get("ChannelPrimaryKey"));
            if (listofchannelId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycategoryId() {
        try {
            java.util.List<SalesData> listofcategoryId = salesdataRepository.findByCategoryId((java.lang.String) map.get("CategoryPrimaryKey"));
            if (listofcategoryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("SalesDataPrimaryKey"));
            salesdataRepository.delete((java.lang.Integer) map.get("SalesDataPrimaryKey")); /* Deleting refrenced data */
            channelRepository.delete((java.lang.String) map.get("ChannelPrimaryKey")); /* Deleting refrenced data */
            retailerRepository.delete((java.lang.String) map.get("RetailerPrimaryKey")); /* Deleting refrenced data */
            distributorRepository.delete((java.lang.String) map.get("DistributorPrimaryKey")); /* Deleting refrenced data */
            salesregionRepository.delete((java.lang.String) map.get("SalesRegionPrimaryKey")); /* Deleting refrenced data */
            materialRepository.delete((java.lang.String) map.get("MaterialPrimaryKey")); /* Deleting refrenced data */
            brandRepository.delete((java.lang.String) map.get("BrandPrimaryKey")); /* Deleting refrenced data */
            categoryRepository.delete((java.lang.String) map.get("CategoryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateSalesData(EntityTestCriteria contraints, SalesData salesdata) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            salesdata.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            salesdataRepository.save(salesdata);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "retailername", "OxuPHoFXgN51EEpJQGEsyp4hMb3UYMevsrU9Mdl3I91CU5BiL7S0AqpPgaKMCb4up"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 2, "salesdate", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "salesmonth", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "salesyear", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "salesinvoicenbr", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "salesinvoicenbr", "FzMAQDPJb1hT6EekFlkY2uvVnYtCz1ZtsTDdTQy1XLRAuRJKQprevR1M6naX3USUj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "materialdescription", "XHsQVJ5JLDSnRG7GAQxk1132Bs1coc8Od6g5d9lAVgfkxgzCgu6WYe6Z4jK54DHXM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "branddescription", "p8X"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 9, "salesqty", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "salesqty", 1.4153474510725399E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "netsalesamt", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "netsalesamt", 1.2697358614492054E19d));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "grosssalesamt", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "grosssalesamt", 1.264627685787516E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                SalesData salesdata = createSalesData(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = salesdata.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        salesdata.setRetailername(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 6:
                        salesdata.setSalesinvoicenbr(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 7:
                        salesdata.setMaterialdescription(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 8:
                        salesdata.setBranddescription(contraints.getNegativeValue().toString());
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 9:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 10:
                        salesdata.setSalesqty(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 12:
                        salesdata.setNetsalesamt(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(salesdata, null);
                        validateSalesData(contraints, salesdata);
                        failureCount++;
                        break;
                    case 14:
                        salesdata.setGrosssalesamt(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateSalesData(contraints, salesdata);
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
