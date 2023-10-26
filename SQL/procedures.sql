DELIMITER //
create procedure cauta_curs(ID_Curs_cautat int)
	BEGIN
	START TRANSACTION; 
		SELECT CNP, Nume, Prenume, Adresa, Telefon, Email, IBAN, Contract from utilizatori, cursuri where utilizatori.CNP = cursuri.CNP_Student and cursuri.ID_Curs = ID_Curs_cautat;
 END; //
 
DELIMITER //
create procedure cauta_utilizator(CNP_User_cautat varchar(20))
	BEGIN
	START TRANSACTION; 
		SELECT * from utilizatori where CNP = CNP_User_cautat;
 END; //
 
DELIMITER //
create procedure adauga_student_curs(CNP_Student_Adaugat varchar(20), ID_Curs_Adaugat int)
	BEGIN
		declare lCNP_Student_Adaugat varchar(20) default NULL;
		declare lCNP_Profesor varchar(20) default NULL;
		declare lCNP_Student varchar(20) default NULL;
		declare lDescriere_Curs varchar(20) default NULL;
		declare lNr_Studenti int default 0;
		declare lNr_Studenti_Max int default 0;
		declare lNr_Ore int default 0;
		declare lNr_Ore_Student int default 0;
        declare lProcent int default 0;
		SELECT CNP_Profesor into lCNP_Profesor from cursuri where ID_Curs = ID_Curs_Adaugat LIMIT 1;
		SELECT CNP_Student into lCNP_Student from cursuri where ID_Curs = ID_Curs_adaugat LIMIT 1;
		SELECT Descriere_Curs into lDescriere_Curs from cursuri where ID_Curs = ID_Curs_adaugat LIMIT 1;
		SELECT Nr_Studenti into lNr_Studenti from cursuri where ID_Curs = ID_Curs_adaugat LIMIT 1;
		SELECT Nr_Studenti_Max into lNr_Studenti_Max from cursuri where ID_Curs = ID_Curs_adaugat LIMIT 1;
		SELECT Nr_Ore into lNr_Ore from cursuri where ID_Curs = ID_Curs_adaugat LIMIT 1;
		SELECT CNP_Student into lCNP_Student_Adaugat from cursuri where CNP_Student = CNP_Student_Adaugat and ID_Curs = ID_Curs_adaugat LIMIT 1;
		SELECT Nr_Ore into lNr_Ore_Student from studenti where CNP_Student = CNP_Student_Adaugat LIMIT 1;
        SELECT Procent_Nota into lProcent from cursuri where ID_Curs = ID_Curs_adaugat LIMIT 1;
		if(lNr_Studenti < lNr_Studenti_Max) then
			if(lNr_ORE_Student >= lNr_Ore) then
				if (lCNP_Student_Adaugat is NULL) then
					if (lCNP_Student is not NULL) then 
                        insert into cursuri values(ID_Curs_Adaugat, lCNP_Profesor, CNP_Student_Adaugat, lDescriere_Curs, lNr_Ore, lNr_Studenti, lNr_Studenti_Max, lProcent, 0, 0);
					else 
                        UPDATE cursuri SET CNP_Student = CNP_Student_adaugat  where ID_Curs = ID_Curs_adaugat and CNP_Profesor = lCNP_Profesor and Descriere_Curs = lDescriere_Curs;
					END if;
                    UPDATE cursuri SET Nr_Studenti = Nr_Studenti+1 where ID_Curs = ID_Curs_adaugat and CNP_Profesor = lCNP_Profesor and Descriere_Curs = lDescriere_Curs;
					UPDATE studenti SET Nr_Ore = Nr_Ore - lNr_Ore where CNP_Student = CNP_Student_adaugat;
				else 
					select 'Studentul exista deja.' as error;
				end if;
			else 
				select 'Nu mai are ore disponibile.' as error;
			end if;
		else
			select 'Nu se mai pot adauga studenti la acest curs deoarece a atins numarul maxim' as error;
		end if;
 END; //
 
