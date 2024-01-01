

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
   id bigint not null primary key,
   channel VARCHAR(64) null,
   channel_payment_id   varchar(128) null,
   payer_id varchar(128) null,
   payer_first_name varchar(128) null,
   payer_last_name varchar(128) null,
   payer_email varchar(128) null,
   total varchar(64) null,
   status VARCHAR(32) null,
   create_at timestamp null,
   upgrade_at timestamp null
);


/*==============================================================*/
/* Table: "order"                                               */
/*==============================================================*/
create table "order" (
   id BIGINT not null,
   email varchar(256) null,
   total DECIMAL  null,
   phone varchar(128) null,
   first_name varchar(32) null,
   last_name varchar(32) null,
   address VARCHAR(1024) null,
   country VARCHAR(256) null,
   state varchar(64) null,
   zip  varchar(32) null,
   city VARCHAR(128) null,
   status VARCHAR(32) null ,
   create_user_id BIGINT  null  ,
   create_at timestamp null,
   upgrade_at timestamp null,
   site_id BIGINT null references site(id) on delete set null,
   constraint PK_ORDER primary key (id)
);
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
   sku  varchar(128) null,
   count INT4 null,
   language VARCHAR(128) NULL,
   create_at timestamp null,
   upgrade_at timestamp null,
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

CREATE TABLE IF NOT EXISTS public.option
(
    id BIGINT not null,
    title varchar(128) not null,
    product_id bigint null,
    CONSTRAINT option_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.value
(
    id BIGINT not null,
    title varchar(128) not null,
    icon varchar(256) null default '',
    product_id bigint null,
    option_id bigint null,
    CONSTRAINT value_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.variant
(
    id BIGINT not null PRIMARY KEY,
    price decimal not null,
    quantity int4   not null,
    product_id bigint  null
);

CREATE TABLE IF NOT EXISTS public.variant_value
(
    variant_id bigint not null,
    value_id bigint not null,
    CONSTRAINT variant_value_pkey PRIMARY KEY (variant_id, value_id)
);

ALTER TABLE IF EXISTS public.option
    ADD CONSTRAINT fk_product_options FOREIGN KEY (product_id)
    REFERENCES public.product (id) MATCH SIMPLE
    ON UPDATE SET NULL
    ON DELETE SET NULL
    NOT VALID;
ALTER TABLE IF EXISTS public.value
    ADD CONSTRAINT fk_option_values FOREIGN KEY (option_id)
    REFERENCES public.option (id) MATCH SIMPLE
    ON UPDATE SET NULL
    ON DELETE SET NULL
    NOT VALID;


ALTER TABLE IF EXISTS public.value
    ADD CONSTRAINT fk_product_values FOREIGN KEY (product_id)
    REFERENCES public.product (id) MATCH SIMPLE
    ON UPDATE SET NULL
    ON DELETE SET NULL
    NOT VALID;


ALTER TABLE IF EXISTS public.variant
    ADD CONSTRAINT variant_product_id_fkey FOREIGN KEY (id)
    REFERENCES public.product (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE SET NULL;


ALTER TABLE IF EXISTS public.variant_value
    ADD CONSTRAINT fk_value_variant FOREIGN KEY (variant_id)
    REFERENCES public.variant (id) MATCH SIMPLE
    ON UPDATE SET NULL
    ON DELETE SET NULL
    NOT VALID;


ALTER TABLE IF EXISTS public.variant_value
    ADD CONSTRAINT fk_variant_value FOREIGN KEY (value_id)
    REFERENCES public.value (id) MATCH SIMPLE
    ON UPDATE SET NULL
    ON DELETE SET NULL
    NOT VALID;

/*==============================================================*/
/* Table: order_detail                                          */
/*==============================================================*/
create table order_detail (
   id BIGINT not null,
   title VARCHAR(256) null,
   currency varchar(32) null,
   price DECIMAL null,
   quantity INT4 null,
   subtotal DECIMAL null,
   icon varchar(1024) null,
   product_id bigint null references product(id) on delete set null,
   order_id BIGINT null references "order"(id) on delete set null,
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
    create_at timestamp null default now(),
    create_user_id  bigint null references "user"(id) on delete set null
);

create table comment (
    id BIGINT not null primary key,
    content varchar(2048) null,
    create_at timestamp null default now(),
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
    create_at timestamp null default now()
);

create table address (
    id bigint not null primary key,
    country varchar(128) not null,
    address varchar(1024) not null,
    phone varchar(64) not null,
    first_name varchar(64) null,
    last_name   varchar(64) null,
    city varchar(128) null,
    state varchar(128) null,
    zip_code varchar(32) null,
    create_user_id bigint null ,
    create_at timestamp null default now()

);