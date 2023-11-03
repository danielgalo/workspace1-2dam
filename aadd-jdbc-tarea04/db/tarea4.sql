DROP DATABASE IF EXISTS tarea4;
CREATE DATABASE IF NOT EXISTS tarea4;
USE tarea4;

CREATE TABLE IF NOT EXISTS Alumno (

	NumMatricula int auto_increment,
    Nombre varchar(100),
    FechaNacimiento DATE,
    Telefono char(9),
    
    primary key (NumMatricula)

);

CREATE TABLE IF NOT EXISTS Profesor(

	IdProfesor int auto_increment,
    NIF_P char(9),
    Nombre varchar(100),
    Especialidad varchar(100),
    Telefono char(9),
    
    primary key(IdProfesor),
    unique key(NIF_P)

);

CREATE TABLE IF NOT EXISTS Asignatura(
	
    CodAsignatura varchar(10),
    Nombre varchar(100),
    IdProfesor int,
    
    primary key(CodAsignatura)
);

ALTER TABLE asignatura
add constraint foreign key (IdProfesor) references Profesor(IdProfesor);

CREATE TABLE IF NOT EXISTS Recibe(

	NumMatricula int,
	CodAsignatura varchar(10),
	CursoEscolar varchar(20),
    
    primary key(NumMatricula, CodAsignatura),
    foreign key (NumMatricula) references Alumno(NumMatricula),
    foreign key (CodAsignatura) references Asignatura(CodAsignatura)

);

SET FOREIGN_KEY_CHECKS=0;
SET GLOBAL FOREIGN_KEY_CHECKS=0;
SELECT * FROM Alumno;
SELECT * FROM profesor;
SELECT * FROM asignatura;
SELECT * FROM recibe;

SELECT * FROM profesor where especialidad = 'Informática' 



