package com.sales.app.shared.salesboundedcontext.sales;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.sales.app.config.annotation.Complexity;
import com.sales.app.config.annotation.SourceCodeAuthorClass;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.sales.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.sales.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Retailer_M")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "21", comments = "Retailer", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Retailer.findByDistributorcode", query = "select e from Retailer e where e.systemInfo.activeStatus=1 and e.distributorcode=:distributorcode"), @javax.persistence.NamedQuery(name = "Retailer.findById", query = "select e from Retailer e where e.systemInfo.activeStatus=1 and e.retailercode =:retailercode") })
public class Retailer implements Serializable, CommonEntityInterface, Comparator<Retailer> {

    @Column(name = "retailername")
    @JsonProperty("retailername")
    @NotNull
    @Size(min = 0, max = 256)
    private String retailername;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "retailercode")
    @JsonProperty("retailercode")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 256)
    private String retailercode;

    @Column(name = "distributorcode")
    @JsonProperty("distributorcode")
    private String distributorcode;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getRetailername() {
        return retailername;
    }

    public void setRetailername(String _retailername) {
        if (_retailername != null) {
            this.retailername = _retailername;
        }
    }

    public String getPrimaryKey() {
        return retailercode;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return retailercode;
    }

    public String getRetailercode() {
        return retailercode;
    }

    public void setRetailercode(String _retailercode) {
        this.retailercode = _retailercode;
    }

    public String getDistributorcode() {
        return distributorcode;
    }

    public void setDistributorcode(String _distributorcode) {
        this.distributorcode = _distributorcode;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        System.out.println("Setting logged in user info for " + recordType);
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    @Override
    public int compare(Retailer object1, Retailer object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((retailername == null ? " " : retailername));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (retailercode == null) {
            return super.hashCode();
        } else {
            return retailercode.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.sales.app.shared.salesboundedcontext.sales.Retailer other = (com.sales.app.shared.salesboundedcontext.sales.Retailer) obj;
            if (retailercode == null) {
                return false;
            } else if (!retailercode.equals(other.retailercode)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
