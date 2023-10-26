delimiter //
CREATE TRIGGER initializare_rol AFTER INSERT ON utilizatori
	FOR EACH ROW BEGIN
		INSERT INTO roluri (CNP_User, ID_Rol, Descriere_Rol) VALUES (new.CNP, 1,  'Utilizator');
END; // 

delimiter //
CREATE TRIGGER modifica_rol_adaugare_profesor AFTER INSERT ON profesori
	FOR EACH ROW BEGIN
		UPDATE roluri set ID_Rol = 3, Descriere_Rol = 'Profesor' WHERE CNP_User = NEW.CNP_Profesor;
END; // 

delimiter //
CREATE TRIGGER modifica_rol_stergere_profesor AFTER DELETE ON profesori
	FOR EACH ROW BEGIN
		UPDATE roluri set ID_Rol = 1, Descriere_Rol = 'Utilizator' WHERE CNP_User = OLD.CNP_Profesor;
END; // 

delimiter // 
CREATE TRIGGER modifica_rol_adaugare_admin_update AFTER UPDATE ON utilizatori
	FOR EACH ROW BEGIN
		if(NEW.AdminAcces = 1) then
			UPDATE roluri set ID_Rol = 4, Descriere_Rol = 'Admin' WHERE CNP_User = NEW.CNP;
		elseif(NEW.AdminAcces = 2) then
			UPDATE roluri set ID_Rol = 5, Descriere_Rol = 'Super Admin'WHERE CNP_User = NEW.CNP;
		end if;
END; // 

delimiter // 
CREATE TRIGGER modifica_rol_adaugare_admin_insert AFTER INSERT ON utilizatori
	FOR EACH ROW BEGIN
		if(NEW.AdminAcces = 1) then
			UPDATE roluri set ID_Rol = 4, Descriere_Rol = 'Admin' WHERE CNP_User = NEW.CNP;
		elseif(NEW.AdminAcces = 2) then
			UPDATE roluri set ID_Rol = 5, Descriere_Rol = 'Super Admin'WHERE CNP_User = NEW.CNP;
		end if;
END; // 

delimiter //
CREATE TRIGGER modifica_rol_adaugare_student AFTER INSERT ON studenti
	FOR EACH ROW BEGIN
		UPDATE roluri set ID_Rol = 2, Descriere_Rol = 'Student' WHERE CNP_User = NEW.CNP_Student;
END; // 

delimiter //
CREATE TRIGGER modifica_rol_stergere_student AFTER DELETE ON studenti
	FOR EACH ROW BEGIN
		UPDATE roluri set ID_Rol = 1, Descriere_Rol = 'Utilizator' WHERE CNP_User = OLD.CNP_Student;
END; // 

DELIMITER //
CREATE TRIGGER adauga_studenti_seminar_update AFTER UPDATE ON cursuri
	FOR EACH ROW BEGIN
		declare lseminar int default null;
        declare lCNP_Student varchar(20) default null;
        declare lCNP_Student2 varchar(20) default null;
        declare lProfesor_Seminar varchar(20) default null;
        declare lProcent_Nota int;
		SELECT ID_Curs into lseminar from seminarii where ID_Curs = NEW.ID_Curs LIMIT 1;
        SELECT CNP_Student into lCNP_Student from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor and CNP_Student = NEW.CNP_Student;
        SELECT CNP_Student into lCNP_Student2 from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT CNP_Profesor_Seminar into lProfesor_Seminar from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT Procent_Nota into lProcent_Nota from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
		if(lseminar is not NULL and lCNP_Student is null) THEN
			if(lCNP_Student2 is null) then
				UPDATE seminarii SET CNP_Student = NEW.CNP_Student where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor;
			else
				insert into seminarii values (NEW.ID_Curs, NEW.CNP_Profesor, lProfesor_Seminar, NEW.CNP_Student, lProcent_Nota, 0);
			end if;
        end if;
END; //

DELIMITER //
CREATE TRIGGER adauga_studenti_seminar_insert AFTER INSERT ON cursuri
	FOR EACH ROW BEGIN
		declare lseminar int default null;
        declare lCNP_Student varchar(20) default null;
        declare lCNP_Student2 varchar(20) default null;
        declare lProfesor_Seminar varchar(20) default null;
        declare lProcent_Nota int;
        
		SELECT ID_Curs into lseminar from seminarii where ID_Curs = NEW.ID_Curs LIMIT 1;
        SELECT CNP_Student into lCNP_Student from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor and CNP_Student = NEW.CNP_Student;
        SELECT CNP_Student into lCNP_Student2 from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT CNP_Profesor_Seminar into lProfesor_Seminar from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT Procent_Nota into lProcent_Nota from seminarii where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        
		if(lseminar is not NULL and lCNP_Student is null) THEN
			if(lCNP_Student2 is null) then
				UPDATE seminarii SET CNP_Student = NEW.CNP_Student where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor;
			else
				insert into seminarii values (NEW.ID_Curs, NEW.CNP_Profesor, lProfesor_Seminar, NEW.CNP_Student, lProcent_Nota, 0);
			end if;
        end if;
END; //

DELIMITER //
CREATE TRIGGER adauga_studenti_laborator_update AFTER UPDATE ON cursuri
	FOR EACH ROW BEGIN
		declare lLaborator int default null;
        declare lCNP_Student varchar(20) default null;
        declare lCNP_Student2 varchar(20) default null;
        declare lProfesor_Laborator varchar(20) default null;
        declare lProcent_Nota int;
        
		SELECT ID_Curs into lLaborator from laboratoare where ID_Curs = NEW.ID_Curs LIMIT 1;
        SELECT CNP_Student into lCNP_Student from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor and CNP_Student = NEW.CNP_Student;
        SELECT CNP_Student into lCNP_Student2 from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT CNP_Profesor_Laborator into lProfesor_Laborator from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT Procent_Nota into lProcent_Nota from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        
		if(lLaborator is not NULL and lCNP_Student is null) THEN
			if(lCNP_Student2 is null) then
				UPDATE laboratoare SET CNP_Student = NEW.CNP_Student where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor;
			else
				insert into laboratoare values (NEW.ID_Curs, NEW.CNP_Profesor, lProfesor_Laborator, NEW.CNP_Student, lProcent_Nota, 0);
			end if;
        end if;
END; //

DELIMITER //
CREATE TRIGGER adauga_studenti_laborator_insert AFTER INSERT ON cursuri
	FOR EACH ROW BEGIN
		declare lLaborator int default null;
        declare lCNP_Student varchar(20) default null;
        declare lCNP_Student2 varchar(20) default null;
        declare lProfesor_Laborator varchar(20) default null;
        declare lProcent_Nota int;
        
		SELECT ID_Curs into lLaborator from laboratoare where ID_Curs = NEW.ID_Curs LIMIT 1;
        SELECT CNP_Student into lCNP_Student from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor and CNP_Student = NEW.CNP_Student;
        SELECT CNP_Student into lCNP_Student2 from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT CNP_Profesor_Laborator into lProfesor_Laborator from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        SELECT Procent_Nota into lProcent_Nota from laboratoare where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor limit 1;
        
		if(lLaborator is not NULL and lCNP_Student is null) THEN
			if(lCNP_Student2 is null) then
				UPDATE laboratoare SET CNP_Student = NEW.CNP_Student where ID_Curs = NEW.ID_Curs and CNP_Profesor_Curs = NEW.CNP_Profesor;
			else
				insert into laboratoare values (NEW.ID_Curs, NEW.CNP_Profesor, lProfesor_Laborator, NEW.CNP_Student, lProcent_Nota, 0);
			end if;
        end if;
END; //