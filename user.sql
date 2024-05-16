-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2024 at 01:40 PM
-- Server version: 8.0.30
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int NOT NULL,
  `First_Name` varchar(25) NOT NULL,
  `Last_Name` varchar(25) NOT NULL,
  `Phone_Number` int NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Date_of_Birth` date NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Email` varchar(75) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `Confirm_Password` varchar(25) NOT NULL,
  `Profile` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `First_Name`, `Last_Name`, `Phone_Number`, `Address`, `Date_of_Birth`, `Gender`, `Email`, `Password`, `Confirm_Password`, `Profile`) VALUES
(1, 'Tobiichi', 'Origami', 1010101, 'JL.Anime', '2004-04-04', 'Female', 'TobiichiOrigami@gmail.com', 'Origami2000', 'Origami2000', ''),
(2, 'itsuka', 'shido', 1010101, 'j.anime', '2000-01-01', 'Male', 'Shido@gmail.com', 'TakamiyaMana', 'Itsuka', ''),
(3, 'naruto', 'uzumaki', 1010101, 'jwa', '2000-02-02', 'Male', 'Naruto@gmail.com', 'HinataHyuga', 'Hinata', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
