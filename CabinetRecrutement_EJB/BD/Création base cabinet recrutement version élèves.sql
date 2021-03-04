-- Titre :             Création base cabinet recrutement version élèves.sql
-- Version :           1.1
-- Date création :     28 juin 2011
-- Date modification : 25 janvier 2021
-- Auteur :            Gabriel Dérian & Karl La Grassa
-- Description :       Script de création de la base de données pour le SI "gestion de cabinet de
--                     recrutement"


-- +----------------------------------------------------------------------------------------------+
-- | Suppression des tables                                                                       |
-- +----------------------------------------------------------------------------------------------+

drop table if exists entreprise cascade;
drop table if exists offre_emploi cascade;
drop table if exists candidature cascade;
drop table if exists niveau_qualification cascade;
drop table if exists offre_emploi_secteur_activite cascade;
drop table if exists candidature_secteur_activite cascade;
drop table if exists secteur_activite cascade;
drop table if exists message_offre_d_emploi cascade;
drop table if exists message_candidature cascade;
-- +----------------------------------------------------------------------------------------------+
-- | Création des tables                                                                          |
-- +----------------------------------------------------------------------------------------------+

create table entreprise
(
  id_entreprise   			serial primary key,
  nom             			text not null,
  descriptif      			text,
  adresse_postale 			text not null -- Pour simplifier, adresse_postale = ville.
);

create table niveau_qualification
(
  id_qualification			serial primary key,
  nom             			text not null
);

create table offre_emploi
(
  id_offre_emploi           serial primary key,
  titre                 	varchar(50) not null,
  descriptif_mission      	text,
  profil_recherche      	text,
  date_depot        		date,
  niveau_qualification_fk   serial not null references  niveau_qualification, 
  entreprise_fk      		integer not null references entreprise
);

create table candidature
(
  id_candidature            serial primary key,
  nom               		varchar(50) not null,
  prenom            		varchar(50) not null,
  date_naissance    		date not null,
  adresse_postale   		varchar(30), -- Pour simplifier, adresse_postale = ville.
  adresse_email     		varchar(50) not null,
  cv            			text, 
  date_depot        		date,
  niveau_qualification_fk	serial not null references niveau_qualification
);

create table secteur_activite
(
  id_secteur_activite		serial primary key,
  nom               		varchar(50) not null
);

create table offre_emploi_secteur_activite
(
  primary key (offre_emploi_fk, secteur_activite_fk),
  offre_emploi_fk          		integer not null references offre_emploi,
  secteur_activite_fk          serial not null references secteur_activite
);


create table candidature_secteur_activite
(
  primary key (candidature_fk, secteur_activite_fk),
  candidature_fk	            integer not null references candidature,
  secteur_activite_fk	        serial not null references secteur_activite
);

create table message_offre_d_emploi
(
	id_message_offre_emploi serial 	primary key,
	date_envoi 						date,
	corps_message text,
	offre_emploi_fk					integer not null references offre_emploi,
	candidature_fk					integer not null references candidature
);

create table message_candidature
(
	id_message_candidature serial 	primary key,
	date_envoi 					  	date,
	corps_message text,
	offre_emploi_fk			  		integer not null references offre_emploi,
	candidature_fk					integer not null references candidature
);


-- +----------------------------------------------------------------------------------------------+
-- | Insertion de quelques données de pour les tests                                              |
-- +----------------------------------------------------------------------------------------------+

-- Insertion des entreprises

insert into entreprise values (nextval('entreprise_id_entreprise_seq'),'IMT Atlantique','IMT Atlantique est une grande école pionnière en formation, en recherche et en entrepreneuriat et en tout plein de choses...','Plouzané');
insert into entreprise values (nextval('entreprise_id_entreprise_seq'),'ENIB','Une école d''ingénieur juste à côté...','Plouzané');
insert into entreprise values (nextval('entreprise_id_entreprise_seq'),'Orange','La vie change avec Orange','Paris');
insert into entreprise values (nextval('entreprise_id_entreprise_seq'),'Naval Group','Leader du naval de défense','Paris');


-- Insertion des secteurs d'activité

insert into secteur_activite values (nextval('secteur_activite_id_secteur_activite_seq'), 'Agroalimentaire');
insert into secteur_activite values (nextval('secteur_activite_id_secteur_activite_seq'), 'Banque / Assurance');
insert into secteur_activite values (nextval('secteur_activite_id_secteur_activite_seq'), 'Bois / Papier / Carton / Imprimerie');
insert into secteur_activite values (nextval('secteur_activite_id_secteur_activite_seq'), 'BTP / Matériaux de construction');
insert into secteur_activite values (nextval('secteur_activite_id_secteur_activite_seq'), 'Chimie / Parachimie');
insert into secteur_activite values (nextval('secteur_activite_id_secteur_activite_seq'), 'Commerce / Négoce / Distribution');

-- Insertions des niveaux de qualifications

insert into niveau_qualification values (nextval('niveau_qualification_id_qualification_seq'),'CAP/BEP');
insert into niveau_qualification values (nextval('niveau_qualification_id_qualification_seq'),'Bac +2');
insert into niveau_qualification values (nextval('niveau_qualification_id_qualification_seq'),'Bac +3');
insert into niveau_qualification values (nextval('niveau_qualification_id_qualification_seq'),'Bac +5');
insert into niveau_qualification values (nextval('niveau_qualification_id_qualification_seq'),'Doctorat');

-- Insertion des offres d'emploi
insert into offre_emploi values (nextval('offre_emploi_id_offre_emploi_seq'),'Ingénieur front-end',
							'Poste à pourvoir',
							'Expérience recommandée',
							'2021-09-04',
							'4',
							'3');
insert into offre_emploi values (nextval('offre_emploi_id_offre_emploi_seq'),'Ingénieur réseau',
							'Poste à pourvoir',
							'Expérience recommandée',
							'2020-09-04',
							'4',
							'4');
							
-- Insertion de candidatures
insert into candidature values (nextval('candidature_id_candidature_seq'), 'La Grassa', 'Karl', '27/04/1999', 
								'Puget-Ville', 'karl@email.fr', 'Ceci est un CV', '04/03/2021','2')


