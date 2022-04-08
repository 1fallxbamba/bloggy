module com.bloggy.bloggy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.bloggy.bloggy to javafx.fxml;
    opens com.bloggy.bloggy.pages.authentication.registration to javafx.fxml;
    opens com.bloggy.bloggy.pages.authentication.login to javafx.fxml;
    opens com.bloggy.bloggy.pages.home to javafx.fxml;
    exports com.bloggy.bloggy;
}