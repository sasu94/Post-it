-- --------------------------------------------------------
-- Host:                         sql7.freemysqlhosting.net
-- Versione server:              5.5.49-0ubuntu0.14.04.1 - (Ubuntu)
-- S.O. server:                  debian-linux-gnu
-- HeidiSQL Versione:            9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database sql7153338
CREATE DATABASE IF NOT EXISTS `sql7153338` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sql7153338`;

-- Dump della struttura di tabella sql7153338.Feedback
CREATE TABLE IF NOT EXISTS `Feedback` (
  `codiceSpedizione` varchar(50) NOT NULL,
  `Argomento` varchar(255) NOT NULL,
  `Commento` varchar(2000) NOT NULL,
  `Data` datetime NOT NULL,
  KEY `FK_Feedback_Spedizione` (`codiceSpedizione`),
  CONSTRAINT `FK_Feedback_Spedizione` FOREIGN KEY (`codiceSpedizione`) REFERENCES `Spedizione` (`Codice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.Feedback: ~2 rows (circa)
/*!40000 ALTER TABLE `Feedback` DISABLE KEYS */;
INSERT INTO `Feedback` (`codiceSpedizione`, `Argomento`, `Commento`, `Data`) VALUES
	('xv', 'Spedizione', 'il contenuto del pacco era danneggiato', '2017-01-25 21:32:18'),
	('asdf', 'Spedizione', 'arrivato troppo in ritardo', '2017-01-25 21:45:48');
/*!40000 ALTER TABLE `Feedback` ENABLE KEYS */;

