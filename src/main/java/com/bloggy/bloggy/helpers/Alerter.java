package com.bloggy.bloggy.helpers;

import javafx.scene.control.Alert;

public class Alerter {

    public static void showMessage(String title, String header, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(message);

        a.show();
    }

}
