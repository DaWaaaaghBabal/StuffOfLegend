CREATE DATABASE IF NOT EXISTS `stuffoflegend` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `stuffoflegend`;

CREATE TABLE IF NOT EXISTS `abilities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11),
  `circle_choice` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  KEY `circle_choice` (`circle_choice`)
);

CREATE TABLE IF NOT EXISTS `ability_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `archetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kdm` int(11) NOT NULL,
  `kom` int(11) NOT NULL,
  `save1` int(11) NOT NULL,
  `save2` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kdm` (`kdm`),
  KEY `kom` (`kom`),
  KEY `save1` (`save1`),
  KEY `save2` (`save2`)
);

CREATE TABLE IF NOT EXISTS `circles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `track` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `type` (`type`),
  KEY `track` (`track`)
);

CREATE TABLE IF NOT EXISTS `circle_choices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `circle` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `circle` (`circle`)
);

CREATE TABLE IF NOT EXISTS `circle_picks` (
  `archetype` int(11) NOT NULL DEFAULT '0',
  `circle_choice` int(11) NOT NULL DEFAULT '0',
  `ability` int(11) DEFAULT NULL,
  PRIMARY KEY (`archetype`,`circle_choice`),
  KEY `ability` (`ability`),
  KEY `circle_choice` (`circle_choice`)
);

CREATE TABLE IF NOT EXISTS `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hp` int(11) NOT NULL,
  `bab` float NOT NULL,
  `skills` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `forced_kdms` (
  `track` int(11) NOT NULL DEFAULT '0',
  `statistic` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`track`,`statistic`),
  KEY `statistic` (`statistic`)
);

CREATE TABLE IF NOT EXISTS `forced_koms` (
  `track` int(11) NOT NULL DEFAULT '0',
  `statistic` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`track`,`statistic`),
  KEY `statistic` (`statistic`)
);

CREATE TABLE IF NOT EXISTS `kdm_choices` (
  `class` int(11) NOT NULL DEFAULT '0',
  `statistic` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`class`,`statistic`),
  KEY `statistic` (`statistic`)
);

CREATE TABLE IF NOT EXISTS `kom_choices` (
  `class` int(11) NOT NULL DEFAULT '0',
  `statistic` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`class`,`statistic`),
  KEY `statistic` (`statistic`)
) ;

CREATE TABLE IF NOT EXISTS `_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label` (`id`),
  UNIQUE KEY `name` (`name`)
);

CREATE TABLE IF NOT EXISTS `progressions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offset` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `saves` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ability1` int(11) NOT NULL,
  `ability2` int(11) NOT NULL,
  UNIQUE KEY `key` (`id`),
  KEY `key_2` (`id`),
  KEY `ability1` (`ability1`),
  KEY `ability2` (`ability2`)
);


CREATE TABLE IF NOT EXISTS `statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` char(3) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tam_choices` (
  `track` int(11) NOT NULL DEFAULT '0',
  `statistic` int(11) NOT NULL DEFAULT '0',
  `sam` tinyint(1) NOT NULL,
  PRIMARY KEY (`track`,`statistic`)
) ;

CREATE TABLE IF NOT EXISTS `tam_picks` (
  `track` int(11) NOT NULL DEFAULT '0',
  `archetype` int(11) NOT NULL DEFAULT '0',
  `statistic` int(11) DEFAULT NULL,
  PRIMARY KEY (`track`,`archetype`),
  KEY `statistic` (`statistic`),
  KEY `archetype` (`archetype`)
);

CREATE TABLE IF NOT EXISTS `tracks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `track_choices` (
  `class` int(11) NOT NULL DEFAULT '0',
  `track` int(11) DEFAULT NULL,
  `progression` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`class`,`progression`),
  KEY `track` (`track`),
  KEY `progression` (`progression`)
);

