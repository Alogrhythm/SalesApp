package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterfaceImpl;
import com.sales.app.shared.salesboundedcontext.sales.SalesData;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "25", comments = "Repository for SalesData Transaction table", complexity = Complexity.MEDIUM)
public class SalesDataRepositoryImpl extends SearchInterfaceImpl implements SalesDataRepository<SalesData> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<SalesData> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.SalesData> query = emanager.createQuery("select u from SalesData u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public SalesData save(SalesData entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<SalesData> save(List<SalesData> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.SalesData obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.sales.app.shared.salesboundedcontext.sales.SalesData s = emanager.find(com.sales.app.shared.salesboundedcontext.sales.SalesData.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(SalesData entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<SalesData> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.SalesData obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<SalesData> findByChannelId(String channelId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesData.findByChannelId");
        query.setParameter("channelId", channelId);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.SalesData> listOfSalesData = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findByChannelId", "Total Records Fetched = " + listOfSalesData.size());
        return listOfSalesData;
    }

    @Transactional
    public List<SalesData> findByReatilercode(String reatilercode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesData.findByReatilercode");
        query.setParameter("reatilercode", reatilercode);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.SalesData> listOfSalesData = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findByReatilercode", "Total Records Fetched = " + listOfSalesData.size());
        return listOfSalesData;
    }

    @Transactional
    public List<SalesData> findByMaterialcode(String materialcode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesData.findByMaterialcode");
        query.setParameter("materialcode", materialcode);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.SalesData> listOfSalesData = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findByMaterialcode", "Total Records Fetched = " + listOfSalesData.size());
        return listOfSalesData;
    }

    @Transactional
    public List<SalesData> findByBrandcode(String brandcode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesData.findByBrandcode");
        query.setParameter("brandcode", brandcode);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.SalesData> listOfSalesData = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findByBrandcode", "Total Records Fetched = " + listOfSalesData.size());
        return listOfSalesData;
    }

    @Transactional
    public List<SalesData> findByCategoryId(String categoryId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesData.findByCategoryId");
        query.setParameter("categoryId", categoryId);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.SalesData> listOfSalesData = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findByCategoryId", "Total Records Fetched = " + listOfSalesData.size());
        return listOfSalesData;
    }

    @Transactional
    public SalesData findById(Integer autoid) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SalesData.findById");
        query.setParameter("autoid", autoid);
        com.sales.app.shared.salesboundedcontext.sales.SalesData listOfSalesData = (com.sales.app.shared.salesboundedcontext.sales.SalesData) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SalesDataRepositoryImpl", "findById", "Total Records Fetched = " + listOfSalesData);
        return listOfSalesData;
    }
}