DELIMITER //
CREATE PROCEDURE selectare_profesor_nr_studenti_minim(OUT ID_Curs_Min int)
	BEGIN
		declare lmin varchar(20) default NULL;
		SELECT min(Nr_Studenti) into lmin from cursuri limit 1;
		SELECT ID_Curs into ID_Curs_Min from cursuri where Nr_Studenti = lmin limit 1;
END; //

DELIMITER //
CREATE PROCEDURE creeaza_grup(CNP_Admin_Grup_Nou varchar(25), Descriere_Grup varchar(25), ID_Grup_Nou int)
	BEGIN
		declare lID_Grup int default null;
		SELECT ID_Grup into lID_Grup from grupuri where ID_Grup = ID_Grup_Nou LIMIT 1;
		if(lID_Grup is null) then
			insert into grupuri values (ID_Grup_Nou, CNP_Admin_Grup_Nou, CNP_Admin_Grup_Nou, null, 2, Descriere_Grup);
		else
			select 'Exista deja un grup cu acest id!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE adauga_profesor_grup(CNP_Profesor_Grup_Nou varchar(25), ID_Grup_Nou int)
	BEGIN
		declare lID_Grup int default null;
		SELECT ID_Grup into lID_Grup from grupuri where ID_Grup = ID_Grup_Nou LIMIT 1;
		if(lID_Grup is not null) then
			update grupuri set CNP_Profesor_Grup = CNP_Profesor_Grup_Nou where ID_Grup = ID_Grup_Nou;
		else
			select 'Nu exista un grup cu acest id!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE invita_grup(CNP_Admin varchar(25), CNP_Student_Invitat varchar(25), ID_Grup_Adaugat int)
	BEGIN
		declare lID_Grup int default 0;
		declare lStudent_Invitat varchar(25) default null;
		declare lDescriere_Grup varchar(25) default null;
		SELECT ID_Grup into lID_Grup from grupuri wherE CNP_Admin_Grup = CNP_Admin and ID_Grup = ID_Grup_Adaugat LIMIT 1;
		SELECT CNP_Student into lStudent_Invitat from grupuri where CNP_Student = CNP_Student_Invitat and ID_Grup = lID_Grup and CNP_Admin_Grup = CNP_Admin LIMIT 1;
		SELECT Descriere_Grup into lDescriere_Grup from grupuri wherE CNP_Admin_Grup = CNP_Admin and ID_Grup = lId_Grup LIMIT 1;
		if(lID_Grup is not null and lDescriere_Grup is not null) then
			if(lStudent_Invitat is null) then
				insert into grupuri values (ID_Grup_Adaugat, CNP_Admin, CNP_Student_Invitat, null, 1, lDescriere_Grup);
			else
				select 'Studentul a fost deja invitat!' as error;
			end if;
		else
			select 'Nu esti admin ca sa inviti persoane! / Nu exista un grup cu acest id!' as error;
		end if;
END; //

