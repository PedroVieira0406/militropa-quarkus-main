INSERT INTO endereco(numero, cep, cidade, estado, logradouro, nome, bairro, complemento)
VALUES 
    (123, '77000-000', 'Palmas', 'TO', 'Rua das Flores', 'Casa Azul', 'Centro', 'kitnet 4'),
    (456, '77001-001', 'Gurupi', 'TO', 'Avenida dos Bandeirantes', 'Casa Verde', 'Jardim Bela Vista', 'Esquina com a Rua Principal'),
    (789, '77002-002', 'Araguaína', 'TO', 'Travessa das Oliveiras', 'Casa Amarela', 'Setor Norte', 'Ao lado da praça'),
    (101, '77003-003', 'Porto Nacional', 'TO', 'Rua das Águias', 'Casa Vermelha', 'Centro', 'Próximo à Escola Municipal'),
    (202, '77004-004', 'Paraíso do Tocantins', 'TO', 'Avenida das Rosas', 'Casa Rosa', 'Jardim Primavera', 'Próximo ao Supermercado'),
    (303, '77005-005', 'Colinas do Tocantins', 'TO', 'Alameda dos Ipês', 'Casa Lilás', 'Centro', 'Ao lado do Banco do Brasil'),
    (124, '77006-006', 'Palmas', 'TO', 'Rua das Flores', 'Casa Azul', 'Centro', 'kitnet 4'),
    (457, '77007-007', 'Gurupi', 'TO', 'Avenida dos Bandeirantes', 'Casa Verde', 'Jardim Bela Vista', 'Esquina com a Rua Principal'),
    (790, '77008-008', 'Araguaína', 'TO', 'Travessa das Oliveiras', 'Casa Amarela', 'Setor Norte', 'Ao lado da praça'),
    (102, '77009-009', 'Porto Nacional', 'TO', 'Rua das Águias', 'Casa Vermelha', 'Centro', 'Próximo à Escola Municipal'),
    (203, '770100', 'Paraíso do Tocantins', 'TO', 'Avenida das Rosas', 'Casa Rosa', 'Jardim Primavera', 'Próximo ao Supermercado'),
    (304, '770111', 'Colinas do Tocantins', 'TO', 'Alameda dos Ipês', 'Casa Lilás', 'Centro', 'Ao lado do Banco do Brasil');


insert into acabamento(
    nome
)
values
('Nenhum'),('Ouro'),('Prata'),('Cobre'),('Madeira'),('Diamante'),('Chumbo'),('Almas'),('Desespero'),('Alegria'),('Sangue'),('Esmeralda'),('Platina');

