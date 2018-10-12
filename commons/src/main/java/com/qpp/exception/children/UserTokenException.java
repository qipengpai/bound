package com.qpp.exception.children;

import com.qpp.exception.BusinessException;

/**
 * @author qipengpai
 * @Title: UserTokenException
 * @ProjectName bound
 * @Description: TODO (例如用户token异常)
 * @date 9:34 2018/10/12
 */
public class UserTokenException extends BusinessException {

    public UserTokenException(String code, String msgFormat) {
        super(code, msgFormat);
    }
}
