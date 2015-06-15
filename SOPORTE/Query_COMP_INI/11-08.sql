/*
delete from compromisoinicialdetalle;
delete from compromisoinicial;
delete from expediente;
*/

select (select count(*) from compromisoinicialdetalle), (select count(*) from compromisoinicial), (select count(*) from expediente)


select * from expediente 
select expediente, estatus  from compromisoinicial order by expediente


update compromisoinicial set estatus = 0