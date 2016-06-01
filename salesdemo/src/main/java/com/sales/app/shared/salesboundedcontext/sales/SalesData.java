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
import javax.validation.constraints.Size;
import java.sql.Date;
import javax.validation.constraints.NotNull;
import com.athena.config.jsonHandler.CustomSqlDateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlDateJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.sales.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.sales.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_SalesData_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "25", comments = "SalesData", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "SalesData.findByChannelId", query = "select e from SalesData e where e.systemInfo.activeStatus=1 and e.channelId=:channelId"), @javax.persistence.NamedQuery(name = "SalesData.findByReatilercode", query = "select e from SalesData e where e.systemInfo.activeStatus=1 and e.reatilercode=:reatilercode"), @javax.persistence.NamedQuery(name = "SalesData.findByMaterialcode", query = "select e from SalesData e where e.systemInfo.activeStatus=1 and e.materialcode=:materialcode"), @javax.persistence.NamedQuery(name = "SalesData.findByBrandcode", query = "select e from SalesData e where e.systemInfo.activeStatus=1 and e.brandcode=:brandcode"), @javax.persistence.NamedQuery(name = "SalesData.findByCategoryId", query = "select e from SalesData e where e.systemInfo.activeStatus=1 and e.categoryId=:categoryId"), @javax.persistence.NamedQuery(name = "SalesData.findById", query = "select e from SalesData e where e.systemInfo.activeStatus=1 and e.autoid =:autoid") })
public class SalesData implements Serializable, CommonEntityInterface, Comparator<SalesData> {

    @Column(name = "retailername")
    @JsonProperty("retailername")
    @Size(min = 0, max = 64)
    private String retailername;

    @Column(name = "salesdate")
    @JsonProperty("salesdate")
    @NotNull
    @JsonSerialize(using = CustomSqlDateJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlDateJsonDeserializer.class)
    private Date salesdate;

    @Column(name = "salesmonth")
    @JsonProperty("salesmonth")
    @NotNull
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer salesmonth;

    @Column(name = "salesyear")
    @JsonProperty("salesyear")
    @NotNull
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer salesyear;

    @Column(name = "salesinvoicenbr")
    @JsonProperty("salesinvoicenbr")
    @NotNull
    @Size(min = 0, max = 64)
    private String salesinvoicenbr;

    @Column(name = "materialdescription")
    @JsonProperty("materialdescription")
    @Size(min = 1, max = 64)
    private String materialdescription;

    @Column(name = "branddescription")
    @JsonProperty("branddescription")
    @Size(max = 1)
    private String branddescription;

    @Column(name = "salesqty")
    @JsonProperty("salesqty")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double salesqty;

    @Column(name = "netsalesamt")
    @JsonProperty("netsalesamt")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double netsalesamt;

    @Column(name = "grosssalesamt")
    @JsonProperty("grosssalesamt")
    @NotNull
    @Min(-9223372000000000000L)
    @Max(9223372000000000000L)
    private Double grosssalesamt;

    @Transient
    private Integer primaryKey;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autoid")
    @JsonProperty("autoid")
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer autoid;

    @Column(name = "channelId")
    @JsonProperty("channelId")
    private String channelId;

    @Column(name = "reatilercode")
    @JsonProperty("reatilercode")
    private String reatilercode;

    @Column(name = "materialcode")
    @JsonProperty("materialcode")
    private String materialcode;

    @Column(name = "brandcode")
    @JsonProperty("brandcode")
    private String brandcode;

    @Column(name = "categoryId")
    @JsonProperty("categoryId")
    private String categoryId;

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
        this.retailername = _retailername;
    }

    public Date getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(Date _salesdate) {
        if (_salesdate != null) {
            this.salesdate = _salesdate;
        }
    }

    public Integer getSalesmonth() {
        return salesmonth;
    }

    public void setSalesmonth(Integer _salesmonth) {
        if (_salesmonth != null) {
            this.salesmonth = _salesmonth;
        }
    }

    public Integer getSalesyear() {
        return salesyear;
    }

    public void setSalesyear(Integer _salesyear) {
        if (_salesyear != null) {
            this.salesyear = _salesyear;
        }
    }

    public String getSalesinvoicenbr() {
        return salesinvoicenbr;
    }

    public void setSalesinvoicenbr(String _salesinvoicenbr) {
        if (_salesinvoicenbr != null) {
            this.salesinvoicenbr = _salesinvoicenbr;
        }
    }

    public String getMaterialdescription() {
        return materialdescription;
    }

    public void setMaterialdescription(String _materialdescription) {
        this.materialdescription = _materialdescription;
    }

    public String getBranddescription() {
        return branddescription;
    }

    public void setBranddescription(String _branddescription) {
        this.branddescription = _branddescription;
    }

    public Double getSalesqty() {
        return salesqty;
    }

    public void setSalesqty(Double _salesqty) {
        if (_salesqty != null) {
            this.salesqty = _salesqty;
        }
    }

    public Double getNetsalesamt() {
        return netsalesamt;
    }

    public void setNetsalesamt(Double _netsalesamt) {
        if (_netsalesamt != null) {
            this.netsalesamt = _netsalesamt;
        }
    }

    public Double getGrosssalesamt() {
        return grosssalesamt;
    }

    public void setGrosssalesamt(Double _grosssalesamt) {
        if (_grosssalesamt != null) {
            this.grosssalesamt = _grosssalesamt;
        }
    }

    public Integer getPrimaryKey() {
        return autoid;
    }

    public void setPrimaryKey(Integer _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public Integer _getPrimarykey() {
        return autoid;
    }

    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer _autoid) {
        this.autoid = _autoid;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String _channelId) {
        this.channelId = _channelId;
    }

    public String getReatilercode() {
        return reatilercode;
    }

    public void setReatilercode(String _reatilercode) {
        this.reatilercode = _reatilercode;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String _materialcode) {
        this.materialcode = _materialcode;
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String _brandcode) {
        this.brandcode = _brandcode;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String _categoryId) {
        this.categoryId = _categoryId;
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
    public int compare(SalesData object1, SalesData object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((salesinvoicenbr == null ? " " : salesinvoicenbr));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (autoid == null) {
            return super.hashCode();
        } else {
            return autoid;
        }
    }

    public boolean equals(Object obj) {
        try {
            com.sales.app.shared.salesboundedcontext.sales.SalesData other = (com.sales.app.shared.salesboundedcontext.sales.SalesData) obj;
            if (autoid == null) {
                return false;
            } else if (!autoid.equals(other.autoid)) {
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
