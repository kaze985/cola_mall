package com.lppnb.generator.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 后台资源表
 * @TableName ums_resource
 */
@Data
public class UmsResourceDO implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源URL
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 资源分类ID
     */
    private Long categoryId;

    private static final long serialVersionUID = 1L;
}