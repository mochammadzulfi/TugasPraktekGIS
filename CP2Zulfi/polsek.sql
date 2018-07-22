-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for emergency
DROP DATABASE IF EXISTS `emergency`;
CREATE DATABASE IF NOT EXISTS `emergency` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `emergency`;

-- Dumping structure for table emergency.data_atm
DROP TABLE IF EXISTS `data_atm`;
CREATE TABLE IF NOT EXISTS `data_atm` (
  `id_atm` int(10) NOT NULL AUTO_INCREMENT,
  `nama_atm` varchar(30) NOT NULL,
  `alamat_atm` varchar(50) NOT NULL,
  `kecamatan` char(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_atm`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_atm: ~12 rows (approximately)
/*!40000 ALTER TABLE `data_atm` DISABLE KEYS */;
INSERT INTO `data_atm` (`id_atm`, `nama_atm`, `alamat_atm`, `kecamatan`, `latitude`, `longitude`, `keterangan`, `gambar`) VALUES
	(2, 'ATM BNI - UTM', 'Jl. Raya Telang, Telang, Kamal, Kabupaten Bangkala', 'kamal', -7.1257649, 112.72157670000001, '', 'e69n6t0xdfosjrem0am5.png'),
	(3, 'ATM Bank Jatim', 'Jl. Nasional 21, Demangan, Kec. Bangkalan, Kabupat', 'kamal', -7.1253532, 112.7210895, '', 'z194scczhi7pjiadhul6.png'),
	(4, 'Bank BTN KCP Kamal', 'Jl. Raya Telang, Telang, Kamal, Kabupaten Bangkala', 'kamal', -7.125242716076371, 112.71933364232791, '', 'ghf6cchb2g06ot2hnfrc.png'),
	(5, 'Bank BTN Alfamart', 'Jl. Trunojoyo, Banyu Ajuh, Kamal, Kabupaten Bangka', 'kamal', -7.1548904088870575, 112.716951989981, '', 'gaqfe5vxbl612orqw9it.png'),
	(6, 'ATM BNI', 'Jl. Trunojoyo, Banyu Ajuh, Kamal, Kabupaten Bangka', 'kamal', -7.155647554607353, 112.71724837407714, '', 'dhjp086arl19mf0zoorc.png'),
	(7, 'ATM BCA', 'Jl. Trunojoyo, Banyu Ajuh, Kamal, Kabupaten Bangka', 'kamal', -7.156359456731105, 112.71733822807914, '', 'yoyo42bbz5x30h47jpd1.png'),
	(8, 'ATM Bank Jatim', 'Jl. Trunojoyo, Banyu Ajuh, Kamal, Kabupaten Bangka', 'kamal', -7.156939622874176, 112.7176010845626, '', 'reyo3ghcat5zqt7jtazg.png'),
	(9, 'ATM BRI', 'Gg. 1, Banyu Ajuh, Kamal, Kabupaten Bangkalan, Jaw', 'kamal', -7.159978829788957, 112.71917822346336, '', 'viq7wj9j0ppmvk5vdc6i.png'),
	(10, 'ATM BNI', 'Jl. Raya Kamal, Kamal, Kabupaten Bangkalan, Jawa T', 'kamal', -7.172409560651815, 112.72255512461311, '', 'ty8awzt87g874057s9va.png'),
	(11, 'ATM BTK UTM', 'Jl. Raya Telang, Telang, Kamal, Kabupaten Bangkala', 'kamal', -7.125831831729965, 112.72223594174034, '', '2socn5d9dcayydm4mjga.png'),
	(12, 'ATM BNI Bangkalan', 'Jl. Nasional 21, Demangan, Kec. Bangkalan, Kabupat', 'Bangkalan', -7.0514867, 112.7325019, '', 'j141t8v7nl2tjnx7auok.png'),
	(13, 'ATM MANDIRI', 'Jl. Nasional 21, Demangan, Kec. Bangkalan, Kabupat', 'Bangkalan', -7.0440992, 112.7370597, '', 'l687xl3z9jmohvr2uzp0.png');
/*!40000 ALTER TABLE `data_atm` ENABLE KEYS */;

-- Dumping structure for table emergency.data_bengkel
DROP TABLE IF EXISTS `data_bengkel`;
CREATE TABLE IF NOT EXISTS `data_bengkel` (
  `id_bengkel` int(10) NOT NULL AUTO_INCREMENT,
  `nama_bengkel` varchar(30) NOT NULL,
  `alamat_bengkel` varchar(50) NOT NULL,
  `kecamatan` char(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `fasilitas` varchar(100) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_bengkel`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_bengkel: ~0 rows (approximately)
/*!40000 ALTER TABLE `data_bengkel` DISABLE KEYS */;
INSERT INTO `data_bengkel` (`id_bengkel`, `nama_bengkel`, `alamat_bengkel`, `kecamatan`, `latitude`, `longitude`, `telepon`, `fasilitas`, `keterangan`, `gambar`) VALUES
	(1, 'Bengkel Taruna Jaya Motor', 'Jalan Kemayoran Baru', 'kamal', 0, 0, '089567543444', 'Tab oli, service', 'Hari libur tutup', 'pmmi8zdfpe7idkub2mjf.png');
/*!40000 ALTER TABLE `data_bengkel` ENABLE KEYS */;

-- Dumping structure for table emergency.data_kantorpemadam
DROP TABLE IF EXISTS `data_kantorpemadam`;
CREATE TABLE IF NOT EXISTS `data_kantorpemadam` (
  `id_kantorpemadam` int(11) NOT NULL AUTO_INCREMENT,
  `nama_kantorpemadam` varchar(50) NOT NULL,
  `alamat_kantorpemadam` varchar(100) NOT NULL,
  `kecamatan` varchar(20) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_kantorpemadam`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_kantorpemadam: ~2 rows (approximately)
/*!40000 ALTER TABLE `data_kantorpemadam` DISABLE KEYS */;
INSERT INTO `data_kantorpemadam` (`id_kantorpemadam`, `nama_kantorpemadam`, `alamat_kantorpemadam`, `kecamatan`, `latitude`, `longitude`, `telepon`, `keterangan`, `gambar`) VALUES
	(2, 'Kantor Pemadam Lokal', 'Jalan Utama Bangkalan', 'Kota', 0, 0, '0895675467890', 'Buka terus 20 jam', 'o7xd1az1x4vtlq7981ge.png'),
	(3, 'Kantor Pemadam Kabupaten Bangkalan', 'Jalan Kemayoran no 20', 'kamal', 0, 0, '089567545678', '', '96rfn4tbozjx29nkkrxy.jpg');
/*!40000 ALTER TABLE `data_kantorpemadam` ENABLE KEYS */;

-- Dumping structure for table emergency.data_kantorpolisi
DROP TABLE IF EXISTS `data_kantorpolisi`;
CREATE TABLE IF NOT EXISTS `data_kantorpolisi` (
  `id_kantorpolisi` int(10) NOT NULL AUTO_INCREMENT,
  `nama_kantorpolisi` varchar(30) NOT NULL,
  `alamat_kantorpolisi` varchar(50) NOT NULL,
  `kecamatan` char(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_kantorpolisi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_kantorpolisi: ~1 rows (approximately)
/*!40000 ALTER TABLE `data_kantorpolisi` DISABLE KEYS */;
INSERT INTO `data_kantorpolisi` (`id_kantorpolisi`, `nama_kantorpolisi`, `alamat_kantorpolisi`, `kecamatan`, `latitude`, `longitude`, `telepon`, `keterangan`, `gambar`) VALUES
	(2, 'Polsek Unair', 'Jl. Dharmawangsa Dalam Selatan No.30, Airlangga, G', 'kamal', -7.272420499251942, 112.75857241823246, '087282828812', '', '');
/*!40000 ALTER TABLE `data_kantorpolisi` ENABLE KEYS */;

-- Dumping structure for table emergency.data_masjid
DROP TABLE IF EXISTS `data_masjid`;
CREATE TABLE IF NOT EXISTS `data_masjid` (
  `id_masjid` int(10) NOT NULL AUTO_INCREMENT,
  `nama_masjid` varchar(30) NOT NULL,
  `alamat_masjid` varchar(100) NOT NULL,
  `kecamatan` char(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `fasilitas` varchar(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_masjid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_masjid: ~0 rows (approximately)
/*!40000 ALTER TABLE `data_masjid` DISABLE KEYS */;
INSERT INTO `data_masjid` (`id_masjid`, `nama_masjid`, `alamat_masjid`, `kecamatan`, `latitude`, `longitude`, `fasilitas`, `gambar`) VALUES
	(1, 'Masjid Almubarok', 'Jalan Mesegit no 20', 'Kota', 0, 0, 'Tempat wudhu. kamar mandi, WC', 'ngft3w4agpqeb0k6huw8.png');
/*!40000 ALTER TABLE `data_masjid` ENABLE KEYS */;

-- Dumping structure for table emergency.data_rs
DROP TABLE IF EXISTS `data_rs`;
CREATE TABLE IF NOT EXISTS `data_rs` (
  `id_rs` int(10) NOT NULL AUTO_INCREMENT,
  `nama_rs` varchar(50) NOT NULL,
  `alamat_rs` varchar(50) NOT NULL,
  `kecamatan` char(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `fasilitas` varchar(200) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_rs`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_rs: ~2 rows (approximately)
/*!40000 ALTER TABLE `data_rs` DISABLE KEYS */;
INSERT INTO `data_rs` (`id_rs`, `nama_rs`, `alamat_rs`, `kecamatan`, `latitude`, `longitude`, `telepon`, `fasilitas`, `gambar`) VALUES
	(1, 'Rumah Sakit Umum Kabupaten Bangkalan', 'Jl. Pemuda Kaffa Blok I No.18, Pejagan, Kec. Bangk', 'Kota', -7.029248136539202, 112.75900902733451, '087750123789', 'ICU, Kamar Mayat, UGD, Ambulance, kamar penginapan', 'k5rshj5c0q553u2cqn3y.png'),
	(2, 'Puskesmas Blega', 'Jl. Raya Blega No.33, Blega, Kabupaten Bangkalan, ', 'blega', -7.1268072674845575, 113.06692662224418, '087850342131', 'ICU, UGD, Ruang berAC', '4kub2mjfy6jajoaiegwq.png');
/*!40000 ALTER TABLE `data_rs` ENABLE KEYS */;

-- Dumping structure for table emergency.data_spbu
DROP TABLE IF EXISTS `data_spbu`;
CREATE TABLE IF NOT EXISTS `data_spbu` (
  `id_spbu` int(10) NOT NULL AUTO_INCREMENT,
  `nama_spbu` varchar(30) NOT NULL,
  `alamat_spbu` varchar(50) NOT NULL,
  `kecamatan` char(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `telepon_spbu` varchar(12) NOT NULL,
  `fasilitas` varchar(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_spbu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.data_spbu: ~10 rows (approximately)
/*!40000 ALTER TABLE `data_spbu` DISABLE KEYS */;
INSERT INTO `data_spbu` (`id_spbu`, `nama_spbu`, `alamat_spbu`, `kecamatan`, `latitude`, `longitude`, `telepon_spbu`, `fasilitas`, `gambar`) VALUES
	(1, 'SPBU Kamal 54 69 102', 'Jl. Trunojoyo, Banyu Ajuh, Kamal, Kabupaten Bangka', 'kamal', -7.156604297578695, 112.71783175453788, '', 'Toilet, Kamar mandi, pompa angin nitrogen, tidak ada indomaret', 'h90oyp7vzmki26yj4f56.jpg'),
	(2, 'SPBU Keleyan 54 69108', 'Jl. Nasional 21, Keleyan, Socah, Kabupaten Bangkal', 'socah', -7.0698692256395, 112.72055687889701, '', 'Fasilitas Toilet, Kamar mandi', 'tfpe7idkub2mjfy6jajo.jpg'),
	(3, 'Spbu Kota Pertamina ', 'Jl. Soekarno Hatta, Mlajah, Kec. Bangkalan, Kabupa', 'kamal', -7.0439211189352795, 112.73783030495292, '', 'Fasilitas : Menjual Oli, Kamar Mandi', 'vtlq7981gemift3w4agp.jpg'),
	(4, 'SPBU Pertamina PASTI PAS junok', 'Jl. Pemuda Kaffa No.18, Tunjung, Kec. Bangkalan, K', 'Bangkalan', -7.034923590391019, 112.7653470872367, '', 'Fasilitas : kamar mandi, toilet', 'qw91edtbbekdgrznah25.jpg'),
	(5, 'SPBU Pertamina Arosbaya', 'Jl. Raya Tengket, Tengket, Arosbaya, Kabupaten Ban', 'arosbaya', -6.953677090365561, 112.82373073086387, '', 'Fasilitas : Kamar Mandi, Toilet, Musholla', '3cnhb8wyq1nda5qyebkh.png'),
	(6, 'SPBU PERTAMINA tana mera', 'Jl. Nasional 21, Poter, Tanah Merah, Kabupaten Ban', 'Tana Mera', -7.070015625476753, 112.82646658405906, '', 'Fasilitas : Tidak ada', 'qeb0k6huw8kstn444vlj.jpg'),
	(7, ' SPBU By Pass Suramadu', 'Jl. H. Moh. Noer, Morkepek, Labang, Kabupaten Bang', 'labang', -7.128787408987833, 112.7906698225463, '089123456789', 'Fasilitas : toilet, pompa nistrogen.', '3cnhb8wyq1nda5qyebkh.png'),
	(8, 'SPBU 54 69106 tana mera', 'Jl. Raya Tanah Merah, Tanah Merah Dajah, Tanah Mer', 'Tana Mera', -7.0855470535592096, 112.87813665852195, '', 'Fasilitas : mini markrt, toillet, pompa angin nitrogen', 'z3y3yj89tj2xwym1w9ey.jpg'),
	(9, 'SPBU Pertamina Kwanyar', 'Jl. Modung - Kwanyar, Pesanggrahan, Kwanyar, Kabup', 'Kwanyar', -7.162624147519438, 112.86141576752311, '', 'Fasilitas : toilet, mini market, pompa kompresor', 'd7iutgwzw10lr01r49ng.png'),
	(10, 'SPBU PERTAMINA blega', 'Jl. Raya Blega, Karang Panasan, Blega, Kabupaten B', 'blega', -7.120057688622783, 113.07454409584648, '', 'Fasilitas : Mini marke, musholla, toile, kamar mandi', 'zoxu6uet2lqmptkrmthw.jpg');
/*!40000 ALTER TABLE `data_spbu` ENABLE KEYS */;

-- Dumping structure for table emergency.kecamatan
DROP TABLE IF EXISTS `kecamatan`;
CREATE TABLE IF NOT EXISTS `kecamatan` (
  `id_kecamatan` int(11) NOT NULL AUTO_INCREMENT,
  `nama_kecamatan` char(30) NOT NULL,
  PRIMARY KEY (`id_kecamatan`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.kecamatan: ~19 rows (approximately)
/*!40000 ALTER TABLE `kecamatan` DISABLE KEYS */;
INSERT INTO `kecamatan` (`id_kecamatan`, `nama_kecamatan`) VALUES
	(1, 'kamal'),
	(2, 'labang'),
	(3, 'socah'),
	(4, 'blega'),
	(5, 'arosbaya'),
	(7, 'Sepuluh'),
	(8, 'Tana Mera'),
	(9, 'Bangkalan'),
	(10, 'Kwanyar'),
	(11, 'Modung'),
	(12, 'Konang'),
	(13, 'Galis'),
	(14, 'Tragah'),
	(15, 'Burneh'),
	(16, 'Arosbaya'),
	(17, 'Geger'),
	(18, 'Kokop'),
	(19, 'Klampis'),
	(20, 'Tanjung Bumi');
/*!40000 ALTER TABLE `kecamatan` ENABLE KEYS */;

-- Dumping structure for table emergency.tbl_user
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id_user` int(10) NOT NULL,
  `nama_user` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `level` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table emergency.tbl_user: ~2 rows (approximately)
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`id_user`, `nama_user`, `username`, `password`, `level`) VALUES
	(1, 'Afan', 'admin', 'admin', 'admin'),
	(2, 'Zulfi', 'zulfi', 'zulfi', 'admin');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
