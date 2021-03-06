# Dejavu二手车平台需求分析
---

## 变更记录

| 版本号 | 修改人 | 修改时间 | 说明 |
|---|---|---|---|
| v0.0.1 | 高存远 | 2021.04.09 | 初始化 |
| v0.1.0 | 高存远 | 2021.04.13 | 添加基本描述 |

---

## 目录


## 综述
Dejavu是一个二手车交易平台，其支持用户在平台上销售自己的二手车或在平台上进行选购。
由于二手车的特点，二手车的交易必须要涉及到线下交易。
所以二手车平台与一般货品交易网站不同，仅支持二手车信息的发布和部分流程的跟踪，无法追踪到全部过程。

Dejavu支持二手车车主的信息发布，买主根据价格，车型，城市等不同信息过滤，筛选和比对车辆信息的功能。
对于交易过程，平台仅负责协助买家与卖家进行沟通联系，并记录成交信息，不负责实际的交易流程。

本需求文档参考瓜子二手车交易平台进行编写。

## 卖车模块
该模块包含卖家需要的功能。

### 预约卖车
+ 卖家填写自己的车辆信息
+ 平台向卖家确认信息
+ 平台放出车辆信息

### 卖家进行卖车流程
+ 买家与卖家进行联系
+ 买家与卖家确认最终成交条件（如最终价格，成交地点等）
+ 买家与卖家进行线下交易
+ 卖家收取金额

## 我的模块
该模块包含所有用户需要的基本功能。

### 查看成交记录
所有用户均可以查看自己的交易记录，包括自己买的二手车和卖的二手车，成交的记录和终止（未成交）的记录。

### 查看预约记录
所有用户可以查看自己预约卖车的记录，并进行编辑。

### 查看砍价记录
所有用户可以查看自己在购车环节中的砍价记录。

## 买车模块
该模块包含了买家需要的功能。
### 城市定位
用户需要可以定位自己所在的城市；如果城市不准确或错误，用户需要可以自己选择所在地。

### 搜索
用户可以按照车型，排量，里程数等条件搜索自己期望的车辆。

### 订阅
对于某一车辆，如果用户中意但并不想立即购买，用户可以订阅该车辆的状态。