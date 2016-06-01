package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterfaceImpl;
import com.sales.app.shared.salesboundedcontext.sales.Material;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "19", comments = "Repository for Material Master table Entity", complexity = Complexity.LOW)
public class MaterialRepositoryImpl extends SearchInterfaceImpl implements MaterialRepository<Material> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Material> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.Material> query = emanager.createQuery("select u from Material u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Material save(Material entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Material> save(List<Material> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.Material obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.sales.app.shared.salesboundedcontext.sales.Material s = emanager.find(com.sales.app.shared.salesboundedcontext.sales.Material.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Material entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Material> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.Material obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<Material> findByBrandcode(String brandcode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Material.findByBrandcode");
        query.setParameter("brandcode", brandcode);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.Material> listOfMaterial = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "findByBrandcode", "Total Records Fetched = " + listOfMaterial.size());
        return listOfMaterial;
    }

    @Transactional
    public Material findById(String materialcode) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Material.findById");
        query.setParameter("materialcode", materialcode);
        com.sales.app.shared.salesboundedcontext.sales.Material listOfMaterial = (com.sales.app.shared.salesboundedcontext.sales.Material) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "MaterialRepositoryImpl", "findById", "Total Records Fetched = " + listOfMaterial);
        return listOfMaterial;
    }
}
