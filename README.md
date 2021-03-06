# Java程序设计大作业 - 山寨版蔚蓝

## 初级玩法介绍

* 整个游戏只有7个按键：↑、↓、←、→、Z、X、C (这一点与原版Celeste相同)
  * ←、→：左右移动
  * C：跳跃
  * X+方向键：冲刺
  * Z：抓墙
  * ↑、↓：抓墙状态下，沿着墙上下移动
* 冲刺之后，踩到地面才能再次冲刺
* 爬墙时会消耗体力，体力耗尽时会沿墙下滑，踩到地面即可回满体力

## 进阶玩法介绍

* 冲刺方向一共有8个：上，下，左，右，上左，上右，下左，下右
* 抓墙时，不按方向键进行跳跃或按住指向墙的方向键进行跳跃，会垂直上跳
* 抓墙时，按住背离墙的方向键进行跳跃，会跳到离墙比较远的地方
* 在墙边(不抓墙)按住指向墙的方向键，会在墙上匀速滑下
* 在墙边(不抓墙)不按左右方向键进行跳跃，会跳到离墙不是很远的地方，跳了之后马上按住指向墙的方向键，可以到达比跳跃之前更高的地方(不是bug，原版游戏就是这样设计的)
* 在墙边(不抓墙)按住左右方向键进行跳跃，会跳到离墙比较远的地方
* 沿墙下滑不消耗体力，抓住不动会缓慢消耗体力，往上攀爬会加速消耗体力，抓墙跳跃会消耗大量体力

## 不足之处

* 体力机制把控得不是很好，与原版游戏出入很大。或许应该增加体力上限，以及爬墙跳跃所消耗的体力，从而间接降低抓墙不动消耗体力的速率
* 没有把游戏的数据文件封装到.jar里 (来源于Java知识的匮乏)
