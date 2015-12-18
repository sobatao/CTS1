# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comments (
  user                      varchar(255),
  posted_at                 timestamp,
  content                   clob,
  post_ean                  varchar(255))
;

create table product (
  ean                       varchar(255) not null,
  name                      varchar(255),
  description               varchar(255),
  picture                   varbinary(255),
  constraint pk_product primary key (ean))
;

create table users (
  id                        bigint not null,
  username                  varchar(255),
  password_hash             varchar(255),
  constraint uq_users_username unique (username),
  constraint pk_users primary key (id))
;

create sequence product_seq;

create sequence users_seq;

alter table comments add constraint fk_comments_post_1 foreign key (post_ean) references product (ean) on delete restrict on update restrict;
create index ix_comments_post_1 on comments (post_ean);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comments;

drop table if exists product;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists product_seq;

drop sequence if exists users_seq;

