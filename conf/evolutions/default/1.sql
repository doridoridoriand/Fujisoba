# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table shop_list (
  id                        bigint not null,
  name                      varchar(255),
  name_en                   varchar(255),
  address                   varchar(255),
  telephone_number          varchar(255),
  created_at                timestamp,
  constraint pk_shop_list primary key (id))
;

create sequence shop_list_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists shop_list;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists shop_list_seq;