-- Atualizando tabela cliente para refletir a nova estrutura
INSERT INTO cliente (
    numeroregistro_posse_porte, cpf, nome, email, id_endereco
)
VALUES
    ('1234567890', '111.111.111-11', 'André', 'andre@example.com', 1),
    ('2345678901', '222.222.222-22', 'Bene', 'bene@example.com', 2),
    ('3456789012', '333.333.333-33', 'Hirosh', 'hirosh@example.com', 3),
    ('4567890123', '444.444.444-44', 'Heitor', 'heitor@example.com', 4),
    ('5678901234', '555.555.555-55', 'Henrique', 'henrique@example.com', 5),
    ('6789012345', '666.666.666-66', 'Junin', 'junin@example.com', 6),
    ('7890123456', '777.777.777-77', 'Maria do Socorro', 'maria@example.com', 7),
    ('8901234567', '888.888.888-88', 'Aline', 'aline@example.com', 8),
    ('9012345678', '999.999.999-99', 'Fernanda', 'fernanda@example.com', 9);

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES

    (15, 2000.00, 30, 1, 'Smith & Wesson Model 686', 'SW686-001', '6"', 'Smith & Wesson', 'Revólver .357 Magnum', 'Um revólver clássico, conhecido por sua confiabilidade e precisão.', '.357 Magnum', 'SW001', 'RN001','Smith_e_Wesson.png'),
    (30, 1500.00, 20, 4, 'Colt AR-15', 'COLTAR15-002', '16"', 'Colt', 'Rifle AR-15', 'Um rifle semiautomático, amplamente utilizado para esportes de tiro e caça.', '5.56mm', 'COLT002', 'RN002','coltAr.png'),
    (8, 800.00, 25, 2, 'Glock 19', 'GLOCK19-003', '4"', 'Glock', 'Pistola Glock 19', 'Uma pistola compacta, popular entre forças policiais e civis.', '9mm', 'GLK003', 'RN003','glock19.png'),
    (5, 1200.00, 15, 6, 'Remington 870', 'REM870-004', '24"', 'Remington', 'Escopeta Remington 870', 'Uma escopeta de bombeamento clássica, amplamente utilizada para caça e defesa.', '12 gauge', 'REM004', 'RN004','Remington_870.png'),
    (20, 1800.00, 10, 4, 'Springfield M1A', 'SPFM1A-005', '18"', 'Springfield Armory', 'Carabina Springfield M1A', 'Uma carabina semiautomática, derivada do M14, conhecida por sua precisão.', '7.62mm NATO', 'SPF005', 'RN005', 'Springfield_M1A.png'),
    (25, 2500.00, 12, 5, 'Heckler & Koch MP5', 'HKMP5-006', '10"', 'Heckler & Koch', 'Submetralhadora MP5', 'Uma submetralhadora compacta, amplamente utilizada por forças policiais e militares em todo o mundo.', '9mm', 'HCK006', 'RN006', 'MP5.png'),
    (30, 3500.00, 8, 7, 'FN SCAR', 'FNSCAR-007', '20"', 'FN Herstal', 'Fuzil FN SCAR', 'Um fuzil de assalto moderno e versátil, utilizado por forças especiais em todo o mundo.', '5.56mm', 'FNS007', 'RN007', 'SCAR.png'),
    (6, 1000.00, 18, 1, 'Smith & Wesson Model 637', 'SW637-008', '4"', 'Smith & Wesson', 'Revólver .38 Special', 'Um revólver compacto, ideal para uso como arma de backup ou defesa pessoal.', '.38 Special', 'SW002', 'RN008','Smith_E_Wesson_Model_637.png');

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES

    -- Revólveres
    (6, 1200.00, 20, 1, 'Colt Python', 'PYTHON-001', '6"', 'Colt', 'Revólver Colt Python', 'Revólver famoso pela precisão e acabamento premium.', '.357 Magnum', 'COLT001', 'RN009', 'Colt_Python.png'),
    (6, 1300.00, 15, 1, 'Ruger GP100', 'GP100-002', '4"', 'Ruger', 'Revólver Ruger GP100', 'Resistente e confiável, ideal para defesa pessoal.', '.357 Magnum', 'RGR002', 'RN010', 'Ruger_GP100.png'),
    (6, 1100.00, 25, 1, 'Taurus 85', 'TAURUS85-003', '3"', 'Taurus', 'Revólver Taurus 85', 'Compacto e leve, perfeito para porte velado.', '.38 Special', 'TRS003', 'RN011', 'Taurus_85.png'),
    (5, 1500.00, 10, 1, 'Smith & Wesson Model 29', 'SW29-004', '8"', 'Smith & Wesson', 'Revólver Smith & Wesson Model 29', 'Famoso pelo calibre poderoso .44 Magnum.', '.44 Magnum', 'SW004', 'RN012', 'Smith_Wesson_29.png'),
    (5, 1400.00, 12, 1, 'Taurus Raging Bull', 'TRB-005', '6"', 'Taurus', 'Revólver Taurus Raging Bull', 'Alta potência para caça e defesa.', '.44 Magnum', 'TRS005', 'RN013', 'Taurus_Raging_Bull.png'),
    (6, 1700.00, 14, 1, 'Rossi .357', 'ROSSI357-006', '5"', 'Rossi', 'Revólver Rossi .357', 'Ideal para prática de tiro esportivo.', '.357 Magnum', 'RSS006', 'RN014', 'Rossi_357.png'),
    (5, 1600.00, 18, 1, 'Smith & Wesson Model 19', 'SW19-007', '4"', 'Smith & Wesson', 'Revólver Smith & Wesson Model 19', 'Clássico utilizado por policiais nos anos 70.', '.357 Magnum', 'SW007', 'RN015', 'Smith_Wesson_19.png'),
    (6, 1450.00, 15, 1, 'Ruger Redhawk', 'REDHAWK-008', '6"', 'Ruger', 'Revólver Ruger Redhawk', 'Alta durabilidade para calibres poderosos.', '.44 Magnum', 'RGR008', 'RN016', 'Ruger_Redhawk.png'),
    (5, 1350.00, 17, 1, 'Colt King Cobra', 'KINGCOBRA-009', '4"', 'Colt', 'Revólver Colt King Cobra', 'Ótimo para uso tático e esportivo.', '.357 Magnum', 'COLT009', 'RN017', 'Colt_King_Cobra.png'),
    (6, 1550.00, 20, 1, 'Taurus Tracker', 'TRACKER-010', '5"', 'Taurus', 'Revólver Taurus Tracker', 'Popular por seu conforto e controle.', '.357 Magnum', 'TRS010', 'RN018', 'Taurus_Tracker.png');

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES
    -- Pistolas
    (15, 900.00, 30, 2, 'Beretta 92FS', 'BER92-001', '5"', 'Beretta', 'Pistola Beretta 92FS', 'Ampla utilização militar e policial.', '9mm', 'BRT001', 'RN019', 'Beretta_92FS.png'),
    (17, 850.00, 25, 2, 'Taurus G2C', 'TAURUSG2C-002', '3.2"', 'Taurus', 'Pistola Taurus G2C', 'Compacta e acessível, ideal para defesa pessoal.', '9mm', 'TRS002', 'RN020', 'Taurus_G2C.png'),
    (15, 1200.00, 18, 2, 'Sig Sauer P320', 'SIG320-003', '4.7"', 'Sig Sauer', 'Pistola Sig Sauer P320', 'Pistola modular e moderna.', '9mm', 'SIG003', 'RN021', 'Sig_Sauer_P320.png'),
    (13, 1100.00, 20, 2, 'Springfield XD-M', 'XD-M-004', '4.5"', 'Springfield', 'Pistola Springfield XD-M', 'Famosa por sua precisão.', '9mm', 'SPF004', 'RN022', 'Springfield_XDM.png'),
    (15, 1000.00, 22, 2, 'CZ P-10C', 'CZP10C-005', '4"', 'CZ', 'Pistola CZ P-10C', 'Ótima alternativa tática.', '9mm', 'CZ005', 'RN023', 'CZ_P10C.png'),
    (17, 950.00, 24, 2, 'Walther PPQ', 'WPPQ-006', '4.5"', 'Walther', 'Pistola Walther PPQ', 'Excelente ergonomia e precisão.', '9mm', 'WTH006', 'RN024', 'Walther_PPQ.png'),
    (19, 1150.00, 16, 2, 'Heckler & Koch VP9', 'HKVP9-007', '4.1"', 'Heckler & Koch', 'Pistola HK VP9', 'Popular entre atiradores profissionais.', '9mm', 'HCK007', 'RN025', 'HK_VP9.png'),
    (15, 850.00, 21, 2, 'Canik TP9', 'CANIKTP9-008', '4.5"', 'Canik', 'Pistola Canik TP9', 'Desempenho superior por um preço acessível.', '9mm', 'CNK008', 'RN026', 'Canik_TP9.png'),
    (17, 1300.00, 14, 2, 'FN Herstal FNX-45', 'FNX45-009', '4.5"', 'FN Herstal', 'Pistola FNX-45', 'Alta capacidade e calibre poderoso.', '.45 ACP', 'FNH009', 'RN027', 'FNX_45.png'),
    (12, 1500.00, 12, 2, 'Colt M1911', 'M1911-010', '5"', 'Colt', 'Pistola Colt M1911', 'Clássica e icônica em calibre .45 ACP.', '.45 ACP', 'COLT010', 'RN028', 'Colt_M1911.png');

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES
    -- Rifles
    (30, 1800.00, 15, 4, 'Remington Model 700', 'REM700-001', '24"', 'Remington', 'Rifle Remington Model 700', 'Famoso por sua precisão em longas distâncias.', '7.62mm', 'REM001', 'RN029', 'Remington_700.png'),
    (20, 2100.00, 18, 4, 'Savage 110', 'SAV110-002', '22"', 'Savage Arms', 'Rifle Savage 110', 'Altamente versátil e confiável.', '.308 Winchester', 'SAV002', 'RN030', 'Savage_110.png'),
    (25, 2400.00, 10, 4, 'Browning X-Bolt', 'BXBOLT-003', '26"', 'Browning', 'Rifle Browning X-Bolt', 'Projetado para máxima precisão.', '.270 Winchester', 'BRW003', 'RN031', 'Browning_XBolt.png'),
    (28, 2000.00, 14, 4, 'Winchester Model 70', 'WIN70-004', '23"', 'Winchester', 'Rifle Winchester Model 70', 'Conhecido como "The Rifleman’s Rifle".', '30-06 Springfield', 'WIN004', 'RN032', 'Winchester_Model70.png'),
    (32, 2200.00, 12, 4, 'Tikka T3x', 'TIKKAT3X-005', '24"', 'Tikka', 'Rifle Tikka T3x', 'Ergonomia superior e precisão.', '6.5 Creedmoor', 'TIK005', 'RN033', 'Tikka_T3x.png'),
    (30, 2500.00, 11, 4, 'Mossberg Patriot', 'MOSPAT-006', '22"', 'Mossberg', 'Rifle Mossberg Patriot', 'Equilibrado e poderoso.', '.300 Winchester Magnum', 'MOS006', 'RN034', 'Mossberg_Patriot.png'),
    (26, 1900.00, 13, 4, 'Ruger American Rifle', 'RUGAMR-007', '21"', 'Ruger', 'Rifle Ruger American', 'Ótimo custo-benefício.', '.243 Winchester', 'RUG007', 'RN035', 'Ruger_American.png'),
    (28, 2600.00, 9, 4, 'Sako 85', 'SAKO85-008', '25"', 'Sako', 'Rifle Sako 85', 'Desempenho de elite em tiros longos.', '7mm Remington Magnum', 'SAK008', 'RN036', 'Sako_85.png'),
    (30, 3000.00, 7, 4, 'Weatherby Mark V', 'WEATHMV-009', '28"', 'Weatherby', 'Rifle Weatherby Mark V', 'Potência máxima para caçadas desafiadoras.', '.257 Weatherby Magnum', 'WTH009', 'RN037', 'Weatherby_MarkV.png'),
    (22, 1600.00, 20, 4, 'Marlin 336', 'MAR336-010', '20"', 'Marlin', 'Rifle Marlin 336', 'Clássico para caçadas de cervos.', '.30-30 Winchester', 'MAR010', 'RN038', 'Marlin_336.png');

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES
    -- Escopetas
    (6, 1200.00, 18, 6, 'Benelli M4', 'BENM4-001', '18.5"', 'Benelli', 'Escopeta Benelli M4', 'Semi-automática confiável, utilizada em forças armadas.', '12 Gauge', 'BEN001', 'RN049', 'Benelli_M4.png'),
    (5, 1000.00, 20, 6, 'Mossberg 500', 'MOS500-002', '18"', 'Mossberg', 'Escopeta Mossberg 500', 'Confiável e econômica.', '12 Gauge', 'MOS002', 'RN050', 'Mossberg_500.png'),
    (5, 1400.00, 14, 6, 'Remington 870 Express', 'REM870EXP-003', '20"', 'Remington', 'Escopeta Remington 870 Express', 'Fácil de manusear e de manutenção.', '12 Gauge', 'REM003', 'RN051', 'Remington_870_Express.png'),
    (7, 1600.00, 12, 6, 'Winchester SXP', 'WINSXP-004', '18.5"', 'Winchester', 'Escopeta Winchester SXP', 'Ação de bombeamento ultrarrápida.', '12 Gauge', 'WIN004', 'RN052', 'Winchester_SXP.png'),
    (8, 1800.00, 10, 6, 'Beretta A300 Outlander', 'BERA300-005', '28"', 'Beretta', 'Escopeta Beretta A300', 'Famosa por sua confiabilidade.', '12 Gauge', 'BER005', 'RN053', 'Beretta_A300.png'),
    (4, 1500.00, 15, 6, 'Browning A5', 'BRWA5-006', '26"', 'Browning', 'Escopeta Browning A5', 'Icônica por seu design clássico.', '12 Gauge', 'BRW006', 'RN054', 'Browning_A5.png'),
    (5, 1300.00, 20, 6, 'CZ 612', 'CZ612-007', '28"', 'CZ', 'Escopeta CZ 612', 'Ótima escolha para iniciantes.', '12 Gauge', 'CZ007', 'RN055', 'CZ_612.png'),
    (6, 1900.00, 8, 6, 'FN P-12', 'FNP12-008', '18.5"', 'FN Herstal', 'Escopeta FN P-12', 'Compacta e confiável para táticas urbanas.', '12 Gauge', 'FNP008', 'RN056', 'FN_P12.png'),
    (7, 2000.00, 9, 6, 'Benelli Supernova', 'BENSNOVA-009', '28"', 'Benelli', 'Escopeta Benelli Supernova', 'Resistente a condições extremas.', '12 Gauge', 'BEN009', 'RN057', 'Benelli_Supernova.png'),
    (6, 1500.00, 16, 6, 'Maverick 88', 'MAV88-010', '18.5"', 'Mossberg', 'Escopeta Maverick 88', 'Econômica e confiável.', '12 Gauge', 'MAV010', 'RN058', 'Maverick_88.png');

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES
    -- Fuzis
    (30, 3500.00, 15, 7, 'M4A1', 'M4A1-002', '14.5"', 'Colt', 'Fuzil M4A1', 'Fuzil moderno usado por forças armadas.', '5.56x45mm NATO', 'M4002', 'RN060', 'M4A1.png'),
    (30, 3000.00, 20, 7, 'AK-47', 'AK47-001', '16.3"', 'Kalashnikov', 'Fuzil AK-47', 'Fuzil clássico, famoso por sua durabilidade.', '7.62x39mm', 'AK001', 'RN059', 'AK_47.png'),
    (20, 4000.00, 12, 7, 'FN SCAR-L', 'SCARL-003', '16"', 'FN Herstal', 'Fuzil FN SCAR-L', 'Leve e modular, adaptável a várias missões.', '5.56x45mm NATO', 'FN003', 'RN061', 'FN_SCAR_L.png'),
    (30, 2800.00, 18, 7, 'HK G36', 'G36-004', '18.9"', 'Heckler & Koch', 'Fuzil HK G36', 'Design inovador e confiabilidade.', '5.56x45mm NATO', 'HK004', 'RN062', 'HK_G36.png'),
    (30, 3300.00, 10, 7, 'Steyr AUG', 'AUG-005', '20"', 'Steyr', 'Fuzil Steyr AUG', 'Fuzil bullpup com alta precisão.', '5.56x45mm NATO', 'STEY005', 'RN063', 'Steyr_AUG.png'),
    (30, 4200.00, 8, 7, 'SIG SG 550', 'SG550-006', '20.8"', 'Sig Sauer', 'Fuzil SIG SG 550', 'Alta precisão e excelente ergonomia.', '5.56x45mm NATO', 'SIG006', 'RN064', 'SIG_SG550.png'),
    (30, 2600.00, 22, 7, 'Galil ACE', 'GALILACE-007', '18"', 'IMI', 'Fuzil Galil ACE', 'Robustez e confiabilidade em campo.', '7.62x39mm', 'IMI007', 'RN065', 'Galil_ACE.png'),
    (30, 3600.00, 9, 7, 'Tavor X95', 'X95-008', '16.5"', 'IWI', 'Fuzil Tavor X95', 'Compacto e ideal para combate urbano.', '5.56x45mm NATO', 'IWI008', 'RN066', 'Tavor_X95.png'),
    (25, 4500.00, 6, 7, 'Barrett REC7', 'REC7-009', '16"', 'Barrett', 'Fuzil Barrett REC7', 'Fuzil de alta qualidade para uso tático.', '6.8mm SPC', 'BAR009', 'RN067', 'Barrett_REC7.png'),
    (20, 5000.00, 4, 7, 'FN FAL', 'FAL-010', '21"', 'FN Herstal', 'Fuzil FN FAL', 'Clássico usado em forças armadas globais.', '7.62x51mm NATO', 'FN010', 'RN068', 'FN_FAL.png');

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipoArma, modelo, numerosigma, comprimentodocano,
    marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas, nomeImagem)
