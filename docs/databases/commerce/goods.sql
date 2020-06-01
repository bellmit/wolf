CREATE DATABASE IF NOT EXISTS `wolf_goods` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_goods`;

DROP TABLE IF EXISTS `goods`;
CREATE TABLE IF NOT EXISTS `goods`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `category_id`   MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类目ID',
    `goods_type`    SMALLINT(6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '产品类型',

    `name`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '产品名',
    `price`         DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '价格',
    `min_price`     DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT 'min价格',
    `max_price`     DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT 'max价格',
    `currency`      INT(10) NOT NULL DEFAULT 0 COMMENT '币种',
    `charge_unit`   INT(10) NOT NULL DEFAULT 0 COMMENT '单位',

    `state`         TINYINT(4) NOT NULL DEFAULT 0 COMMENT '产品状态',
    `stock_type`    INT(10) NOT NULL DEFAULT 0 COMMENT '库存类型',
    `sku_type`      INT(10) NOT NULL DEFAULT 0 COMMENT 'SKU类型',

    `vs_price`      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '划线价',
    `feature`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '产品特色',
    `main_pic`      VARCHAR(200) NOT NULL DEFAULT '' COMMENT '产品主图',
    `main_video`    VARCHAR(200) NOT NULL DEFAULT '' COMMENT '产品主视频',
    `url`           VARCHAR(200) NOT NULL DEFAULT '' COMMENT '外部链接地址',

    `code`          VARCHAR(50) NOT NULL DEFAULT '' COMMENT '产品编码',
    `tags`          VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'tags',
    `creator`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX idx_type(`org_id`, `goods_type`, `state`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '产品';


DROP TABLE IF EXISTS `goods_detail`;
CREATE TABLE IF NOT EXISTS `goods_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `pics`          TEXT COMMENT '商品图',
    `sku_info`      TEXT COMMENT 'sku信息',
    `ext_info`      TEXT COMMENT 'ext信息',
    `detail`        TEXT COMMENT '产品详情',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '产品详情';


DROP TABLE IF EXISTS `sku`;
CREATE TABLE IF NOT EXISTS `sku`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `goods_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `price`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT '价格',
    `currency`      INT(10) NOT NULL DEFAULT 0 COMMENT '币种',
    `charge_unit`   INT(10) NOT NULL DEFAULT 0 COMMENT '单位',
    `stock_type`    INT(10) NOT NULL DEFAULT 0 COMMENT '库存类型',
    `sku`           VARCHAR(1000) NOT NULL DEFAULT '' COMMENT 'sku',
    `sku_key`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'skuKey',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX idx_goods(`goods_id`, `org_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'sku';

DROP TABLE IF EXISTS `goods_loan`;
CREATE TABLE IF NOT EXISTS `goods_loan`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `goods_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `repay_strategy`    INT(10) NOT NULL DEFAULT 0 COMMENT '还款策略',
    `prepay_strategy`   INT(10) NOT NULL DEFAULT 0 COMMENT '提前还款策略',
    `amount_strategy`   INT(10) NOT NULL DEFAULT 0 COMMENT '金额策略',
    `handling_fee_rate` DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '手续费',
    `fee_pay_strategy`  INT(10) NOT NULL DEFAULT 0 COMMENT '手续费策略',
    `period`            INT(10) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `period_unit`       INT(10) NOT NULL DEFAULT 0 COMMENT '时长单位',
    `period_strategy`   INT(10) NOT NULL DEFAULT 0 COMMENT '时长策略',
    `interest`          DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '利息',
    `interest_unit`     INT(10) NOT NULL DEFAULT 0 COMMENT '利息单位',
    `penalty`           DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '滞纳金',
    `penalty_unit`      INT(10) NOT NULL DEFAULT 0 COMMENT '滞纳金单位',

    `installment`       text COMMENT '分期信息',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX idx_goods(`goods_id`, `org_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'loan';


DROP TABLE IF EXISTS `goods_installment`;
CREATE TABLE IF NOT EXISTS `goods_installment`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `goods_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `period`        INT(10) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `percentage`    DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '还款比例',
    `fee_percentage`DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '手续费比例',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX idx_goods(`goods_id`, `org_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'installment';

