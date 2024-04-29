-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 17 avr. 2024 à 14:34
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `managementFootYc`
--

-- --------------------------------------------------------

--
-- Structure de la table `app_role`
--

CREATE TABLE `app_role` (
  `id` bigint(20) NOT NULL,
  `name` enum('ADMIN','MEMBER','BDE','DELEGATE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `app_role`
--

INSERT INTO `app_role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'MEMBER'),
(3, 'BDE'),
(4, 'DELEGATE');

-- --------------------------------------------------------

--
-- Structure de la table `app_user`
--

CREATE TABLE `app_user` (
  `is_deleted` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `url_picture` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `app_user`
--

INSERT INTO `app_user` (`is_deleted`, `created_at`, `deleted_at`, `role_id`, `updated_at`, `id`, `class_name`, `email`, `first_name`, `last_name`, `password`, `url_picture`, `username`) VALUES
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0x0adcfd6c48a4494fa170873a924131f3, 'La casa de JS', 'ethan.lewis@example.com', 'Stanton', 'Rayleen', '$2a$10$jk8NMTqmriBmAzLAcZbvDemhoww7RhMHqH8V2TVly69uM97NwmT3G', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 's.rayleen9'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0x0ea21cbd14cc4568b25a0bd3e2bc0632, 'La casa de JS', 'benjamin.harris@example.com', 'Rojas', 'Aaron', '$2a$10$NqfLnLvx8yRXSsIHjIe5q.VqkujQPl4AoSrnU.02fu7UvTHGOP7jG', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'r.aaron72'),
(b'0', '2024-04-17 12:58:04.000000', NULL, 2, NULL, 0x120bd2b44cc8451d9fd8f308a9d9a3f7, 'Vander linde', 'gabriel.bell@example.com', 'Singleton', 'Raven', '$2a$10$CyFe9NQPE.YGpLn72rsReedafXdNIy2qX.FlBggL3NxqScOr598/m', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 's.raven34'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0x1ecc4c79f85340e18df467fbd7db8527, 'NAMEK', 'john.doe@example.com', 'Pierce', 'Heath', '$2a$10$uEWWU/tZI5KsBrYerj5BAO2MRp719frvMPnzBTememxY/lFFOFNJy', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'p.heath70'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0x2080f263ca5d4737ac4400f2ecc24e77, 'NAMEK', 'm.moussafia99@gmail.com', 'moussafia', 'mohammed', '$2a$10$qajwl/PEEURKkvI8b1WU7uzrAiPiaKEmmHWdGDEAAPA27q4GT6oOu', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'm.mohammed36'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0x2cb8d04165664a338b841ea1af912b43, 'La casa de JS', 'matthew.edwards@example.com', 'Calhoun', 'Ace', '$2a$10$nxxEF44D7VbdDjAg4ZywROx9QBminTduz35id0J6n6dw2tDyDWK9S', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'c.ace43'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0x446717b361e24f16aff04f0d3cc21e20, 'Vander linde', 'logan.nelson@example.com', 'Marquez', 'Vincent', '$2a$10$rRxuStvD9.pDvdrJ40ApNOGXYVgV3ifOSUP1UUoiEzB7QKFHq5aXS', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'm.vincent78'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0x61f14cefbe384edba9a33ff914311654, 'La casa de JS', 'samuel.morris@example.com', 'Michael', 'Gavin', '$2a$10$.k2i4.vGeXcKnND/YR4IgeinlEf.wmQkFTiczw5tzne94bFWzMOZm', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'm.gavin58'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0x660cf4430d0544eab5af264507773aaf, 'NAMEK', 'david.jones@example.com', 'Booker', 'Korin', '$2a$10$InqeUTCdTqRuux.wJLs95uUN7R5H9f21qk87s/I78G0Smyh4BNGJa', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'b.korin5'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0x69e79cfa256c4c7aa56f38da20c16a9c, 'NAMEK', 'jane.smith@example.com', 'Harrell', 'Marcellus', '$2a$10$4UCL.VzFQVnj.hH6trz0W.QoJHOiNw3.a492SZeOIKIl.AK9a/lhu', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'h.marcellus97'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0x6ec6a6e17e1445fa8b7451c3b5839cfc, 'La casa de JS', 'james.martin@example.com', 'Lucero', 'Julina', '$2a$10$wiAeeYMWc1LldhvGC6l77uMRjMcKffPlyuvBJ525gVRHUJIxSY64i', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'l.julina87'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0x7a640728923b4ff48819d7074c19c430, 'La casa de JS', 'ava.thompson@example.com', 'Mccoy', 'Lee', '$2a$10$WR4GR9ClWwoctZxq4nS/iOJNa6nHvIHAtzqBR3VUSV91eCPVgJ/g.', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'm.lee51'),
(b'0', '2024-04-17 12:58:04.000000', NULL, 2, NULL, 0x7f510d52d1704a2982262477720a7631, 'Vander linde', 'evelyn.ward@example.com', 'Dorsey', 'Brighton', '$2a$10$5iPN.a46ClsPjfd.UX1AteDloR1twXFxd0b14C.TlxuimbchwWMra', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'd.brighton12'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0x81523e1a17d54b41a3b95f3cfb9479f5, 'NAMEK', 'moussafia.mohammed1999@gmail.com', 'ahmed', 'moussafia', '$2a$10$jUGFrsRVvCVmrt..iMB.ZOuQOMC9efz5nrB6adtG4YW3R0dyCplGu', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'a.moussafia46'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0x960bc95506c44c1b991d7ece47ba2e35, 'La casa de JS', 'mia.robinson@example.com', 'Navarro', 'Nicolas', '$2a$10$j1pal7epH83QE6AjY22TO.ogG8j1kJonuRuIpy.cVl495mIktxSZe', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'n.nicolas40'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0x9946c22037fa428cb09b45a60fc00204, 'NAMEK', 'michael.wilson@example.com', 'Campbell', 'Taye', '$2a$10$QE.IUtCWBAUBHg9I7vE5rev7aKuB8Kxu6Mp8s/LIR7N/U.QSrV77u', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'c.taye55'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0x9b5974f448e84d02881b4fbd57ebad2e, 'Vander linde', 'aiden.cooper@example.com', 'Gallegos', 'Alice', '$2a$10$S4eeV6iRCdOzRv1YNgPmN.eXmbcprW5owW5g3bbStFOGo2donABni', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'g.alice56'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0x9cd29456ad95415d9c1c531e24452d36, 'La casa de JS', 'zoey.carter@example.com', 'Bell', 'Luke', '$2a$10$frpDicZxY4LDRBCKTQ9Swe06F2EZZ23GcoZSyqPFWDZ6CWc/Ecq/W', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'b.luke14'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xa299d0898159462fb21f9de658b0c470, 'Vander linde', 'harper.king@example.com', 'Howell', 'Tyson', '$2a$10$drGNjKvAVntH9va6BjILqucltw9bfwQNJ4yageSIh9dkUSnt.4FdW', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'h.tyson13'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xa455d86b4e49452aa6ef8331469895e5, 'Vander linde', 'avery.howard@example.com', 'David', 'Kate', '$2a$10$NaKgENBAKLjmW3pXFIvC2.OqX6D/6JBf9645GA3lK8Ga9DKfi1Yjy', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'd.kate0'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xb319822683b74a978599bd365dfccdb1, 'La casa de JS', 'grace.collins@example.com', 'Tucker', 'Lydon', '$2a$10$kFvG4NLZCaLjKmvtBfJQmOtUELIFenwbAYHqx0WAVIBNZj5jx1uLi', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 't.lydon76'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xba648ea6558147b3a6d9e3a024a32e55, 'NAMEK', 'olivia.thomas@example.com', 'Browning', 'Zane', '$2a$10$it6tt.o8p37si9IG9fdjNOYrRetHNH1W7aKuazaWPBNWj3HVa29r.', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'b.zane15'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xbb2a57141f3447e4a3670f76d580ef6d, 'La casa de JS', 'jackson.lee@example.com', 'Hammond', 'Clementine', '$2a$10$E0xtQo7oA/CxnCiiEFJ4vOU7rhURN.CsxahAS/TiKARmPNKDuw7YO', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'h.clementine64'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xbb40c6d7d4dc4348ab3f15b88a0d81a1, 'Vander linde', 'lucas.ross@example.com', 'Lam', 'Monteen', '$2a$10$eAxOtebih4DbbAhHnZTOJe6f101rXeikXcPq1jIIzvawISjH3u3uq', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'l.monteen12'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xbd60a7166a6043ffa68743d1d23acf11, 'NAMEK', 'sarah.miller@example.com', 'Joyce', 'Rebecca', '$2a$10$FAwuG4q.Kf7plxMr2ex6duHIV.bjvB1S8hVyViBNWCYL7usGXtaXi', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'j.rebecca96'),
(b'0', '2024-04-17 12:58:04.000000', NULL, 2, NULL, 0xbf25576463324af88924718907847e2b, 'Vander linde', 'riley.murphy@example.com', 'Hart', 'Susannah', '$2a$10$Q/7NRDaE0/eodqNm21jPmOZI6hvphqDQysTQXp7r1xhZjgwe8Af96', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'h.susannah67'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xc7fd616afe3544fd859e4450d79bd51f, 'NAMEK', 'laura.davis@example.com', 'Jordan', 'Bree', '$2a$10$sh4.zK5Dj7HSiHbWaskxhuPV6A76ZpgXhbszsSPPonhqEUq.v282S', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'j.bree51'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xc9b477ea0d4846e6805fbd6ed6155c7f, 'La casa de JS', 'lily.hall@example.com', 'Mccarty', 'Matteo', '$2a$10$ptuUm00RUrpVBaQ3bvUd7eMong2XBHSEi.k.e99H49XYVCn7DGeT2', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'm.matteo25'),
(b'0', '2024-04-17 12:58:04.000000', NULL, 2, NULL, 0xccaa6a7acece44e2812e0ad0a30eface, 'Vander linde', 'jaxon.richards@example.com', 'Huang', 'Cameron', '$2a$10$dAMVGlcF6dDtE8qr6lEiFeqGqDae6/agUywB8/En5DFp5GcHZeKWK', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'h.cameron61'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xd55b7f6dbee545618936d7630368eb7f, 'La casa de JS', 'alexander.hill@example.com', 'Weeks', 'Jordon', '$2a$10$iTKV3HKxjQCIxVbm15gVCexKmUdAvbsM0C57Go7BHRBSso4crC4RO', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'w.jordon29'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xda89d0298cc249a78dc6562a1819041f, 'NAMEK', 'robert.taylor@example.com', 'Carroll', 'Aiden', '$2a$10$FZhqj5HQrBbDD75S3v9/suydlTqhttOsKzzYNRHc27hDrMDJ8X2Jm', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'c.aiden7'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xdd536cf839e54b119fefdd062a61c912, 'La casa de JS', 'sophia.white@example.com', 'Bowers', 'Felix', '$2a$10$cyQrcggKv5IbvYsha.7U1uVymlSd04emz12eFUs5YwWeyTQNyRu.C', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'b.felix95'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xe81ede3af96a4c9a8bbcbba2a6bb8447, 'La casa de JS', 'daniel.clark@example.com', 'Holder', 'Blanche', '$2a$10$b2afD9MYOy2S/OCRk44iIe7dE14BnV9YazDTlTB2PzDDa4uXx9jWO', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'h.blanche57'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xeedae10a57134059bd181e0f7f425381, 'La casa de JS', 'madison.baker@example.com', 'Little', 'Cash', '$2a$10$lF4Xw0nWhcgFs2s6iLFCkOr1sxoAZ3zRAlptieIznjPktjnUO5Az6', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'l.cash52'),
(b'0', '2024-04-17 12:58:04.000000', NULL, 2, NULL, 0xf0b08007629f4fcebe4f770a754c2e49, 'Vander linde', 'layla.kelly@example.com', 'Clayton', 'Anthony', '$2a$10$bpsvQ7ttViBwTRSJj2J50uhVoqjQl4LAVn.bi0AytzWaaRRV/mQM.', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'c.anthony83'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xf15c5fcd25a3465cb0e2c0915099e660, 'La casa de JS', 'emma.walker@example.com', 'Frey', 'Berlynn', '$2a$10$zIiYvblq0QrGHC6v.mgI/ORhnzF7vSx7tdfyqTB9BqW7JbA/JSXOq', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'f.berlynn41'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xf1d12efc4fa8414bac6c86411f61c0a9, 'NAMEK', 'jacob.anderson@example.com', 'Mercado', 'Harrison', '$2a$10$NhONorDooccnBkpKxUM47.ToGQidXT9RGkVF.cv.2CLalzVsCI0tS', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'm.harrison38'),
(b'0', '2024-04-17 12:58:03.000000', NULL, 2, NULL, 0xf547d9ecb8ec427b8a1c94f75c73bd3d, 'La casa de JS', 'chloe.green@example.com', 'Carney', 'Arthur', '$2a$10$YVPPvpwozglphr7zJhInY.SBLAyWEv0MQqFMwYb1fsmeoHqfer4mC', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'c.arthur43'),
(b'0', '2024-04-17 12:58:02.000000', NULL, 2, NULL, 0xf7e53996c527499a85efd51f2c88df31, 'NAMEK', 'william.jackson@example.com', 'Osborn', 'Xavier', '$2a$10$XWgsPZ6S3aZI3VZ7xNFOiuDUV8gtC6Pk9EbURaZWpW03C4GCaDHJa', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'o.xavier50'),
(b'0', '2024-04-17 12:58:01.000000', NULL, 2, NULL, 0xfa193d31138849b29ece14790fa2a541, 'NAMEK', 'emily.brown@example.com', 'Odom', 'Janetta', '$2a$10$TRXLq1BWFUCIgZNy7MI4e.ZJgv0edV2M9Fnn1idrZV6A9CFZJTmGC', 'https://intranet.youcode.ma/storage/users/profile/395-1662642887.JPG', 'o.janetta44');

-- --------------------------------------------------------

--
-- Structure de la table `group`
--

CREATE TABLE `group` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `match`
--

CREATE TABLE `match` (
  `date` date DEFAULT NULL,
  `end_date_match` time(6) DEFAULT NULL,
  `start_date_match` time(6) DEFAULT NULL,
  `arbitrator_id` binary(16) DEFAULT NULL,
  `code_match` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `match_team`
--

CREATE TABLE `match_team` (
  `is_draw` bit(1) NOT NULL,
  `is_passed` bit(1) NOT NULL,
  `is_win` bit(1) NOT NULL,
  `level` tinyint(4) DEFAULT NULL,
  `result` int(11) NOT NULL,
  `match_id` binary(16) NOT NULL,
  `team_id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

CREATE TABLE `participant` (
  `date_of_creation` datetime(6) DEFAULT NULL,
  `team_id` binary(16) NOT NULL,
  `user_id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `participant`
--

INSERT INTO `participant` (`date_of_creation`, `team_id`, `user_id`) VALUES
('2024-04-17 13:07:03.000000', 0x056a6d75091243048ea54654a595a649, 0x0adcfd6c48a4494fa170873a924131f3),
('2024-04-17 13:07:03.000000', 0x056a6d75091243048ea54654a595a649, 0x0ea21cbd14cc4568b25a0bd3e2bc0632),
('2024-04-17 13:07:03.000000', 0x056a6d75091243048ea54654a595a649, 0x6ec6a6e17e1445fa8b7451c3b5839cfc),
('2024-04-17 13:07:03.000000', 0x056a6d75091243048ea54654a595a649, 0x9946c22037fa428cb09b45a60fc00204),
('2024-04-17 13:07:03.000000', 0x056a6d75091243048ea54654a595a649, 0xfa193d31138849b29ece14790fa2a541),
('2024-04-17 13:33:54.000000', 0x0fda20450806474eb334dde847118403, 0x120bd2b44cc8451d9fd8f308a9d9a3f7),
('2024-04-17 13:33:54.000000', 0x0fda20450806474eb334dde847118403, 0x7f510d52d1704a2982262477720a7631),
('2024-04-17 13:33:54.000000', 0x0fda20450806474eb334dde847118403, 0xbf25576463324af88924718907847e2b),
('2024-04-17 13:33:54.000000', 0x0fda20450806474eb334dde847118403, 0xccaa6a7acece44e2812e0ad0a30eface),
('2024-04-17 13:33:54.000000', 0x0fda20450806474eb334dde847118403, 0xf0b08007629f4fcebe4f770a754c2e49),
('2024-04-17 13:17:17.000000', 0x208106c342964f07920e1bdfaaf9cef5, 0xd55b7f6dbee545618936d7630368eb7f),
('2024-04-17 13:17:17.000000', 0x208106c342964f07920e1bdfaaf9cef5, 0xda89d0298cc249a78dc6562a1819041f),
('2024-04-17 13:17:17.000000', 0x208106c342964f07920e1bdfaaf9cef5, 0xdd536cf839e54b119fefdd062a61c912),
('2024-04-17 13:17:17.000000', 0x208106c342964f07920e1bdfaaf9cef5, 0xf15c5fcd25a3465cb0e2c0915099e660),
('2024-04-17 13:17:17.000000', 0x208106c342964f07920e1bdfaaf9cef5, 0xf1d12efc4fa8414bac6c86411f61c0a9),
('2024-04-17 13:20:06.000000', 0x56fcdaa42c9942aa84cacc1b78950dfb, 0x2cb8d04165664a338b841ea1af912b43),
('2024-04-17 13:20:06.000000', 0x56fcdaa42c9942aa84cacc1b78950dfb, 0x446717b361e24f16aff04f0d3cc21e20),
('2024-04-17 13:20:06.000000', 0x56fcdaa42c9942aa84cacc1b78950dfb, 0x61f14cefbe384edba9a33ff914311654),
('2024-04-17 13:20:06.000000', 0x56fcdaa42c9942aa84cacc1b78950dfb, 0x9b5974f448e84d02881b4fbd57ebad2e),
('2024-04-17 13:20:06.000000', 0x56fcdaa42c9942aa84cacc1b78950dfb, 0xf7e53996c527499a85efd51f2c88df31),
('2024-04-17 13:31:37.000000', 0x7ad46bc254414f83bac02540c9f09a1f, 0xbb40c6d7d4dc4348ab3f15b88a0d81a1),
('2024-04-17 13:31:37.000000', 0x7ad46bc254414f83bac02540c9f09a1f, 0xc9b477ea0d4846e6805fbd6ed6155c7f),
('2024-04-17 13:31:37.000000', 0x7ad46bc254414f83bac02540c9f09a1f, 0xe81ede3af96a4c9a8bbcbba2a6bb8447),
('2024-04-17 13:31:37.000000', 0x7ad46bc254414f83bac02540c9f09a1f, 0xeedae10a57134059bd181e0f7f425381),
('2024-04-17 13:31:37.000000', 0x7ad46bc254414f83bac02540c9f09a1f, 0xf547d9ecb8ec427b8a1c94f75c73bd3d),
('2024-04-17 13:01:15.000000', 0x7e3164722f8043f3a3e7e52d38f0b17b, 0x1ecc4c79f85340e18df467fbd7db8527),
('2024-04-17 13:01:15.000000', 0x7e3164722f8043f3a3e7e52d38f0b17b, 0x2080f263ca5d4737ac4400f2ecc24e77),
('2024-04-17 13:01:15.000000', 0x7e3164722f8043f3a3e7e52d38f0b17b, 0x660cf4430d0544eab5af264507773aaf),
('2024-04-17 13:01:15.000000', 0x7e3164722f8043f3a3e7e52d38f0b17b, 0x69e79cfa256c4c7aa56f38da20c16a9c),
('2024-04-17 13:01:15.000000', 0x7e3164722f8043f3a3e7e52d38f0b17b, 0x81523e1a17d54b41a3b95f3cfb9479f5),
('2024-04-17 13:14:21.000000', 0xbc24b4e4495e40709e7b96870925223b, 0x7a640728923b4ff48819d7074c19c430),
('2024-04-17 13:14:21.000000', 0xbc24b4e4495e40709e7b96870925223b, 0x960bc95506c44c1b991d7ece47ba2e35),
('2024-04-17 13:14:21.000000', 0xbc24b4e4495e40709e7b96870925223b, 0xba648ea6558147b3a6d9e3a024a32e55),
('2024-04-17 13:14:21.000000', 0xbc24b4e4495e40709e7b96870925223b, 0xbd60a7166a6043ffa68743d1d23acf11),
('2024-04-17 13:14:21.000000', 0xbc24b4e4495e40709e7b96870925223b, 0xc7fd616afe3544fd859e4450d79bd51f),
('2024-04-17 13:22:23.000000', 0xea8ee221cff94ba0ade9fc9aeda2c96f, 0x9cd29456ad95415d9c1c531e24452d36),
('2024-04-17 13:22:23.000000', 0xea8ee221cff94ba0ade9fc9aeda2c96f, 0xa299d0898159462fb21f9de658b0c470),
('2024-04-17 13:22:23.000000', 0xea8ee221cff94ba0ade9fc9aeda2c96f, 0xa455d86b4e49452aa6ef8331469895e5),
('2024-04-17 13:22:23.000000', 0xea8ee221cff94ba0ade9fc9aeda2c96f, 0xb319822683b74a978599bd365dfccdb1),
('2024-04-17 13:22:23.000000', 0xea8ee221cff94ba0ade9fc9aeda2c96f, 0xbb2a57141f3447e4a3670f76d580ef6d);

-- --------------------------------------------------------

--
-- Structure de la table `rules`
--

CREATE TABLE `rules` (
  `id` bigint(20) NOT NULL,
  `description` TEXT DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `team`
--

CREATE TABLE `team` (
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `logo_public_id` varchar(255) DEFAULT NULL,
  `name_team` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `team`
--

INSERT INTO `team` (`created_at`, `deleted_at`, `updated_at`, `id`, `logo`, `logo_public_id`, `name_team`) VALUES
('2024-04-17 13:07:03.000000', NULL, NULL, 0x056a6d75091243048ea54654a595a649, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713355623/logo_team/ghaajyhf13ta1tvxcbcg.jpg', 'logo_team/ghaajyhf13ta1tvxcbcg', '2B !! 2B'),
('2024-04-17 13:33:54.000000', NULL, NULL, 0x0fda20450806474eb334dde847118403, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713357233/logo_team/twcubdciybtsntv6lx0z.png', 'logo_team/twcubdciybtsntv6lx0z', 'PIXEL WARRIORS'),
('2024-04-17 13:17:17.000000', NULL, NULL, 0x208106c342964f07920e1bdfaaf9cef5, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713356236/logo_team/qmxyczwmxjcawvf7l9hm.png', 'logo_team/qmxyczwmxjcawvf7l9hm', 'GENEI RYODAN'),
('2024-04-17 13:20:06.000000', NULL, NULL, 0x56fcdaa42c9942aa84cacc1b78950dfb, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713356405/logo_team/wlqt0ifym8cmhhmm34ne.jpg', 'logo_team/wlqt0ifym8cmhhmm34ne', 'LA CASA DEL JS'),
('2024-04-17 13:31:37.000000', NULL, NULL, 0x7ad46bc254414f83bac02540c9f09a1f, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713357096/logo_team/ytygkgleqm2szmodjqiy.png', 'logo_team/ytygkgleqm2szmodjqiy', 'CODERS STRIKE'),
('2024-04-17 13:01:15.000000', NULL, NULL, 0x7e3164722f8043f3a3e7e52d38f0b17b, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713355273/logo_team/iouo3bonmws0euwwfyxa.jpg', 'logo_team/iouo3bonmws0euwwfyxa', 'Namek'),
('2024-04-17 13:14:21.000000', NULL, NULL, 0xbc24b4e4495e40709e7b96870925223b, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713356060/logo_team/hqdjluoaxoqrhy7moojr.png', 'logo_team/hqdjluoaxoqrhy7moojr', 'DÉVELOPPEUR DATA'),
('2024-04-17 13:22:23.000000', NULL, NULL, 0xea8ee221cff94ba0ade9fc9aeda2c96f, 'http://res.cloudinary.com/dncljj3e2/image/upload/v1713356543/logo_team/o8trrjlhpif98weyygs9.png', 'logo_team/o8trrjlhpif98weyygs9', 'VAN DER LINDE');

-- --------------------------------------------------------

--
-- Structure de la table `team_group`
--

CREATE TABLE `team_group` (
  `draws` int(11) DEFAULT NULL,
  `is_passed` bit(1) DEFAULT NULL,
  `losses` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `wins` int(11) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `team_id` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `app_role`
--
ALTER TABLE `app_role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1j9d9a06i600gd43uu3km82jw` (`email`),
  ADD UNIQUE KEY `UK_3k4cplvh82srueuttfkwnylq0` (`username`),
  ADD KEY `FK7j4aje90v4gyxjhm3eg99m5p2` (`role_id`);

--
-- Index pour la table `group`
--
ALTER TABLE `group`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `match`
--
ALTER TABLE `match`
  ADD PRIMARY KEY (`code_match`),
  ADD KEY `FKs3b9cirddsw1gnx4mj772q08h` (`arbitrator_id`);

--
-- Index pour la table `match_team`
--
ALTER TABLE `match_team`
  ADD PRIMARY KEY (`match_id`,`team_id`),
  ADD KEY `FKi3usjlhave79uq8hbodsqsth1` (`team_id`);

--
-- Index pour la table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`team_id`,`user_id`),
  ADD KEY `FKg8x4qky5kbfthhoyurrm5wo1o` (`user_id`);

--
-- Index pour la table `rules`
--
ALTER TABLE `rules`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bm96oxmsopgcvwpufgj5n0j1s` (`name_team`);

--
-- Index pour la table `team_group`
--
ALTER TABLE `team_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe7774ppa0qsfv51jlq7l7jl6l` (`group_id`),
  ADD KEY `FK93se3m31apnn0brek4r4e4b3s` (`team_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `app_role`
--
ALTER TABLE `app_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `group`
--
ALTER TABLE `group`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `rules`
--
ALTER TABLE `rules`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `team_group`
--
ALTER TABLE `team_group`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `app_user`
--
ALTER TABLE `app_user`
  ADD CONSTRAINT `FK7j4aje90v4gyxjhm3eg99m5p2` FOREIGN KEY (`role_id`) REFERENCES `app_role` (`id`);

--
-- Contraintes pour la table `match`
--
ALTER TABLE `match`
  ADD CONSTRAINT `FKs3b9cirddsw1gnx4mj772q08h` FOREIGN KEY (`arbitrator_id`) REFERENCES `app_user` (`id`);

--
-- Contraintes pour la table `match_team`
--
ALTER TABLE `match_team`
  ADD CONSTRAINT `FK9d91gvibhq4egikgdfv8pogpe` FOREIGN KEY (`match_id`) REFERENCES `match` (`code_match`),
  ADD CONSTRAINT `FKi3usjlhave79uq8hbodsqsth1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`);

--
-- Contraintes pour la table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `FKbe8iv24dwl7nlyuhnwy9c44tt` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  ADD CONSTRAINT `FKg8x4qky5kbfthhoyurrm5wo1o` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`);

--
-- Contraintes pour la table `team_group`
--
ALTER TABLE `team_group`
  ADD CONSTRAINT `FK93se3m31apnn0brek4r4e4b3s` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  ADD CONSTRAINT `FKe7774ppa0qsfv51jlq7l7jl6l` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
