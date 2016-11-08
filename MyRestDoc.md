## 登录
**POST** http://10.8.1.141:80/jeesite/a/login
### request 
#### header 
Content-Type:application/x-www-form-urlencoded
#### body   
username=thinkgem&password=admin
### response 
```
{
  "id": "1",
  "loginName": "thinkgem",
  "name": "系统管理员",
  "mobileLogin": true,
  "sessionid": "2449f0bd31d54f2fa3f8965e268cf46b"
}
```
## 退出
**GET/POST** http://10.8.1.141:80/jeesite/a/logout
### response 
```
{
  "status":1,
  "message":"退出登录"
}
```

## 查询单表列表
**GET** http://10.8.1.141:80/jeesite/a/tmsPolicys
### request
Accept:application/json    
Content-Type:application/json;charset=utf-8
#### body 
```
{
  "orderBy":"xxx",
  "name":"xxx"
}
```
### response 
[ {
  "id" : "36c1200ec44944fe834964493a3c5c07",
  "isNewRecord" : false,
  "createDate" : "2016-11-08 11:02:52",
  "updateDate" : "2016-11-08 11:02:52",
  "name" : "策略1"
}, {
  "id" : "0431e9d05dee4a96b8e74f57231bccd8",
  "isNewRecord" : false,
  "createDate" : "2016-11-08 10:25:19",
  "updateDate" : "2016-11-08 10:25:19",
  "name" : "策略1"
} ]

## 查询单个实体
**GET**  http://10.8.1.141:80/jeesite/a/tmsPolicys/{id}
### request
#### header
Accept:application/json    
Content-Type:application/json;charset=utf-8
### response 
```
{
  "status" : 1,
  "data" : {
    "id" : "0431e9d05dee4a96b8e74f57231bccd8",
    "name" : "策略1"
  }
}
```

## 增加或修改
**POST** http://10.8.1.141:80/jeesite/a/tmsPolicys
### request
#### header
Accept:application/json    
Content-Type:application/json;charset=utf-8
#### body 
##### 增加
```
{"name":"策略11"}
```
##### 修改
```
{"name":"策略11","id":"a0d2d96bef0d4948be284215a113847a"}
```
### response 
```
{
  "status" : 1,
  "data" : {
    "id" : "0431e9d05dee4a96b8e74f57231bccd8",
    "name" : "策略1"
  }
}
```

## 分页查询
**GET** http://10.8.1.141:80/jeesite/a/tmsPolicys/page
### request
#### header
Accept:application/json    
Content-Type:application/json;charset=utf-8
#### body 
```
{
  "pageNo":1,
  "pageSize":10,
  "name":"xxx",
  "orderBy":"xxx",
}
```
### response
```
{
  "status" : 1,
  "data" : {
    "pageNo" : 1,
    "pageSize" : 30,
    "count" : 2,
    "maxPage" : 1,
    "list" : [ {
      "id" : "36c1200ec44944fe834964493a3c5c07",
      "name" : "策略1"
    }, {
      "id" : "0431e9d05dee4a96b8e74f57231bccd8",
      "name" : "策略1"
    } ],
    "maxResults" : 30,
    "firstResult" : 0
  }
}
```
## 删除
**DELETE**   http://10.8.1.141:80/jeesite/a/tmsPolicys/{id}
### request
#### header
Accept:application/json    
Content-Type:application/json;charset=utf-8
### response
```
{
  "status":1,
  "message":"删除成功"
}
