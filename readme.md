创梦工厂项目

## 1.面向对象

想做一个类似小猿搜题的搜索项目解决方案的软件

数据部分需要采用类似众包平台的形式调动大学生积极性来补全数据库

## 2.模块说明

### 1.搜题平台（面向搜题用户）

1.登录注册模块（创建搜题用户）

2.搜索相关项目解决方案

3.反馈缺失题目信息（意图创建新的项目，等待人工审核）

### 2.众包平台（面向项目方案提供者）

1.登录注册模块（需要存储用户关键信息如：微信号，支付宝号等）

2.查看申请任务模块（需要一个用户能够查看和申请当前拥有任务的模块）

3.编辑解决方案（申请成功的用户可以编辑解决方案）

4.撤销或提交解决方案（编辑完成后可以提交解决方案等待人工审核）

### 3.审核平台（面向消息审核者）

1.审核反馈来的题目信息（审核题目的实用性并将其通过提交到众包平台上供申请）

2.审核已提交的解决方案（审核方案的可行性并根据提交者的信息进行针对性的拨款或不合格打回）

## 3.数据库构型(省略id)

### user

name

phone

password

vx_account

### module

name

description

create_user

### interface

module_id

name

description

amount

### solution

name

keyword

description

text (json)

create_user

## 4.接口文档

### 1.登陆注册

#### 1.login（无需登录）

接口url：/auth/login

请求方式：POST  

请求参数：

| 参数名称     | 参数类型 | 说明   |
| ------------ | -------- | ------ |
| phone        | string   | 电话号 |
| **password** | string   | 密码   |

返回数据： result 携带一个token

~~~json
{
	"success": true,
	"message": "success",
	"code": 200,
	"result": "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODgzODEwNjUsImlhdCI6MTY4ODM3NzQ2NSwiY29udGVudCI6IjEifQ.i1WXc_QKIam5H_Z3OjYNs8aGhCWqVS9ahmd1Ykstj6g"
}
~~~



#### 2.logout

接口url：/auth/logout

请求方式：POST  

请求参数：

| 参数名称      | 参数类型 | 说明            |
| ------------- | -------- | --------------- |
| Authorization | string   | 请求头携带token |

返回数据：

~~~json
{
	"success": true,
	"message": "success",
	"code": 200,
	"result": null
}
~~~



#### 3.register（无需登录）

接口url：/auth/register

请求方式：POST  

请求参数：

| 参数名称     | 参数类型 | 说明   |
| -------- | -------- | ------ |
| phone    | string   | 电话号 |
| password | string   | 密码   |

返回数据：result 携带一个token

~~~json
{
	"success": true,
	"message": "success",
	"code": 200,
	"result": "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODgzODEwNjUsImlhdCI6MTY4ODM3NzQ2NSwiY29udGVudCI6IjEifQ.i1WXc_QKIam5H_Z3OjYNs8aGhCWqVS9ahmd1Ykstj6g"
}
~~~



### 2.修改信息

#### 1.修改用户名

接口url：/info/changeName

请求方式：get 

请求参数：name

ex: localhost:8000/info/changeName?name=cccc

| 参数名称 | 参数类型 | 说明   |
| -------- | -------- | ------ |
| name     | string   | 用户号 |

返回数据：

1.成功

~~~json
{
	"success": true,
	"message": "success",
	"code": 200,
	"result": null
}
~~~

2.用户名被使用

~~~json
{
	"success": false,
	"message": "用户名已被使用",
	"code": 105,
	"result": null
}
~~~



#### 2.修改vx号

接口url：/info/changeVxAccount

请求方式：get 

请求参数：vx

ex: localhost:8000/info/changeVxAccount?vx=cccb

| 参数名称 | 参数类型 | 说明   |
| -------- | -------- | ------ |
| vx       | string   | 微信号 |

返回数据：

1.成功

~~~json
{
	"success": true,
	"message": "success",
	"code": 200,
	"result": null
}
~~~

2.vx号被使用

~~~json
{
	"success": false,
	"message": "微信号已被使用",
	"code": 104,
	"result": null
}
~~~

#### 3.修改密码（修改完密码直接下线）

接口url：/info/changePassword

请求方式：get 

请求参数：password

ex: localhost:8000/info/changePassword?password=vvvvv

| 参数名称 | 参数类型 | 说明 |
| -------- | -------- | ---- |
| password | string   | 密码 |

返回数据：

~~~json
{
	"success": true,
	"message": "success",
	"code": 200,
	"result": null
}
~~~