VALUES
    -- Metralhadoras
    (200, 15000.00, 5, 5, 'M240', 'M240-001', '22"', 'FN Herstal', 'Metralhadora M240', 'Metralhadora pesada, conhecida por sua robustez e precisão.', '7.62x51mm NATO', 'M240001', 'RN070', 'M240.png'),
    (1500, 25000.00, 3, 5, 'M2 Browning', 'M2-002', '39"', 'Browning', 'Metralhadora M2 Browning', 'Ícone das metralhadoras pesadas, utilizada em veículos e defesa aérea.', '12.7x99mm NATO', 'M20002', 'RN071', 'M2_Browning.png'),
    (600, 17000.00, 4, 5, 'Minimi', 'MINIMI-003', '19"', 'FN Herstal', 'Metralhadora Minimi', 'Leve, de uso militar, adaptável a diferentes calibres.', '5.56x45mm NATO', 'MINIMI003', 'RN072', 'Minimi.png'),
    (800, 18000.00, 6, 5, 'MG3', 'MG3-004', '23"', 'Heckler & Koch', 'Metralhadora MG3', 'Versátil, utilizada em várias configurações militares e de apoio.', '7.62x51mm NATO', 'MG3004', 'RN073', 'MG3.png'),
    (1000, 22000.00, 3, 5, 'Negev NG7', 'NG7-005', '19.5"', 'IWI', 'Metralhadora Negev NG7', 'Moderna metralhadora de calibre 7.62, com excelente performance em combate.', '7.62x51mm NATO', 'NG7005', 'RN074', 'Negev_NG7.png'),
    (1500, 20000.00, 5, 5, 'PKM', 'PKM-006', '26"', 'Kalashnikov', 'Metralhadora PKM', 'Metralhadora russa com alta taxa de fogo e confiabilidade.', '7.62x54mmR', 'PKM006', 'RN075', 'PKM.png'),
    (1200, 21000.00, 7, 5, 'RPD', 'RPD-007', '22.5"', 'Tula Arms', 'Metralhadora RPD', 'Metralhadora leve de uso em apoio, famosa pela sua fiabilidade.', '7.62x39mm', 'RPD007', 'RN076', 'RPD.png'),
    (1000, 24000.00, 6, 5, 'M249 SAW', 'M249-008', '19"', 'FN Herstal', 'Metralhadora M249 SAW', 'Leve e altamente eficaz, usada em combate terrestre.', '5.56x45mm NATO', 'M249008', 'RN077', 'M249_SAW.png'),
    (900, 23000.00, 8, 5, 'MG42', 'MG42-009', '27"', 'Mauser', 'Metralhadora MG42', 'Famosa por sua alta cadência de tiro e utilização na Segunda Guerra Mundial.', '7.92x57mm Mauser', 'MG42009', 'RN078', 'MG42.png'),
    (1300, 21000.00, 4, 5, 'F2000', 'F2000-010', '20"', 'FN Herstal', 'Metralhadora F2000', 'Sistema modular e eficiente, com grande capacidade de fogo contínuo.', '5.56x45mm NATO', 'F2000010', 'RN079', 'F2000.png');

