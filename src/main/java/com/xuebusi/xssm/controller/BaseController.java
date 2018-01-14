package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.common.BeanValidators;
import com.xuebusi.xssm.common.JsonResult;
import com.xuebusi.xssm.common.ViewHint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

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
	private Validator validator;
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 */
	public String beanValidator(Object object) {
		try {
			BeanValidators.validate(validator, object);
		} catch (ConstraintViolationException ex) {
			String message = BeanValidators.extractPropertyAndMessageAsString(ex, ",");
			return message;
		}
		return null;
	}
	public String success() {
		return JsonResult.success(ViewHint.SUCCESS);
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

}
