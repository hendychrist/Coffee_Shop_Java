-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Jun 2019 pada 18.32
-- Versi server: 10.1.38-MariaDB
-- Versi PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rowan_martini`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `cart`
--

CREATE TABLE `cart` (
  `MenuID` int(5) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Quantity` int(50) NOT NULL,
  `Price` int(50) NOT NULL,
  `TotalPrice` int(100) NOT NULL,
  `TransactionDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `cart`
--

INSERT INTO `cart` (`MenuID`, `Name`, `Quantity`, `Price`, `TotalPrice`, `TransactionDate`) VALUES
(1, ' TruffleNut Frappucino ', 3, 120000, 360000, '2019-01-02'),
(2, ' Soursoup Juice ', 2, 50000, 100000, '2018-12-31'),
(3, ' Plain Water ', 5, 36000, 180000, '2018-10-25'),
(4, ' Hot Chocolate ', 4, 59000, 236000, '2019-06-22');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detailtransaction`
--

CREATE TABLE `detailtransaction` (
  `TransactionID` int(10) NOT NULL,
  `MenuID` varchar(20) NOT NULL,
  `Quantity` int(10) NOT NULL,
  `Price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detailtransaction`
--

INSERT INTO `detailtransaction` (`TransactionID`, `MenuID`, `Quantity`, `Price`) VALUES
(6, '  Soursoup Juice  ', 2, 100000),
(2, '  Plain Water  ', 5, 180000),
(1, '  Hot Chocolate  ', 4, 236000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `menus`
--

CREATE TABLE `menus` (
  `MenuID` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Quantity` int(10) NOT NULL,
  `Price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `menus`
--

INSERT INTO `menus` (`MenuID`, `Name`, `Quantity`, `Price`) VALUES
(1, 'TruffleNut Frappucino', 9, 120000),
(2, 'Soursoup Juice', 16, 50000),
(3, 'Plain Water', 6, 36000),
(4, 'Hot Chocolate', 12, 59000),
(6, 'Pearl Milk Tea', 10, 78000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `UserID` int(10) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `UserPassword` varchar(50) NOT NULL,
  `UserPhoneNumber` varchar(20) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `UserPassword`, `UserPhoneNumber`, `Gender`, `Role`) VALUES
(1, 'hendy_christian ', ' abcde1234', '87897784644', 'Male', ' User '),
(2, 'david_christian ', ' asdfg1234', '08228188460', 'Female', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`MenuID`);

--
-- Indeks untuk tabel `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`MenuID`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `UserID` (`UserID`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `menus`
--
ALTER TABLE `menus`
  MODIFY `MenuID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