INSERT INTO funcionario (matricula, cpf, telefone, nome, email)
VALUES
    ('123456', '111.111.111-11', '123456789', 'Daniel', 'daniel@example.com'),
    ('789012', '222.222.222-22', '987654321', 'Jacare', 'jacare@example.com');

INSERT INTO usuario (perfil, login, senha)
VALUES 
-- senha_cliente_andre
    (1, 'cliente_andre', '/GOjHgpptfKVaR92/cXMp7sJi0raFpcUWzVNZj5jjwjx0Z/oi19lZx6/v+f6nOpnnYLb9fg9vGw8pUWW08ekUw=='),
-- senha_cliente_bene
    (1, 'cliente_bene', 'tHg5Av764hOiw2peQgpDxMBsweO2HAsg4znQ5kxtcNMz/vPhKldRslxOL53tWghnqAQzna1E80x6CkNJbLT/kQ=='),
-- senha_cliente_hirosh
    (1, 'cliente_hirosh', 'a55M/kc9MqJFbtr5FGF6Ru8LZSwm36pYubnZZMvyox3Tyg8wOt7NZEDKUAIqr/NFejO6r8R+eUj0NvLLlgyl2Q=='),
-- senha_cliente_heitor
    (1, 'cliente_heitor', 'bbkoG72xCIeM+5TONI15VERApwynQf256JHHvX6bkAve00r43+cXaOOVLBYk/Q3xnnjce/7/jsvSaTOHB8RadQ=='),
