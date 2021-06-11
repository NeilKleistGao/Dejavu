# 对接前端接口文档
## 用户部分
### 获取用户基本信息
url: `/user/info/query`

说明：获取用户的基本信息

method: GET

表单参数：无

GET参数：
+ `uid`：用户id

返回数据示例：
```json
{
    "phone": 13910733521,
    "mail": "takuumi@fujiwara.com",
    "avatar": "safdgfhg.jpg",
    "name": "takuumi fujiwara"
}
```

### 查询符合条件的用户信息
url: `/user/info/query_all`

说明：获取满足条件的用户的信息（管理员）

method: GET

表单参数：无

GET参数：
+ `name`：用户姓名，可选
+ `phone`：用户电话，可选
+ `email`：用户邮箱，可选

返回数据示例：
```json
{
    "result": [
        {
            "uid": 114514,
            "phone": 13910733521,
            "mail": "takuumi@fujiwara.com",
            "name": "takuumi fujiwara"
        }
    ]
}
```

### 用户信息修改
url: `/user/info/modify`

说明：修改用户基本信息

method: POST

表单参数：
+ `uid`：用户id
+ `mail`: 用户邮箱，可选
+ `avatar`：用户头像链接，可选

GET参数：无

返回数据示例：
```json
{
    "result": true // 成功时返回true，否则返回false
}
```

### 用户密码修改
url: `/user/info/password`

说明：修改用户密码

method: POST

表单参数：
+ `uid`：用户id
+ `old`：用户旧密码
+ `new`：用户新密码

GET参数：无

返回数据示例：
{
    "result": false, // 成功修改返回true，否则返回false
    "info": "密码错误" // 失败原因，仅在失败时才返回
}

### 用户注册
url: `/user/register`

说明：用户注册

method: POST

表单参数：
+ `phone`：用户电话号码
+ `mail`: 用户邮箱
+ `password`：用户密码

GET参数：无

返回数据示例：
```json
{
    "result": 114514, // 成功返回用户id，错误返回-1
    "info": "用户已存在"
}

```

### 用户登录
url: `/user/login`

说明：用户登录

method: POST

表单参数：
+ `phone`：用户手机号
+ `password`：用户密码

GET参数：无

返回数据示例：
```json
{
    "token": "yyfsmddx",
    "uid": 114514,
    "info": "密码错误" // 仅登录失败时返回原因
}
```

### 用户登出
url: `/user/logout`

说明：用户登出

method: POST

表单参数：
+ `token`：交还的token
+ `uid`：对应的uid

返回数据示例：
```json
{
    "result": true // 成功登出时返回true，否则返回false
}
```

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
    "date": "2016-08", // 上牌日期
    "images": ["img1.jpg", "img2.jpg"]
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
            "price": 50,
            "cover": "1.png" // 返回图片列表中第一张图片即可
        },
        {
            "car-id": "1919810",
            "model": "yyy",
            "manufacturer": "yyy",
            "server": 2,
            "guild-price": 200,
            "price": 100,
            "cover": "2.png"
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

### 新增车辆信息
url: `/car/new`

说明：新增售车信息

method: POST

表单参数：
+ uid：用户id
+ token：当前用户token
+ model：车辆类型名称
+ guild_price：指导价格
+ manufacture：生产商
+ server_life：使用时长
+ mileage：里程
+ displacement：排量
+ region:所在地
+ price：车辆报价
+ body_type：车辆类型
+ fuel_type：燃料类型
+ gearbox：变速箱类型
+ power：发动机功率
+ not_repaired_damage：有尚未修复的损坏
+ date：上牌日期
+ images：车辆图片列表

GET参数：无

返回数据示例：
```json
{
    "car_id": 114514, // 返回新车辆的车辆id，如果创建失败返回-1
    "info": "请登录" // 仅在错误时返回，错误信息提示，“请登录”表示token或uid无效
}
```

## 交易部分
### 创建新的砍价
url: `/transaction/bargain/new`

说明：创建新的砍价记录

method: POST

表单参数：
+ uid：发起创建砍价的用户id
+ token:当前用户token
+ car_id: 砍价车辆id
+ price：砍价价格
+ start_time：砍价开始时间
+ end_time：砍价终止时间

GET参数：无

返回数据示例：
```json
{
    "result": true, // true表明创建成功，否则返回false
    "info": "请登录" // 仅在错误时返回，错误信息提示，“请登录”表示token或uid无效
}
```

### 查询砍价信息
url: `/transaction/bargain/query`

说明：查询符合条件的砍价记录（管理员可以查询全部，非管理员仅能查询自己）

method: GET

表单参数：无

GET参数：
+ id: 当前用户id
+ token：当前用户token
+ uid: 创建砍价的用户的id，可选
+ cid: 对应汽车的id，可选

返回数据示例：
```json
{
    "results": [
        {
            "price": 1000,
            "start_time": "2018-01-01",
            "end_time": "2019-01-01",
            "uid": 1,
            "cid": 2
        },
         {
            "price": 2000,
            "start_time": "2018-01-01",
            "end_time": "2019-01-01",
            "uid": 1,
            "cid": 2
        }
    ], // 出错时该字段长度为0
    "info": "请登录" // 仅在出错时返回，当没有权限时返回“请登录”
}
```

### 查询成交信息
url: `/transaction/sale/query`

说明：查询符合条件的成交记录（管理员可以查询全部，非管理员仅能查询自己）

method: GET

表单参数：无

GET参数：
+ uid：当前用户的id
+ token：当前用户token
+ buyer: 购买用户的id，可选
+ seller: 出售用户的id，可选


返回数据示例：
```json
{
    "results": [
        {
            "buyer_id": 114,
            "seller_id": 514,
            "car_id": 1919,
            "deal_price": 810,
            "deal_date": "2013-01-01"
        }
    ], // 出错时该字段长度为0
    "info": "请登录" // 仅在出错时返回，当没有权限时返回“请登录”
}
```

### 修改成交信息
url: `/transaction/sale/modify`

说明：修改已存在的交易的成交信息（管理员）

method: POST

表单参数：
+ uid：当前用户id
+ token：当前用户token
+ sale_id：成交信息id
+ buyer_id: 购买用户的id，可选
+ seller_id：买家用户id，可选
+ car_id: 成交车辆id，可选
+ deal_price：成交价格，可选
+ deal_date：成交日期，可选

GET参数：无

返回数据示例：
```json
{
    "result": false, // 成功修改返回true，否则返回false
    "info": "交易不存在" // 失败原因，仅在失败时才返回，当没有权限时返回“请登录”
}
```

### 提交成交信息
url: `/transaction/sale/submit`

说明：提交新的成交（管理员）

method: POST

表单参数：
+ uid：当前用户id
+ token：当前用户token
+ buyer_id: 购买用户的id
+ seller_id：买家用户id
+ car_id: 成交车辆id
+ deal_price：成交价格
+ deal_date：成交日期

GET参数：无

返回数据示例：
```json
{
    "result": 114514, // 新交易的id，如果失败返回-1
    "info": "请登录" // 失败原因，仅在失败时才返回，当没有权限时返回“请登录”
}
```

### 删除成交信息
url: `/transaction/sale/delete`

说明：删除成交信息（管理员）

method: POST

表单参数：
+ uid：当前用户id
+ token：当前用户token
+ sale_id：成交信息id

GET参数：无

返回数据示例：
```json
{
    "result": false, // 成功删除返回true，否则返回false
    "info": "交易不存在" // 失败原因，仅在失败时才返回，当没有权限时返回“请登录”
}
```