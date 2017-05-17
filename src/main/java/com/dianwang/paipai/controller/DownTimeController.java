package com.dianwang.paipai.controller;

import com.dianwang.paipai.DowTimeContent;
import com.dianwang.paipai.Main;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import java.util.List;
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

    private StringProperty text = new SimpleStringProperty();

    private DowTimeContent dowTimeContent = new DowTimeContent();

    private static final String msgTemplate = "%s - 最高价：【%s】 伏击价：【%s】 相差 %s 元 强制还有 %s 秒";

    private static final String STOPTIME = "08:46:50";


    public void processRight(ActionEvent event) {

    }

    @FXML
    void initialize() {
        text.setValue("");
        text.addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                hotmessage.selectPositionCaret(hotmessage.getLength());
                hotmessage.deselect();
            }
        });
        hotmessage.textProperty().bind(text);
        hotmessage.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                dowTimeContent.addContent(String.format(msgTemplate,DateTime.now().toString("HH:mm:ss"),86500,86900,400,5.4));
                text.setValue(dowTimeContent.render());
                hotmessage.setScrollTop(Double.MAX_VALUE);
                if(DateTime.now().toString("HH:mm:ss").equals(STOPTIME)) {
                    timer.cancel();
                   Stage stage = (Stage) hotmessage.getParent().getScene().getWindow();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.close();
                        }
                    });
                }
            }
        }, DateTime.now().toDate(), 100);
    }


    public void setApp(Main application) {
        this.application = application;
    }
}