-- senha_cliente_henrique
    (1, 'cliente_henrique', 'VobPCVa6t0EWSzXmvjNz/1iyKtcnISau1orzafq2UZEflPtvY9IjE/XCSg4Eccw/ifM14KsdoqWy+ag1r0OWFg=='),
-- senha_cliente_junin
    (1, 'cliente_junin', 'dsBjbU2BWnA/oQtxfZ7WLv3/Wdb4vRRb+YzXqHw+qJMuZ3rUoZKX0nWOvsp+r12ZZnhNriIxNsrIS/L2ROm1Ag=='),
-- senha_cliente_maria
    (1, 'cliente_maria', 'gQvX0a7+KxRYbi5K/1hDHzq32iNHUZHwYkF2ok22XL6MJ2SvWLZlduiM8D4UZdP57jvpyU89CSiFKinzIjXw1g=='),
-- senha_cliente_aline
    (1, 'cliente_aline', 'pzn74eUzJBP32jVsBmOpL50OTrZVgI9VBF7jKLXKKkAsGRfpqnaKblr696yAzh/1ALEyuVAkmOWVyXZzDGu+SQ=='),
-- senha_cliente_fernanda
    (1, 'cliente_fernanda', 'GJT+Wolwvp3TaibJtfTCixQ6mXbWOH6Y5M3FWOYCN0Twh+kdjIn5wSD0eVKjqQbhdiue9w8mRPRgbquvOBp3fw=='),
