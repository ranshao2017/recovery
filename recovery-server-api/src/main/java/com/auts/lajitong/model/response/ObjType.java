package com.auts.lajitong.model.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * 物品分类Model
 * @author zqh
 */
public class ObjType {

    private int oid;
	@JSONField(name = "object_name")
    private String objectName;
    private int unitPrice;
	private String remark;
	private String s_unit_price;

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

    public String getS_unit_price() {
        return new BigDecimal(this.unitPrice).movePointLeft(2).toString();
    }

    public void setS_unit_price(String s_unit_price) {
        this.s_unit_price = s_unit_price;
    }

    @Override
    public String toString() {
        return "ObjType{" +
                "oid=" + oid +
                ", objectName='" + objectName + '\'' +
                ", unitPrice=" + unitPrice +
                ", remark='" + remark + '\'' +
                ", s_unit_price='" + s_unit_price + '\'' +
                '}';
    }
}
