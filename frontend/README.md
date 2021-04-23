# Dejavu前端说明文档
## 页面及页面内容
| URL | 组件文件 | 说明 |
| --- | --- | --- |
| / | Index.vue | 主页，包含买车/卖车功能导航 |
| /search | Search.vue | 搜索页，按照各项指标筛选车辆信息 |
| /login | Login.vue | 登录页面，如果已登录，则重定向到用户主页 |
| /register | Register.vue | 注册页面，如果已登录，则重定向到用户主页 | 
| /user | User.vue | 用户主页，允许修改部分个人信息，显示简略的各项记录 |
| /sell | Sell.vue | 预约卖车页面，用户填写车辆信息 |
| /car | CarDetail.vue | 车辆详细信息页面，包含车辆信息，砍价操作和少量砍价记录 |
| /* | Error404.vue | 404页面 |

## 组件及组件功能
| 组件文件 | 说明 |
| --- | --- |
| MainPageBanner.vue | 主页所使用的提示横幅组件 |

## 代码规范
ESLint已开启，如果代码不符合规范，框架将自动报错。

## 注释规范
请在每个`vue`文件开头写入如下注释，每次修改文件内容的同时修改：
```html
<!--
 * @FileDescription: 该文件的描述信息
 * @Author: 作者信息
 * @Date: 文件创建时间
 * @LastEditors: 最后更新作者
 * @LastEditTime: 最后更新时间
 -->
```

对于每个method: 
```js
/**
  * @description: 方法描述
  * @param {参数类型} 参数名称
  * @param {参数类型} 参数名称
  * @return 没有返回信息写 void / 有返回信息 {返回类型} 描述信息
  */
```

`watch`等其他部分不需要添加注释（除非你认为有必要），因为这些函数的作用非常明显。

`data`中的变量需要添加注释：
```js
data () {
    return {
        a: 0, // 变量a的说明
        b: 1 // 变量b的说明
    }
}
```