-- senha_funcionario_daniel
    (2, 'funcionario_daniel', 'gz92jkTcveMCL5phxX4m8vcDbE7IwdfMYLEbCFjsd55e89YZ8XlOte2oYZKk9l+UgIR94tyzIt1wyI22/K1sdg=='),
-- senha_funcionario_jacare
    (2, 'funcionario_jacare', '/1TvZJJL/8eyuJ8ktCm35K2ShjAQYM9dPzdDUwU8Fk2S5UMQlZ+AJI6ruk/VRwEB+6LISFzdAOVVQvhl0Owc3w=='),
--Vuasi666
    (2, 'Vuasi666', 'Ctis6fXh/t+HZ/rrRdKWoe+NFw5AQZTsz52vBgy8OHIAK3ZqtS/NIsgzcm4M5Gkrstv1hfjlAI9QRe/dAY+ijQ=='),
--Vuasi999
    (1, 'Vuasi999', 'Ctis6fXh/t+HZ/rrRdKWoe+NFw5AQZTsz52vBgy8OHIAK3ZqtS/NIsgzcm4M5Gkrstv1hfjlAI9QRe/dAY+ijQ==');


INSERT INTO cliente_usuario (id_cliente, id_usuario)
VALUES 
    -- Cliente André
    (1, 1),
    -- Cliente Bene
    (2, 2),
    -- Cliente Hirosh
    (3, 3),
    -- Cliente Heitor
    (4, 4),
    -- Cliente Henrique
    (5, 5),
    -- Cliente Junin
    (6, 6),
    -- Cliente Maria do Socorro
    (7, 7),
    -- Cliente Aline
    (8, 8),
    -- Cliente Fernanda
    (9, 9);