CREATE TABLE IF NOT EXISTS `track_picks` (
  `archetype` int(11) NOT NULL DEFAULT '0',
  `progression` int(11) NOT NULL DEFAULT '0',
  `track` int(11) DEFAULT NULL,
  PRIMARY KEY (`archetype`,`progression`),
  KEY `track` (`track`)
);

CREATE TABLE IF NOT EXISTS `substitutions` (
  `archetype` int(11) NOT NULL DEFAULT '0',
  `progression` int(11) NOT NULL DEFAULT '0',
  `track` int(11) DEFAULT NULL,
  PRIMARY KEY (`archetype`,`progression`),
  KEY `track` (`track`)
);

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `login` varchar(64) COLLATE,
  `password` varchar(32) COLLATE,
  `email` varchar(256),
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
);

CREATE TABLE IF NOT EXISTS `_translations_abilities` (
  `ability` int(11) NOT NULL DEFAULT '0',
  `language` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) DEFAULT NULL,
  `text` text,
  PRIMARY KEY (`ability`,`language`),
  KEY `language` (`language`)
);

CREATE TABLE IF NOT EXISTS `_translations_ability_types` (
  `ability_type` int(11) NOT NULL DEFAULT '0',
  `language` int(11) NOT NULL DEFAULT '0',
  `label_short` varchar(3) DEFAULT NULL,
  `label_long` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ability_type`,`language`),
  KEY `language` (`language`)
);
CREATE TABLE IF NOT EXISTS `_translations_circles` (
  `circle` int(11) NOT NULL DEFAULT '0',
  `language` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`circle`,`language`),
  KEY `language` (`language`)
);

CREATE TABLE IF NOT EXISTS `_translations_classes` (
  `class` int(11) NOT NULL,
  `language` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`class`,`language`),
  KEY `language` (`language`)
);

CREATE TABLE IF NOT EXISTS `_translations_progression` (
  `progression` int(11) NOT NULL DEFAULT '0',
  `language` int(11) NOT NULL DEFAULT '0',
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`progression`,`language`),
  KEY `language` (`language`)
);

CREATE TABLE IF NOT EXISTS `_translations_saves` (
  `save` int(11) NOT NULL DEFAULT '0',
  `language` int(11) NOT NULL DEFAULT '0',
  `label_short` varchar(4) DEFAULT NULL,
  `label_long` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`save`,`language`),
  KEY `language` (`language`)
);

CREATE TABLE IF NOT EXISTS `_translations_statistics` (
  `ability` int(11) NOT NULL DEFAULT '0',
  `language` int(11) NOT NULL DEFAULT '0',
  `label_short` varchar(3) DEFAULT NULL,
  `label_long` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ability`,`language`),
  KEY `language` (`language`)
);

CREATE TABLE IF NOT EXISTS `_translations_tracks` (
  `track` int(11) NOT NULL,
  `language` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`track`,`language`),
  KEY `language` (`language`)
);

ALTER TABLE `abilities`
  ADD CONSTRAINT `abilities_ibfk_1` FOREIGN KEY (`type`) REFERENCES `ability_types` (`id`),
  ADD CONSTRAINT `abilities_ibfk_2` FOREIGN KEY (`circle_choice`) REFERENCES `circle_choices` (`id`);

ALTER TABLE `circles`
  ADD CONSTRAINT `circles_ibfk_1` FOREIGN KEY (`type`) REFERENCES `ability_types` (`id`),
  ADD CONSTRAINT `circles_ibfk_2` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`);

ALTER TABLE `circle_choices`
  ADD CONSTRAINT `circle_choices_ibfk_1` FOREIGN KEY (`circle`) REFERENCES `circles` (`id`);

ALTER TABLE `circle_picks`
  ADD CONSTRAINT `circle_picks_ibfk_1` FOREIGN KEY (`archetype`) REFERENCES `archetypes` (`id`),
  ADD CONSTRAINT `circle_picks_ibfk_2` FOREIGN KEY (`circle_choice`) REFERENCES `circle_choices` (`id`),
  ADD CONSTRAINT `circle_picks_ibfk_3` FOREIGN KEY (`ability`) REFERENCES `abilities` (`id`);

ALTER TABLE `forced_kdms`
  ADD CONSTRAINT `forced_kdms_ibfk_1` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`),
  ADD CONSTRAINT `forced_kdms_ibfk_2` FOREIGN KEY (`statistic`) REFERENCES `statistics` (`id`);

