package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.DeliveryDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryModel {
    public String getNextDeliveryId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Fetch the last delivery ID
            if (lastId != null && lastId.startsWith("DE")) {  // Ensure the ID has the "DE" prefix
                String substring = lastId.substring(2);  // Extract the numeric part (after "DE")

                try {
                    int i = Integer.parseInt(substring);  // Convert numeric part to integer
                    int newIdIndex = i + 1;  // Increment the index
                    return String.format("DE%03d", newIdIndex);  // Format with 3 digits, e.g., DE002
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid ID format: " + lastId, e); // Handle parsing error
                }
            } else {
                throw new SQLException("Invalid ID format or missing prefix 'DE': " + lastId);
            }
        }
        return "DE001";  // Default ID if no records found
    }

    public boolean saveDelivery(DeliveryDto deliveryDto) throws SQLException {
        return CrudUtil.execute("insert into delivery values (?,?,?,?)", deliveryDto.getDeliveryId(), deliveryDto.getDriverId(), deliveryDto.getLocation(), deliveryDto.getDelivery_fee());
    }

    public ArrayList<DeliveryDto> getAllDeliveries() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from delivery");

        ArrayList<DeliveryDto> deliveryDtos = new ArrayList<>();

        while (rst.next()) {
            DeliveryDto deliveryDto = new DeliveryDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
            deliveryDtos.add(deliveryDto);
        }
        return deliveryDtos;
    }

    public boolean updateDelivery(DeliveryDto deliveryDto) throws SQLException {
        return CrudUtil.execute("update delivery set driverId=?, location=?, delivery_fee=? where deliveryId=?", deliveryDto.getDriverId(), deliveryDto.getLocation(), deliveryDto.getDelivery_fee(), deliveryDto.getDeliveryId());
    }

    public boolean deleteDelivery(String deliveryId) throws SQLException {
        return CrudUtil.execute("delete from delivery where deliveryId=?", deliveryId);
    }

    public ArrayList<String> getAllDeliveryIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select deliveryId from delivery");

        ArrayList<String> deliveryIds = new ArrayList<>();

        while (rst.next()) {
            deliveryIds.add(rst.getString(1));
        }

        return deliveryIds;
    }

    public DeliveryDto findByDeliveryId(String selectedDeliveryId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from delivery where deliveryId=?", selectedDeliveryId);

        if (rst.next()) {
            return new DeliveryDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
        }
        return null;
    }
}