DELIMITER //
create procedure creeaza_curs(ID_Curs_Adaugat int, CNP_Profesor_Adaugat varchar(25), descriere varchar(25), nr_ore_adaugat int, nr_studenti_adaugat int, nr_studenti_max_adaugat int, procent int)
	BEGIN
		declare lID_Curs int default null;
        select ID_Curs into lID_curs from cursuri where ID_Curs = ID_Curs_Adaugat LIMIT 1;
        if(lID_Curs is null) then
			insert into cursuri values (ID_Curs_Adaugat, CNP_Profesor_Adaugat, NULL, descriere, nr_ore_adaugat, nr_studenti_adaugat, nr_studenti_max_adaugat, procent, 0, 0);
		else
			select 'Exista deja un curs cu acest ID!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE creeaza_seminar(CNP_Profesor_Curs varchar(25), CNP_Profesor_Seminar varchar(25), ID_Curs_Adaugat int, Procent int)
	BEGIN
		declare lID_Curs int default null;
        declare lseminar int default null;
		SELECT ID_Curs into lID_Curs from cursuri where CNP_Profesor = CNP_Profesor_Curs and ID_Curs=ID_Curs_Adaugat LIMIT 1;
		if(lID_Curs is not null) then
			select ID_Curs into lseminar from seminarii where ID_Curs = ID_Curs_Adaugat LIMIT 1;
            if(lseminar is null) then
				insert into seminarii values (ID_Curs_Adaugat, CNP_Profesor_Curs, CNP_Profesor_Seminar, null, Procent, 0);
            else
				select 'Materia selectata are deja un seminar adaugat!' as error;
            end if;
        else
			select 'Nu sunteti profesor la acest curs! / Cursul nu exista!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE creeaza_laborator(CNP_Profesor_Curs varchar(25), CNP_Profesor_Laborator varchar(25), ID_Curs_Adaugat int, Procent int)
	BEGIN
		declare lID_Curs int default null;
        declare llaborator int default null;
		SELECT ID_Curs into lID_Curs from cursuri where CNP_Profesor = CNP_Profesor_Curs and ID_Curs=ID_Curs_Adaugat LIMIT 1;
		if(lID_Curs is not null) then
			select ID_Curs into llaborator from laboratoare where ID_Curs = ID_Curs_Adaugat LIMIT 1;
            if(llaborator is null) then
				insert into laboratoare values (ID_Curs_Adaugat, CNP_Profesor_Curs, CNP_Profesor_Laborator, null, Procent, 0);
			else
				select 'Materia selectata are deja un laborator adaugat!' as error;
			end if;
        else
			select 'Nu sunteti profesor la acest curs! / Cursul nu exista!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE sterge_student(CNP_Student_de_Sters varchar(20))
	BEGIN
		declare lCNP_Student varchar(20) default null;
		select CNP_Student into lCNP_Student from studenti where CNP_Student = CNP_Student_de_Sters LIMIT 1;
		if(lCNP_Student is not null) then
			delete from studenti where CNP_Student = CNP_Student_de_Sters;
		else
			select 'Nu exista un student cu acest CNP!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE sterge_profesor(CNP_Profesor_de_Sters varchar(20))
	BEGIN
		declare lCNP_Profesor varchar(20) default null;
		select CNP_Profesor into lCNP_Profesor from profesori where CNP_Profesor = CNP_Profesor_de_Sters LIMIT 1;
		if(lCNP_Profesor is not null) then
			delete from profesori where CNP_Profesor = CNP_Profesor_de_sters;
		else
			select 'Nu exista un profesor cu acest CNP!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE sterge_admin(CNP_Admin_de_Sters varchar(20))
	BEGIN
		declare lCNP_Admin varchar(20) default null;
		declare lAdminAcces int default 1;
		select CNP into lCNP_Admin from utilizatori where CNP = CNP_Admin_de_Sters LIMIT 1;
		if(lCNP_Admin is not null) then
			select AdminAcces into lAdminAcces from utilizatori where CNP = CNP_Admin_de_Sters LIMIT 1;
			if(lAdminAcces = 1) then
				update utilizatori set AdminAcces = 0 where CNP = CNP_Admin_de_Sters;
				update roluri set ID_Rol = 1, Descriere_Rol = 'Utilizator' where CNP_user = CNP_Admin_de_Sters;
			else
				select 'Acest utilizator nu detine functia de administrator' as error;
			end if;
		else
			select 'Nu exista un admin cu acest CNP!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE programare_curs(ID_Curs_adaugat int, ziua_adaugat int, ora_start_adaugat time, ora_final_adaugat time)
	BEGIN
		declare lID_Curs int default null;
        declare punic int default null;
		declare zi_laborator int;
		declare ora_laborator_start time;
		declare ora_laborator_final time;
		declare zi_seminar int;
		declare ora_seminar_start time;
		declare ora_seminar_final time;
		declare oks int default 1;
		declare okl int default 1;
		select ID_Curs into lID_Curs from cursuri where ID_curs = ID_Curs_adaugat LIMIT 1;
		if(lID_Curs is not null) then
			select ID_Curs into punic from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0;
            if(punic is null) then
				select ora_start into ora_seminar_start from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1; 
				select ora_final into ora_seminar_final from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1;
				select ziua into zi_seminar from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1; 
				select ora_start into ora_laborator_start from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2; 
				select ora_final into ora_laborator_final from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2;
				select ziua into zi_laborator from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2; 
				if(ziua_adaugat = zi_seminar) then
					if(ora_start_adaugat >= ora_seminar_start and ora_start_adaugat <= ora_seminar_final) then
						set oks = 0;
					elseif(ora_final_adaugat >= ora_seminar_start and ora_final_adaugat <= ora_seminar_final) then
							set oks = 0;
					end if;
				end if;
				if(ziua_adaugat = zi_laborator) then
					if(ora_start_adaugat >= ora_laborator_start and ora_start_adaugat <= ora_laborator_final) then
						set okl = 0;
					elseif(ora_final_adaugat >= ora_laborator_start and ora_final_adaugat <= ora_laborator_final) then
							set okl = 0;
					end if;
				end if;
				if(oks = 1 and okl = 1) then
					insert into orar values(ID_Curs_adaugat, 0, 'Curs', ziua_adaugat, ora_start_adaugat, ora_final_adaugat);
				else
					select 'Programul se suprapune cu alta activitate din cadrul acestei materii!' as error;
				end if;
			else
				select 'Acest curs este deja programat!' as error;
			end if;
				
		else
			select 'Cursul cu acest ID nu s-a gasit!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE programare_seminar(ID_Curs_adaugat int, ziua_adaugat int, ora_start_adaugat time, ora_final_adaugat time)
	BEGIN
		declare lID_Curs int default null;
        declare punic int default null;
		declare zi_laborator int;
		declare ora_laborator_start time;
		declare ora_laborator_final time;
		declare zi_curs int;
		declare ora_curs_start time;
		declare ora_curs_final time;
		declare okc int default 1;
		declare okl int default 1;
		select ID_Curs into lID_Curs from seminarii where ID_curs = ID_Curs_adaugat LIMIT 1;
		if(lID_Curs is not null) then
			select ID_Curs into punic from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1;
            if(punic is null) then
				select ora_start into ora_curs_start from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0; 
				select ora_final into ora_curs_final from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0;
				select ziua into zi_curs from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0; 
				select ora_start into ora_laborator_start from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2; 
				select ora_final into ora_laborator_final from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2;
				select ziua into zi_laborator from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2; 
				if(ziua_adaugat = zi_curs) then
					if(ora_start_adaugat >= ora_curs_start and ora_start_adaugat <= ora_curs_final) then
						set okc = 0;
					elseif(ora_final_adaugat >= ora_curs_start and ora_final_adaugat <= ora_curs_final) then
						set okc = 0;
					end if;
				end if;
				if(ziua_adaugat = zi_laborator) then
					if(ora_start_adaugat >= ora_laborator_start and ora_start_adaugat <= ora_laborator_final) then
						set okl = 0;
					elseif(ora_final_adaugat >= ora_laborator_start and ora_final_adaugat <= ora_laborator_final) then
						set okl = 0;
					end if;
				end if;
				if(okc = 1 and okl = 1) then
					insert into orar values(ID_Curs_adaugat, 1, 'Seminar', ziua_adaugat, ora_start_adaugat, ora_final_adaugat);
				else
					select 'Programul se suprapune cu alta activitate din cadrul acestei materii!' as error;
				end if;
			else	
				select 'Acest seminar este deja programat!' as error;
			end if;
		else
			select 'Cursul cu acest ID nu s-a gasit!' as error;
		end if;