INSERT INTO funcionario_usuario (id_funcionario, id_usuario)
VALUES
    -- Funcionário Daniel
    (1, 10),
    -- Funcionário Jacare
    (2, 11);

INSERT INTO funcionario_endereco (id_endereco, id_funcionario)
VALUES
    -- Funcionário Daniel
    (10, 1),
    -- Funcionário Jacare
    (11, 2);

INSERT INTO arma_acabamento(id_arma, id_acabamento)
VALUES
(1, 1),
(2, 1),
(3, 5),
(4, 12),
(5, 3),
(6, 8),
(7, 1),
(8, 11),
(9, 7),
(10, 2),
(11, 6),
(12, 13),
(13, 4),
(14, 3),
(15, 9),
(16, 6),
(17, 2),
(18, 8),
(19, 12),
(20, 4),
(21, 10),
(22, 9),
(23, 5),
(24, 7),
(25, 13),
(26, 11),
(27, 3),
(28, 10),
(29, 6),
(30, 5),
(31, 8),
(32, 7),
(33, 1),
(34, 9),
(35, 2),
(36, 4),
(37, 10),
(38, 3),
(39, 5),
(40, 13),
(41, 6),
(42, 12),
(43, 11),
(44, 8),
(45, 2),
(46, 1),
(47, 4),
(48, 9),
(49, 7);

