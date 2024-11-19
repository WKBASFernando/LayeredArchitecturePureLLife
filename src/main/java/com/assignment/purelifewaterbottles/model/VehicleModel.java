package com.assignment.purelifewaterbottles.model;

import com.assignment.purelifewaterbottles.dto.DriverAndVehicleDto;
import com.assignment.purelifewaterbottles.dto.VehicleDto;
import com.assignment.purelifewaterbottles.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public String getNextVehicleId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select vehicleId from vehicle order by vehicleId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("V%03d", newIdIndex);
        }
        return "V001";
    }

    public boolean saveVehicle(VehicleDto vehicleDto) throws SQLException {
        return CrudUtil.execute("insert into vehicle values (?,?,?)", vehicleDto.getVehicleId(), vehicleDto.getType(), vehicleDto.getVehicleNumber());
    }

    public ArrayList<DriverAndVehicleDto> getAllDriversAndVehicles() throws SQLException {
        ResultSet rst = CrudUtil.execute("select d.driverId, v.vehicleId, d.name, d.phoneNo, v.type, v.vehicleNumber, d.driver_fee from driver d join vehicle v on v.vehicleId = d.vehicleId");

        ArrayList<DriverAndVehicleDto> driverAndVehicleDtos = new ArrayList<>();

        while (rst.next()) {
            DriverAndVehicleDto driverAndVehicleDto = new DriverAndVehicleDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getNString(6), rst.getDouble(7));
            driverAndVehicleDtos.add(driverAndVehicleDto);
        }
        return driverAndVehicleDtos;
    }

    public boolean updateVehicle(VehicleDto vehicleDto) throws SQLException {
        return CrudUtil.execute("update vehicle set type=?, vehicleNumber=? where vehicleId=?", vehicleDto.getType(), vehicleDto.getVehicleNumber(), vehicleDto.getVehicleId());
    }

    public boolean deleteVehicle(String vehicleId) throws SQLException {
        return CrudUtil.execute("delete from vehicle where vehicleId=?", vehicleId);
    }
}
