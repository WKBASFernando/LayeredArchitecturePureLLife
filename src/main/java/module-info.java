module com.assignment.purelifewaterbottles {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.assignment.purelifewaterbottles to javafx.fxml;
    exports com.assignment.purelifewaterbottles;
}