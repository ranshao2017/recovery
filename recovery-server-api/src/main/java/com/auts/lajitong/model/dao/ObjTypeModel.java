package com.auts.lajitong.model.dao;
/**
 * 物品分类Model
 * @author zqh
 */
public class ObjTypeModel {

    private int oid;
    private String objectName;
    private int unitPrice;
	private String remark;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ObjTypeModel{" +
				"oid=" + oid +
				", objectName='" + objectName + '\'' +
				", unitPrice=" + unitPrice +
				", remark='" + remark + '\'' +
				'}';
	}
}
