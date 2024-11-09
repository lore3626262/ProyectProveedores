----Autor:Sandra Lorena Martinez Merchan

--alter session set "_ORACLE_SCRIPT"=true;
---creacion de usuarios
CREATE USER SISTEMA_PROVEEDORES IDENTIFIED BY 123;
---se le dan todos los privilegios
GRANT CONNECT, RESOURCE TO SISTEMA_PROVEEDORES;

ALTER USER SISTEMA_PROVEEDORES QUOTA 50M ON USERS;

ALTER USER SISTEMA_PROVEEDORES QUOTA UNLIMITED ON USERS;
----conectamos al sistema de proveedores

CONN SISTEMA_PROVEEDORES/123



/*--------tabla: Personas--------------*/

CREATE TABLE PERSONAS(
id NUMBER(10)    NOT NULL,
nombres VARCHAR2(100) NOT NULL,
apellidos VARCHAR(100)NOT NULL,
fecha_nacimiento DATE NOT NULL,
tipo_documento VARCHAR2(2)NOT NULL,
numero_documento VARCHAR2(15) NOT NULL,
CONSTRAINT PK_PERSONAS PRIMARY KEY(id)
);

/*--------tabla: productos--------------*/
CREATE TABLE PRODUCTOS(
id NUMBER (10) NOT NULL,
nombre VARCHAR2(10)NOT NULL,
precio_unitario NUMBER(8,3)NOT NULL,
CONSTRAINT PK_PRODUCTOS PRIMARY KEY(id)
);

/*--------tabla: proveedor de muchos a muchos--------------*/
CREATE TABLE PROVEEDOR_PRODUCTO (
 id_proveedor  NUMBER(10)  NOT NULL,
 id_producto NUMBER(10)   NOT NULL,
 CONSTRAINT PK_PROVEEDOR_PRODUCTO PRIMARY KEY (id_proveedor, id_producto),
 CONSTRAINT FK_PROVEEDOR_PERSONA FOREIGN KEY (id_proveedor)
 REFERENCES PERSONAS (id),
 CONSTRAINT FK_PROVEEDOR_PRODUCTO FOREIGN KEY (id_producto)
 REFERENCES PRODUCTOS (id)
);

/*--------tabla:facturas-------------------*/

CREATE TABLE FACTURAS (
   id                   NUMBER(10)           NOT NULL,
   fecha                DATE                 NOT NULL,
   id_cliente          NUMBER(10)           NOT NULL,
   id_vendedor         NUMBER(10)           NOT NULL,
   CONSTRAINT PK_FACTURAS PRIMARY KEY (id),
   CONSTRAINT FK_FACTURA_CLIENTE FOREIGN KEY (id_cliente)
      REFERENCES PERSONAS (id),
   CONSTRAINT FK_FACTURA_VENDEDOR FOREIGN KEY (id_vendedor)
      REFERENCES PERSONAS (id)
);

/*--------tabla:Detalles-------------------*/

CREATE TABLE DETALLES (
    id NUMBER(10) NOT NULL,
    id_factura NUMBER(10) NOT NULL,
    cantidad NUMBER NOT NULL,
    precio_venta NUMBER(8,3),
    id_producto NUMBER(10),
    CONSTRAINT PK_DETALLES PRIMARY KEY (id),
    CONSTRAINT FK_FACTURA FOREIGN KEY (id_factura) REFERENCES FACTURAS(id),
    CONSTRAINT FK_PRODUCTO FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id)
);


create sequence FACT_SEQ start with 100 increment by 1 maxvalue 1000000 minvalue 1 nocycle;


create sequence PRODUC_SEQ start with 1 increment by 1 maxvalue 1000 minvalue 1 nocycle;



create sequence PERSON_SEQ start with 1 increment by 1 maxvalue 10000 minvalue 1 nocycle;

create sequence DETALLE_SEQ start with 1 increment by 1 maxvalue 100000 minvalue 1 nocycle;