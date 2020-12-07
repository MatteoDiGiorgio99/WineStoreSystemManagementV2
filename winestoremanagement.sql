-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 07, 2020 alle 16:41
-- Versione del server: 10.4.14-MariaDB
-- Versione PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `winestoremanagement`
--
CREATE DATABASE IF NOT EXISTS `winestoremanagement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `winestoremanagement`;

-- --------------------------------------------------------

--
-- Struttura della tabella `administrators`
--

DROP TABLE IF EXISTS `administrators`;
CREATE TABLE `administrators` (
  `IDAdministrator` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `IDEmployee` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `notifications`
--

DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `IDNotification` int(11) NOT NULL,
  `User` int(11) NOT NULL,
  `Wine` int(11) NOT NULL,
  `IsNotified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `IDOrder` int(11) NOT NULL,
  `Status` int(11) NOT NULL DEFAULT 1,
  `User` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `orderstatuses`
--

DROP TABLE IF EXISTS `orderstatuses`;
CREATE TABLE `orderstatuses` (
  `IDOrderStatus` int(11) NOT NULL,
  `Description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `orderstatuses`
--

INSERT INTO `orderstatuses` (`IDOrderStatus`, `Description`) VALUES
(1, 'Confirmed'),
(2, 'Shipped');

-- --------------------------------------------------------

--
-- Struttura della tabella `orderwines`
--

DROP TABLE IF EXISTS `orderwines`;
CREATE TABLE `orderwines` (
  `IDOrderWine` int(11) NOT NULL,
  `Wine` int(11) NOT NULL,
  `OrderNumber` int(11) NOT NULL,
  `BottlesNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `IDUser` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `vines`
--

DROP TABLE IF EXISTS `vines`;
CREATE TABLE `vines` (
  `IDVine` int(11) NOT NULL,
  `Description` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `wineproducers`
--

DROP TABLE IF EXISTS `wineproducers`;
CREATE TABLE `wineproducers` (
  `IDWineProducer` int(11) NOT NULL,
  `Wine` int(11) NOT NULL,
  `Vine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `wines`
--

DROP TABLE IF EXISTS `wines`;
CREATE TABLE `wines` (
  `IDWine` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Producer` varchar(30) NOT NULL,
  `Year` int(11) NOT NULL,
  `Price` decimal(16,2) NOT NULL,
  `Notes` varchar(30) NOT NULL,
  `BottlesNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `administrators`
--
ALTER TABLE `administrators`
  ADD PRIMARY KEY (`IDAdministrator`),
  ADD UNIQUE KEY `administratorEmailUnique` (`Email`);

--
-- Indici per le tabelle `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`IDEmployee`) USING BTREE,
  ADD UNIQUE KEY `employeesEmailUnique` (`Email`) USING BTREE;

--
-- Indici per le tabelle `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`IDNotification`),
  ADD KEY `User` (`User`),
  ADD KEY `FK_Notifications_Wines` (`Wine`);

--
-- Indici per le tabelle `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`IDOrder`),
  ADD KEY `FK_Order_OrderStatus` (`Status`),
  ADD KEY `FK_Order_User` (`User`);

--
-- Indici per le tabelle `orderstatuses`
--
ALTER TABLE `orderstatuses`
  ADD PRIMARY KEY (`IDOrderStatus`);

--
-- Indici per le tabelle `orderwines`
--
ALTER TABLE `orderwines`
  ADD PRIMARY KEY (`IDOrderWine`),
  ADD KEY `FK_OrderWine_Order` (`OrderNumber`),
  ADD KEY `FK_OrderWine_Wine` (`Wine`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`IDUser`) USING BTREE,
  ADD UNIQUE KEY `usersEmailUnique` (`Email`) USING BTREE;

--
-- Indici per le tabelle `vines`
--
ALTER TABLE `vines`
  ADD PRIMARY KEY (`IDVine`);

--
-- Indici per le tabelle `wineproducers`
--
ALTER TABLE `wineproducers`
  ADD PRIMARY KEY (`IDWineProducer`),
  ADD KEY `FK_WineProducer_Vine` (`Vine`),
  ADD KEY `FK_WineProducer_Wine` (`Wine`);

--
-- Indici per le tabelle `wines`
--
ALTER TABLE `wines`
  ADD PRIMARY KEY (`IDWine`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `administrators`
--
ALTER TABLE `administrators`
  MODIFY `IDAdministrator` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `employees`
--
ALTER TABLE `employees`
  MODIFY `IDEmployee` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `notifications`
--
ALTER TABLE `notifications`
  MODIFY `IDNotification` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `orders`
--
ALTER TABLE `orders`
  MODIFY `IDOrder` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `orderstatuses`
--
ALTER TABLE `orderstatuses`
  MODIFY `IDOrderStatus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `orderwines`
--
ALTER TABLE `orderwines`
  MODIFY `IDOrderWine` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `IDUser` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `vines`
--
ALTER TABLE `vines`
  MODIFY `IDVine` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `wineproducers`
--
ALTER TABLE `wineproducers`
  MODIFY `IDWineProducer` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `wines`
--
ALTER TABLE `wines`
  MODIFY `IDWine` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FK_Notifications_Wines` FOREIGN KEY (`Wine`) REFERENCES `wines` (`IDWine`) ON UPDATE NO ACTION;

--
-- Limiti per la tabella `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_Order_OrderStatus` FOREIGN KEY (`Status`) REFERENCES `orderstatuses` (`IDOrderStatus`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_Order_User` FOREIGN KEY (`User`) REFERENCES `users` (`IDUser`) ON UPDATE NO ACTION;

--
-- Limiti per la tabella `orderwines`
--
ALTER TABLE `orderwines`
  ADD CONSTRAINT `FK_OrderWine_Order` FOREIGN KEY (`OrderNumber`) REFERENCES `orders` (`IDOrder`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_OrderWine_Wine` FOREIGN KEY (`Wine`) REFERENCES `wines` (`IDWine`) ON UPDATE NO ACTION;

--
-- Limiti per la tabella `wineproducers`
--
ALTER TABLE `wineproducers`
  ADD CONSTRAINT `FK_WineProducer_Vine` FOREIGN KEY (`Vine`) REFERENCES `vines` (`IDVine`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_WineProducer_Wine` FOREIGN KEY (`Wine`) REFERENCES `wines` (`IDWine`) ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
