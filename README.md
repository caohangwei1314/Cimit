# Cimit : Application Current Limit

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Author](https://img.shields.io/badge/Author-PinuoC-67C23A.svg)](https://caohangwei.cn/)

## 介绍
面对高并发问题时，我们一般有三种应对策略，缓存、降级和限流。当系统的资源量不足以响应请求量时，大量的请求访问会导致系统直接被压垮，为了保证有限的资源能够正常服务，同时尽可能提升系统的吞吐量，那么限流就是一个重要的手段。

Cimit 具有以下特点：

- **漏桶算法**：漏桶算法 (Leaky Bucket) 能够强行限制流量的传输速率，平滑网络的突发流量。
- **自定义规则**：针对不同的临界资源，可以自定义不同的限流规则。
- **细粒度**：能够很好的适应业务代码。

## 快速开始

### 构建 Limiter
``` java
LeakyBucketLimiter limiter = (LeakyBucketLimiter) LimiterFactory.getLeakyBucketLimiter();
```

### 拒绝策略
``` java
if (limiter.tryAcquire()) {
    System.out.println("Hello Cimit!");
}
```

### 等待策略（非公平）
``` java
if (limiter.acquire()) {
    System.out.println("Hello Cimit!");
}
```

## 待办事项

- [ ] 注解
- [ ] 分布式限流
- [ ] 手动开关
- [ ] 实时监控

## 关于作者

博客：https://caohangwei.cn/