INSERT INTO arma_acabamento(id_arma, id_acabamento)
values
(50, 5),
(51, 3),
(52, 12),
(53, 8),
(54, 10),
(55, 6),
(56, 4),
(57, 9),
(58, 2),
(59, 13),
(60, 11),
(61, 7),
(62, 5),
(63, 3),
(64, 10),
(65, 6),
(66, 8),
(67, 9),
(68, 12),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 9),
(9, 10),
(10, 11),
(11, 12),
(12, 13),
(13, 2),
(14, 5),
(15, 9),
(16, 8),
(17, 10),
(18, 6);

INSERT INTO arma_acabamento(id_arma, id_acabamento)
values
(19, 4),
(20, 11),
(21, 13),
(22, 7),
(23, 5),
(24, 3),
(25, 6),
(26, 8),
(27, 4),
(28, 10),
(29, 2),
(30, 9),
(31, 11),
(32, 13),
(33, 7),
(34, 8),
(35, 12),
(36, 5),
(37, 9),
(38, 4),
(39, 6),
(40, 2),
(41, 3),
(42, 13),
(43, 10),
(44, 8),
(45, 7),
(46, 12),
(47, 6),
(48, 9),
(49, 2),
(50, 3),
(51, 4),
(52, 7),
(53, 12),
(54, 10),
(55, 6),
(56, 13),
(57, 11),
(58, 2),
(59, 5),
(60, 8),
(61, 12),
(62, 9),
(63, 4),
(64, 10);
