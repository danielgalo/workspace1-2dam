use bd_neptuno2;
select * from categorias;

select iva from productos;
drop table bd_neptuno2.usuario;

select * from bd_neptuno2.usuario;

drop table if exists bd_neptuno2.usuario;

delete from bd_neptuno2.usuario where IdUser < 10000;

select * from categorias_new;