package com.dianwang.paipai;

import com.teamdev.jexplorer.Browser;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


//这个例子说明通过swingnode嵌入canva貌似有问题！！！

public class Example extends Application {
    Browser browser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.browser = new com.teamdev.jexplorer.Browser();
        this.browser.navigate("www.baidu.com");

        final AwtInitializerTask awtInitializerTask = new AwtInitializerTask(() -> {
            JPanel jPanel = new JPanel(new BorderLayout());

            // this works
//            JTextField txt = new JTextField("Hello Test");
//            jPanel.add(txt);

            //this not
            Canvas canvas = new Canvas();
            canvas.setBackground(Color.RED);
            jPanel.add(canvas, BorderLayout.CENTER);
//            jPanel.add(browser, BorderLayout.CENTER);

            return jPanel;

        });

        SwingUtilities.invokeLater(awtInitializerTask);
        final SwingNode swingNode = new SwingNode();
        try {
            swingNode.setContent(awtInitializerTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();

        }

        Scene scene = new Scene(new BorderPane(swingNode), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String args[]) {
        launch(args);
    }

    public void stop() {
        this.browser.dispose();
    }

    private class AwtInitializerTask extends FutureTask<JComponent> {
        public AwtInitializerTask(Callable<JComponent> callable) {
            super(callable);
        }
    }

}

