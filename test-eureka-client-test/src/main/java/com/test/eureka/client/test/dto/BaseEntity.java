package com.test.eureka.client.test.dto;

import java.util.Date;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/1  18:02
 * Version: V1.0
 * Description: 基础实体类
 * ======================
 */
public class BaseEntity {

    private String id ;

    private Integer deleteFlag ;

    private String createBy ;

    private String updateBy ;

    private Date updateTiem ;

    private Date createTime ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTiem() {
        return updateTiem;
    }

    public void setUpdateTiem(Date updateTiem) {
        this.updateTiem = updateTiem;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
