package com.assignment.purelifewaterbottles.dao.custom;

import com.assignment.purelifewaterbottles.dao.CrudDAO;
import com.assignment.purelifewaterbottles.dto.DeliveryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryDAO extends CrudDAO<DeliveryDto> {
    ArrayList<String> getAllIds() throws SQLException;
    DeliveryDto find(String selectedDeliveryId) throws SQLException;
}