END; //

CREATE PROCEDURE programare_laborator(ID_Curs_adaugat int, ziua_adaugat int, ora_start_adaugat time, ora_final_adaugat time)
	BEGIN
		declare lID_Curs int default null;
        declare punic int default null;
		declare zi_seminar int;
		declare ora_seminar_start time;
		declare ora_seminar_final time;
		declare zi_curs int;
		declare ora_curs_start time;
		declare ora_curs_final time;
		declare okc int default 1;
		declare oks int default 1;
		select ID_Curs into lID_Curs from laboratoare where ID_curs = ID_Curs_adaugat LIMIT 1;
		if(lID_Curs is not null) then
			select ID_Curs into punic from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 2;
            if(punic is null) then
				select ora_start into ora_curs_start from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0; 
				select ora_final into ora_curs_final from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0;
				select ziua into zi_curs from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 0; 
				select ora_start into ora_seminar_start from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1; 
				select ora_final into ora_seminar_final from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1;
				select ziua into zi_seminar from orar where ID_Curs = ID_Curs_adaugat and tip_activitate = 1; 
				if(ziua_adaugat = zi_curs) then
					if(ora_start_adaugat >= ora_curs_start and ora_start_adaugat <= ora_curs_final) then
						set okc = 0;
					elseif(ora_final_adaugat >= ora_curs_start and ora_final_adaugat <= ora_curs_final) then
							set okc = 0;
					end if;
				end if;
				if(ziua_adaugat = zi_seminar) then
					if(ora_start_adaugat >= ora_seminar_start and ora_start_adaugat <= ora_seminar_final) then
						set oks = 0;
					elseif(ora_final_adaugat >= ora_seminar_start and ora_final_adaugat <= ora_seminar_final) then
							set oks = 0;
					end if;
				end if;
				if(okc = 1 and oks = 1) then
					insert into orar values(ID_Curs_adaugat, 2, 'Laborator', ziua_adaugat, ora_start_adaugat, ora_final_adaugat);
				else
					select 'Programul se suprapune cu alta activitate din cadrul acestei materii!' as error;
				end if;
			else
				select 'Acest laborator este deja programat!';
			end if;
		else
			select 'Cursul cu acest ID nu s-a gasit!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE adaugare_nota_curs(ID_Curs_Notare int, CNP_Profesor_Notare varchar(20), CNP_Student_Notat varchar(20), Nota_Adaugata int)
	BEGIN
		declare lID_Curs int default null;
        declare lProfesor varchar(20) default null;
        declare lStudent varchar(20) default null;
        
        SELECT ID_CURS into lID_Curs from cursuri where ID_Curs = ID_Curs_Notare LIMIT 1;
        SELECT CNP_Profesor into lProfesor from cursuri where ID_Curs = ID_Curs_Notare;
        SELECt CNP_Student into lStudent from cursuri where ID_Curs = ID_Curs_Notare LIMIT 1;
        
        if(lID_Curs is not null) then
			if(lProfesor is not null) then
				if(lStudent is not null) then
					update cursuri set Nota_Curs = Nota_Adaugata where ID_curs = ID_Curs_Notare and CNP_Student = CNP_Student_Notat;
				else
					select 'Acest student nu este inscris la acest curs!' as error;
				end if;
			else
				select 'Nu sunteti profesor la acest curs!' as error;
			end if;
		else
			select 'Nu exista acest curs!' as error;
		end if;
        
