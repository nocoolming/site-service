

/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" (
   id BIGINT not null,
   username VARCHAR(128) null,
   password VARCHAR(256) null,
   mail VARCHAR(1024) null,
   first_name VARCHAR(256) null,
   last_name VARCHAR(256) null,
   create_at timestamp null,
   upgrade_at timestamp null,
   site_id BIGINT null ,
   constraint Pk_USER primary key (id)
);

/*==============================================================*/
/* Table: site                                                  */
/*==============================================================*/
create table site (
   id BIGINT not null,
   title VARCHAR(256) null,
   keywords VARCHAR(256) null,
   description VARCHAR(256) null,
   summary varchar(1024) null,
   content varchar(2048) null,
   domain VARCHAR(128) null,
   brand_category varchar(128) null,
   brand_image_url varchar(1024) null,
   brand_text varchar(256) null,
   language VARCHAR(128) NOT NULL,
   create_at timestamp null,
   upgrade_at timestamp null,
   constraint PK_SITE primary key (id)
);

alter table "user"
 add constraint fk_site_users
 foreign key(site_id) references site(id)
 on delete set null;

/*==============================================================*/
/* Table: payment_order                                         */
/*==============================================================*/
create table payment_order (
   id VARCHAR(128) not null,
   channel VARCHAR(1024) null,
   total DECIMAL null,
   create_at timestamp null,
   order_id BIGINT null ,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_PAYMENT_ORDER primary key (id)
);

/*==============================================================*/
/* Table: "order"                                               */
/*==============================================================*/
create table "order" (
   id BIGINT not null,
   total DECIMAL not null,
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
   payment_id VARCHAR(128) references payment_order(id) on delete set null,
   create_user_id BIGINT not null  ,
   create_at timestamp null,
   upgrade_at timestamp null,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_ORDER primary key (id)
);

alter table payment_order
    add constraint fk_payment_order
       foreign key(order_id) references "order"(id)
       on delete set null;

create table product_collect (
    id bigint not null primary key,
    title varchar(256) not null,
    keywords varchar(256) not null,
    description varchar(1024) not null,
    create_at timestamp not null,
    upgrade_at timestamp not null,
    create_user_id  long not null references "user"(id) on delete set null
    upgrade_user_id BIGINT null references "user"(id) on delete set null,
    site_id BIGINT null references site(id) on delete set null
);

create table product_attribute (
    id bigint not null primary key,
    title varchar(256) not null,
    create_at timestamp not null,
    collect_id long not null references product_collect(id) on delete set null,
    create_user_id  long not null references "user"(id) on delete set null
    site_id BIGINT null references site(id) on delete set null
);

create table product_attribute_value (
    id bigint not null primary key,
    value varchar(256) not null,
    create_at timestamp not null,
    attribute_id long not null references product_attribute(id) on delete set null,
    collect_id long not null references product_collect(id) on delete set null,
    create_user_id  long not null references "user"(id) on delete set null
    site_id BIGINT null references site(id) on delete set null
);

create table inventory (
    id bigint not null primary key,
    attribute_id long not null references product_attribute_value(id) on delete set null,
    count long not null default 0,
    product_id   long not null references product(id) on delete set null,
    collect_id long not null references product_collect(id) on delete set null,
    create_user_id  long not null references "user"(id) on delete set null
    site_id BIGINT null references site(id) on delete set null
)
/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product (
   id BIGINT not null,
   category_id BIGINT null,
   title VARCHAR(256) not null,
   keywords VARCHAR(256) not null,
   description VARCHAR(1024) not null,
   content VARCHAR(4096) not null,
   slate_content varchar(4096) null,
   price DECIMAL null,
   icon varchar(1024) null,
   count INT4 null,
   language VARCHAR(128) NULL,
   create_at timestamp null,
   upgrade_at timestamp null,
   collect_id long not null references product_collect(id) on delete set null,
   create_user_id BIGINT null references "user"(id) on delete set null,
   upgrade_user_id BIGINT null references "user"(id) on delete set null,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_PRODUCT primary key (id)
);


