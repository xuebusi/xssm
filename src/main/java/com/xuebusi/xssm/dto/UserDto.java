package com.xuebusi.xssm.dto;

import com.xuebusi.xssm.common.validator.Price;
import com.xuebusi.xssm.common.validator.Status;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by 学布斯 on 2017/12/18.
 */
public class UserDto implements Serializable{
    private String id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 18, message = "用户名长度为6-18位")
    private String name;

    @NotBlank(message = "地址不能为空")
    private String address;

    private String age;

    @Length(min = 11, max = 11, message = "手机号码长度必须是11位")
    private String phone;

    @Status
    private String status;

    @Price
    private String price;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}