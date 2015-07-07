-- 宿舍人员表
CREATE TABLE DORMITORY_USER (
  ID       INT(8) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NAME     VARCHAR(20)           NOT NULL
  COMMENT '名字',
  ADD_TIME DATETIME              NOT NULL
  COMMENT '新增时间'
)
  COMMENT '宿舍人员表';

-- 缴费/开销表
CREATE TABLE DORMITORY_PAY (
  ID      INT(8) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  REMARK  VARCHAR(4000)         NOT NULL
  COMMENT '备注',
  TIME    DATETIME              NOT NULL
  COMMENT '缴费/开销时间',
  AMOUNT  NUMERIC(5)            NOT NULL
  COMMENT '缴费/开销金额',
  USER_ID INT(8)                NOT NULL
  COMMENT '用户ID',
  FLAG    CHAR(1)               NOT NULL
  COMMENT '0 缴费 1 开销 2 住宿费（5元/天）'
)
  COMMENT '缴费/开销表';