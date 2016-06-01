package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterfaceImpl;
import com.sales.app.shared.salesboundedcontext.sales.UserAccess;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "15", comments = "Repository for UserAccess Master table Entity", complexity = Complexity.LOW)
public class UserAccessRepositoryImpl extends SearchInterfaceImpl implements UserAccessRepository<UserAccess> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<UserAccess> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.UserAccess> query = emanager.createQuery("select u from UserAccess u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public UserAccess save(UserAccess entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<UserAccess> save(List<UserAccess> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.UserAccess obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.sales.app.shared.salesboundedcontext.sales.UserAccess s = emanager.find(com.sales.app.shared.salesboundedcontext.sales.UserAccess.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(UserAccess entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<UserAccess> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.UserAccess obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<UserAccess> findByUserId(String userId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserAccess.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.UserAccess> listOfUserAccess = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfUserAccess.size());
        return listOfUserAccess;
    }

    @Transactional
    public List<UserAccess> findByRegion(String region) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserAccess.findByRegion");
        query.setParameter("region", region);
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.UserAccess> listOfUserAccess = query.getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "findByRegion", "Total Records Fetched = " + listOfUserAccess.size());
        return listOfUserAccess;
    }

    @Transactional
    public UserAccess findById(Integer userAccessId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("UserAccess.findById");
        query.setParameter("userAccessId", userAccessId);
        com.sales.app.shared.salesboundedcontext.sales.UserAccess listOfUserAccess = (com.sales.app.shared.salesboundedcontext.sales.UserAccess) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessRepositoryImpl", "findById", "Total Records Fetched = " + listOfUserAccess);
        return listOfUserAccess;
    }
}
