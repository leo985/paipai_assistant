package com.dianwang.paipai.controller;

import com.dianwang.paipai.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.joda.time.DateTime;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leo on 2017/4/23.
 */
public class DownTimeController extends FlowPane {

    private Main application;

    @FXML
    private TextArea hotmessage;

    private Timer timer;


    public void processRight(ActionEvent event) {

    }

    @FXML
    void initialize() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                hotmessage.setText(hotmessage.getText()+"\r\n hello world!");
            }
        }, DateTime.now().toDate(),1000);
    }


    public void setApp(Main application){
        this.application = application;
    }
}
