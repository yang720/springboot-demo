package com.ly.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务
 * @author liuyang
 * @date 2020/3/12 18:38
 */
@Service
public class ScheduledService {

    /**
     * second(秒), minute(分), hour(时), day of month(日期), month(月), day of week(周几)
     * 取值范围：秒[0-59] 分[0-59] 时[0-23] 日期[1-31] 月[1-12] 周几[0-7 或 SUN-SAT ；0和7都是SUN也就是周日]
     * 允许字符：秒,分,时,月 [, - * /]
     *          日期 [, - * ? / L W C]
     *          星期 [, - * ? / L W #]
     * 字符含义：[, 枚举]
     *          [- 区间]
     *          [* 任意]
     *          [/ 步长]
     *          [? 日期和星期冲突] 如：* * * ? * MON  每周一执行任务，日期处不能为*  ；  * * * 1 * ?  每月一号执行，星期不能为*
     *          [L 最后]
     *          [W 工作日]
     *          [C 和calendar联系后计算过的值]
     *          [# 星期，4#2：表示第2个星期四]
     *
     * 【0 0/5 14,18 * * ?】 每天14点整和18点整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】 每月的周一至周六10:15分执行一次
     * 【0 0 2 ? * 6L】 每月的最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】 每月的最后一个工作日凌晨2点执行一次
     * 【0 0 2-4 ? * 1#1】 每月的第一个周一凌晨2点到4点期间，每个整点都执行一次
     *
     * @Scheduled(cron = "0 * * * * MON-SAT")
     * @Scheduled(cron = "0,1,2,3 * * * * MON-SAT")  表示周一至周六每分钟第0,1,2,3秒执行
     * @Scheduled(cron = "0-3 * * * * MON-SAT")  同上
     * @Scheduled(cron = "0/5 * * * * MON-SAT")  表示周一至周六每5秒执行一次
     *
     */
    @Scheduled(cron = "0 * * * * MON-SAT")
    public void hello(){
        System.out.println("hello ...");
    }
}
