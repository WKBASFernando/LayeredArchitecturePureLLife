module com.assignment.purelifewaterbottles {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens com.assignment.purelifewaterbottles.controller to javafx.fxml;
    exports com.assignment.purelifewaterbottles;
}