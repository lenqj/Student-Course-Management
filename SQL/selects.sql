select * from utilizatori;
select * from studenti;
select * from profesori;
select * from roluri;

select * from cursuri;

select * from seminarii;
select * from laboratoare;
use proiect;

select * from orar;
SELECT CNP_Student from studenti where CNP_Student not in (SELECT CNP_Student from cursuri where  CNP_Profesor = '3' and ID_Curs = '3') or CNP_Student is not null;
select * from grupuri;
select * from mesaje;