END; //

DELIMITER //
CREATE PROCEDURE adaugare_nota_seminar(ID_Curs_Notare int, CNP_Profesor_Notare varchar(20), CNP_Student_Notat varchar(20), Nota_Adaugata int)
	BEGIN
		declare lID_Curs int default null;
        declare lProfesor varchar(20) default null;
        declare lStudent varchar(20) default null;
        SELECT ID_CURS into lID_Curs from seminarii where ID_Curs = ID_Curs_Notare LIMIT 1;
        SELECT CNP_Profesor_Seminar into lProfesor from seminarii where ID_Curs = ID_Curs_Notare;
        SELECt CNP_Student into lStudent from seminarii where ID_Curs = ID_Curs_Notare LIMIT 1;
        if(lID_Curs is not null) then
			if(lProfesor is not null) then
				if(lStudent is not null) then
					update seminarii set Nota_Seminar = Nota_Adaugata where ID_curs = ID_Curs_Notare and CNP_Student = CNP_Student_Notat;
				else
					select 'Acest student nu este inscris la acest curs!' as error;
				end if;
			else
				select 'Nu sunteti profesor la acest curs!' as error;
			end if;
		else
			select 'Nu exista acest curs!' as error;
		end if;
        
END; //

DELIMITER //
CREATE PROCEDURE adaugare_nota_laborator(ID_Curs_Notare int, CNP_Profesor_Notare varchar(20), CNP_Student_Notat varchar(20), Nota_Adaugata int)
	BEGIN
		declare lID_Curs int default null;
        declare lProfesor varchar(20) default null;
        declare lStudent varchar(20) default null;
        SELECT ID_CURS into lID_Curs from laboratoare where ID_Curs = ID_Curs_Notare LIMIT 1;
        SELECT CNP_Profesor_Laborator into lProfesor from laboratoare where ID_Curs = ID_Curs_Notare;
        SELECt CNP_Student into lStudent from laboratoare where ID_Curs = ID_Curs_Notare LIMIT 1;
        if(lID_Curs is not null) then
			if(lProfesor is not null) then
				if(lStudent is not null) then
					update laboratoare set Nota_Laborator = Nota_Adaugata where ID_curs = ID_Curs_Notare and CNP_Student = CNP_Student_Notat;
				else
					select 'Acest student nu este inscris la acest curs!' as error;
				end if;
			else
				select 'Nu sunteti profesor la acest curs!' as error;
			end if;
		else
			select 'Nu exista acest curs!' as error;
		end if;
        
