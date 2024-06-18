package com.example.Bookstore_management;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import java.sql.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.UUID;


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
    private TableColumn<customerData, String> purchase_col_date;

    @FXML
    private TextField purchase_name;

    @FXML
    private TextField purchase_phone;

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

    public void dashboardAB() throws SQLException {

        String sql = "SELECT COUNT(BookID) FROM book";

        connect = database.connectDb();
        int countAB = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                countAB = result.getInt("COUNT(BookID)");
            }

            dashboard_AB.setText(String.valueOf(countAB));

        }catch(Exception e){e.printStackTrace();}
    }

    public void dashboardTI() throws SQLException {

        String sql = "SELECT SUM(Price) FROM customer_cart";

        connect = database.connectDb();
        double sumTotal = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                sumTotal = result.getDouble("SUM(Price)");
            }

            dashboard_TI.setText("$" + String.valueOf(sumTotal));

        }catch(Exception e){e.printStackTrace();}
    }

    public void dashboardTC() throws SQLException {
        String sql = "SELECT COUNT(CustomerID) FROM customer_info";

        connect = database.connectDb();
        int countTC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                countTC = result.getInt("COUNT(CustomerID)");
            }

            dashboard_TC.setText(String.valueOf(countTC));

        }catch(Exception e){e.printStackTrace();}

    }


    public void availableBookAdd() throws SQLException {

        String sql = "INSERT INTO book (BookID, Title, PubDate, Price, Cover) VALUES (?, ?, ?, ?, ?)";
        String sqlAuthor = "INSERT INTO author (Name) VALUES (?)";
        String sqlGenre = "INSERT INTO genre (Name) VALUES (?)";
        String sqlBAuthor = "INSERT INTO book_author (BookID, AuthorID) VALUES (?, ?)";
        String sqlBGenre = "INSERT INTO book_genre (BookID, GenreID) VALUES (?, ?)";

        try {
            connect = database.connectDb();

            // Insert into book table
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
            prepare.setString(2, availableBook_bookTitle.getText());
            prepare.setDate(3, java.sql.Date.valueOf(availableBook_publishDatePicker.getValue()));
            prepare.setDouble(4, Double.parseDouble(availableBook_price.getText()));
            prepare.setString(5, getData.path); // Assuming getData.path holds the image path
            prepare.executeUpdate();

            // Insert into author table
            prepare = connect.prepareStatement(sqlAuthor);
            prepare.setString(1, availableBook_bookAuthor.getText());
            prepare.executeUpdate();

            // Insert into genre table
            prepare = connect.prepareStatement(sqlGenre);
            prepare.setString(1, availableBook_bookGenre.getText());
            prepare.executeUpdate();

            // Insert into bookauthor table
            for (String author : availableBook_bookAuthor.getText().split(",\\s*")) {
                prepare = connect.prepareStatement(sqlBAuthor);
                prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
                prepare.setInt(2, getAuthorID(author.trim()));
                prepare.executeUpdate();
            }

            // Insert into bookgenre table
            for (String genre : availableBook_bookGenre.getText().split(",\\s*")) {
                prepare = connect.prepareStatement(sqlBGenre);
                prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
                prepare.setInt(2, getGenreID(genre.trim()));
                prepare.executeUpdate();
            }

            showMessage("Book Inserted Successfully!");

        } catch (SQLException e) {
            showErrorMessage("Error Inserting Book: " + e.getMessage());
            e.printStackTrace();
        }

        availableBookShowListData();
    }

    public void availableBookUpdate() {
        String sql = "UPDATE book SET Title = ?, PubDate = ?, Price = ?, Cover = ? WHERE BookID = ?";
        String sqlDeleteAuthor = "DELETE FROM book_author WHERE BookID = ?";
        String sqlDeleteGenre = "DELETE FROM book_genre WHERE BookID = ?";
        String sqlAuthor = "INSERT INTO book_author (BookID, AuthorID) VALUES (?, ?)";
        String sqlGenre = "INSERT INTO book_genre (BookID, GenreID) VALUES (?, ?)";

        try {
            connect = database.connectDb();

            // Update book table
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, availableBook_bookTitle.getText());
            prepare.setString(2, String.valueOf(availableBook_publishDatePicker.getValue()));
            prepare.setString(3, availableBook_price.getText());
            prepare.setString(4, getData.path);
            prepare.setString(5, (availableBook_bookID.getText()));
            prepare.executeUpdate();

            // Delete existing authors and genres
            prepare = connect.prepareStatement(sqlDeleteAuthor);
            prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
            prepare.executeUpdate();

            prepare = connect.prepareStatement(sqlDeleteGenre);
            prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
            prepare.executeUpdate();

            // Insert updated authors and genres
            for (String author : availableBook_bookAuthor.getText().split(",\\s*")) {
                prepare = connect.prepareStatement(sqlAuthor);
                prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
                prepare.setInt(2, getAuthorID(author.trim()));
                prepare.executeUpdate();
            }

            for (String genre : availableBook_bookGenre.getText().split(",\\s*")) {
                prepare = connect.prepareStatement(sqlGenre);
                prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
                prepare.setInt(2, getGenreID(genre.trim()));
                prepare.executeUpdate();
            }

            showMessage("Book Updated Successfully!");

        } catch (SQLException e) {
            showErrorMessage("Error Updating Book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void availableBookDelete() {
        String sql = "DELETE FROM book WHERE BookID = ?";
        try {
            connect = database.connectDb();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Book ID: " + availableBook_bookID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(availableBook_bookID.getText()));
                prepare.executeUpdate();

                showMessage("Book Deleted Successfully!");

                // Refresh the TableView
                availableBookShowListData();
                // Clear input fields
                availableBookClear();
            }
        } catch (SQLException e) {
            showErrorMessage("Error Deleting Book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int getAuthorID(String authorName) throws SQLException {
        String sql = "SELECT AuthorID FROM author WHERE Name = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, authorName);
        ResultSet result = prepare.executeQuery();
        if (result.next()) {
            return result.getInt("AuthorID");
        } else {
            throw new SQLException("Author not found: " + authorName);
        }
    }

    private int getGenreID(String genreName) throws SQLException {
        String sql = "SELECT GenreID FROM genre WHERE Name = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, genreName);
        ResultSet result = prepare.executeQuery();
        if (result.next()) {
            return result.getInt("GenreID");
        } else {
            throw new SQLException("Genre not found: " + genreName);
        }
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

    public void availableBookInsertImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("File", "*jpg", "*png"));

        File file = open.showOpenDialog(availableBook_imgView.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString(), 146, 137, false, true);
            availableBook_imgView.setImage(image);
        }
    }

    public ObservableList<bookData> availableBookListData() throws SQLException {

        ObservableList<bookData> listData = FXCollections.observableArrayList();
        String sql = "SELECT b.BookID, b.Title, GROUP_CONCAT(a.Name SEPARATOR ', ') AS Authors, " +
                "GROUP_CONCAT(g.Name SEPARATOR ', ') AS Genres, b.PubDate, b.Price, b.Cover " +
                "FROM book b " +
                "LEFT JOIN book_author ba ON b.BookID = ba.BookID " +
                "LEFT JOIN author a ON ba.AuthorID = a.AuthorID " +
                "LEFT JOIN book_genre bg ON b.BookID = bg.BookID " +
                "LEFT JOIN genre g ON bg.GenreID = g.GenreID " +
                "GROUP BY b.BookID, b.Title, b.PubDate, b.Price";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            bookData bookD;

            while(result.next()){
                bookD = new bookData(result.getInt("BookID"), result.getString("Title")
                        , result.getString("Authors"), result.getString("Genres")
                        , result.getDate("PubDate"), result.getDouble("Price")
                        , result.getString("Cover"));

                listData.add(bookD);
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<bookData> availableBookList;
    public void availableBookShowListData() throws SQLException {
        availableBookList = availableBookListData();

        availableBook_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        availableBook_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableBook_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableBook_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableBook_col_publishDate.setCellValueFactory(new PropertyValueFactory<>("pubDate"));
        availableBook_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        FilteredList<bookData> filter = new FilteredList<>(availableBookList, e -> true);
        SortedList<bookData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(availableBook_tableView.comparatorProperty());
        availableBook_tableView.setItems(sortList);
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

                if(Integer.toString(predicateBookData.getBookId()).contains(searchKey)){
                    return true;
                }else if(predicateBookData.getTitle().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getAuthor().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getGenre().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBookData.getPubDate().toString().contains(searchKey)){
                    return true;
                }else if(Double.toString(predicateBookData.getPrice()).contains(searchKey)){
                    return true;
                }else return false;
            });
        });

        SortedList<bookData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(availableBook_tableView.comparatorProperty());
        availableBook_tableView.setItems(sortList);
    }



    private int cartId;
    private double totalP;
    private double displayTotal;
    private SpinnerValueFactory<Integer> spinner;
    private int qty;
    private int customerId;
    private ObservableList<customerData> purchaseCustomerList;

    public void purchaseAdd() throws SQLException {

        String sql = "CALL purchaseAdd(?, ?, ?)";

        connect = database.connectDb();

        try{
            Alert alert;

            if(purchase_bookTitle.getSelectionModel().getSelectedItem() == null
                    || purchase_bookID.getSelectionModel().getSelectedItem() == null){
                showErrorMessage("Please choose book first!");
            }else{

                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, customerId);
                prepare.setInt(2, (int) purchase_bookID.getValue());
                prepare.setInt(3, purchase_quantity.getValue());

                prepare.executeUpdate();

                purchaseDisplayTotal();
                purchaseShowCustomerListData();
            }
        }catch(Exception e){e.printStackTrace();}

    }

    public void purchasePay() throws SQLException {
        String sqlCustomerInfo = "INSERT INTO customer_info (CustomerID, Name, PhoneNumber) VALUES(?,?,?)";
        String sqlCustomerCart = "INSERT INTO customer_cart (CustomerID, CartID, Price, BoughtDate) VALUES(?,?,?,?)";



        try (Connection connect = database.connectDb()) {
            // Generate a unique CustomerID
            connect.setAutoCommit(false);
            String sqlGetMaxCustomerID = "SELECT MAX(CustomerID) FROM customer_info";
            int maxCustomerID = 0;
            try (PreparedStatement prepareGetMaxCustomerID = connect.prepareStatement(sqlGetMaxCustomerID);
                 ResultSet resultGetMaxCustomerID = prepareGetMaxCustomerID.executeQuery()) {
                if (resultGetMaxCustomerID.next()) {
                    maxCustomerID = resultGetMaxCustomerID.getInt(1);
                }
            }

            int newCustomerID = maxCustomerID + 1;

            try (PreparedStatement prepareCustomerInfo = connect.prepareStatement(sqlCustomerInfo)) {
                prepareCustomerInfo.setInt(1, newCustomerID);
                prepareCustomerInfo.setString(2, purchase_name.getText());
                prepareCustomerInfo.setString(3, purchase_phone.getText());
                prepareCustomerInfo.executeUpdate();
            }

            try (PreparedStatement prepareCustomerCart = connect.prepareStatement(sqlCustomerCart)) {
                prepareCustomerCart.setInt(1, newCustomerID);
                prepareCustomerCart.setInt(2, cartId);
                prepareCustomerCart.setDouble(3, displayTotal);
                prepareCustomerCart.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                prepareCustomerCart.executeUpdate();
            }

            connect.commit();
            cartId = 0;

            showMessage("Successful!");
        } catch (Exception e) {
            connect.rollback();
            e.printStackTrace();
        }
    }

    public void purchaseDisplayTotal() throws SQLException {
        String sql = "SELECT SUM(b.Price * bc.Quantity) AS TotalPrice " +
                "FROM book b " +
                "JOIN book_customercart bc ON b.BookID = bc.BookID " +
                "WHERE bc.CartID = ?";
        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setInt(1, cartId);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    displayTotal = result.getDouble("TotalPrice");
                }
                purchase_total.setText("$" + displayTotal);
            }
        }
    }

    public void purchaseBookId() throws SQLException {
        String sql = "SELECT BookID FROM book";
        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            ObservableList listData = FXCollections.observableArrayList();
            while (result.next()) {
                listData.add(result.getInt("BookID"));
            }
            purchase_bookID.setItems(listData);
            purchaseBookTitle();
        }
    }

    public void purchaseBookTitle() throws SQLException {

        String sql = "SELECT BookID, Title FROM book WHERE BookID = '"
                +purchase_bookID.getSelectionModel().getSelectedItem()+"'";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while(result.next()){
                listData.add(result.getString("Title"));
            }

            purchase_bookTitle.setItems(listData);

            purchaseBookInfo();

        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookInfo() throws SQLException {
        String sql = "SELECT b.BookID, b.Title, GROUP_CONCAT(a.Name SEPARATOR ', ') AS Author, " +
                "GROUP_CONCAT(g.Name SEPARATOR ', ') AS Genre, b.PubDate " +
                "FROM book b " +
                "JOIN book_author ba ON b.BookID = ba.BookID " +
                "JOIN author a ON ba.AuthorID = a.AuthorID " +
                "JOIN book_genre bg ON b.BookID = bg.BookID " +
                "JOIN genre g ON bg.GenreID = g.GenreID " +
                "WHERE b.Title = ?";
        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, String.valueOf(purchase_bookTitle.getSelectionModel().getSelectedItem()));
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    purchase_info_bookID.setText(result.getString("BookID"));
                    purchase_info_bookTitle.setText(result.getString("Title"));
                    purchase_info_author.setText(result.getString("Author"));
                    purchase_info_genre.setText(result.getString("Genre"));
                    purchase_info_date.setText(result.getString("PubDate"));
                }
            }
        }
    }

    public ObservableList<customerData> purchaseListData() throws SQLException {
        purchaseCustomerId();

        String sql = "SELECT b.BookID, b.Title, a.Name AS Author, g.Name AS Genre, bc.Quantity, b.Price, cc.BoughtDate " +
                "FROM book b " +
                "JOIN book_customercart bc ON b.BookID = bc.BookID " +
                "JOIN customer_cart cc ON bc.CartID = cc.CartID " +
                "JOIN book_author ba ON b.BookID = ba.BookID " +
                "JOIN author a ON ba.AuthorID = a.AuthorID " +
                "JOIN book_genre bg ON b.BookID = bg.BookID " +
                "JOIN genre g ON bg.GenreID = g.GenreID " +
                "WHERE cc.CartID =?";

        ObservableList<customerData> listData = FXCollections.observableArrayList();

        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setInt(1, cartId);
            try (ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                    customerData customerD = new customerData(
                            result.getInt("BookID"),
                            result.getString("Title"),
                            result.getString("Author"),
                            result.getString("Genre"),
                            result.getInt("Quantity"),
                            result.getDouble("Price"),
                            result.getDate("BoughtDate"));
                    listData.add(customerD);
                }
            }
        }
        return listData;
    }

    public void purchaseShowCustomerListData() throws SQLException {
        purchaseCustomerList = purchaseListData();

        purchase_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        purchase_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        purchase_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        purchase_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        purchase_tableView.setItems(purchaseCustomerList);
    }

    public void purchaseDisplayQTY() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
    }

    public void purchaseQty() {
        qty = purchase_quantity.getValue();
    }

    public void purchaseCustomerId() throws SQLException {
        String sql = "SELECT MAX(CustomerID) FROM customer_cart";
        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            if (result.next()) {
                customerId = result.getInt("MAX(CustomerID)");
            }

            String checkData = "SELECT MAX(CustomerID) FROM customer_info";
            try (PreparedStatement prepareCheck = connect.prepareStatement(checkData);
                 ResultSet resultCheck = prepareCheck.executeQuery()) {

                int checkCID = 0;
                if (resultCheck.next()) {
                    checkCID = resultCheck.getInt("MAX(CustomerID)");
                }

                if (customerId == 0) {
                    customerId += 1;
                } else if (checkCID == customerId) {
                    customerId = checkCID + 1;
                }
            }
        }
    }

    public void purchaseRemove() throws SQLException {
        customerData selectedBook = purchase_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            String sql = "DELETE FROM book_customercart WHERE BookID = ? AND CartID = ?";
            try (Connection connect = database.connectDb();
                 PreparedStatement prepare = connect.prepareStatement(sql)) {

                prepare.setInt(1, selectedBook.getBookId());
                prepare.setInt(2, cartId);
                prepare.executeUpdate();

                purchaseDisplayTotal();
                purchaseShowCustomerListData();
            }
        } else {
            showErrorMessage("Please select a book to remove!");
        }
    }

    public void purchaseBookSelect() {
        purchase_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                customerData selectedBook = purchase_tableView.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    purchase_info_bookID.setText(String.valueOf(selectedBook.getBookId()));
                    purchase_info_bookTitle.setText(selectedBook.getTitle());
                    purchase_info_author.setText(selectedBook.getAuthor());
                    purchase_info_genre.setText(selectedBook.getGenre());
                    purchase_quantity.getValueFactory().setValue(selectedBook.getQuantity());
                }
            }
        });
    }


    public void displayUsername(){
        String user = getData.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    @FXML
    public void switchPanel(ActionEvent event) throws SQLException {
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

        try {
            dashboardAB();
            dashboardTI();
            dashboardTC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // TO SHOW THE DATA ON TABLEVIEW (AVAILABLE BOOKS)
        try {
            availableBookShowListData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            purchaseBookId();
            purchaseBookTitle();
            purchaseShowCustomerListData();
            purchaseDisplayQTY();
            purchaseDisplayTotal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}



