package com.wah.ui;

import com.wah.model.FileMeta;
import com.wah.service.FileService;
import com.wah.task.FileScanner;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-25
 * Time: 22:44
 */
public class AppController implements Initializable {
    @FXML
    public GridPane rootPane;
    @FXML
    public Label srcDirectory;
    @FXML
    public TextField searchField;

    @FXML
    public TableView<FileMeta> fileTable;
    @FXML
    public TableColumn<FileMeta,String> nameColumn;
    @FXML
    public TableColumn<FileMeta,String> sizeColumn;
    @FXML
    public TableColumn<FileMeta,String> lastModifiedColumn;

    private final FileService fileService = new FileService();
    private final FileScanner fileScanner = new FileScanner();

    @FXML
    public void choose(MouseEvent mouseEvent) {
        //把文件搜索写成单线程
        DirectoryChooser chooser = new DirectoryChooser();
        File root = chooser.showDialog(rootPane.getScene().getWindow());
        if (root == null) {
            return;
        }
        Thread thread = new Thread(() -> {
            fileScanner.scan(root);
        });
        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //会在FXMLLoader.load执行时，实例化好AppController后调用
        //只要有输入，就会出现值
        StringProperty stringProperty = searchField.textProperty();
        stringProperty.addListener((observable, oldValue, newValue) -> {
            List<FileMeta> fileList = fileService.query(newValue.trim());
            Platform.runLater(() -> {
                fileTable.getItems().clear();
                fileTable.getItems().addAll(fileList);
            });
        });
    }
}
