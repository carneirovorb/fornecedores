-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 21/12/2016 às 17:46
-- Versão do servidor: 10.1.16-MariaDB
-- Versão do PHP: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_fornecedores`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_fornecedores`
--

CREATE TABLE `tb_fornecedores` (
  `id` int(3) NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `endereco` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `tb_fornecedores`
--

INSERT INTO `tb_fornecedores` (`id`, `cnpj`, `nome`, `telefone`, `email`, `endereco`) VALUES
(1, '4444', 'vorb', '81878568', 'vorb@mail.com', 'rua tal'),
(2, '5312986', '', '81878568', '', ', , , '),
(3, '53129', 'voorb', '81878568', 'valber@gmail', 'rua prata, 28, bolivia, 454200'),
(4, '88854', 'vorbao', '8187', 'valber', 'arapiraca, 25, suzana, 4540'),
(5, '53129', 'carneiro valber', '7581', 'valbercarneiro@outlook.com', 'rua arapiraca, 385, suzna, 44380000'),
(6, '53129', 'vorbaoo', '7581', 'valbercarneiro@outlook.com', 'rua arapiraca, 385, suzna, 44380000'),
(7, '05312986550', '', '', '', ', , , '),
(8, '05312986550', 'carneirovorb', '75991784036', 'planctonalbino@gmail.com', 'rua arapiraca, 385, Suzana, 45400000');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `tb_fornecedores`
--
ALTER TABLE `tb_fornecedores`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `tb_fornecedores`
--
ALTER TABLE `tb_fornecedores`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
