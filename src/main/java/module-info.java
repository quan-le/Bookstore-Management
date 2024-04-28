module com.example.bookstore_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;

    opens com.example.bookstore_management to javafx.fxml;
    exports com.example.bookstore_management;
}