END; //

DELIMITER //
CREATE PROCEDURE trimite_mesaj(CNP_User_Mesaj varchar(25), ID_Grup_Mesaj int, Mesaj_Grup text)
	BEGIN
		declare lCNP_User_Mesaj int default null;
        declare lID_Grup_Mesaj int default null;
        
        SELECT ID_Grup into lID_Grup_Mesaj from grupuri where ID_Grup = ID_Grup_Mesaj LIMIT 1;
		
		if(lID_Grup_Mesaj is not null) then
			SELECT CNP_Student or CNP_Profesor_Grup into lCNP_User_Mesaj from grupuri where CNP_Student = CNP_User_Mesaj or CNP_Profesor_Grup = CNP_User_Mesaj LIMIT 1;
            if(lCNP_User_Mesaj is not null) then
				insert into mesaje values (ID_Grup_Mesaj, CNP_User_Mesaj, Mesaj_Grup, NOW());
			else
				select 'Nu faceti parte din acest grup!' as error;
            end if;
        else
			select 'Grupul nu exista!' as error;
		end if;
END; //

DELIMITER //
CREATE PROCEDURE seteaza_nota_finala(CNP_User_Nota varchar(25), ID_Curs_Nota int)
	BEGIN
        declare lNota_Curs int default 0;
        declare lNota_Seminar int default 0;
        declare lNota_Laborator int default 0;
        declare lProcent_Curs int default 0;
        declare lProcent_Seminar int default 0;
        declare lProcent_Laborator int default 0;
        SELECT Nota_Curs into lNota_Curs from cursuri where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota LIMIT 1;
        SELECT Nota_Seminar into lNota_Seminar from seminarii where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota LIMIT 1;
        SELECT Nota_Laborator into lNota_Laborator from laboratoare where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota LIMIT 1;
        SELECT Procent_Nota into lProcent_Curs from cursuri where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota LIMIT 1;
        SELECT Procent_Nota into lProcent_Seminar from seminarii where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota LIMIT 1;
        SELECT Procent_Nota into lProcent_Laborator from laboratoare where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota LIMIT 1;
        
		update cursuri set Nota_Finala = lNota_Curs*(lProcent_Curs/100)+lNota_Seminar*(lProcent_Seminar/100)+lNota_Laborator*(lProcent_Laborator/100) where ID_Curs = ID_Curs_Nota and CNP_Student = CNP_User_Nota;
END; //

DELIMITER //
CREATE PROCEDURE sterge_utilizator(CNP_User varchar(25))
	BEGIN
		delete from utilizatori where CNP = CNP_User;
END; //