-- Dump della struttura di tabella sql7153338.Giacenza
CREATE TABLE IF NOT EXISTS `Giacenza` (
  `Codice` varchar(50) NOT NULL,
  `Giorni` int(11) NOT NULL,
  PRIMARY KEY (`Codice`),
  CONSTRAINT `FK__Reso` FOREIGN KEY (`codice`) REFERENCES `Reso` (`CodiceSpedizione`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.Giacenza: ~2 rows (circa)
/*!40000 ALTER TABLE `Giacenza` DISABLE KEYS */;
INSERT INTO `Giacenza` (`Codice`, `Giorni`) VALUES
	('cghcmb', 4),
	('xv', 3);
/*!40000 ALTER TABLE `Giacenza` ENABLE KEYS */;

-- Dump della struttura di tabella sql7153338.InformazioniConsegna
CREATE TABLE IF NOT EXISTS `InformazioniConsegna` (
  `idSpedizione` varchar(50) NOT NULL,
  `Data` datetime NOT NULL,
  `Posizione` varchar(255) DEFAULT NULL,
  `Informazioni` varchar(255) NOT NULL,
  `Corriere` varchar(255) NOT NULL,
  PRIMARY KEY (`idSpedizione`),
  KEY `FK2_InformazioniConsegna_Corriere` (`Corriere`),
  CONSTRAINT `FK2_InformazioniConsegna_Corriere` FOREIGN KEY (`Corriere`) REFERENCES `Utente` (`Email`),
  CONSTRAINT `FK_InformazioniConsegna_Spedizione` FOREIGN KEY (`idSpedizione`) REFERENCES `Spedizione` (`Codice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.InformazioniConsegna: ~13 rows (circa)
/*!40000 ALTER TABLE `InformazioniConsegna` DISABLE KEYS */;
INSERT INTO `InformazioniConsegna` (`idSpedizione`, `Data`, `Posizione`, `Informazioni`, `Corriere`) VALUES
	('jhhg', '2017-01-27 14:06:32', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'faccio Prova', 'c@c.com'),
	('kjhhk', '2017-01-27 14:09:27', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'prova sbagliata', 'c@c.com'),
	('op2', '2017-01-30 11:19:57', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'kk', 'c@c.com'),
	('paccoDanneggiato', '2017-01-30 11:17:37', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'Pacco Danneggiato', 'c@c.com'),
	('Prova Pacco Danneggiato', '2017-01-27 14:38:41', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'Pacco Danneggiato', 'c@c.com'),
	('provaCorreggi', '2017-01-31 17:47:55', '', 'Consegna completata', 'c@c.com'),
	('provaCorretta', '2017-01-30 10:55:35', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'Consegna completata', 'c@c.com'),
	('provaData1', '2017-01-31 17:48:47', '', 'Pacco Danneggiato', 'c@c.com'),
	('provaData2', '2017-01-30 10:35:21', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'Consegna completata', 'c@c.com'),
	('provaReso', '2017-01-30 10:55:56', '1650 Charleston Road Mountain View, CA 94043 Stati Uniti', 'causale', 'c@c.com');
/*!40000 ALTER TABLE `InformazioniConsegna` ENABLE KEYS */;

-- Dump della struttura di tabella sql7153338.PaccoMancante
CREATE TABLE IF NOT EXISTS `PaccoMancante` (
  `Codice` varchar(50) NOT NULL,
  `Corriere` varchar(255) NOT NULL,
  KEY `codice` (`Codice`),
  KEY `corriere` (`Corriere`),
  CONSTRAINT `corriere` FOREIGN KEY (`Corriere`) REFERENCES `Utente` (`Email`),
  CONSTRAINT `codice` FOREIGN KEY (`Codice`) REFERENCES `Spedizione` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.PaccoMancante: ~9 rows (circa)
/*!40000 ALTER TABLE `PaccoMancante` DISABLE KEYS */;
INSERT INTO `PaccoMancante` (`Codice`, `Corriere`) VALUES
	('heh', 'c@c.com'),
	('Prova Pacco Mancante', 'c@c.com'),
	('Prova Pacco Mancante 2', 'c@c.com'),
	('Ultima pacco Mancante', 'c@c.com'),
	('paccoMancante', 'c@c.com'),
	('op', 'c@c.com'),
	('asdf', 'c@c.com'),
	('Codice0', 'c@c.com'),
	('Codice1', 'c@c.com');
/*!40000 ALTER TABLE `PaccoMancante` ENABLE KEYS */;

-- Dump della struttura di tabella sql7153338.Reso
CREATE TABLE IF NOT EXISTS `Reso` (
  `CodiceSpedizione` varchar(50) NOT NULL,
  `Causale` varchar(255) NOT NULL,
  `Data` date DEFAULT NULL,
  PRIMARY KEY (`CodiceSpedizione`),
  CONSTRAINT `FK_Reso_Spedizione` FOREIGN KEY (`CodiceSpedizione`) REFERENCES `Spedizione` (`Codice`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.Reso: ~10 rows (circa)
/*!40000 ALTER TABLE `Reso` DISABLE KEYS */;
INSERT INTO `Reso` (`CodiceSpedizione`, `Causale`, `Data`) VALUES
	('asdfg', 'Giacenza', '2017-01-22'),
	('cghcmb', 'Giacenza', '2017-01-18'),
	('jhhg', 'faccio Prova', '2017-01-27'),
	('kjhhk', 'prova sbagliata', '2017-01-27'),
	('op2', 'kk', '2017-01-30'),
	('Prova Pacco Danneggiato', 'Pacco Danneggiato', '2017-01-27'),
	('prova4', 'asdfg', '2017-02-09'),
	('provaReso', 'causale', '2017-01-30'),
	('vdsfv', 'causale bella', '2017-01-25'),
	('xv', 'Giacenza', '2017-01-23');
/*!40000 ALTER TABLE `Reso` ENABLE KEYS */;

-- Dump della struttura di tabella sql7153338.Spedizione
CREATE TABLE IF NOT EXISTS `Spedizione` (
  `Codice` varchar(50) NOT NULL,
  `Stato` varchar(255) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Cognome` varchar(255) NOT NULL,
  `Indirizzo` varchar(255) NOT NULL,
  `Corriere` varchar(255) DEFAULT NULL,
  `DataPrevistaConsegna` date NOT NULL,
  PRIMARY KEY (`Codice`),
  KEY `FK_Spedizione_Utente` (`Corriere`),
  CONSTRAINT `FK_Spedizione_Utente` FOREIGN KEY (`Corriere`) REFERENCES `Utente` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.Spedizione: ~52 rows (circa)
/*!40000 ALTER TABLE `Spedizione` DISABLE KEYS */;
INSERT INTO `Spedizione` (`Codice`, `Stato`, `Nome`, `Cognome`, `Indirizzo`, `Corriere`, `DataPrevistaConsegna`) VALUES
	('asdf', 'da Consegnare', 'sadf', 'sadf', 'asdf', 'c@c.com', '2017-02-06'),
	('asdfg', 'in Consegna', 'gsfd', 'gfds', 'gfd', 'c@c.com', '2017-02-06'),
	('asdkm', 'da Consegnare', 'òlksda', 'aslòdk', 'alòksc', 'c@c.com', '2017-01-21'),
	('cghcmb', 'da Consegnare', 'gh', 'gfchv', 'fghv', 'c@c.com', '2011-10-10'),
	('Codice0', 'da Consegnare', 'Alessandra', 'Aiello', 'Via Polo Marcantonio, 1. 88042 Falerna CZ', 'c@c.com', '2017-02-10'),
	('Codice1', 'da Consegnare', 'Maria', 'Sana', 'Via Umberto Nobile, 2. 87036 Quattromiglia CS', 'c@c.com', '2017-02-10'),
	('Codice10', 'da Consegnare', 'Enzo', 'Picone', 'Via Reggio Campi, 249. 89126 Reggio Calabria RC', 'c@c.com', '2017-02-10'),
	('Codice11', 'da Consegnare', 'dd', 'dd', 'dd', 'c@c.com', '2017-02-10'),
	('Codice2', 'da Consegnare', 'Francesca', 'Rossi', 'Strada Statale 109 della Piccola Sila, 18. 88050 Zagarise CZ', 'c@c.com', '2017-02-10'),
	('Codice20', 'da Consegnare', 'Ma', 'rio', 'Via università', 'a@a.com', '2017-02-13'),
	('Codice3', 'da Consegnare', 'Chiara', 'Russo', 'Via 24 Maggio, 3. 87012 Castrovillari CS', 'c@c.com', '2017-02-10'),
	('Codice4', 'da Consegnare', 'Salvatore', 'Monetti', 'Piazza 4 Novembre, 35. 88056 Tiriolo CZ', 'c@c.com', '2017-02-10'),
	('Codice5', 'da Consegnare', 'Francesco', 'Fusco', 'Viale Coniugi Crigna, 52. 89861 Tropea VV', 'c@c.com', '2017-02-10'),
	('Codice6', 'da Consegnare', 'Pinco', 'Pallino', 'Via Pietro Bucci, 30. Rende 87036 CS', 'c@c.com', '2017-02-10'),
	('Codice7', 'da Consegnare', 'Giulia', 'Maria', 'Via Carro, 32. 87036 Rende CS', 'c@c.com', '2017-02-10'),
	('Codice8', 'da Consegnare', 'Mario', 'Rossi', 'Via Popilia, 83L Cosenza 87100 CS', 'c@c.com', '2017-02-10'),
	('Codice9', 'da Consegnare', 'Gasparre', 'Fortunato', 'Viale I Maggio, 145. 88046 Lamezia Terme CZ', 'c@c.com', '2017-02-10'),
	('ekh', 'da Consegnare', 'ddd', 'aaaa', 'osojo', 'c@c.com', '2017-01-25'),
	('fs', 'da Consegnare', 'bfgd', 'bgf', 'bfd', 'c@c.com', '2017-01-24'),
	('gcgc', 'da Consegnare', 'kkkjk', 'kjijoijoi', 'sndh', 'c@c.com', '2017-01-25'),
	('gfdrrr', 'in Consegna', 'oiufu', 'gfhjko', 'iuyvnhvhg', 'c@c.com', '2017-01-27'),
	('heh', 'da Consegnare', 'dhdh', 'djsj', 'sjsj', 'c@c.com', '2017-01-26'),
	('jhhg', 'Reso', 'ikki', 'sspp', 'js ', 'c@c.com', '2017-01-27'),
	('kjdj', 'Consegna Completata', 'dkjk', 'jidu', 'abb', 'c@c.com', '2017-01-26'),
	('kjhhk', 'Reso', 'kkk', 'bbb', 'ooo', 'c@c.com', '2017-01-27'),
	('kjhsk', 'Reso', 'lkj', 'njuu', 'iiiiii', 'c@c.com', '2017-01-27'),
	('op', 'da Consegnare', 'jj', 'jj', 'jj', 'c@c.com', '2017-01-30'),
	('op2', 'Reso', 'jj', 'jj', 'jj', 'c@c.com', '2017-01-30'),
	('paccoDanneggiato', 'Reso', 'pro', 'oo', 'oo', 'c@c.com', '2017-01-30'),
	('paccoMancante', 'da Consegnare', 'prova', 'paccoM', 'MSn', 'c@c.com', '2017-01-30'),
	('Prova Pacco Danneggiato', 'Reso', 'jopopo', 'poipii', 'poipopo', 'c@c.com', '2017-01-27'),
	('Prova Pacco Mancante', 'da Consegnare', 'kjl', 'ijkjkj', 'kjhkjh', 'c@c.com', '2017-01-27'),
	('Prova Pacco Mancante 2', 'da Consegnare', 'lkjskjls', 'oidfjdjl', 'fdoijjid', 'c@c.com', '2017-01-27'),
	('prova1', 'da Consegnare', 'asd', 'asd', 'asd', 'c@c.com', '2017-02-20'),
	('prova2', 'da Consegnare', 'asd', 'sdf', 'sdf', 'c@c.com', '2017-02-13'),
	('prova4', 'Reso', 'asd', 'asd', 'asd', NULL, '2017-10-20'),
	('provaCorreggi', 'Consegnato', 'prova', 'correggi', 'dac', 'c@c.com', '2017-01-31'),
	('provaCorretta', 'Consegnato', 'j', 'j', 'j', 'c@c.com', '2017-01-30'),
	('provaData1', 'Reso', 'jjj', 'jjj', 'jjj', 'c@c.com', '2017-01-31'),
	('provaData2', 'Consegnato', 'jjj', 'jjjj', 'jjj', 'c@c.com', '2017-01-30'),
	('provaData3', 'Reso', 'kk', 'kk', 'kkk', 'c@c.com', '2017-01-30'),
	('provaMancataConsegna', 'Consegna in Ritardo', 'prova', 'MancataC', 'MC', 'c@c.com', '2017-02-01'),
	('provaRegConsegna', 'da Consegnare', 'kkk', 'kkk', 'kkk', 'c@c.com', '2017-01-28'),
	('ProvaRegConsegna2', 'Consegna in Ritardo', 'llljijij', 'ooo', 'oioii', 'c@c.com', '2017-01-28'),
	('ProvaRegConsegnaErr', 'Reso', 'jhjkhjk', 'kkkk', 'kkk', 'c@c.com', '2017-01-28'),
	('provaReso', 'Reso', 'prova', 'reso', 'da consegnare', 'c@c.com', '2017-01-30'),
	('provaUltima', 'in Consegna', 'jjj', 'ooo', 'oo', 'c@c.com', '2017-01-29'),
	('sbaglio di proposito', 'in Consegna', 'cancellare', 'oo', 'oo', 'c@c.com', '2017-01-29'),
	('Ultima pacco Mancante', 'da Consegnare', 'joijoi', 'iojioj', 'oijjoij', 'c@c.com', '2017-01-27'),
	('vb', 'da Consegnare', 'hj', 'fghj', 'dfgh', 'c@c.com', '2012-10-01'),
	('vdsfv', 'Reso', 'Mario', 'Rossi', 'via rella', NULL, '2017-04-12'),
	('xv', 'da Consegnare', 'nb', 'cvnb', 'cv', 'c@c.com', '2018-05-28');
/*!40000 ALTER TABLE `Spedizione` ENABLE KEYS */;

-- Dump della struttura di tabella sql7153338.Utente
CREATE TABLE IF NOT EXISTS `Utente` (
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Cognome` varchar(255) NOT NULL,
  `Tipologia` varchar(1) NOT NULL DEFAULT 'D',
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella sql7153338.Utente: ~5 rows (circa)
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;
INSERT INTO `Utente` (`Email`, `Password`, `Nome`, `Cognome`, `Tipologia`) VALUES
	('a@a.com', 'c', 'Ciccio', 'Pasticcio', 'C'),
	('c@c.com', 'c', 'Corri', 'Ere', 'C'),
	('d@d.com', 'd', 'Diri', 'Gente', 'D'),
	('g@g.com', 'c', 'Asdf', 'Fdsa', 'C'),
	('s@s.com', 's', 'Segre', 'Taria', 'S');
/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