ALTER TABLE `forced_koms`
  ADD CONSTRAINT `forced_koms_ibfk_1` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`),
  ADD CONSTRAINT `forced_koms_ibfk_2` FOREIGN KEY (`statistic`) REFERENCES `statistics` (`id`);

ALTER TABLE `kdm_choices`
  ADD CONSTRAINT `kdm_choices_ibfk_1` FOREIGN KEY (`class`) REFERENCES `classes` (`id`),
  ADD CONSTRAINT `kdm_choices_ibfk_2` FOREIGN KEY (`statistic`) REFERENCES `statistics` (`id`);

ALTER TABLE `kom_choices`
  ADD CONSTRAINT `kom_choices_ibfk_1` FOREIGN KEY (`class`) REFERENCES `classes` (`id`),
  ADD CONSTRAINT `kom_choices_ibfk_2` FOREIGN KEY (`statistic`) REFERENCES `statistics` (`id`);

ALTER TABLE `saves`
  ADD CONSTRAINT `saves_ibfk_1` FOREIGN KEY (`ability1`) REFERENCES `statistics` (`id`),
  ADD CONSTRAINT `saves_ibfk_2` FOREIGN KEY (`ability2`) REFERENCES `statistics` (`id`);

ALTER TABLE `tam_picks`
  ADD CONSTRAINT `tam_picks_ibfk_1` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`),
  ADD CONSTRAINT `tam_picks_ibfk_2` FOREIGN KEY (`archetype`) REFERENCES `archetypes` (`id`),
  ADD CONSTRAINT `tam_picks_ibfk_3` FOREIGN KEY (`statistic`) REFERENCES `statistics` (`id`);

ALTER TABLE `track_choices`
  ADD CONSTRAINT `track_choices_ibfk_1` FOREIGN KEY (`class`) REFERENCES `classes` (`id`),
  ADD CONSTRAINT `track_choices_ibfk_2` FOREIGN KEY (`progression`) REFERENCES `progressions` (`id`),
  ADD CONSTRAINT `track_choices_ibfk_3` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`);

ALTER TABLE `track_picks`
  ADD CONSTRAINT `track_picks_ibfk_1` FOREIGN KEY (`archetype`) REFERENCES `archetypes` (`id`),
  ADD CONSTRAINT `track_picks_ibfk_2` FOREIGN KEY (`progression`) REFERENCES `progressions` (`id`),
  ADD CONSTRAINT `track_picks_ibfk_3` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`);

