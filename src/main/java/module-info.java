module com.assignment.purelifewaterbottles {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires javafx.base;
    requires java.mail;
    requires net.sf.jasperreports.core;

    opens com.assignment.purelifewaterbottles.controller to javafx.fxml;
    exports com.assignment.purelifewaterbottles;
    opens com.assignment.purelifewaterbottles.dto.tm to javafx.base;
    opens com.assignment.purelifewaterbottles.dto to javafx.base;
    opens com.assignment.purelifewaterbottles.view.tdm to javafx.base;
}