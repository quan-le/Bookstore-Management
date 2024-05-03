package com.example.Bookstore_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class DashboardController {

    @FXML
    private Button avaiableBook_addBtn;

    @FXML
    private AnchorPane avaiableBook_anchorPane;

    @FXML
    private TextField avaiableBook_bookAuthor;

    @FXML
    private TextField avaiableBook_bookGerne;

    @FXML
    private TextField avaiableBook_bookID;

    @FXML
    private TextField avaiableBook_bookTitle;

    @FXML
    private Button avaiableBook_clearBtn;

    @FXML
    private TableColumn<?, ?> avaiableBook_col_author;

    @FXML
    private TableColumn<?, ?> avaiableBook_col_bookID;

    @FXML
    private TableColumn<?, ?> avaiableBook_col_bookTitle;

    @FXML
    private TableColumn<?, ?> avaiableBook_col_genre;

    @FXML
    private TableColumn<?, ?> avaiableBook_col_price;

    @FXML
    private TableColumn<?, ?> avaiableBook_col_publishDate;

    @FXML
    private Button avaiableBook_deleteBtn;

    @FXML
    private ImageView avaiableBook_imgView;

    @FXML
    private Button avaiableBook_importBtn;

    @FXML
    private TextField avaiableBook_price;

    @FXML
    private DatePicker avaiableBook_publishDatePicker;

    @FXML
    private TextField avaiableBook_search;

    @FXML
    private TableView<?> avaiableBook_tableView;

    @FXML
    private Button avaiableBook_updateBtn;

    @FXML
    private Button avaiableBooks_btn;

    @FXML
    private AnchorPane dashboard_anchorPane;

    @FXML
    private Label dashboard_avaiableBook;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_customers;

    @FXML
    private LineChart<?, ?> dashboard_linechart;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private AnchorPane main_anchorPane;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private AnchorPane purchase_anchorPane;

    @FXML
    private ComboBox<?> purchase_bookID;

    @FXML
    private ComboBox<?> purchase_bookTitle;

    @FXML
    private Button purchase_btn;

    @FXML
    private TableColumn<?, ?> purchase_col_author;

    @FXML
    private TableColumn<?, ?> purchase_col_bookID;

    @FXML
    private TableColumn<?, ?> purchase_col_bookTitle;

    @FXML
    private TableColumn<?, ?> purchase_col_gerne;

    @FXML
    private TableColumn<?, ?> purchase_col_price;

    @FXML
    private TableColumn<?, ?> purchase_col_publishDate;

    @FXML
    private Label purchase_info_author;

    @FXML
    private Label purchase_info_bookID;

    @FXML
    private Label purchase_info_bookTitle;

    @FXML
    private Label purchase_info_date;

    @FXML
    private Label purchase_info_genre;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private TableView<?> purchase_tableView;

    @FXML
    private Label username;

    @FXML
    public void switchPanel(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_anchorPane.setVisible(true);
            avaiableBook_anchorPane.setVisible(false);
            purchase_anchorPane.setVisible(false);
        }
        if (event.getSource() == avaiableBooks_btn) {
            dashboard_anchorPane.setVisible(false);
            avaiableBook_anchorPane.setVisible(true);
            purchase_anchorPane.setVisible(false);
        }
        if (event.getSource() == purchase_btn) {
            dashboard_anchorPane.setVisible(false);
            avaiableBook_anchorPane.setVisible(false);
            purchase_anchorPane.setVisible(true);
        }
    }
}
