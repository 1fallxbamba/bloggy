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

    opens com.bloggy.bloggy to javafx.fxml;
    opens com.bloggy.bloggy.pages.authentication.registration to javafx.fxml;
    exports com.bloggy.bloggy;
}