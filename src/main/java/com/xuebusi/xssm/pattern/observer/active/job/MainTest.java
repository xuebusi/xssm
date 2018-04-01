package com.xuebusi.xssm.pattern.observer.active.job;

import com.xuebusi.xssm.pattern.observer.active.enums.ActStatusEnum;
import com.xuebusi.xssm.pattern.observer.active.job.dto.ActiveDto;
import com.xuebusi.xssm.pattern.observer.active.job.impl.ActiveSub;
import com.xuebusi.xssm.pattern.observer.active.job.impl.MultiActiveObs;
import com.xuebusi.xssm.pattern.observer.active.job.impl.UltimaActiveObs;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 */
public class MainTest {
    public static void main(String[] args) {
        ActiveSub activeSub = registerObserver();
        List<ActiveDto> activeDtoList = getActiveList();

        // 如果活动已经结束，通知所有观察者
        for (ActiveDto activeDto : activeDtoList) {
            if (activeDto != null && activeIsEnd(activeDto.getActiveStatus())) {
                activeSub.notifyToAll(activeDto);
            }
        }

    }

    /**
     * 注册观察者
     * @return 被观察者
     */
    public static ActiveSub registerObserver() {
        // 被观察者
        ActiveSub activeSub = new ActiveSub();
        // 观察者
        IActiveObs multiActiveObs = new MultiActiveObs();
        IActiveObs ultimaActiveObs = new UltimaActiveObs();
        // 注册观察者
        activeSub.addObserver(multiActiveObs);
        activeSub.addObserver(ultimaActiveObs);

        return activeSub;
    }

    /**
     * 模拟查询活动列表
     * @return
     */
    public static List<ActiveDto> getActiveList() {
        List<ActiveDto> activeDtoList = new ArrayList<>();

        ActiveDto activeDtoA = new ActiveDto("101", "活动A", "2");
        ActiveDto activeDtoB = new ActiveDto("102", "活动B", "2");
        ActiveDto activeDtoC = new ActiveDto("103", "活动C", "2");

        activeDtoList.add(activeDtoA);
        activeDtoList.add(activeDtoB);
        activeDtoList.add(activeDtoC);

        return activeDtoList;
    }

    /**
     * 活动是否结束
     * @param activeStatus
     * @return
     */
    public static boolean activeIsEnd(String activeStatus) {
        return ActStatusEnum.END.getCode().equals(activeStatus);
    }
}