/*==============================================================*/
/* Table: product_image                                         */
/*==============================================================*/
create table product_image (
   id BIGINT not null,
   alt VARCHAR(256) null,
   url VARCHAR(1024) not null,
   product_id BIGINT null references product(id) on delete set null,
   create_at timestamp null,
   create_user_id BIGINT null references "user"(id) on delete set null,
   constraint PK_PRODUCT_IMAGE primary key (id)
);
/*==============================================================*/
/* Table: order_detail                                          */
/*==============================================================*/
create table order_detail (
   id BIGINT not null,
   title VARCHAR(256) not null,
   currency varchar(32) null,
   price DECIMAL null,
   quantity INT4 null,
   subtotal DECIMAL null,
   order_id BIGINT null references "order"(id) on delete set null,
   product_id BIGINT null references product(id) on delete set null,
   create_at timestamp null,
   upgrade_at timestamp null,
   create_user_id BIGINT null references "user"(id) on delete set null,
   constraint PK_ORDER_DETAIL primary key (id)
);
/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table role (
   id BIGINT not null,
   title VARCHAR(256) null,
   summary VARCHAR(1024) null,
   create_user_id BIGINT null  references "user"(id) on delete set null,
   create_at timestamp null,
   upgrade_at timestamp null,
   upgrade_user_id BIGINT null,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_ROLE primary key (id)
);


/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category (
   id BIGINT not null,
   title VARCHAR(256) NOT null,
   code VARCHAR(1024) not null,
   parent_code VARCHAR(1024) null,
   is_menu  BOOLEAN null,
   url  varchar(256) null,
   icon varchar(256) null,
   site_id BIGINT null references site(id),
   create_at timestamp null,
   create_user_id BIGINT null references "user"(id) on delete set null,
   upgrade_at timestamp null,
   upgrade_user_id BIGINT null references "user"(id) on delete set null,
   constraint PK_CATEGORY primary key (id)
);

create table category_language(
    id BIGINT not null primary key,
    title VARCHAR(256) NOT NULL,
    language VARCHAR(128) NOT NULL,
    category_id BIGINT not null,
    create_at timestamp null,
    create_user_id BIGINT null references "user"(id) on delete set null,
    upgrade_at timestamp null,
    upgrade_user_id BIGINT null references "user"(id) on delete set null
);

create table cart (
    id BIGINT not null primary key,
	currency varchar(32) null,
    subtotal decimal null,
    create_user_id  bigint  null ,
   create_at timestamp null,
   upgrade_at timestamp null,
   site_id bigint not null
);

/*==============================================================*/
/* Table: cart_item                                                  */
/*==============================================================*/
create table cart_item (
   id BIGINT not null primary key,
   price DECIMAL null,
	currency varchar(32) null,
   unit VARCHAR(256) null,
   quantity INT4 null,
   product_id bigint not null references product(id) on delete set null,
   create_at timestamp null,
   upgrade_at timestamp null
);


/*==============================================================*/
/* Table: note                                                  */
/*==============================================================*/
create table note (
   id BIGINT not null,
   title VARCHAR(256) null,
   keywords VARCHAR(256) null,
   description VARCHAR(1024) null,
   content text null,
   slate_content text null,
   language VARCHAR(128) NULL,
   icon varchar(1024) null,
   create_at timestamp null,
   create_user_id BIGINT null references "user"(id) on delete set null,
   upgrade_at timestamp null,
   upgrade_user_id BIGINT null references "user"(id) on delete set null,
   category_id BIGINT null references category(id) on delete set null,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_NOTE primary key (id)
);


/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission (
   id BIGINT not null,
   summary VARCHAR(1024) null,
   title VARCHAR(256) null,
   is_menu BOOL null,
   is_url BOOL null,
   url VARCHAR(1024) null,
   create_at timestamp null,
   upgrade_at timestamp null,
   create_user_id BIGINT null references "user"(id) on delete set null,
   upgrade_user_id BIGINT null references "user"(id) on delete set null,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_PERMISSION primary key (id)
);


/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission (
   role_id BIGINT not null references role(id)  on delete set null,
   permission_id BIGINT not null references permission(id) on delete set null,
   constraint PK_ROLE_PERMISSION primary key (role_id,
permission_id)
);

create table user_role (
    user_id BIGINT not null references "user"(id) on delete set null,
    role_id BIGINT not null references role(id) on delete set null,
    constraint PK_USER_ROLE primary key(user_id, role_id)
);

create table file (
    id BIGINT not null primary key,
    url varchar(1024) null,
    alt varchar(256) null,
    logical_name varchar(256) null,
    physical_name varchar(256) null,
    extension varchar(32) null,
    size bigint null,
    create_at date null default now(),
    create_user_id  bigint null references "user"(id) on delete set null
);

create table comment (
    id BIGINT not null primary key,
    content varchar(2048) null,
    create_at date null default now(),
    create_user_id bigint null references "user"(id) on delete set null,
    note_id bigint null references note(id) on delete set null,
    product_id bigint null references product(id) on delete set null,
    comment_id bigint null references comment(id) on delete set null
);

create table contact (
    id bigint not null primary key,
    name varchar(256) not null,
    email varchar(256) not null,
    content varchar(4096) not null,
    create_at date null default now()
);