package com.auts.lajitong.model.dao;
/**
 * 物品识别信息Model
 * @author zqh
 */
public class KnowObjModel {

    private int kid;
    //百科词条名
    private String knowName;
    //物品分类ID
    private int objType;

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public String getKnowName() {
		return knowName;
	}

	public void setKnowName(String knowName) {
		this.knowName = knowName;
	}

	public int getObjType() {
		return objType;
	}

	public void setObjType(int objType) {
		this.objType = objType;
	}

	@Override
	public String toString() {
		return "KnowObjModel{" +
				"kid=" + kid +
				", knowName='" + knowName + '\'' +
				", objType='" + objType + '\'' +
				'}';
	}
}
