package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterfaceImpl;
import com.sales.app.shared.salesboundedcontext.sales.Brand;
import org.springframework.stereotype.Repository;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "21", comments = "Repository for Brand Master table Entity", complexity = Complexity.LOW)
public class BrandRepositoryImpl extends SearchInterfaceImpl implements BrandRepository<Brand> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Brand> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.Brand> query = emanager.createQuery("select u from Brand u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Brand save(Brand entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Brand> save(List<Brand> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.Brand obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.sales.app.shared.salesboundedcontext.sales.Brand s = emanager.find(com.sales.app.shared.salesboundedcontext.sales.Brand.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Brand entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Brand> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.Brand obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<Brand> findByCategoryId(String categoryId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Brand.findByCategoryId");
        query.setParameter("categoryId", categoryId);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.Brand> listOfBrand = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "findByCategoryId", "Total Records Fetched = " + listOfBrand.size());
        return listOfBrand;
    }

    @Transactional
    public Brand findById(String brandcode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Brand.findById");
        query.setParameter("brandcode", brandcode);
        com.sales.app.shared.salesboundedcontext.sales.Brand listOfBrand = (com.sales.app.shared.salesboundedcontext.sales.Brand) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "BrandRepositoryImpl", "findById", "Total Records Fetched = " + listOfBrand);
        return listOfBrand;
    }
}
