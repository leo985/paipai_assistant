/*
 * Copyright (c) 2012, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.dianwang.paipai.controller;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import com.dianwang.paipai.Main;
import com.dianwang.paipai.SwtBrowserCanva;
import com.teamdev.jexplorer.Browser;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Login Controller.
 */
public class Test1Controller extends FlowPane {

//    @FXML // ResourceBundle that was given to the FXMLLoader
//    private ResourceBundle resources;
//    @FXML // URL location of the FXML file that was given to the FXMLLoader
//    private URL location;
//    @FXML
//    TextField userId;
//    @FXML
//    PasswordField password;
//    @FXML
//    Button login;
    @FXML
    SwingNode webview;


    private Main application;
    
    
    public void setApp(Main application){
        this.application = application;
    }
    
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws ExecutionException, InterruptedException {
        createSwingContent(webview);
    }



    private class AwtInitializerTask extends FutureTask<JPanel> {
        public AwtInitializerTask(Callable<JPanel> callable) {
            super(callable);
        }
    }



    private void createSwingContent(final SwingNode swingNode) throws ExecutionException, InterruptedException {

        final AwtInitializerTask awtInitializerTask = new AwtInitializerTask(() -> {
            JPanel jPanel = new JPanel();
            Browser browser = new Browser();
            browser.setBounds(50,50,300,300);
            browser.setSize(500,500);
            browser.navigate("http://moni.51hupai.org/");
            jPanel.add(browser);
            jPanel.add(new JButton("I am a JButton"));
            return jPanel;
        });
        SwingUtilities.invokeLater(awtInitializerTask);
        swingNode.setContent(awtInitializerTask.get());
    }

    public static SwtBrowserCanva createFromContent() {
        final SwtBrowserCanva browserCanvas = new SwtBrowserCanva();
        if (browserCanvas.initialise()) {
            // ...navigate to the desired URL
            browserCanvas.setUrl("http://moni.51hupai.org/");
        }
        else {
            System.out.println("Failed to initialise browser");
        }
        return browserCanvas;
    }

    public static JComponent createContent() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setSize(500,500);
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
        final JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.navigate("http://moni.51hupai.org/");
        webBrowser.setBarsVisible(true);
        webBrowser.setBounds(0,0,500,500);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        contentPane.add(webBrowserPanel, BorderLayout.CENTER);
        // Create an additional bar allowing to show/hide the menu bar of the web browser.
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
//        JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
//        menuBarCheckBox.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                webBrowser.setMenuBarVisible(e.getStateChange() == ItemEvent.SELECTED);
//            }
//        });
//        buttonPanel.add(menuBarCheckBox);
//        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        return contentPane;
    }

//    public void processLogin(ActionEvent event) {
//        if (application == null){
//            // We are running in isolated FXML, possibly in Scene Builder.
//            // NO-OP.
//            errorMessage.setText("Hello " + userId.getText());
//        } else {
//            if (!application.userLogging(userId.getText(), password.getText())){
//                errorMessage.setText("Unknown user " + userId.getText());
//            }
//        }
//    }


}
