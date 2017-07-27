CREATE TABLE usuario_perfil( 
  id     VARCHAR2(20) NOT NULL PRIMARY KEY, 
  senha  VARCHAR2(60) NOT NULL,
  nome   VARCHAR2(60) NOT NULL, 
  sexo   VARCHAR(1),
  idade  NUMBER(2)
 );


insert into usuario_perfil values('joao','123', 'Joao Flavio Cury','M',34);
