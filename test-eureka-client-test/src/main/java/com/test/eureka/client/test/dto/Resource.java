package com.test.eureka.client.test.dto;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/1  18:00
 * Version: V1.0
 * Description: 资源
 * ======================
 */
public class Resource {
    private String id ;

    private String name ;

    private String type ;

    private String url ;

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
