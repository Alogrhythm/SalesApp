package com.sales.app.server.repository.salesboundedcontext.sales;
import com.sales.app.server.repository.core.SearchInterfaceImpl;
import com.sales.app.shared.salesboundedcontext.sales.Channel;
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
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "22", comments = "Repository for Channel Master table Entity", complexity = Complexity.LOW)
public class ChannelRepositoryImpl extends SearchInterfaceImpl implements ChannelRepository<Channel> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Channel> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.sales.app.shared.salesboundedcontext.sales.Channel> query = emanager.createQuery("select u from Channel u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Channel save(Channel entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Channel> save(List<Channel> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.Channel obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SBCSA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.sales.app.shared.salesboundedcontext.sales.Channel s = emanager.find(com.sales.app.shared.salesboundedcontext.sales.Channel.class, id);
        emanager.remove(s);
        Log.out.println("SBCSA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Channel entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Channel> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.sales.app.shared.salesboundedcontext.sales.Channel obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SBCSA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public Channel findById(String channelId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Channel.findById");
        query.setParameter("channelId", channelId);
        com.sales.app.shared.salesboundedcontext.sales.Channel listOfChannel = (com.sales.app.shared.salesboundedcontext.sales.Channel) query.getSingleResult();
        Log.out.println("SBCSA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ChannelRepositoryImpl", "findById", "Total Records Fetched = " + listOfChannel);
        return listOfChannel;
    }
}
