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
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
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

@Table(name = "ast_Distributor_M")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "20", comments = "Distributor", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Distributor.findByRegioncode", query = "select e from Distributor e where e.systemInfo.activeStatus=1 and e.regioncode=:regioncode"), @javax.persistence.NamedQuery(name = "Distributor.findById", query = "select e from Distributor e where e.systemInfo.activeStatus=1 and e.distributorcode =:distributorcode") })
public class Distributor implements Serializable, CommonEntityInterface, Comparator<Distributor> {

    @Column(name = "distributorname")
    @JsonProperty("distributorname")
    @NotNull
    @Size(min = 0, max = 64)
    private String distributorname;

    @Column(name = "longitude")
    @JsonProperty("longitude")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double longitude;

    @Column(name = "lattitude")
    @JsonProperty("lattitude")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double lattitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "distributorcode")
    @JsonProperty("distributorcode")
    @GeneratedValue(generator = "UUIDGenerator")
    private String distributorcode;

    @Column(name = "regioncode")
    @JsonProperty("regioncode")
    private String regioncode;

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

    public String getDistributorname() {
        return distributorname;
    }

    public void setDistributorname(String _distributorname) {
        if (_distributorname != null) {
            this.distributorname = _distributorname;
        }
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double _longitude) {
        if (_longitude != null) {
            this.longitude = _longitude;
        }
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double _lattitude) {
        if (_lattitude != null) {
            this.lattitude = _lattitude;
        }
    }

    public String getPrimaryKey() {
        return distributorcode;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return distributorcode;
    }

    public String getDistributorcode() {
        return distributorcode;
    }

    public void setDistributorcode(String _distributorcode) {
        this.distributorcode = _distributorcode;
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String _regioncode) {
        this.regioncode = _regioncode;
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
    public int compare(Distributor object1, Distributor object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((distributorname == null ? " " : distributorname) + ",");
        sb.append((longitude == null ? " " : longitude) + ",");
        sb.append((lattitude == null ? " " : lattitude));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (distributorcode == null) {
            return super.hashCode();
        } else {
            return distributorcode.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.sales.app.shared.salesboundedcontext.sales.Distributor other = (com.sales.app.shared.salesboundedcontext.sales.Distributor) obj;
            if (distributorcode == null) {
                return false;
            } else if (!distributorcode.equals(other.distributorcode)) {
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
