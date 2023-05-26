# rmall
## 开发框架
### springboot 2.7.11

## 数据库
### mysql + mybatis

## 缓存
### redis

## 缓存同步数据库
### canal

## RPC框架
### dubbo

## 注册中心
### zookeeper

## 负载均衡
### nginx

## 消息中间件
### rabbitmq + kafka

### 踩坑记录

#### 1、JSON.parseObject()方法报错： com.alibaba.fastjson.JSONException: syntax error, expect {, actual string, pos 0, fastjson-version 1.2.47
- 原因：
- 字符串中存在转义字符，导致解析失败；在使用JSON.toJSONString()方法时，就会加入转义字符
- 解决办法：
- 通过JSON.parse(msg).toString()进行解析，去掉转义字符
