/******************************************************************
 *
 *    Package:     com.company.platform.base.model.requestLimit
 *
 *    Filename:    RequestLimitByTimeModel.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.qpp.model.basic;


import com.qpp.model.result.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@SuppressWarnings("serial")
public class RequestLimitByTimeModel extends BaseModel {

    String key ;
    
    Integer times;

}
