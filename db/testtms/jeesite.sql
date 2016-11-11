SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tms_item;
DROP TABLE IF EXISTS tms_menu;
DROP TABLE IF EXISTS tms_menu_main;
DROP TABLE IF EXISTS tms_policy;
DROP TABLE IF EXISTS tms_rule;
DROP TABLE IF EXISTS tms_policy_main;




/* Create Tables */

CREATE TABLE tms_item
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 主菜单编号
	tms_menu_id varchar(64) COMMENT '主菜单编号',
	-- 内容
	text varchar(64) COMMENT '内容',
	-- 链接
	uri varchar(64) COMMENT '链接',
	-- 图表
	icon varchar(64) COMMENT '图表',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);


CREATE TABLE tms_menu
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 内容
	text varchar(64) COMMENT '内容',
	-- 链接
	uri varchar(64) COMMENT '链接',
	-- 图标
	icon varchar(64) COMMENT '图标',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- 父级编号
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	-- 所有父级编号
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	-- 名称
	name varchar(100) NOT NULL COMMENT '名称',
	-- 排序
	sort decimal(10,0) NOT NULL COMMENT '排序',
	PRIMARY KEY (id)
);


CREATE TABLE tms_menu_main
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 内容
	text varchar(64) COMMENT '内容',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);


CREATE TABLE tms_policy
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 策略名称
	name varchar(64) COMMENT '策略名称',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);


CREATE TABLE tms_policy_main
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 策略名称
	name varchar(64) COMMENT '策略名称',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);


CREATE TABLE tms_rule
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 编号
	tms_policy_id varchar(64) COMMENT '编号',
	name varchar(64),
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE tms_item
	ADD FOREIGN KEY (tms_menu_id)
	REFERENCES tms_menu_main (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tms_rule
	ADD FOREIGN KEY (tms_policy_id)
	REFERENCES tms_policy_main (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



