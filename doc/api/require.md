# 对接前端接口文档
## 用户部分
## 车辆部分
### 获取车辆详细信息
url: `/car`

说明：根据提供的车辆id，返回车辆相关信息

method: GET

表单参数：无

GET参数：
+ `id`：车辆id

返回数据示例：
```json
{
    "model_name": "AE86", // 车型名称
    "guild_price": 100000, // 指导价格
    "manufacturer": "丰田", // 厂商
    "service_life": 1.2, // 使用年限
    "mileage": 1000, // 里程数
    "displacement": 0, // 排量
    "region": "北京", // 地区
    "uid": "114514", // 所有用户
    "price": 80000, // 价格
    "state": 1, // 状态
    "body_type": "微型车", // 车身类型
    "fuel_type": "汽油", // 燃料类型
    "gear_box": "手动", // 变速箱
    "power": 0, // 马力
    "not_repaired_damage": false, // 是否有修复过
    "date": "2016-08" // 上牌日期
}
```

### 获取满足条件的车辆数据
url: `/car/list`

说明：获取满足条件的车辆列表

method: GET

表单参数：无

GET参数：
+ `region`:地区
+ `key`: 搜索关键字，可选
+ `model`:车型名称，可选
+ `min-guild-price`:最低指导价格，可选
+ `max-guild-price`:最高指导价格，可选
+ `manufacturer`:厂商，可选
+ `server`:使用年限，可选
+ `mileage`:里程数，可选
+ `displacement`:排量，可选
+ `min-price`:最低价格，可选
+ `max-price`:最高价格，可选
+ `body`:车身类型，可选
+ `fuel`:燃料类型，可选
+ `gear`:变速箱，可选
+ `order-by`：根据以上某个字段排序，可选
+ `order`:升序(值为0)或降序(值为1)，可选
+ `page`:当前页码，可选，默认为1，一页大小为20

返回数据示例：
```json
{
    "list": [
        {
            "car-id": "114514",
            "model": "xxx",
            "manufacturer": "xxx",
            "server": 1,
            "guild-price": 100,
            "price": 50
        },
        {
            "car-id": "1919810",
            "model": "yyy",
            "manufacturer": "yyy",
            "server": 2,
            "guild-price": 200,
            "price": 100
        }
    ]
}
```

### 获取车辆列表页数
url: `/car/pn`

说明：获取车辆列表长度

method: GET

表单参数：无

GET参数：与“获取满足条件的车辆数据”相同

返回数据示例：
```json
{
    "page-number": 114514
}
```

## 交易部分