ALTER TABLE `_translations_abilities`
  ADD CONSTRAINT `_translations_abilities_ibfk_1` FOREIGN KEY (`ability`) REFERENCES `abilities` (`id`),
  ADD CONSTRAINT `_translations_abilities_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_ability_types`
  ADD CONSTRAINT `_translations_ability_types_ibfk_1` FOREIGN KEY (`ability_type`) REFERENCES `ability_types` (`id`),
  ADD CONSTRAINT `_translations_ability_types_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_circles`
  ADD CONSTRAINT `_translations_circles_ibfk_1` FOREIGN KEY (`circle`) REFERENCES `circles` (`id`),
  ADD CONSTRAINT `_translations_circles_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_classes`
  ADD CONSTRAINT `_translations_classes_ibfk_1` FOREIGN KEY (`class`) REFERENCES `classes` (`id`),
  ADD CONSTRAINT `_translations_classes_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_progression`
  ADD CONSTRAINT `_translations_progression_ibfk_1` FOREIGN KEY (`progression`) REFERENCES `progressions` (`id`),
  ADD CONSTRAINT `_translations_progression_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_saves`
  ADD CONSTRAINT `_translations_saves_ibfk_1` FOREIGN KEY (`save`) REFERENCES `saves` (`id`),
  ADD CONSTRAINT `_translations_saves_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_statistics`
  ADD CONSTRAINT `_translations_statistics_ibfk_1` FOREIGN KEY (`ability`) REFERENCES `statistics` (`id`),
  ADD CONSTRAINT `_translations_statistics_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

ALTER TABLE `_translations_tracks`
  ADD CONSTRAINT `_translations_tracks_ibfk_1` FOREIGN KEY (`track`) REFERENCES `tracks` (`id`),
  ADD CONSTRAINT `_translations_tracks_ibfk_2` FOREIGN KEY (`language`) REFERENCES `languages` (`id`);

 
INSERT INTO `_languages` (`id`, `name`) VALUES
(1, 'English'),
(2, 'FranÃ§ais');
INSERT INTO `ability_types` (`id`) VALUES
(1),
(2),
(3);
INSERT INTO `_translations_ability_types` (`ability_type`, `language`, `label_short`, `label_long`) VALUES
(1, 1, 'EX', 'Extraordinary ability'),
(1, 2, 'EX', 'CapacitÃ© extraordinaire'),
(2, 1, 'SU', 'Supernatural ability'),
(2, 2, 'SU', 'CapacitÃ© surnaturelle'),
(3, 1, 'SLA', 'Spell-like ability'),
(3, 2, 'PM', 'Pouvoir magique');

INSERT INTO `statistics` (`id`, `key`) VALUES
(1, 'STR'),
(2, 'CON'),
(3, 'DEX'),
(4, 'INT'),
(5, 'WIS'),
(6, 'CHA');
INSERT INTO `_translations_statistics` (`ability`, `language`, `label_short`, `label_long`) VALUES
(1, 1, 'STR', 'Strength'),
(1, 2, 'FOR', 'Force'),
(2, 1, 'CON', 'Constitution'),
(2, 2, 'CON', 'Constitution'),
(3, 1, 'DEX', 'Dexterity'),
(3, 2, 'DEX', 'DextÃ©ritÃ©'),
(4, 1, 'INT', 'Intelligence'),
(4, 2, 'INT', 'Intelligence'),
(5, 1, 'WIS', 'Wisdom'),
(5, 2, 'SAG', 'Sagesse'),
(6, 1, 'CHA', 'Charisma'),
(6, 2, 'CHA', 'Charisme');

INSERT INTO `saves` (`id`, `ability1`, `ability2`) VALUES
(1, 1, 2),
(2, 3, 4),
(3, 5, 6);
INSERT INTO `_translations_saves` (`save`, `language`, `label_short`, `label_long`) VALUES
(1, 1, 'FORT', 'Fortitude'),
(1, 2, 'VIG', 'Vigueur'),
(2, 1, 'REF', 'Reflexes'),
(2, 2, 'REF', 'RÃ©flexes'),
(3, 1, 'WILL', 'Will'),
(3, 2, 'VOL', 'VolontÃ©');

INSERT INTO `progressions` (`id`, `offset`) VALUES
(1, 0),
(2, 1),
(3, 2),
(4, 1);
INSERT INTO `_translations_progression` (`progression`, `language`, `name`) VALUES
(1, 1, 'Fast'),
(1, 2, 'Rapide'),
(2, 1, 'Medium'),
(2, 2, 'Moyenne'),
(3, 1, 'Slow'),
(3, 2, 'Lente'),
(4, 1, 'Full Buy-In'),
(4, 2, 'Implication totale');

INSERT INTO `classes` (`id`, `hp`, `bab`, `skills`) VALUES
(1, 8, 1, 6),
(2, 8, 0.75, 6);
INSERT INTO `_translations_classes` (`class`, `language`, `name`) VALUES
(1, 1, 'Monk'),
(1, 2, 'Moine'),
(2, 1, 'Sage'),
(2, 2, 'Sage');

INSERT INTO `kom_choices` (`class`, `statistic`) VALUES
(1, 5),
(2, 4),
(2, 5),
(2, 6);

INSERT INTO `kdm_choices` (`class`, `statistic`) VALUES
(1, 2),
(2, 1),
(2, 2),
(2, 3);

INSERT INTO `tracks` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7);
INSERT INTO `_translations_tracks` (`track`, `language`, `name`) VALUES
(1, 1, 'Discipline of the Dragon'),
(1, 2, 'Discipline du Dragon'),
(2, 1, 'Discipline of the Serpent'),
(2, 2, 'Discipline du Serpent'),
(3, 1, 'Discipline of the Crane'),
(3, 2, 'Discipline de la Grue'),
(4, 1, 'Arcane Secrets'),
(4, 2, 'Secrets des Arcanes'),
(5, 1, 'Just Blade'),
(5, 2, 'Lame du Juste'),
(6, 1, 'Arcane Lore'),
(6, 2, 'Savoir des Arcanes'),
(7, 1, 'Force of Will'),
(7, 2, 'Force de Volonté');

INSERT INTO `track_choices` (`class`, `progression`, `track`) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(2, 1, 4),
(2, 2, 5),
(2, 2, 6),
(2, 3, 7);

INSERT INTO `circles` (`id`,  `track`, `number`, `type`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 2, 1, 1),
(4, 2, 2, 1),
(5, 3, 1, 1),
(6, 3, 2, 1),
(7, 4, 1, 3),
(8, 4, 2, 3),
(9, 5, 1, 2),
(10, 5, 2, 2),
(11, 6, 1, 2),
(12, 6, 2, 2),
(13, 7, 1, 2),
(14, 7, 2, 2);

INSERT INTO `_translations_circles` (`circle`, `language`, `name`) VALUES
(1, 1, 'Dance of the Sun and Moon'),
(1, 2, 'Danse du Soleil et de la Lune'),
(2, 1, 'Obsidian Mind'),
(2, 2, 'Esprit d''Obsidienne'),
(3, 1, 'External Techniques'),
(3, 2, 'Techniques Externes'),
(4, 1, 'Internal Principles'),
(4, 2, 'Principes Internes'),
(5, 1, 'Fast Movement'),
(5, 2, 'Mouvement accéléré'),
(6, 1, 'Between the Drops'),
(6, 2, 'Entre les Gouttes'),
(7, 1, 'Hungry Shadows'),
(7, 2, 'Ombres affamées'),
(8, 1, 'Space, Discontent'),
(8, 2, 'Espace, instatisfait'),
(9, 1, 'Grim Inheritor'),
(9, 2, 'Héritier Sombre'),
(10, 1, 'Mental Thrust'),
(10, 2, 'Poussée mentale'),
(11, 1, 'Black Tidings'),
(11, 2, 'Noires nouvelles'),
(12, 1, 'Canto'),
(12, 2, 'Canto'),
(13, 1, 'Healing Burst'),
(13, 2, 'Rafale guérisseuse'),
(14, 1, 'A Stitch In Time'),
(14, 2, 'Un Accroc dans le Temps');


INSERT INTO `circle_choices` (`id`,  `circle`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 3),
(5, 4),
(6, 4),
(7, 4),
(8, 5),
(9, 6),
(10, 7),
(11, 8),
(12, 9),
(13, 10),
(14, 11),
(15, 11),
(16, 12),
(17, 13),
(18, 14);

INSERT INTO `abilities` (`id`, `circle_choice`, `type`) VALUES
(1, 1, NULL),
(2, 1, NULL),
(3, 2, NULL),
(4, 3, NULL),
(5, 4, NULL),
(6, 5, NULL),
(7, 6, NULL),
(8, 7, NULL),
(9, 8, NULL),
(10, 9, NULL),
(11, 10, NULL),
(12, 11, NULL),
(13, 11, NULL),
(14, 12, NULL),
(15, 13, NULL),
(16, 14, NULL),
(17, 15, NULL),
(18, 16, NULL),
(19, 17, NULL),
(20, 18, NULL);

INSERT INTO `_translations_abilities` (`ability`, `language`, `name`, `text`) VALUES
(1, 1, 'Careful Sun', 'Discipline of the Dragon 1.1'),
(1, 2, 'Soleil prudent', 'Discipline du Dragon 1.1'),
(2, 1, 'Reckless Moon', 'Discipline of the Dragon 1.2'),
(2, 2, 'Lune Impétueuse', 'Discipline du Dragon 1.2'),
(3, 1, NULL, 'Discipline of the Dragon 2'),
(3, 2, NULL, 'Discipline du Dragon 2'),
(4, 1, NULL, 'Discipline of the Serpent 1.1'),
(4, 2, NULL, 'Discipline du Serpent 1.1'),
(5, 1, NULL, 'Discipline of the Serpent 1.2'),
(5, 2, NULL, 'Discipline du Serpent 1.2'),
(6, 1, NULL, 'Discipline of the Serpent 2.1'),
(6, 2, NULL, 'Discipline du Serpent 2.1'),
(7, 1, 'Pushing Blow', 'Discipline of the Serpent 2.2.1'),
(7, 2, 'Frappe de projection', 'Discipline du Serpent 2.2.1'),
(8, 1, 'Neutralize', 'Discipline of the Serpent 2.2.2'),
(8, 2, 'Neutraliser', 'Discipline du Serpent 2.2.2'),
(9, 1, NULL, 'Discipline of the Crane 1'),
(9, 2, NULL, 'Discipline de la Grue 1'),
(10, 1, NULL, 'Discipline of the Crane 2'),
(10, 2, NULL, 'Discipline de la Grue 2'),
(11, 1, NULL, 'Arcane Secrets 1'),
(11, 2, NULL, 'Secrets des Arcanes 1'),
(12, 1, 'Lungbreaker', 'Arcane Secrets 2.1'),
(12, 2, 'Brise-poumons', 'Secrets des Arcanes 2.1'),
(12, 1, 'Stutter step', 'Arcane Secrets 2.2'),
(12, 2, 'Foulée bégayante', 'Secrets des Arcanes 2.2'),
(13, 1, NULL, 'Just Blade 1'),
(13, 2, NULL, 'Lame du Juste 1'),
(14, 1, NULL, 'Just Blade 2'),
(14, 2, NULL, 'Lame du Juste 2'),
(15, 1, 'Dread Wave', 'Arcane Lore 1.1'),
(15, 2, 'Vague d''Effroi', 'Savoir des Arcanes 1.1'),
(16, 1, 'Hammerfall', 'Arcane Lore 1.2'),
(16, 2, 'Marteau', 'Savoir des Arcanes 1.2'),
(17, 1, 'Red Hymn', 'Arcane Lore 2.1'),
(17, 2, 'Hymne Rouge', 'Savoir des Arcanes 2.1'),
(18, 1, 'Grey Hymn', 'Arcane Lore 2.2'),
(18, 2, 'Hymne Gris', 'Savoir des Arcanes 2.2'),
(19, 1, NULL, 'Force of Will 1'),
(19, 2, NULL, 'Force de Volonté 1'),
(20, 1, NULL, 'Force of Will 2'),
(20, 2, NULL, 'Force de Volonté 2');



INSERT INTO `users` (`id`, `login`, `password`, `email`) VALUES
(1, 'admin', 'password', 'admin@admin.fr');






