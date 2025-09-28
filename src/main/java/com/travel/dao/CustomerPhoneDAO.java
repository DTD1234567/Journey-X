package com.travel.dao;
import com.travel.model.CustomerPhone;
import java.util.List;
public interface CustomerPhoneDAO {
    void create(CustomerPhone p) throws Exception;
    void deleteByCustomerId(int customerId) throws Exception;
    List<CustomerPhone> findByCustomerId(int customerId) throws Exception;
}
