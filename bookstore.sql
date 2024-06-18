-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2024 at 06:24 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `purchaseAdd` (IN `_CustomerID` INT, IN `_BookID` INT, IN `_Quantity` INT)   BEGIN
  DECLARE _CartID INT;
  DECLARE _Price DOUBLE;
  
  -- Get the price of the book
  SELECT Price INTO _Price FROM book WHERE BookID = _BookID;
  
  -- Create a new cart if the customer doesn't have one
  IF NOT EXISTS (SELECT 1 FROM customer_cart WHERE CustomerID = _CustomerID) THEN
    INSERT INTO customer_cart (CustomerID, Price, BoughtDate) VALUES (_CustomerID, 0, NOW());
    SET _CartID = LAST_INSERT_ID();
  ELSE
    SELECT CartID INTO _CartID FROM customer_cart WHERE CustomerID = _CustomerID;
  END IF;
  
  -- Add the book to the cart
  INSERT INTO book_customercart (BookID, CartID, Quantity) VALUES (_BookID, _CartID, _Quantity);
  
  -- Update the total price of the cart
  UPDATE customer_cart SET Price = Price + (_Price * _Quantity) WHERE CartID = _CartID;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `AuthorID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`AuthorID`, `Name`, `Age`) VALUES
(1, 'dano', NULL),
(2, 'HungBel', NULL),
(3, 'Spednova', NULL),
(4, 'HuyBel', NULL),
(5, 'PhD. Đỗ Quốc Bảo', NULL),
(6, 'Stephenie Meyer', 0);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `BookID` int(11) NOT NULL,
  `Title` varchar(100) DEFAULT NULL,
  `PubDate` date DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Cover` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`BookID`, `Title`, `PubDate`, `Price`, `Cover`) VALUES
(1, 'p2', '2024-05-04', 10, 'D:\\download\\440054791_438684015562438_7160749161221022304_n.png'),
(2, 'p2-HW', '2024-05-06', 5, 'D:\\download\\440910639_399790196220940_7929191263626709846_n.png'),
(3, 'erd', '2024-05-04', 1, 'D:downloadcon nguoi.jpg'),
(4, 'Nhạc lý cơ bản', '2024-05-04', 15, 'C:\\Users\\armyr\\Downloads\\445db01ad2c54afe83d1d0e64af42e3e.png'),
(5, 'Hiểu về Bitcoin', '2023-01-05', 4, 'C:UsersarmyrDownloadsia-sach-hieu-ve-bitcoin-1_2b7b917f85684eed807c0ab7923fa118_master.png'),
(6, 'Có bồ là tội đồ', '2024-02-15', 69, 'C:\\Users\\armyr\\Downloads\\4d8374764fe99aed3438886a3c5254aa.png'),
(7, 'The Host', '2008-05-06', 16, 'C:\\Users\\armyr\\Downloads\\the-host-book-1-661x1024.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `book_author`
--

CREATE TABLE `book_author` (
  `BookID` int(11) NOT NULL,
  `AuthorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book_author`
--

INSERT INTO `book_author` (`BookID`, `AuthorID`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 5),
(7, 6);

-- --------------------------------------------------------

--
-- Table structure for table `book_customercart`
--

CREATE TABLE `book_customercart` (
  `BookID` int(11) NOT NULL,
  `CartID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book_customercart`
--

INSERT INTO `book_customercart` (`BookID`, `CartID`, `Quantity`) VALUES
(1, 1, 1),
(4, 1, 1),
(6, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `book_genre`
--

CREATE TABLE `book_genre` (
  `BookID` int(11) NOT NULL,
  `GenreID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book_genre`
--

INSERT INTO `book_genre` (`BookID`, `GenreID`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 5),
(7, 6),
(7, 7);

-- --------------------------------------------------------

--
-- Table structure for table `customer_cart`
--

CREATE TABLE `customer_cart` (
  `CartID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `BoughtDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_cart`
--

INSERT INTO `customer_cart` (`CartID`, `CustomerID`, `Price`, `BoughtDate`) VALUES
(1, 1, 94, '2024-06-18');

-- --------------------------------------------------------

--
-- Table structure for table `customer_info`
--

CREATE TABLE `customer_info` (
  `CustomerID` int(11) NOT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_info`
--

INSERT INTO `customer_info` (`CustomerID`, `PhoneNumber`, `Name`) VALUES
(1, '0912345678', 'Dano');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `GenreID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`GenreID`, `Name`) VALUES
(1, 'sci'),
(2, 'com'),
(3, 'music'),
(4, 'Fantasy'),
(5, 'Philosophy'),
(6, 'Sci-fi'),
(7, 'Romance');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`AuthorID`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`BookID`);

--
-- Indexes for table `book_author`
--
ALTER TABLE `book_author`
  ADD PRIMARY KEY (`BookID`,`AuthorID`),
  ADD KEY `AuthorID` (`AuthorID`);

--
-- Indexes for table `book_customercart`
--
ALTER TABLE `book_customercart`
  ADD PRIMARY KEY (`BookID`,`CartID`),
  ADD KEY `CartID` (`CartID`);

--
-- Indexes for table `book_genre`
--
ALTER TABLE `book_genre`
  ADD PRIMARY KEY (`BookID`,`GenreID`),
  ADD KEY `GenreID` (`GenreID`);

--
-- Indexes for table `customer_cart`
--
ALTER TABLE `customer_cart`
  ADD PRIMARY KEY (`CartID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `customer_info`
--
ALTER TABLE `customer_info`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`GenreID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_cart`
--
ALTER TABLE `customer_cart`
  MODIFY `CartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customer_info`
--
ALTER TABLE `customer_info`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book_author`
--
ALTER TABLE `book_author`
  ADD CONSTRAINT `book_author_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`),
  ADD CONSTRAINT `book_author_ibfk_2` FOREIGN KEY (`AuthorID`) REFERENCES `author` (`AuthorID`);

--
-- Constraints for table `book_customercart`
--
ALTER TABLE `book_customercart`
  ADD CONSTRAINT `book_customercart_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`),
  ADD CONSTRAINT `book_customercart_ibfk_2` FOREIGN KEY (`CartID`) REFERENCES `customer_cart` (`CartID`);

--
-- Constraints for table `book_genre`
--
ALTER TABLE `book_genre`
  ADD CONSTRAINT `book_genre_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `book` (`BookID`),
  ADD CONSTRAINT `book_genre_ibfk_2` FOREIGN KEY (`GenreID`) REFERENCES `genre` (`GenreID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
