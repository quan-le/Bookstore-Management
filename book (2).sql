-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2024 at 05:31 AM
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
-- Database: `book`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `book_id` int(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `pubDate` date DEFAULT NULL,
  `price` int(11) NOT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `book_id`, `title`, `author`, `genre`, `pubDate`, `price`, `image`) VALUES
(4, 1, 'p2', 'dano', 'sci', '2024-05-04', 10, 'D:\\\\download\\\\440054791_438684015562438_7160749161221022304_n.png'),
(5, 2, 'p2-HW', 'dano', 'sci', '2024-05-06', 5, 'D:\\\\download\\\\440910639_399790196220940_7929191263626709846_n.png'),
(6, 3, 'erd', 'HungBel', 'com', '2024-05-04', 1, 'D:\\download\\con nguoi.jpg'),
(7, 4, 'Nhạc lý cơ bản', 'Spednova', 'music', '2024-05-04', 15, 'C:\\Users\\armyr\\Downloads\\445db01ad2c54afe83d1d0e64af42e3e.png'),
(8, 5, 'Hiểu về Bitcoin', 'HuyBel', 'Fantasy', '2023-01-05', 4, 'C:\\Users\\armyr\\Downloads\\bia-sach-hieu-ve-bitcoin-1_2b7b917f85684eed807c0ab7923fa118_master.png'),
(9, 6, 'Có bồ là tội đồ', 'PhD. Đỗ Quốc Bảo', 'Philosophy', '2024-02-15', 69, 'C:\\\\Users\\\\armyr\\\\Downloads\\\\4d8374764fe99aed3438886a3c5254aa.png');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `book_id` int(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `book_id`, `title`, `author`, `genre`, `quantity`, `price`, `date`) VALUES
(22, 1, 6, 'Có bồ là tội đồ', 'PhD. Đỗ Quốc Bảo', 'Philosophy', 1, 69, '2024-05-10'),
(23, 2, 2, 'p2-HW', 'dano', 'sci', 4, 20, '2024-05-10'),
(24, 2, 4, 'Nhạc lý cơ bản', 'Spednova', 'music', 1, 15, '2024-05-10'),
(25, 2, 1, 'p2', 'dano', 'sci', 3, 30, '2024-05-10');

-- --------------------------------------------------------

--
-- Table structure for table `customer_info`
--

CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_info`
--

INSERT INTO `customer_info` (`id`, `customer_id`, `total`, `date`) VALUES
(1, 1, 69, '2024-05-10'),
(2, 2, 65, '2024-05-10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_info`
--
ALTER TABLE `customer_info`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `customer_info`
--
ALTER TABLE `customer_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
