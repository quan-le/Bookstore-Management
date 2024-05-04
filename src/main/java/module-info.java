module com.example.bookstore_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.Bookstore_management to javafx.fxml;
    exports com.example.Bookstore_management;
    //exports Theory to javafx.graphics;
    opens Theory to javafx.fxml, javafx.graphics;
    exports Theory;

}