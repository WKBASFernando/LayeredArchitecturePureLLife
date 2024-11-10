module com.assignment.purelifewaterbottles {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires javafx.base;

    opens com.assignment.purelifewaterbottles.controller to javafx.fxml;
    exports com.assignment.purelifewaterbottles;
    opens com.assignment.purelifewaterbottles.dto.tm to javafx.base;
}