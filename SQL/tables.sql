drop database if exists proiect;
create database proiect;
use proiect;

create table utilizatori (
CNP varchar(25) primary key unique,
Nume varchar(25) not null,
Prenume varchar(25) not null,
Adresa text,
Telefon varchar(25),
Email text,
IBAN varchar(25),
Contract varchar(25),
Password varchar(25),
AdminAcces int default 0
);

create table roluri(
CNP_User varchar(25) UNIQUE, 
ID_Rol int not null,
Descriere_Rol varchar(25) not null, 
primary key(CNP_User, Id_Rol),
CONSTRAINT FK_CNP_USER_ROLURI FOREIGN KEY (CNP_User) REFERENCES utilizatori (CNP) ON UPDATE CASCADE ON DELETE CASCADE
);

create table profesori(
CNP_Profesor varchar(25) primary key UNIQUE,
Ore_Min int,
Ore_Max int,
Departament varchar(25),
CONSTRAINT FK_CNP_PROFESOR_PROFESORI FOREIGN KEY (CNP_Profesor) REFERENCES utilizatori (CNP) ON UPDATE CASCADE ON DELETE CASCADE
);

create table studenti(
CNP_Student varchar(25) primary key UNIQUE,
Nr_Ore int,
An_Studiu int,
CONSTRAINT FK_CNP_STUDENT FOREIGN KEY (CNP_Student) REFERENCES utilizatori (CNP) ON UPDATE CASCADE ON DELETE CASCADE
);

create table cursuri(
ID_Curs int not null,
CNP_Profesor varchar(25) not null,
CNP_Student varchar(25),
Descriere_Curs varchar(25) not null,
Nr_Ore int,
Nr_Studenti int default 0,
Nr_Studenti_Max int,
Procent_Nota int,
Nota_Curs int,
Nota_Finala int,
CONSTRAINT FK_CNP_PROFESOR_CURSURI FOREIGN KEY (CNP_Profesor) REFERENCES profesori (CNP_Profesor) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_STUDENT_CURSURI FOREIGN KEY (CNP_Student) REFERENCES studenti (CNP_Student) ON UPDATE CASCADE ON DELETE CASCADE
);

create table grupuri(
ID_Grup int not null,
CNP_Admin_Grup varchar(25),
CNP_Student varchar(25),
CNP_Profesor_Grup varchar(25),
Invitatie_Student int default -1,
Descriere_Grup varchar(25) not null,
primary key(ID_Grup, CNP_Admin_Grup, CNP_Student),
CONSTRAINT FK_CNP_ADMIN_GRUP_GRUP FOREIGN KEY (CNP_Admin_Grup) REFERENCES studenti (CNP_Student) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_STUDENT_GRUP FOREIGN KEY (CNP_Student) REFERENCES studenti (CNP_Student) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_PROFESOR_GRUP_GRUP FOREIGN KEY (CNP_Profesor_Grup) REFERENCES profesori (CNP_Profesor) ON UPDATE CASCADE ON DELETE CASCADE
);

create table seminarii(
ID_Curs int,
CNP_Profesor_Curs varchar(25),
CNP_Profesor_Seminar varchar(25),
CNP_Student varchar(25),
Procent_Nota int default 0,
Nota_Seminar int default 0,
CONSTRAINT FK_CNP_PROFESOR_CURS_SEMINARII FOREIGN KEY (CNP_Profesor_Curs) REFERENCES cursuri (CNP_Profesor) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_PROFESOR_SEMINAR_SEMINARII FOREIGN KEY (CNP_Profesor_Seminar) REFERENCES profesori (CNP_Profesor) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_STUDENT_SEMINARII FOREIGN KEY (CNP_Student) REFERENCES cursuri (CNP_Student) ON UPDATE CASCADE ON DELETE CASCADE
);

create table laboratoare(
ID_Curs int,
CNP_Profesor_Curs varchar(25),
CNP_Profesor_Laborator varchar(25),
CNP_Student varchar(25),
Procent_Nota int default 0,
Nota_Laborator int default 0,
CONSTRAINT FK_CNP_PROFESOR_CURS_LABORATOARE FOREIGN KEY (CNP_Profesor_Curs) REFERENCES cursuri (CNP_Profesor) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_PROFESOR_LABORATOR_LABORATOARE FOREIGN KEY (CNP_Profesor_Laborator) REFERENCES profesori (CNP_Profesor) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_STUDENT_LABORATOARE FOREIGN KEY (CNP_Student) REFERENCES cursuri (CNP_Student) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE orar(
ID_Curs int not null,
tip_activitate int default 0,
descriere varchar(20) not null,
ziua int not null,
ora_start TIME not null,
ora_final TIME not null
);

CREATE TABLE mesaje(
ID_Grup int not null,
CNP varchar(25),
Mesaj text,
Data datetime,
CONSTRAINT FK_ID_GRUP_MESAJE FOREIGN KEY (ID_Grup) REFERENCES grupuri (ID_Grup) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FK_CNP_MESAJE FOREIGN KEY (CNP) REFERENCES utilizatori (CNP) ON UPDATE CASCADE ON DELETE CASCADE
);