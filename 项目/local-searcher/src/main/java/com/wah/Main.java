package com.wah;

import com.wah.service.DBService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:32
 */
public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        URL url = Main.class.getClassLoader().getResource("app.fxml");
        if (url == null) {
            throw new RuntimeException("app.fxml 文件没有找到");
        }
        Parent root =  FXMLLoader.load(url);
        Scene scene = new Scene(root);

        primaryStage.setTitle("本地文件搜索工具");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(900);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        //数据库初始化
        DBService service = new DBService();
        service.init();
        launch(args);
    }
}
