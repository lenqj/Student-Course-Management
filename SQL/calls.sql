#creare curs
delete from cursuri;
call creeaza_curs(1, '1', 'BD', 2, 0, 30, 60);
call creeaza_curs(2, '2', 'AF', 2, 0, 30, 40);
call creeaza_curs(3, '3', 'POO', 2, 0, 30, 50);
#call creeaza_curs(1, '2', 'CAN', 2, 0, 20);
#call creeaza_curs(4, '5', 'BD', 2, 0, 30);
#call creeaza_curs(5, '7', 'BD', 2, 0, 30);
#call creeaza_curs(6, '10', 'BD', 2, 0, 30);

#creare seminar
delete from seminarii;

call creeaza_seminar('1', '3', 1, 20);
call creeaza_seminar('2', '1', 2, 30);
call creeaza_seminar('3', '3', 3, 10);

call creeaza_seminar('5', '1', 2, 15);
call creeaza_seminar('11', '3', 3, 15);

#creare laborator 
delete from laboratoare;

call creeaza_laborator('1', '2', 1, 20);
call creeaza_laborator('2', '2', 2, 30);
call creeaza_laborator('3', '3', 3, 10);


call creeaza_laborator('5', '1', 2, 15);
call creeaza_laborator('11', '3', 3, 15);

#programare curs

call programare_curs(1, 2, '10:00', '11:50');
call programare_curs(2, 3, '8:00', '9:50');
call programare_curs(3, 4, '10:00', '11:50');

call programare_curs(3, 4, '14:00', '15:50');
call programare_curs(10, 5, '18:00', '19:50');

#programare seminar

call programare_seminar(1, 3, '12:00', '13:50');
call programare_seminar(2, 2, '10:00', '11:50');
call programare_seminar(3, 5, '10:00', '11:50');


call programare_seminar(10, 5, '18:00', '19:50');

#programare laborator

call programare_laborator(1, 5, '16:00', '17:50');
call programare_laborator(2, 4, '10:00', '11:50');
call programare_laborator(3, 1, '18:00', '19:50');

#cautare cursuri

call cauta_curs(1);
call cauta_curs(10);

#cautare utilizatori

call cauta_utilizator('7');
call cauta_utilizator('1');
call cauta_utilizator('5');

call cauta_utilizator('10');
call cauta_utilizator('200');

#adaugare studenti la cursuri

call adauga_student_curs('4', 2);
call adauga_student_curs('6', 2);
call adauga_student_curs('5', 2);
call adauga_student_curs('5', 1);


call adauga_student_curs('20', 3);


call adauga_student_curs('2', 1);
call adauga_student_curs('9', 10);
call adauga_student_curs('9', 1);
call adauga_student_curs('10', 2);
call adauga_student_curs('11', 2);
call adauga_student_curs('10', 10);

#adaugare_nota
call adaugare_nota_curs(1, '1', '5', 5);
call adaugare_nota_seminar(1, '1', '5', 10);
call adaugare_nota_laborator(1, '1', '5', 5);

call seteaza_nota_finala('5', 1);
call adaugare_nota_curs(2, '2', '5', 7);
#call adaugare_nota_curs(5, '1', '5', 8);
#call adaugare_nota_curs(1, '2', '5', 8);
#call adaugare_nota_curs(1, '1', '7', 8);
#call adaugare_nota_curs(1, '5', '5', 10);

#creare grupuri
delete from grupuri;


call creeaza_grup('5', "Grup 1", 1);
call invita_grup('5', '4', 1);
call adauga_profesor_grup('3',  1);
call trimite_mesaj('5', 1, "Mesaj 1");
call trimite_mesaj('3', 1, "Mesaj 2");
call trimite_mesaj('4', 1, "Mesaj 3");
call trimite_mesaj('7', 1, "Mesaj 3");


call creeaza_grup('6', "Grup 2", 2);
call invita_grup('6', '4', 2);
call adauga_profesor_grup('3',  2);
call trimite_mesaj('6', 2, "Mesaj 1");
call trimite_mesaj('3', 2, "Mesaj 2");
call trimite_mesaj('4', 2, "Mesaj 3");
call trimite_mesaj('7', 2, "Mesaj 3");

#call creeaza_grup('20', "Grup 2", 2);
#call creeaza_grup('1', "Grup 3", 3);
#call creeaza_grup('10', "Grup 4", 4);

call creeaza_grup('4', "Grup 1", 1);
call creeaza_grup('6', "Grup 5", 5);

#invitare in grupuri 
call invita_grup('4', '6', 1);
call invita_grup('5','4', 1);
call invita_grup('6', '5', 10);
call invita_grup('1', '4', 5);
call invita_grup('5', '4', 1);

#sterge student
call sterge_utilizator('5');
call sterge_student('5');
call sterge_profesor('1');
call sterge_admin('10');


call sterge_student('100');
call sterge_student('1');
call sterge_student('10');
call sterge_student('7');

#sterge profesor
call sterge_profesor('4');
call sterge_profesor('100');
call sterge_profesor('10');
call sterge_profesor('8');

#sterge admin
call sterge_admin('11');
call sterge_admin('1');
call sterge_admin('4');
call sterge_admin('7');
call sterge_admin('100');