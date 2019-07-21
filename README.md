<image src="https://pinuoc.oss-cn-hangzhou.aliyuncs.com/blog/Cimit.png" alt="Cimit logo">

# Cimit: Application Current Limit

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Author](https://img.shields.io/badge/Author-PinuoC-67C23A.svg)](https://caohangwei.cn/)

## 介绍
面对高并发问题时，我们一般有三种应对策略，缓存、降级和限流。当系统的资源量不足以响应请求量时，大量的请求访问会导致系统直接被压垮，为了保证有限的资源能够正常服务，同时尽可能提升系统的吞吐量，那么限流就是一个重要的手段。

Cimit 具有以下特点：

- **分布式限流**：通过发票服务器方式，统一管理流量请求。
- **自定义规则**：针对不同的临界资源，可以自定义不同的限流规则。
- **漏桶算法**：漏桶算法 (Leaky Bucket) 能够强行限制流量的传输速率，平滑网络的突发流量。
- **细粒度**：能够很好的适应业务代码。
- **注解**：一个注解轻松搞定。

## 快速开始

### 分布式配置
如果要使用分布式限流的功能，需要启动 `cimit-server` 服务作为发票服务器，同时需要在 `resource` 资源目录下创建 `cimit.properties` 文件，并在里面配置发票服务器的 IP 地址和端口号。

cimit.properties 文件配置如下：
```java
cimit.server.url = localhost:8080
```

### 1. 注解方式

注解 `@Cimit` 定义了一个限流器，它具有以下属性：
- `value`: 限流器名称。这是一个唯一属性，通过给方法设置不同的名称来创建多个限流器。（注意：使用相同名称的注解会共享同一个限流器规则）
- `capacity`: 限流器最大桶容量。此属性可以限制最大请求数量，超过容量的请求将直接拒绝。
- `rate`: 限流器响应速度。规定了限流器单位时间内能够处理请求的数量。
- `period`: 限流器响应时间。自定义响应时间。
- `timeUnit`: 限流器响应时间单位。可以支持纳秒级别以上的时间单位。
- `waiting`: 限流器拒绝策略。默认请求直接拒绝，通过设置为 true 变为等待策略。
- `distributed`: 分布式限流器。默认为单机版，通过设置为 true 变为分布式限流。

例子：

添加 Spring AOP 配置：
```java
@Configuration
public class CimitAspectConfiguration {

    @Bean
    public CimitAspect cimitAspect() {
        return new CimitAspect();
    }
}
```

添加注解 `@Cimit`：
```java
@Cimit(value = "Cimit", capacity = 1000, rate = 100, period = 1, timeUnit = TimeUnit.SECONDS)
public void example() {
    System.out.println("Hello Cimit!");
}
```
### 2. 手动创建
自定义规则：
```java
CimitRule rule = new CimitRule("Cimit", 1000, 1000, 1, TimeUnit.SECONDS, false);
```
通过 `CimitFactory` 工厂创建相应的限流器：
``` java
LeakyBucketLimiter limiter = (LeakyBucketLimiter) CimitFactory.getLeakyBucketLimiter(rule);
```

该限流器有两种方法 `tryAcquire` 和 `acquire`，`tryAcquire` 将会尝试去往漏桶中添加水（流量），如果桶的剩余流量不足便会返回 false。
``` java
if (limiter.tryAcquire()) {
    System.out.println("Hello Cimit!");
}
```

而 `acquire` 方法则会一直等待，直到请求成功为止。

``` java
if (limiter.acquire()) {
    System.out.println("Hello Cimit!");
}
```

## 待办事项

- [x] ~~注解功能~~
- [x] ~~分布式限流~~
- [ ] 降级策略
- [ ] 手动开关
- [ ] 实时监控

## 关于作者

博客：https://caohangwei.cn/

如果你觉得本项目对你有帮助，请点击右上角 star 给作者一点点鼓励，谢谢 😄