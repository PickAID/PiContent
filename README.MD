# PiContent

[English](README_EN.md)

PiContent 是 Pi 栈的内容生产工具包。它负责 Item、Block、BlockEntity、datagen、creative 和客户端表现需求的 authoring metadata，不负责 gameplay runtime、网络、序列化内部实现或渲染后端。

## 当前状态

- 已初始化 Forge 1.20.1 模块壳。
- P0 目标：Item builder、Block builder、BlockEntity builder、datagen declaration bundle 和 declaration-only render hook。

## Maven

```gradle
implementation fg.deobf("com.mihono.pickaid:picontent:0.0.3")
```

## 边界

- Public API 不持有 `Minecraft` client singleton 或 renderer backend 对象。
- Public API 不暴露原始网络 buffer 类型。
- PiContent 只声明内容和生成资产需求，不执行伤害、移动、能力或世界状态变更。
