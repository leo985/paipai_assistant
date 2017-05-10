package com.dianwang.paipai.controller;

import com.dianwang.paipai.Main;
import com.dianwang.paipai.constant.StradegyConstants;
import com.dianwang.paipai.model.StradegyItem;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import javafx.application.HostServices;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by leo on 2017/4/23.
 */
public class MainController extends AnchorPane {

    private Main application;

    @FXML
    private Button btnRight;

    @FXML
    private ComboBox ddlStradegy;

    @FXML
    private FlowPane stradegyContent;

    @FXML
   private BorderPane webview;


    private HostServices hostservices;

    public void setHostservices(HostServices hostservices) {
        this.hostservices = hostservices;
    }

    public void processRight(ActionEvent event) {
        if (application == null){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("An exception occurred!");
        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea("hello world!!!!")));
        alert.showAndWait();

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {

        List<StradegyItem> stradegys = loadStradegies();
        if(stradegys.size() > 0) {
            this.ddlStradegy.setPromptText(stradegys.get(0).getText());
        }
        this.ddlStradegy.setConverter(new StringConverter<StradegyItem>() {
            @Override
            public String toString(StradegyItem item) {
                return item.getText();
            }

            @Override
            public StradegyItem fromString(String itemStr) {
                System.out.println(itemStr);
                return null;
            }
        });
        this.ddlStradegy.setCellFactory(new Callback<ListView<StradegyItem>, ListCell<StradegyItem>>() {
            @Override
            public ListCell<StradegyItem> call(ListView<StradegyItem> param) {
                final ListCell<StradegyItem> cell = new ListCell<StradegyItem>() {
                    @Override
                    protected void updateItem(StradegyItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getText());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        this.ddlStradegy.getItems().addAll(stradegys);
        loadStradgeyContent("/page/stradegy/OneA.fxml");
        showWebContent();
        //init web view

//        BrowserView view = new BrowserView(browser);
//        webview.getChildren().add(view);

//        WebEngine webEngine =   web.getEngine();
//        webEngine.load("http://moni.51hupai.org/");


//
//        System.setProperty("teamdev.license.info", "true");
//        Browser browser = new Browser();
//        browser.navigate("http://moni.51hupai.org/");
//        JPanel panel = new JPanel();
//        panel.setSize(500,500);
//        panel.add(new JButton("hello world"));
//        panel.add(browser);
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                swingPanel.setContent(panel);
//            }
//        });
//        NativeInterface.runEventPump();

    }

    public void showWebContent() {
        System.setProperty("teamdev.license.info", "true");
        BrowserPreferences.setUserAgent("Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10136");
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        this.webview.setCenter(view);
        // Modify zoom level every time when main frame is loaded.
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    event.getBrowser().setZoomLevel(0.1);
                }
            }
        });
//        browser.loadURL("https://test.alltobid.com/");
//        browser.loadURL("http://moni.51hupai.org/");
        browser.loadURL("http://ini.sh-pp.com/flash.html");
        Bounds bounds = webview.localToScene(webview.getBoundsInLocal());
        System.out.println(bounds.getMinX());
        System.out.println(bounds.getMaxX());
        System.out.println(bounds.getMinY());
        System.out.println(bounds.getMaxY());



    }

    private void loadStradgeyContent(String fxml) throws IOException {
        Node ddl = stradegyContent.getChildren().get(0);
        stradegyContent.getChildren().clear();
        stradegyContent.getChildren().add(ddl);
        stradegyContent.getChildren().add(loadFxml(fxml));
        stradegyContent.requestFocus();
    }

//    public static JComponent createContent() {
//        JPanel contentPane = new JPanel(new BorderLayout());
//        JPanel webBrowserPanel = new JPanel(new BorderLayout());
//        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
//        final JWebBrowser webBrowser = new JWebBrowser();
//        webBrowser.navigate("http://moni.51hupai.org/");
//        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
//        contentPane.add(webBrowserPanel, BorderLayout.CENTER);
//        // Create an additional bar allowing to show/hide the menu bar of the web browser.
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
//        JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
//        menuBarCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
//            }
//        });
//        buttonPanel.add(menuBarCheckBox);
//        contentPane.add(buttonPanel, BorderLayout.SOUTH);
//        return contentPane;
//    }

    public void setApp(Main application){
        this.application = application;
    }

    private Node loadFxml(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainController.class.getResourceAsStream(path);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(path));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        return page;
    }

    private List<StradegyItem> loadStradegies() {
        List<StradegyItem> stradegys = new ArrayList<>();
        stradegys.add(StradegyItem.from("一次伏击A(一次自动出价)", StradegyConstants.STRADEGY_1A));
        stradegys.add(StradegyItem.from("一次伏击B(一次自动出价)",StradegyConstants.STRADEGY_1B));
        stradegys.add(StradegyItem.from("一次伏击C(一次多选出价)",StradegyConstants.STRADEGY_1C));
        stradegys.add(StradegyItem.from("一伏二补A（两次自动出价）", StradegyConstants.STRADEGY_2A));
        stradegys.add(StradegyItem.from("一伏二补B（两次自动出价）", StradegyConstants.STRAEGY_2B));
        stradegys.add(StradegyItem.from("手动出价", StradegyConstants.STRADEGY_2C));
        return stradegys;
    }

    public void onStradegyChanged(ActionEvent event) throws IOException {
        StradegyItem selected = (StradegyItem)((ComboBox) event.getTarget()).getValue();
        this.ddlStradegy.setPromptText(selected.getText());
        if(selected.getValue().equals(StradegyConstants.STRADEGY_1A)) {
            loadStradgeyContent("/page/stradegy/OneA.fxml");
        }
        else if(selected.getValue().equals(StradegyConstants.STRADEGY_1B)) {
            loadStradgeyContent("/page/stradegy/OneA.fxml");
        }
        else if(selected.getValue().equals(StradegyConstants.STRADEGY_1C)) {
            loadStradgeyContent("/page/stradegy/OneC.fxml");
        }
    }

    public static String getOpenCmd(String url) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("mac") >= 0) {
            return String.format("%s %s", "open", url);
        }
        if (os.indexOf("win") >= 0) {
            return String.format("%s %s", "explorer", url);
        }
        if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0) {
            return String.format("%s %s", "xdg-open", url);
        }
        throw new IOException("Unable to identify the OS");
    }

    public void loadWebContent(ActionEvent actionEvent) {

        if (nonNull(this.hostservices)) {
            try {
                hostservices.showDocument("http://moni.51hupai.org/");
            } catch (NullPointerException npe) {
                // service delegate can be null but there's no way to check it first so we have to catch the npe
//                .info("Unable to open url using HostServices, trying fallback");
                try {
                    Runtime.getRuntime().exec(getOpenCmd("http://moni.51hupai.org/"));
                } catch (IOException e) {
                    System.out.println("Unable to open the url");
                }
            }
        } else {
            System.out.println("Unable to open '{}', please copy and paste the url to your browser.");
        }
    }

}
