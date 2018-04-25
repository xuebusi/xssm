package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.common.BeanValidators;
import com.xuebusi.xssm.common.JsonResult;
import com.xuebusi.xssm.common.ViewHint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Map;

/**
 * 控制器支持类
 * @author 学布斯
 * @version 2018-1-14
 */
public abstract class BaseController {

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 */
	public Map<String, String> beanValidator(Object object) {
        /*ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();*/
        try {
			BeanValidators.validate(validator, object);
		} catch (ConstraintViolationException ex) {
			//String message = BeanValidators.extractPropertyAndMessageAsString(ex, ",");
			Map<String, String> propertyAndMessage = BeanValidators.extractPropertyAndMessage(ex);
            return propertyAndMessage;
		}
		return null;
	}
	public String success() {
		return JsonResult.success();
	}

	public String success(Object data) {
		return JsonResult.success(ViewHint.SUCCESS, data);
	}

	public String success(ViewHint viewHint) {
		return JsonResult.success(viewHint);
	}

	public String success(ViewHint viewHint, Object data) {
		return JsonResult.success(viewHint, data);
	}

	public String error() {
		return JsonResult.error(ViewHint.ERROR);
	}

	public String error(Object data) {
		return JsonResult.error(ViewHint.ERROR, data);
	}

	public String error(ViewHint viewHint) {
		return JsonResult.error(viewHint);
	}

	public String error(ViewHint viewHint, Object data) {
		return JsonResult.error(viewHint, data);
	}

    public String paramError(Object data) {
        return JsonResult.error(ViewHint.PARAM_ERROR, data);
    }

}
