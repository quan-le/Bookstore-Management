package com.example.Bookstore_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.sql.Statement;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.Connection;


public class DashboardController {

    @FXML
    private Button availableBook_addBtn;

    @FXML
    private AnchorPane availableBook_anchorPane;

    @FXML
    private TextField availableBook_bookAuthor;

    @FXML
    private TextField availableBook_bookGenre;

    @FXML
    private TextField availableBook_bookID;

    @FXML
    private TextField availableBook_bookTitle;

    @FXML
    private Button availableBook_clearBtn;

    @FXML
    private TableColumn<bookData, String> availableBook_col_author;

    @FXML
    private TableColumn<bookData, String> availableBook_col_bookID;

    @FXML
    private TableColumn<bookData, String> availableBook_col_bookTitle;

    @FXML
    private TableColumn<bookData, String> availableBook_col_genre;

    @FXML
    private TableColumn<bookData, String> availableBook_col_price;

    @FXML
    private TableColumn<bookData, LocalDate> availableBook_col_publishDate;

    @FXML
    private Button availableBook_deleteBtn;

    @FXML
    private ImageView availableBook_imgView;

    @FXML
    private Button availableBook_importBtn;

    @FXML
    private TextField availableBook_price;

    @FXML
    private DatePicker availableBook_publishDatePicker;

    @FXML
    private TextField availableBook_search;

    @FXML
    private TableView<bookData> availableBook_tableView;

    @FXML
    private Button availableBook_updateBtn;

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane dashboard_anchorPane;

    @FXML
    private Label dashboard_availableBook;

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
    private TableColumn<?, ?> purchase_col_genre;

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

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void availableBookAdd(){

        String sql = "INSERT INTO book (book_id, title, author, genre, pub_date, price, image) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try{
            Alert alert;

            if(availableBook_bookID.getText().isEmpty()
                    || availableBook_bookTitle.getText().isEmpty()
                    || availableBook_bookAuthor.getText().isEmpty()
                    || availableBook_bookGenre.getText().isEmpty()
                    || availableBook_publishDatePicker.getValue() == null
                    || availableBook_price.getText().isEmpty()
                    || getData.path == null || getData.path.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("fields cannot be empty");
                alert.showAndWait();
            }else{
                //check to see if book id already exist
                String checkData = "SELECT book_id FROM book WHERE book_id = '" + availableBook_bookID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book ID: " + availableBook_bookID.getText() + " already exist!");
                    alert.showAndWait();
                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, availableBook_bookID.getText());
                    prepare.setString(2, availableBook_bookTitle.getText());
                    prepare.setString(3, availableBook_bookGenre.getText());
                    prepare.setString(4, availableBook_bookAuthor.getText());
                    prepare.setString(5, String.valueOf(availableBook_publishDatePicker.getValue()));
                    prepare.setString(6, availableBook_price.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book Added!");
                    alert.showAndWait();

                    //update table
                    availableBookShowListData();
                    //clear fields
                    availableBookClear();
                }
            }
        }catch(Exception e) {e.printStackTrace();}

    }

    public void availableBookUpdate(){

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE book SET title = '"
                + availableBook_bookTitle.getText() + "', author = '"
                + availableBook_bookAuthor.getText() + "', genre = '"
                + availableBook_bookGenre.getText() + "', pub_date = '"
                + availableBook_publishDatePicker.getValue() + "', price = '"
                + availableBook_price.getText() + "', image = '"
                + uri +"'";

        connect = database.connectDb();

        try{

        }catch(Exception e) {e.printStackTrace();}

    }

    public void availableBookClear(){
        availableBook_bookID.setText("");
        availableBook_bookTitle.setText("");
        availableBook_bookAuthor.setText("");
        availableBook_bookGenre.setText("");
        availableBook_publishDatePicker.setValue(null);
        availableBook_price.setText("");

        getData.path = "";

        availableBook_imgView.setImage(null);

    }

    public void availableBookInsertImage(){

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("File Image", "*jpg", "*png"));

        File file = open.showOpenDialog(main_anchorPane.getScene().getWindow());

        if(file != null){

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 146, 161, false, true);
            availableBook_imgView.setImage(image);
        }
    }

    public ObservableList<bookData> availableBookListData() {

        ObservableList<bookData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM BOOK";

        connect = database.connectDb();

        try{
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            bookData bookD;

            while(result.next()) {
                bookD = new bookData(result.getInt("book_id"), result.getString("title")
                        , result.getString("author"), result.getString("genre"), result.getDate("pub_date")
                        , result.getDouble("price"), result.getString("image"));

                listData.add(bookD);
            }
        }catch(Exception e) {e.printStackTrace();}
        return listData;
    }

    private ObservableList<bookData> availableBookList;
    public void availableBookShowListData(){
        availableBookList = availableBookListData();

        availableBook_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        availableBook_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableBook_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableBook_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableBook_col_publishDate.setCellValueFactory(new PropertyValueFactory<>("pub_date"));
        availableBook_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        availableBook_tableView.setItems(availableBookList);
    }

    public void availableBookSelect(){
        bookData bookD = availableBook_tableView.getSelectionModel().getSelectedItem();
        int num = availableBook_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1){
            return;
        }

        availableBook_bookID.setText(String.valueOf(bookD.getBookId()));
        availableBook_bookTitle.setText(bookD.getTitle());
        availableBook_bookAuthor.setText(bookD.getAuthor());
        availableBook_bookGenre.setText(bookD.getGenre());
        availableBook_publishDatePicker.setValue(LocalDate.parse(String.valueOf(bookD.getDate())));
        availableBook_price.setText(String.valueOf(bookD.getPrice()));

        String uri = "file:" + bookD.getImage();

        image = new Image(uri, 146, 161, false, true);
        availableBook_imgView.setImage(image);
    }

    @FXML
    public void switchPanel(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_anchorPane.setVisible(true);
            availableBook_anchorPane.setVisible(false);
            purchase_anchorPane.setVisible(false);
        }
        if (event.getSource() == availableBooks_btn) {
            dashboard_anchorPane.setVisible(false);
            availableBook_anchorPane.setVisible(true);
            purchase_anchorPane.setVisible(false);

            availableBookShowListData();

        }
        if (event.getSource() == purchase_btn) {
            dashboard_anchorPane.setVisible(false);
            availableBook_anchorPane.setVisible(false);
            purchase_anchorPane.setVisible(true);
        }
    }
}
