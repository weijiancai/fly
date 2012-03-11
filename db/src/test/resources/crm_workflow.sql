create database temp;
use temp;

DROP TABLE IF EXISTS `t_oper_log_info`;
CREATE TABLE `t_oper_log_info` (
  `id` int(9) NOT NULL,
  `oper_module_name` varchar(64) character set utf8 collate utf8_bin default NULL,
  `oper_type` varchar(32) character set utf8 collate utf8_bin default NULL,
  `oper_notes` varchar(4000) character set utf8 collate utf8_bin default NULL,
  `user_id` varchar(64) character set utf8 collate utf8_bin default NULL,
  `insert_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_process_info`;
CREATE TABLE `t_process_info` (
  `id` int(9) NOT NULL,
  `process_name` varchar(64) character set utf8 collate utf8_bin default NULL,
  `version` int(9) default NULL,
  `wo_type` varchar(32) character set utf8 collate utf8_bin default NULL,
  `area` varchar(32) character set utf8 collate utf8_bin default NULL,
  `status` varchar(1) character set utf8 collate utf8_bin default NULL,
  `img_path` varchar(128) character set utf8 collate utf8_bin default NULL,
  `create_user_id` varchar(64) character set utf8 collate utf8_bin default NULL,
  `depoy_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_node_info`;
CREATE TABLE `t_node_info` (
  `id` int(9) NOT NULL,
  `process_id` int(9) default NULL,
  `node_name` varchar(64) character set utf8 collate utf8_bin default NULL,
  `node_type` varchar(1) character set utf8 collate utf8_bin default NULL,
  `node_x` int(9) default NULL,
  `node_y` int(9) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_node_oper_type_info`;
CREATE TABLE `t_node_oper_type_info` (
  `id` int(9) NOT NULL,
  `node_id` int(9) default NULL,
  `oper_type` varchar(1) character set utf8 collate utf8_bin default NULL,
  `oper_type_name` varchar(64) character set utf8 collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_oper_user_info`;
CREATE TABLE `t_oper_user_info` (
  `id` int(9) NOT NULL,
  `user_id` varchar(64) character set utf8 collate utf8_bin default NULL,
  `user_no` varchar(64) character set utf8 collate utf8_bin default NULL,
  `user_name` varchar(128) character set utf8 collate utf8_bin default NULL,
  `status` varchar(1) character set utf8 collate utf8_bin default NULL,
  `wo_email` varchar(128) character set utf8 collate utf8_bin default NULL,
  `wo_phone` varchar(128) character set utf8 collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_node_user_list_info`;
CREATE TABLE `t_node_user_list_info` (
  `id` int(9) NOT NULL,
  `node_id` int(9) default NULL,
  `user_id` varchar(64) character set utf8 collate utf8_bin default NULL,
  `process_id` int(9) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_process_definition_info`;
CREATE TABLE `t_process_definition_info` (
  `id` int(9) NOT NULL,
  `process_id` int(9) default NULL,
  `line_name` varchar(64) character set utf8 collate utf8_bin default NULL,
  `from_node_id` int(9) default NULL,
  `from_x` int(9) default NULL,
  `from_y` int(9) default NULL,
  `to_node_id` int(9) default NULL,
  `to_x` int(9) default NULL,
  `to_y` int(9) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_wo_type_info`;
CREATE TABLE `t_wo_type_info` (
  `id` varchar(32) character set utf8 collate utf8_bin NOT NULL comment '��������ID',
  `wotype_name` varchar(64) character set utf8 collate utf8_bin default NULL comment '�����������',
  `defination_obj` varchar(1) character set utf8 collate utf8_bin default '1' comment '���̶������',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '��������';



INSERT INTO `t_wo_type_info` (`id`,`wotype_name`,`defination_obj`) VALUES 
 (0x5453,0xE68A95E8AF89E5B7A5E58D95,0x32),
 (0x5851,0xE99C80E6B182E5B7A5E58D95,0x31);

insert into sys_sequence (name, current_value, increment) values('SEQ_T_OPER_LOG_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_PROCESS_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_NODE_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_NODE_OPER_TYPE_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_OPER_USER_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_NODE_USER_LIST_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_PROCESS_DEFINITION_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_WO_TYPE_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_WORK_ORDER_INFO', 0, 1);
insert into sys_sequence (name, current_value, increment) values('SEQ_T_WORK_ORDER_PROCESS_INFO', 0, 1);