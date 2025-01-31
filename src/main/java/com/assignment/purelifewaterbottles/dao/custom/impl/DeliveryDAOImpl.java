package com.assignment.purelifewaterbottles.dao.custom.impl;

import com.assignment.purelifewaterbottles.dao.custom.DeliveryDAO;
import com.assignment.purelifewaterbottles.dto.DeliveryDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
//    public String getNextId() throws SQLException {
//        ResultSet rst = CrudUtil.execute("SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1");
//
//        if (rst.next()) {
//            String lastId = rst.getString(1);
//            if (lastId != null && lastId.startsWith("DE")) {
//                String substring = lastId.substring(2);
//
//                try {
//                    int i = Integer.parseInt(substring);
//                    int newIdIndex = i + 1;
//                    return String.format("DE%03d", newIdIndex);
//                } catch (NumberFormatException e) {
//                    throw new SQLException("Invalid ID format: " + lastId, e);
//                }
//            } else {
//                throw new SQLException("Invalid ID format or missing prefix 'DE': " + lastId);
//            }
//        }
//        return "DE001";
//    }

    public boolean save(DeliveryDto deliveryDto) throws SQLException {
        return CrudUtil.execute("insert into delivery values (?,?,?,?)", deliveryDto.getDeliveryId(), deliveryDto.getDriverId(), deliveryDto.getLocation(), deliveryDto.getDelivery_fee());
    }

    public ArrayList<DeliveryDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from delivery");

        ArrayList<DeliveryDto> deliveryDtos = new ArrayList<>();

        while (rst.next()) {
            DeliveryDto deliveryDto = new DeliveryDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
            deliveryDtos.add(deliveryDto);
        }
        return deliveryDtos;
    }

    public boolean update(DeliveryDto deliveryDto) throws SQLException {
        return CrudUtil.execute("update delivery set driverId=?, location=?, delivery_fee=? where deliveryId=?", deliveryDto.getDriverId(), deliveryDto.getLocation(), deliveryDto.getDelivery_fee(), deliveryDto.getDeliveryId());
    }

    public boolean delete(String deliveryId) throws SQLException {
        return CrudUtil.execute("delete from delivery where deliveryId=?", deliveryId);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            if (lastId != null && lastId.startsWith("DE")) {
                String substring = lastId.substring(2);

                try {
                    int i = Integer.parseInt(substring);
                    int newIdIndex = i + 1;
                    return String.format("DE%03d", newIdIndex);
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid ID format: " + lastId, e);
                }
            } else {
                throw new SQLException("Invalid ID format or missing prefix 'DE': " + lastId);
            }
        }

        return "DE001";
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select deliveryId from delivery");

        ArrayList<String> deliveryIds = new ArrayList<>();

        while (rst.next()) {
            deliveryIds.add(rst.getString(1));
        }

        return deliveryIds;
    }

    public DeliveryDto find(String selectedDeliveryId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from delivery where deliveryId=?", selectedDeliveryId);

        if (rst.next()) {
            return new DeliveryDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
        }
        return null;
    }
}
