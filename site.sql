/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" (
   id INT8 not null,
   username VARCHAR(128) null,
   password VARCHAR(256) null,
   mail VARCHAR(1024) null,
   first_name VARCHAR(256) null,
   last_name VARCHAR(256) null,
   create_at DATE null,
   update_at DATE null,
   create_user_id INT8 null,
   upgrade_user_id INT8 null,
   site_id INT8 null,
   constraint Pk_USER primary key (id)
);

/*==============================================================*/
/* Table: site                                                  */
/*==============================================================*/
create table site (
   id INT8 not null,
   title VARCHAR(256) null,
   keywords VARCHAR(256) null,
   description VARCHAR(256) null,
   domain VARCHAR(128) null,
   create_at DATE null,
   update_at DATE null,
   create_user_id INT8 null references "user"(id),
   upgrade_user_id INT8 null references "user"(id),
   constraint PK_SITE primary key (id)
);

/*==============================================================*/
/* Table: "order"                                               */
/*==============================================================*/
create table "order" (
   id INT8 not null,
   pay_id INT8 null,
   order_total DECIMAL not null,
   receivables DECIMAL null,
   actual_payments DECIMAL null,
   contact VARCHAR(256) null,
   mobile VARCHAR(128) null,
   tel VARCHAR(128) null,
   address VARCHAR(1024) null,
   country VARCHAR(256) null,
   province VARCHAR(128) null,
   city VARCHAR(128) null,
   street VARCHAR(1024) null,
   create_user_id INT8 not null  references "user"(id),
   create_at DATE null,
   upgrade_at DATE null,
   site_id INT8 null  references site(id),
   constraint PK_ORDER primary key (id)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table role (
   id INT8 not null,
   title VARCHAR(256) null,
   summary VARCHAR(1024) null,
   create_user_id INT8 null  references "user"(id),
   create_at DATE null,
   update_at DATE null,
   upgrade_user_id INT8 null,
   upgrade_at DATE null,
   site_id INT8 null,
   constraint PK_ROLE primary key (id)
);


/*==============================================================*/
/* Table: cart                                                  */
/*==============================================================*/
create table cart (
   id INT8 not null,
   title VARCHAR(256) not null,
   content VARCHAR(4096) not null,
   price DECIMAL null,
   unit INT8 null,
   count INT4 null,
   order_id INT8 null,
   create_at DATE null,
   update_at DATE null,
   create_user_id INT8 null,
   upgrade_user_id INT8 null
);

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product (
   id INT8 not null,
   cat_id INT8 null,
   title VARCHAR(256) not null,
   keywords VARCHAR(256) not null,
   description VARCHAR(1024) not null,
   content VARCHAR(4096) not null,
   price DECIMAL null,
   count INT4 null,
   create_at DATE null,
   update_at DATE null,
   create_user_id INT8 null,
   upgrade_user_id INT8 null,
   site_id INT8 null,
   constraint PK_PRODUCT primary key (id)
);

/*==============================================================*/
/* Table: note                                                  */
/*==============================================================*/
create table note (
   id INT8 not null,
   title VARCHAR(256) null,
   keywords VARCHAR(256) null,
   description VARCHAR(1024) null,
   content VARCHAR(4096) null,
   create_at DATE null,
   create_user_id INT8 null,
   upgrade_time DATE null,
   upgrade_user_id INT8 null,
   category_id INT8 null,
   site_id INT8 null,
   constraint PK_NOTE primary key (id)
);


/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category (
   id INT8 not null,
   title VARCHAR(256) null,
   code VARCHAR(1024) null,
   parent_code VARCHAR(1024) null,
   site_id INT8 null,
   create_at DATE null,
   create_user_id INT8 null,
   upgrade_at DATE null,
   upgrade_user_id INT8 null,
   constraint PK_CATEGORY primary key (id)
);

/*==============================================================*/
/* Table: payment_order                                         */
/*==============================================================*/
create table payment_order (
   id INT8 not null,
   ord_id INT8 null,
   channel VARCHAR(1024) null,
   total DECIMAL null,
   create_at DATE null,
   payment_order_id VARCHAR(256) null,
   order_id INT8 null,
   site_id INT8 null,
   constraint PK_PAYMENT_ORDER primary key (id)
);


/*==============================================================*/
/* Table: order_detail                                          */
/*==============================================================*/
create table order_detail (
   id INT8 not null,
   ord_id INT8 null,
   title VARCHAR(256) not null,
   content VARCHAR(4096) not null,
   price DECIMAL null,
   count INT4 null,
   order_id INT8 null,
   create_at DATE null,
   update_at DATE null,
   create_user_id INT8 null,
   upgrade_user_id INT8 null,
   constraint PK_ORDER_DETAIL primary key (id)
);


/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission (
   id INT8 not null,
   summary VARCHAR(1024) null,
   title VARCHAR(256) null,
   is_menu BOOL null,
   is_url BOOL null,
   url VARCHAR(1024) null,
   create_user_id INT8 null,
   create_at DATE null,
   update_at DATE null,
   upgrade_user_id INT8 null,
   upgrade_at DATE null,
   site_id INT8 null,
   constraint PK_PERMISSION primary key (id)
);


/*==============================================================*/
/* Table: product_image                                         */
/*==============================================================*/
create table product_image (
   id INT8 not null,
   pro_id INT8 null,
   alt VARCHAR(256) null,
   url VARCHAR(1024) not null,
   product_id INT8 null,
   create_at DATE null,
   create_user_id INT8 null,
   constraint PK_PRODUCT_IMAGE primary key (id)
);


/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission (
   Rol_id INT8 not null,
   id INT8 not null,
   constraint PK_ROLE_PERMISSION primary key (Rol_id,
id)
);

