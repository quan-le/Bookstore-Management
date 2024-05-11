package com.example.Bookstore_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.Date;


public class DashboardController implements Initializable {

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
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private AnchorPane dashboard_anchorPane;

    @FXML
    private Label dashboard_AB;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TC;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_customers;

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
    private TableColumn<customerData, String> purchase_col_bookID;

    @FXML
    private TableColumn<customerData, String> purchase_col_bookTitle;

    @FXML
    private TableColumn<customerData, String> purchase_col_author;

    @FXML
    private TableColumn<customerData, String> purchase_col_genre;

    @FXML
    private TableColumn<customerData, String> purchase_col_quantity;

    @FXML
    private TableColumn<customerData, String> purchase_col_price;

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
    private Spinner<Integer> purchase_quantity;

    @FXML
    private Label purchase_total;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private TableView<customerData> purchase_tableView;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void dashboardAB(){

        String sql = "SELECT COUNT(id) FROM book";

        connect = database.connectDb();
        int countAB = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                countAB = result.getInt("COUNT(id)");
            }

            dashboard_AB.setText(String.valueOf(countAB));

        }catch(Exception e){e.printStackTrace();}
    }

    public void dashboardTI(){

        String sql = "SELECT SUM(total) FROM customer_info";

        connect = database.connectDb();
        double sumTotal = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                sumTotal = result.getDouble("SUM(total)");
            }

            dashboard_TI.setText("$" + String.valueOf(sumTotal));

        }catch(Exception e){e.printStackTrace();}
    }

    public void dashboardTC(){
        String sql = "SELECT COUNT(id) FROM customer_info";

        connect = database.connectDb();
        int countTC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                countTC = result.getInt("COUNT(id)");
            }

            dashboard_TC.setText(String.valueOf(countTC));

        }catch(Exception e){e.printStackTrace();}

    }


    public void availableBookAdd(){

        String sql = "INSERT INTO book (book_id, title, author, genre, pubDate, price, image) "
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
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                // CHECK IF BOOK ID IS ALREADY EXIST
                String checkData = "SELECT book_id FROM book WHERE book_id = '"
                        +availableBook_bookID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book ID: " + availableBook_bookID.getText() + " was already exist!");
                    alert.showAndWait();
                }else{

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, availableBook_bookID.getText());
                    prepare.setString(2, availableBook_bookTitle.getText());
                    prepare.setString(3, availableBook_bookAuthor.getText());
                    prepare.setString(4, availableBook_bookGenre.getText());
                    prepare.setString(5, String.valueOf(availableBook_publishDatePicker.getValue()));
                    prepare.setString(6, availableBook_price.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO BE UPDATED THE TABLEVIEW
                    availableBookShowListData();
                    // CLEAR FIELDS
                    availableBookClear();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }

    public void availableBookUpdate(){

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE book SET title = '"
                +availableBook_bookTitle.getText()+"', author = '"
                +availableBook_bookAuthor.getText()+"', genre = '"
                +availableBook_bookGenre.getText()+"', pubDate = '"
                +availableBook_publishDatePicker.getValue()+"', price = '"
                +availableBook_price.getText()+"', image = '"
                +uri+"' WHERE book_id = '"+availableBook_bookID.getText()+"'";

        connect = database.connectDb();

        try{
            Alert alert;

            if(availableBook_bookID.getText().isEmpty()
                    || availableBook_bookTitle.getText().isEmpty()
                    || availableBook_bookAuthor.getText().isEmpty()
                    || availableBook_bookGenre.getText().isEmpty()
                    || availableBook_publishDatePicker.getValue() == null
                    || availableBook_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Book ID: " + availableBook_bookID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful Updated!");
                    alert.showAndWait();

                    // TO BE UPDATED THE TABLEVIEW
                    availableBookShowListData();
                    // CLEAR FIELDS
                    availableBookClear();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }

    public void availableBookDelete(){

        String sql = "DELETE FROM book WHERE book_id = '"
                +availableBook_bookID.getText()+"'";

        connect = database.connectDb();

        try{
            Alert alert;

            if(availableBook_bookID.getText().isEmpty()
                    || availableBook_bookTitle.getText().isEmpty()
                    || availableBook_bookAuthor.getText().isEmpty()
                    || availableBook_bookGenre.getText().isEmpty()
                    || availableBook_publishDatePicker.getValue() == null
                    || availableBook_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Book ID: " + availableBook_bookID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful Delete!");
                    alert.showAndWait();

                    // TO BE UPDATED THE TABLEVIEW
                    availableBookShowListData();
                    // CLEAR FIELDS
                    availableBookClear();
                }
            }
        }catch(Exception e){e.printStackTrace();}

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
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_anchorPane.getScene().getWindow());

        if(file != null){
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 146, 137, false, true);
            availableBook_imgView.setImage(image);
        }

    }

    public ObservableList<bookData> availableBookListData(){

        ObservableList<bookData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM book";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            bookData bookD;

            while(result.next()){
                bookD = new bookData(result.getInt("book_id"), result.getString("title")
                        , result.getString("author"), result.getString("genre")
                        , result.getDate("pubDate"), result.getDouble("price")
                        , result.getString("image"));

                listData.add(bookD);
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<bookData> availableBookList;
    public void availableBookShowListData(){
        availableBookList = availableBookListData();

        availableBook_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        availableBook_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableBook_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableBook_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableBook_col_publishDate.setCellValueFactory(new PropertyValueFactory<>("pubDate"));
        availableBook_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        availableBook_tableView.setItems(availableBookList);
    }

    public void availableBookSelect(){
        bookData bookD = availableBook_tableView.getSelectionModel().getSelectedItem();
        int num = availableBook_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){ return; }

        availableBook_bookID.setText(String.valueOf(bookD.getBookId()));
        availableBook_bookTitle.setText(bookD.getTitle());
        availableBook_bookAuthor.setText(bookD.getAuthor());
        availableBook_bookGenre.setText(bookD.getGenre());
        availableBook_publishDatePicker.setValue(LocalDate.parse(String.valueOf(bookD.getPubDate())));
        availableBook_price.setText(String.valueOf(bookD.getPrice()));

        getData.path = bookD.getImage();

        String uri = "file:" + bookD.getImage();

        image = new Image(uri, 112, 137, false, true);

        availableBook_imgView.setImage(image);
    }

    public void availableBookSearch(){

        FilteredList<bookData> filter = new FilteredList<>(availableBookList, e -> true);

        availableBook_search.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicateBookData -> {

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if(predicateBookData.getBookId().toString().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getTitle().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getAuthor().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getGenre().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getPubDate().toString().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getPrice().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });

        SortedList<bookData> sortList = new SortedList(filter);
        sortList.comparatorProperty().bind(availableBook_tableView.comparatorProperty());
        availableBook_tableView.setItems(sortList);

    }

    private double totalP;
    public void purchaseAdd(){
        purchasecustomerId();

        String sql = "INSERT INTO customer (customer_id, book_id, title, author, genre, quantity, price, date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try{
            Alert alert;

            if(purchase_bookTitle.getSelectionModel().getSelectedItem() == null
                    || purchase_bookID.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose book first");
                alert.showAndWait();
            }else{

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerId));
                prepare.setString(2, purchase_info_bookID.getText());
                prepare.setString(3, purchase_info_bookTitle.getText());
                prepare.setString(4, purchase_info_author.getText());
                prepare.setString(5, purchase_info_genre.getText());
                prepare.setString(6, String.valueOf(qty));

                String checkData = "SELECT title, price FROM book WHERE title = '"
                        +purchase_bookTitle.getSelectionModel().getSelectedItem()+"'";

                double priceD = 0;

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if(result.next()){
                    priceD = result.getDouble("price");
                }

                totalP = (qty * priceD);

                prepare.setString(7, String.valueOf(totalP));

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(8, String.valueOf(sqlDate));

                prepare.executeUpdate();

                purchaseDisplayTotal();
                purchaseShowCustomerListData();
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void purchasePay(){

        String sql = "INSERT INTO customer_info (customer_id, total, date) "
                + "VALUES(?,?,?)";

        connect = database.connectDb();

        try{
            Alert alert;
            if(displayTotal == 0){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(displayTotal));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(3, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }

    private double displayTotal;
    public void purchaseDisplayTotal(){
        purchasecustomerId();

        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '"+customerId+"'";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                displayTotal = result.getDouble("SUM(price)");
            }

            purchase_total.setText("$" + String.valueOf(displayTotal));

        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookId(){

        String sql = "SELECT book_id FROM book";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while(result.next()){
                listData.add(result.getString("book_id"));
            }

            purchase_bookID.setItems(listData);
            purchaseBookTitle();
        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookTitle(){

        String sql = "SELECT book_id, title FROM book WHERE book_id = '"
                +purchase_bookID.getSelectionModel().getSelectedItem()+"'";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while(result.next()){
                listData.add(result.getString("title"));
            }

            purchase_bookTitle.setItems(listData);

            purchaseBookInfo();

        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookInfo(){

        String sql = "SELECT * FROM book WHERE title = '"
                +purchase_bookTitle.getSelectionModel().getSelectedItem()+"'";

        connect = database.connectDb();

        String bookId = "";
        String title = "";
        String author = "";
        String genre = "";
        String date = "";

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                bookId = result.getString("book_id");
                title = result.getString("title");
                author = result.getString("author");
                genre = result.getString("genre");
                date = result.getString("pubDate");
            }

            purchase_info_bookID.setText(bookId);
            purchase_info_bookTitle.setText(title);
            purchase_info_author.setText(author);
            purchase_info_genre.setText(genre);
            purchase_info_date.setText(date);

        }catch(Exception e){e.printStackTrace();}

    }

    public ObservableList<customerData> purchaseListData(){
        purchasecustomerId();
        String sql = "SELECT * FROM customer WHERE customer_id = '"+customerId+"'";

        ObservableList<customerData> listData = FXCollections.observableArrayList();

        connect = database.connectDb();

        try{
            prepare  = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            customerData customerD;

            while(result.next()){
                customerD = new customerData(result.getInt("customer_id")
                        , result.getInt("book_id")
                        , result.getString("title")
                        , result.getString("author")
                        , result.getString("genre")
                        , result.getInt("quantity")
                        , result.getDouble("price")
                        , result.getDate("date"));

                listData.add(customerD);
            }

        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<customerData> purchaseCustomerList;
    public void purchaseShowCustomerListData(){
        purchaseCustomerList = purchaseListData();

        purchase_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        purchase_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        purchase_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        purchase_tableView.setItems(purchaseCustomerList);

    }

    private SpinnerValueFactory<Integer> spinner;

    public void purchaseDisplayQTY(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
    }
    private int qty;
    public void purchaseQty(){
        qty = purchase_quantity.getValue();
    }

    private int customerId;
    public void purchasecustomerId(){

        String sql = "SELECT MAX(customer_id) FROM customer";
        int checkCID = 0 ;
        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                customerId = result.getInt("MAX(customer_id)");
            }

            String checkData = "SELECT MAX(customer_id) FROM customer_info";

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            if(result.next()){
                checkCID = result.getInt("MAX(customer_id)");
            }

            if(customerId == 0){
                customerId += 1;
            }else if(checkCID == customerId){
                customerId = checkCID + 1;
            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void displayUsername(){
        String user = getData.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }


    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)main_anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void switchPanel(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_anchorPane.setVisible(true);
            availableBook_anchorPane.setVisible(false);
            purchase_anchorPane.setVisible(false);

            dashboardAB();
            dashboardTI();
            dashboardTC();

        }
        if (event.getSource() == availableBooks_btn) {
            dashboard_anchorPane.setVisible(false);
            availableBook_anchorPane.setVisible(true);
            purchase_anchorPane.setVisible(false);

            availableBookShowListData();
            availableBookSearch();

        }
        if (event.getSource() == purchase_btn) {
            dashboard_anchorPane.setVisible(false);
            availableBook_anchorPane.setVisible(false);
            purchase_anchorPane.setVisible(true);

            purchaseBookTitle();
            purchaseBookId();
            purchaseShowCustomerListData();
            purchaseDisplayQTY();
            purchaseDisplayTotal();
        }
    }

    public void showErrorMessage(String errorMessage){
        Alert alert;

        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public void showMessage(String message){
        Alert alert;

        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();

        dashboardAB();
        dashboardTI();
        dashboardTC();

        // TO SHOW THE DATA ON TABLEVIEW (AVAILABLE BOOKS)
        availableBookShowListData();

        purchaseBookId();
        purchaseBookTitle();
        purchaseShowCustomerListData();
        purchaseDisplayQTY();
        purchaseDisplayTotal();

    }
}



