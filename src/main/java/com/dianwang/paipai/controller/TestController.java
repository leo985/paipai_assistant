package com.dianwang.paipai.controller;

import com.dianwang.paipai.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Created by leo on 2017/4/23.
 */
public class TestController extends AnchorPane {

    private Main application;




    public void processRight(ActionEvent event) {
        if (application == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("An exception occurred!");
        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea("hello world!!!!")));
        alert.showAndWait();

    }


    public void setApp(Main application){
        this.application = application;
    }
}
