DROP DATABASE stuffoflegend;
CREATE DATABASE stuffoflegend DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE stuffoflegend;

CREATE TABLE IF NOT EXISTS users (
  id int NOT NULL AUTO_INCREMENT,
  login varchar(64),
  password varchar(44),
  email varchar(256),
  PRIMARY KEY (id),
  UNIQUE KEY (login)
);

CREATE TABLE IF NOT EXISTS languages (
  id int NOT NULL AUTO_INCREMENT,
  label varchar(5),
  name varchar(32) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (label),
  UNIQUE KEY (name)
);

CREATE TABLE IF NOT EXISTS ability_types (
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS statistics (
  id int NOT NULL AUTO_INCREMENT,
  char_id char(3) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS progressions (
  id int NOT NULL AUTO_INCREMENT,
  offset int DEFAULT '1',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS classes (
  id int NOT NULL AUTO_INCREMENT,
  hp int NOT NULL,
  bab float NOT NULL,
  skills int NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS saves (
  id int NOT NULL AUTO_INCREMENT,
  stat1 int NOT NULL,
  stat2 int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (stat1) REFERENCES statistics(id),
  FOREIGN KEY (stat2) REFERENCES statistics(id)
);

CREATE TABLE IF NOT EXISTS tracks (
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS circles (
  id int NOT NULL AUTO_INCREMENT,
  number int NOT NULL,
  ability_type int NOT NULL,
  track int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (ability_type) REFERENCES ability_types (id),
  FOREIGN KEY (track) REFERENCES tracks (id)
);

CREATE TABLE IF NOT EXISTS circle_choices (
  id int NOT NULL AUTO_INCREMENT,
  circle int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (circle) REFERENCES circles (id)
);

CREATE TABLE IF NOT EXISTS abilities (
  id int NOT NULL AUTO_INCREMENT,
  ability_type int,
  circle_choice int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (ability_type) REFERENCES ability_types(id),
  FOREIGN KEY (circle_choice) REFERENCES circle_choices(id)
);

CREATE TABLE IF NOT EXISTS forced_kdms (
  track int NOT NULL,
  stat int NOT NULL,
  PRIMARY KEY (track),
  FOREIGN KEY (track) REFERENCES tracks(id),
  FOREIGN KEY (stat) REFERENCES statistics(id)
);

CREATE TABLE IF NOT EXISTS forced_koms (
  track int NOT NULL,
  stat int NOT NULL,
  PRIMARY KEY (track),
  FOREIGN KEY (track) REFERENCES tracks(id),
  FOREIGN KEY (stat) REFERENCES statistics(id)
);

CREATE TABLE IF NOT EXISTS kdm_choices (
  class int NOT NULL,
  stat int NOT NULL,
  PRIMARY KEY (class, stat),
  FOREIGN KEY (stat) REFERENCES statistics(id),
  FOREIGN KEY (class) REFERENCES classes(id)
);

CREATE TABLE IF NOT EXISTS kom_choices (
  class int NOT NULL,
  stat int NOT NULL,
  PRIMARY KEY (class, stat),
  FOREIGN KEY (stat) REFERENCES statistics(id),
  FOREIGN KEY (class) REFERENCES classes(id)
);

CREATE TABLE IF NOT EXISTS save_choices (
  class int NOT NULL,
  save int NOT NULL,
  id tinyint(1) NOT NULL,
  PRIMARY KEY (class, save, id),
  FOREIGN KEY (save) REFERENCES saves(id),
  FOREIGN KEY (class) REFERENCES classes(id)
);

CREATE TABLE IF NOT EXISTS tam_choices (
  track int NOT NULL,
  stat int NOT NULL,
  sam tinyint(1) NOT NULL,
  PRIMARY KEY (track, stat),
  FOREIGN KEY (stat) REFERENCES statistics(id),
  FOREIGN KEY (track) REFERENCES tracks(id)
) ;

CREATE TABLE IF NOT EXISTS track_choices (
  class int NOT NULL,
  track int,
  progression int NOT NULL,
  PRIMARY KEY (class, track, progression),
  FOREIGN KEY (class) REFERENCES classes(id),
  FOREIGN KEY (track) REFERENCES tracks(id),
  FOREIGN KEY (progression) REFERENCES progressions(id)
);

CREATE TABLE IF NOT EXISTS archetypes (
  id int NOT NULL AUTO_INCREMENT,
  kdm int NOT NULL,
  kom int NOT NULL,
  save1 int NOT NULL,
  save2 int NOT NULL,
  name varchar(256) NOT NULL,
  class int NOT NULL,
  author int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (kdm) REFERENCES statistics(id),
  FOREIGN KEY (kom) REFERENCES statistics(id),
  FOREIGN KEY (save1) REFERENCES saves(id),
  FOREIGN KEY (save2) REFERENCES saves(id),
  FOREIGN KEY (class) REFERENCES classes(id),
  FOREIGN KEY (author) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS circle_picks (
  archetype int NOT NULL,
  circle_choice int NOT NULL,
  ability int NOT NULL,
  PRIMARY KEY (archetype,circle_choice),
  FOREIGN KEY (archetype) REFERENCES archetypes(id),
  FOREIGN KEY (circle_choice) REFERENCES circle_choices(id),
  FOREIGN KEY (ability) REFERENCES abilities(id)
);

CREATE TABLE IF NOT EXISTS tam_picks (
  track int NOT NULL,
  archetype int NOT NULL,
  stat int,
  PRIMARY KEY (track,archetype),
  FOREIGN KEY (archetype) REFERENCES archetypes(id),
  FOREIGN KEY (track) REFERENCES tracks(id),
  FOREIGN KEY (stat) REFERENCES statistics(id)
);

CREATE TABLE IF NOT EXISTS track_picks (
  archetype int NOT NULL,
  progression int NOT NULL,
  track int,
  PRIMARY KEY (archetype,progression),
  FOREIGN KEY (archetype) REFERENCES archetypes(id),
  FOREIGN KEY (progression) REFERENCES progressions(id),
  FOREIGN KEY (track) REFERENCES tracks(id)
);

CREATE TABLE IF NOT EXISTS substitutions (
  id int NOT NULL AUTO_INCREMENT,
  archetype int NOT NULL,
  progression int NOT NULL,
  track int,
  PRIMARY KEY (id),
  FOREIGN KEY (archetype) REFERENCES archetypes(id),
  FOREIGN KEY (progression) REFERENCES progressions(id),
  FOREIGN KEY (track) REFERENCES tracks(id)
);

CREATE TABLE IF NOT EXISTS _translations_abilities (
  ability int NOT NULL,
  language int NOT NULL,
  name varchar(64),
  rules_text TEXT,
  PRIMARY KEY (ability,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (ability) REFERENCES abilities(id)
);

CREATE TABLE IF NOT EXISTS _translations_ability_types (
  ability_type int NOT NULL,
  language int NOT NULL,
  label_short varchar(3),
  label_long varchar(64),
  PRIMARY KEY (ability_type,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (ability_type) REFERENCES ability_types(id)
);
CREATE TABLE IF NOT EXISTS _translations_circles (
  circle int NOT NULL,
  language int NOT NULL,
  name varchar(64),
  PRIMARY KEY (circle,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (circle) REFERENCES circles(id)
);

CREATE TABLE IF NOT EXISTS _translations_classes (
  class int NOT NULL,
  language int NOT NULL,
  name varchar(64) NOT NULL,
  PRIMARY KEY (class,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (class) REFERENCES classes(id)
);

CREATE TABLE IF NOT EXISTS _translations_progression (
  progression int NOT NULL,
  language int NOT NULL,
  name varchar(32),
  PRIMARY KEY (progression,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (progression) REFERENCES progressions(id)
);

CREATE TABLE IF NOT EXISTS _translations_saves (
  save int NOT NULL,
  language int NOT NULL,
  label_short varchar(4),
  label_long varchar(32),
  PRIMARY KEY (save,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (save) REFERENCES saves(id)
);

CREATE TABLE IF NOT EXISTS _translations_statistics (
  stat int NOT NULL,
  language int NOT NULL,
  label_short varchar(3),
  label_long varchar(32),
  PRIMARY KEY (stat,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (stat) REFERENCES statistics(id)
);

CREATE TABLE IF NOT EXISTS _translations_tracks (
  track int NOT NULL,
  language int NOT NULL,
  name varchar(64) NOT NULL,
  PRIMARY KEY (track,language),
  FOREIGN KEY (language) REFERENCES languages(id),
  FOREIGN KEY (track) REFERENCES tracks(id)
);

INSERT INTO languages (id, name) VALUES
(1, 'English'),
(2, 'Français');
INSERT INTO ability_types (id) VALUES
(1),
(2),
(3);

INSERT INTO _translations_ability_types (ability_type, language, label_short, label_long) VALUES
(1, 1, 'EX', 'Extraordinary ability'),
(1, 2, 'EX', 'Capacité extraordinaire'),
(2, 1, 'SU', 'Supernatural ability'),
(2, 2, 'SU', 'Capacité surnaturelle'),
(3, 1, 'SLA', 'Spell-like ability'),
(3, 2, 'PM', 'Pouvoir magique');

INSERT INTO statistics (id, char_id) VALUES
(1, 'STR'),
(2, 'CON'),
(3, 'DEX'),
(4, 'INT'),
(5, 'WIS'),
(6, 'CHA');
INSERT INTO _translations_statistics (stat, language, label_short, label_long) VALUES
(1, 1, 'STR', 'Strength'),
(1, 2, 'FOR', 'Force'),
(2, 1, 'CON', 'Constitution'),
(2, 2, 'CON', 'Constitution'),
(3, 1, 'DEX', 'Dexterity'),
(3, 2, 'DEX', 'Dextérité'),
(4, 1, 'INT', 'Intelligence'),
(4, 2, 'INT', 'Intelligence'),
(5, 1, 'WIS', 'Wisdom'),
(5, 2, 'SAG', 'Sagesse'),
(6, 1, 'CHA', 'Charisma'),
(6, 2, 'CHA', 'Charisme');

INSERT INTO saves (id, stat1, stat2) VALUES
(1, 1, 2),
(2, 3, 4),
(3, 5, 6);
INSERT INTO _translations_saves (save, language, label_short, label_long) VALUES
(1, 1, 'FORT', 'Fortitude'),
(1, 2, 'VIG', 'Vigueur'),
(2, 1, 'REF', 'Reflexes'),
(2, 2, 'REF', 'Réflexes'),
(3, 1, 'WILL', 'Will'),
(3, 2, 'VOL', 'Volonté');

INSERT INTO progressions (id, offset) VALUES
(1, 0),
(2, 1),
(3, 2),
(4, 1);
INSERT INTO _translations_progression (progression, language, name) VALUES
(1, 1, 'Fast'),
(1, 2, 'Rapide'),
(2, 1, 'Medium'),
(2, 2, 'Moyenne'),
(3, 1, 'Slow'),
(3, 2, 'Lente'),
(4, 1, 'Full Buy-In'),
(4, 2, 'Implication totale');

INSERT INTO classes (id, hp, bab, skills) VALUES
(1, 8, 1, 6),
(2, 8, 0.75, 6);
INSERT INTO _translations_classes (class, language, name) VALUES
(1, 1, 'Monk'),
(1, 2, 'Moine'),
(2, 1, 'Sage'),
(2, 2, 'Sage');

INSERT INTO kom_choices (class, stat) VALUES
(1, 5),
(2, 4),
(2, 5),
(2, 6);

INSERT INTO kdm_choices (class, stat) VALUES
(1, 2),
(2, 1),
(2, 2),
(2, 3);

INSERT INTO save_choices (class, id, save) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 2, 1),
(1, 2, 2),
(1, 2, 3),
(2, 1, 1),
(2, 1, 2),
(2, 1, 3),
(2, 2, 1),
(2, 2, 2),
(2, 2, 3);

INSERT INTO tracks (id) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7);
INSERT INTO _translations_tracks (track, language, name) VALUES
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

INSERT INTO track_choices (class, progression, track) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(2, 1, 4),
(2, 2, 5),
(2, 2, 6),
(2, 3, 7);

INSERT INTO circles (id,  track, number, ability_type) VALUES
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

INSERT INTO _translations_circles (circle, language, name) VALUES
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
(9, 2, 'Sombre Héritier'),
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


INSERT INTO circle_choices (id,  circle) VALUES
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

INSERT INTO abilities (id, circle_choice, ability_type) VALUES
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
(19, 16, NULL),
(20, 17, NULL),
(21, 18, NULL);

INSERT INTO _translations_abilities (ability, language, name, rules_text) VALUES
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
(13, 1, 'Stutter step', 'Arcane Secrets 2.2'),
(13, 2, 'Foulée bégayante', 'Secrets des Arcanes 2.2'),
(14, 1, NULL, 'Just Blade 1'),
(14, 2, NULL, 'Lame du Juste 1'),
(15, 1, NULL, 'Just Blade 2'),
(15, 2, NULL, 'Lame du Juste 2'),
(16, 1, 'Dread Wave', 'Arcane Lore 1.1'),
(16, 2, 'Vague d''Effroi', 'Savoir des Arcanes 1.1'),
(17, 1, 'Hammerfall', 'Arcane Lore 1.2'),
(17, 2, 'Marteau', 'Savoir des Arcanes 1.2'),
(18, 1, 'Red Hymn', 'Arcane Lore 2.1'),
(18, 2, 'Hymne Rouge', 'Savoir des Arcanes 2.1'),
(19, 1, 'Grey Hymn', 'Arcane Lore 2.2'),
(19, 2, 'Hymne Gris', 'Savoir des Arcanes 2.2'),
(20, 1, NULL, 'Force of Will 1'),
(20, 2, NULL, 'Force de Volonté 1'),
(21, 1, NULL, 'Force of Will 2'),
(21, 2, NULL, 'Force de Volonté 2');

INSERT INTO users (id, login, password, email) VALUES
(1, 'admin', 'password', 'admin@admin.fr');






