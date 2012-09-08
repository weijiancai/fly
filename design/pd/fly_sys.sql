/*==============================================================*/
/* Database name:  sys                                          */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012-9-7 22:15:14                            */
/*==============================================================*/


drop database if exists sys;

/*==============================================================*/
/* Database: sys                                                */
/*==============================================================*/
create database sys character set utf8;

use sys;

/*==============================================================*/
/* Table: sys_class_define                                      */
/*==============================================================*/
create table sys_class_define
(
   id                   varchar(32) not null comment '类内码',
   name                 varchar(64) not null comment '类名',
   cname                varchar(128) comment '类中文名',
   super_class          varchar(32) comment '父类',
   author               varchar(64) comment '作者',
   class_desc           varchar(1024) comment '类描述',
   version              varchar(64) comment '版本',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_define comment '类定义';

/*==============================================================*/
/* Index: idx_classDefine_name                                  */
/*==============================================================*/
create unique index idx_classDefine_name on sys_class_define
(
   name
);

/*==============================================================*/
/* Table: sys_class_field                                       */
/*==============================================================*/
create table sys_class_field
(
   id                   varchar(32) not null comment '字段ID',
   col_id               varchar(32) comment '列内码',
   class_id             varchar(32) not null comment '类内码',
   name                 varchar(64) not null comment '字段名称',
   field_desc           varchar(128) comment '字段描述',
   type                 varchar(32) comment '字段数据类型',
   dz_category_id       varchar(32) comment '字典类别',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_field comment '类字段';

/*==============================================================*/
/* Table: sys_class_form                                        */
/*==============================================================*/
create table sys_class_form
(
   id                   varchar(32) not null comment '类定义form内码',
   class_id             varchar(32) not null comment '类定义ID',
   name                 varchar(64) not null comment '名称',
   form_type            char(1) not null comment 'form类型',
   col_count            int(2) comment '列数',
   col_width            int(3) comment '列宽',
   label_gap            int(2) comment 'label间隙',
   field_gap            int(2) comment '字段间隙',
   hgap                 int(2) default 3 comment '水平方面间隔',
   vgap                 int(2) default 5 comment '垂直方向间隔',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_form comment '类定义表单';

/*==============================================================*/
/* Table: sys_class_form_field                                  */
/*==============================================================*/
create table sys_class_form_field
(
   id                   varchar(32) not null comment '编辑内码',
   form_id              varchar(32) not null comment '类定义form ID',
   field_id             varchar(32) not null comment '字段内码',
   display_name         varchar(64) comment '显示名',
   is_single_line       char(1) comment '是否独行',
   width                int(11) comment '列宽',
   height               int(11) comment '列高',
   display_style        varchar(32) comment '显示样式',
   is_display           char(1) not null comment '是否显示',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_form_field comment '表单字段';

/*==============================================================*/
/* Table: sys_class_query                                       */
/*==============================================================*/
create table sys_class_query
(
   id                   varchar(32) not null comment '查询内码',
   class_id             varchar(32) not null comment '类定义ID',
   name                 varchar(64) not null comment '名称',
   col_count            int(2) comment '列数',
   col_width            int(3) comment '列宽',
   label_gap            int(2) comment 'label间隙',
   field_gap            int(2) comment '字段间隙',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_query comment '类查询';

/*==============================================================*/
/* Table: sys_class_query_field                                 */
/*==============================================================*/
create table sys_class_query_field
(
   id                   varchar(32) not null comment '编辑内码',
   query_id             varchar(32) not null comment '类定义query ID',
   field_id             varchar(32) not null comment '字段内码',
   width                int(11) comment '列宽',
   height               int(11) comment '列高',
   display_name         varchar(64) comment '显示名',
   display_style        varchar(32) comment '显示样式',
   operator             varchar(32) comment '操作符',
   is_display           char(1) not null comment '是否显示',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_query_field comment '查询字段';

/*==============================================================*/
/* Table: sys_class_table                                       */
/*==============================================================*/
create table sys_class_table
(
   id                   varchar(32) not null comment '类定义table内码',
   class_id             varchar(32) not null comment '类定义ID',
   name                 varchar(64) not null comment '名称',
   sql_text             varchar(4098) comment 'sql语句',
   join_sql             varchar(128) not null comment '表连接语句',
   col_width            int(3) comment '列宽',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_table comment '类定义表格';

/*==============================================================*/
/* Table: sys_class_table_field                                 */
/*==============================================================*/
create table sys_class_table_field
(
   id                   varchar(32) not null comment '内码',
   class_table_id       varchar(32) not null comment '类Table内码',
   field_id             varchar(32) not null comment '字段内码',
   display_name         varchar(64) comment '显示名',
   display_style        varchar(32) comment '显示样式',
   align                varchar(10) default 'left' comment '对齐方式',
   is_display           char(1) not null comment '是否显示',
   col_width            int(11) comment '列宽',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_class_table_field comment '表格字段';

/*==============================================================*/
/* Table: sys_class_tree                                        */
/*==============================================================*/
create table sys_class_tree
(
   id                   varchar(32) not null comment '内码',
   field_id             varchar(32) comment '字段ID',
   primary key (id)
);

alter table sys_class_tree comment '树字段';

/*==============================================================*/
/* Table: sys_dbms_column                                       */
/*==============================================================*/
create table sys_dbms_column
(
   id                   varchar(32) not null comment '列内码',
   table_id             varchar(32) not null comment '表',
   name                 varchar(64) not null comment '列名',
   alias                varchar(64) not null comment '别名',
   comment              varchar(64) comment '列注释',
   display_name         varchar(64) comment '显示名',
   data_type            varchar(64) comment '数据类型',
   code_id              varchar(64) comment '字典代码',
   default_value        varchar(64) comment '默认值',
   is_nullable          char(1) not null comment '可空',
   is_pk                char(1) not null comment '是否主键',
   is_fk                char(1) not null comment '是否外键',
   fk_column_id         varchar(32) comment '外键引用的列',
   max_length           int(11) comment '最大长度',
   is_valid             char(1) not null comment '是否有效',
   input_date           datetime not null comment '录入时间',
   sort_num             int(11) not null comment '排序号',
   primary key (id)
);

alter table sys_dbms_column comment '数据库列';

/*==============================================================*/
/* Table: sys_dbms_define                                       */
/*==============================================================*/
create table sys_dbms_define
(
   id                   varchar(32) not null comment '内码',
   name                 varchar(128) not null comment '名称',
   type                 varchar(64) not null comment '类型',
   host                 varchar(64) comment '主机',
   port                 varchar(64) comment '端口',
   driver_class         varchar(128) not null comment '驱动类',
   is_valid             char(1) not null comment '是否有效',
   input_date           datetime not null comment '录入时间',
   sort_num             int(11) not null comment '排序号',
   primary key (id)
);

alter table sys_dbms_define comment '数据库管理系统定义';

/*==============================================================*/
/* Index: idx_dbms_define_name                                  */
/*==============================================================*/
create index idx_dbms_define_name on sys_dbms_define
(
   name
);

/*==============================================================*/
/* Table: sys_dbms_schema                                       */
/*==============================================================*/
create table sys_dbms_schema
(
   id                   varchar(32) not null comment '内码',
   dbms_id              varchar(32) not null comment 'dbms',
   name                 varchar(64) not null comment '名称',
   alias                varchar(64) comment '别名',
   version              varchar(64) not null comment '版本',
   is_valid             char(1) not null comment '是否有效',
   input_date           datetime not null comment '录入时间',
   sort_num             int(11) not null comment '排序号',
   url                  varchar(1024) comment 'JDBC URL',
   is_default           char(1) not null comment '是否默认',
   user_name            varchar(64) not null comment '用户名',
   password             varchar(64) not null comment '密码',
   primary key (id)
);

alter table sys_dbms_schema comment '数据库管理系统schema';

/*==============================================================*/
/* Index: idx_dbms_schema_name                                  */
/*==============================================================*/
create unique index idx_dbms_schema_name on sys_dbms_schema
(
   name
);

/*==============================================================*/
/* Table: sys_dbms_schema_sync                                  */
/*==============================================================*/
create table sys_dbms_schema_sync
(
   src_schema           varchar(32) not null comment '源数据库schema',
   target_schema        varchar(32) not null comment '目标数据库schema'
);

alter table sys_dbms_schema_sync comment '数据库schema同步';

/*==============================================================*/
/* Table: sys_dbms_table                                        */
/*==============================================================*/
create table sys_dbms_table
(
   id                   varchar(32) not null comment '内码',
   schema_id            varchar(32) not null comment 'schema',
   name                 varchar(64) not null comment '表名',
   alias                varchar(64) comment '别名',
   comment              varchar(128) not null comment '表注释',
   display_name         varchar(64) comment '显示名',
   is_valid             char(1) not null comment '是否有效',
   input_date           datetime not null comment '录入时间',
   sort_num             int(11) not null comment '排序号',
   primary key (id)
);

alter table sys_dbms_table comment '数据库表';

/*==============================================================*/
/* Index: idx_dbms_table_name                                   */
/*==============================================================*/
create unique index idx_dbms_table_name on sys_dbms_table
(
   name
);

/*==============================================================*/
/* Table: sys_dbms_table_ref                                    */
/*==============================================================*/
create table sys_dbms_table_ref
(
   table_id             varchar(32) not null comment 'Dbms Table Id',
   table_ref_id         varchar(32) not null comment '引用表Id',
   column_id            varchar(32) not null comment '数据库列ID'
);

alter table sys_dbms_table_ref comment '数据库Table引用表';

/*==============================================================*/
/* Table: sys_dz_category                                       */
/*==============================================================*/
create table sys_dz_category
(
   id                   varchar(32) not null comment '类别内码',
   name                 varchar(64) not null comment '类别名称',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_dz_category comment '字典类别';

/*==============================================================*/
/* Index: idx_name                                              */
/*==============================================================*/
create unique index idx_name on sys_dz_category
(
   name
);

/*==============================================================*/
/* Table: sys_dz_code                                           */
/*==============================================================*/
create table sys_dz_code
(
   id                   varchar(32) not null comment '代码ID',
   category_id          varchar(32) not null comment '类别ID',
   value                varchar(64) comment '代码值',
   name                 varchar(128) not null comment '代码名称',
   is_valid             char(1) not null comment '是否有效',
   input_date           datetime not null comment '录入时间',
   sort_num             int(11) not null comment '排序号',
   primary key (id)
);

alter table sys_dz_code comment '字典代码';

/*==============================================================*/
/* Table: sys_form_field_append                                 */
/*==============================================================*/
create table sys_form_field_append
(
   form_field_id        varchar(32) not null comment '表单字段内码',
   query_mode           int(2) comment '查询模式',
   is_readonly          char(1) comment '是否只读',
   is_required          char(1) comment '是否必须',
   data_type            int(2) comment '数据类型',
   primary key (form_field_id)
);

alter table sys_form_field_append comment '表单字段附加信息';

/*==============================================================*/
/* Table: sys_module_define                                     */
/*==============================================================*/
create table sys_module_define
(
   id                   varchar(32) not null comment '模块内码',
   name                 varchar(64) not null comment '名称',
   display_name         varchar(64) not null comment '显示名',
   super_module_id      varchar(32) comment '父模块',
   class_id             varchar(32) comment '类内码',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (id)
);

alter table sys_module_define comment '系统模块';

/*==============================================================*/
/* Table: sys_module_tpl                                        */
/*==============================================================*/
create table sys_module_tpl
(
   id                   varchar(32) not null comment '内码',
   name                 varchar(64) not null comment '模板名称',
   display_name         varchar(128) not null comment '显示名',
   form_id              varchar(32) comment '表单内码',
   table_id             varchar(32) comment 'Table内码',
   tpl_desc             varchar(1024) comment '模板描述',
   primary key (id)
);

alter table sys_module_tpl comment '模块模板';

/*==============================================================*/
/* Table: sys_project_define                                    */
/*==============================================================*/
create table sys_project_define
(
   id                   varchar(32) not null comment '项目内码',
   name                 varchar(64) comment '名称',
   project_desc         varchar(1024) comment '描述',
   package_name         varchar(64) comment '包名',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   project_url          varchar(1024) comment '项目url',
   primary key (id)
);

alter table sys_project_define comment '项目定义';

/*==============================================================*/
/* Table: sys_project_module                                    */
/*==============================================================*/
create table sys_project_module
(
   project_id           varchar(32) not null comment '项目内码',
   module_id            varchar(32) not null comment '模块内码',
   display_name         varchar(64) not null comment '显示名',
   super_module_id      varchar(32) comment '父模块',
   is_valid             char(1) not null comment '是否有效',
   sort_num             int(11) not null comment '排序号',
   input_date           datetime not null comment '录入时间',
   primary key (project_id, module_id)
);

alter table sys_project_module comment '项目模块';

/*==============================================================*/
/* Table: sys_r_class_table                                     */
/*==============================================================*/
create table sys_r_class_table
(
   class_id             varchar(32) not null comment '类内码',
   dbms_table_id        varchar(32) not null comment '数据库table内码',
   is_primary           char(1) not null comment '是否主表',
   join_sql             varchar(128) not null comment '表连接语句',
   primary key (class_id, dbms_table_id)
);

alter table sys_r_class_table comment '类数据库表';

/*==============================================================*/
/* Table: sys_r_module_class                                    */
/*==============================================================*/
create table sys_r_module_class
(
   class_id             varchar(32) not null comment '类内码',
   module_d             varchar(32) not null comment '模块内码',
   form_id              varchar(32) comment 'form内码',
   table_id             varchar(32) comment 'table内码',
   primary key (class_id, module_d)
);

alter table sys_r_module_class comment '模块类';

alter table sys_class_field add constraint FK_classField_dz_categrory_id foreign key (dz_category_id)
      references sys_dz_category (id) on delete cascade on update cascade;

alter table sys_class_field add constraint FK_fieldClass_id foreign key (class_id)
      references sys_class_define (id) on delete cascade on update cascade;

alter table sys_class_field add constraint FK_fieldColumn_id foreign key (col_id)
      references sys_dbms_column (id) on delete cascade on update cascade;

alter table sys_class_form add constraint FK_classForm_class_id foreign key (class_id)
      references sys_class_define (id) on delete cascade on update cascade;

alter table sys_class_form_field add constraint FK_formField_field_id foreign key (field_id)
      references sys_class_field (id) on delete cascade on update cascade;

alter table sys_class_form_field add constraint FK_formField_form_id foreign key (form_id)
      references sys_class_form (id) on delete cascade on update cascade;

alter table sys_class_query add constraint FK_classQuery_class_id foreign key (class_id)
      references sys_class_define (id) on delete cascade on update cascade;

alter table sys_class_query_field add constraint FK_queryField_field_id foreign key (field_id)
      references sys_class_field (id) on delete cascade on update cascade;

alter table sys_class_query_field add constraint FK_queryField_query_id foreign key (query_id)
      references sys_class_query (id) on delete cascade on update cascade;

alter table sys_class_table add constraint FK_classTable_class_id foreign key (class_id)
      references sys_class_define (id) on delete cascade on update cascade;

alter table sys_class_table_field add constraint FK_tableField_ctable_id foreign key (class_table_id)
      references sys_class_table (id) on delete cascade on update cascade;

alter table sys_class_table_field add constraint FK_tableField_field_id foreign key (field_id)
      references sys_class_field (id) on delete cascade on update cascade;

alter table sys_class_tree add constraint FK_Reference_18 foreign key (field_id)
      references sys_class_field (id) on delete restrict on update restrict;

alter table sys_dbms_column add constraint FK_column_table_id foreign key (table_id)
      references sys_dbms_table (id) on delete cascade on update cascade;

alter table sys_dbms_schema add constraint FK_shm_dbms_id foreign key (dbms_id)
      references sys_dbms_define (id) on delete cascade on update cascade;

alter table sys_dbms_table add constraint FK_table_shm_id foreign key (schema_id)
      references sys_dbms_schema (id) on delete cascade on update cascade;

alter table sys_dz_code add constraint FK_code_category_id foreign key (category_id)
      references sys_dz_category (id) on delete cascade on update cascade;

alter table sys_form_field_append add constraint FK_formFieldAppend_form_field_id foreign key (form_field_id)
      references sys_class_form_field (id) on delete cascade on update cascade;

alter table sys_project_module add constraint FK_projectModule_module_id foreign key (module_id)
      references sys_module_define (id) on delete cascade on update cascade;

alter table sys_project_module add constraint FK_projectModule_project_id foreign key (project_id)
      references sys_project_define (id) on delete cascade on update cascade;

alter table sys_r_class_table add constraint FK_rClassTable_class_id foreign key (class_id)
      references sys_class_define (id) on delete cascade on update cascade;

alter table sys_r_class_table add constraint FK_rClassTable_dbmsTable_id foreign key (dbms_table_id)
      references sys_dbms_table (id) on delete cascade on update cascade;

alter table sys_r_module_class add constraint FK_Reference_13 foreign key (class_id)
      references sys_class_define (id) on delete restrict on update restrict;

alter table sys_r_module_class add constraint FK_Reference_15 foreign key (module_d)
      references sys_module_define (id) on delete restrict on update restrict;

