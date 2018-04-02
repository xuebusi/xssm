package com.xuebusi.xssm.pattern.observer.active.job;

import com.xuebusi.xssm.pattern.observer.active.enums.ActStatusEnum;
import com.xuebusi.xssm.pattern.observer.active.job.dto.ActiveDto;
import com.xuebusi.xssm.pattern.observer.active.job.impl.CommonSub;
import com.xuebusi.xssm.pattern.observer.active.job.impl.MultiActiveObs;
import com.xuebusi.xssm.pattern.observer.active.job.impl.UltimaActiveObs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * 测试类
 */
public class MainTest {

    public static void main(String[] args) {
        ICommonSub commonSub = registerObserver();
        List<ActiveDto> activeDtoList = getActiveList();

        // 如果活动已经结束，通知所有监听者
        for (ActiveDto activeDto : activeDtoList) {
            if (activeDto == null) {
                continue;
            }
            if (activeIsEnd(activeDto.getActiveStatus())) {
                commonSub.notifyObs(activeDto);
            }
        }

    }

    /**
     * 注册监听者
     * @return 被监听者接口
     */
    public static ICommonSub registerObserver() {
        // 被监听者
        CommonSub commonSub = new CommonSub();
        // 监听者
        Observer multiActiveObs = new MultiActiveObs();
        Observer ultimaActiveObs = new UltimaActiveObs();
        // 注册监听者
        commonSub.addObserver(multiActiveObs);
        commonSub.addObserver(ultimaActiveObs);
        // 移除监听者
        commonSub.deleteObserver(multiActiveObs);
        commonSub.deleteObserver(ultimaActiveObs);
        System.out.println("监听者数量:" + commonSub.countObservers());

        return commonSub;
    }

    /**
     * 模拟查询活动列表
     * @return
     */
    public static List<ActiveDto> getActiveList() {
        List<ActiveDto> activeDtoList = new ArrayList<>();

        ActiveDto activeDtoA = new ActiveDto("3424167885000", "第七届全球游戏大会", "2");
        ActiveDto activeDtoB = new ActiveDto("2432243756900", "世界区块链峰会", "2");
        ActiveDto activeDtoC = new ActiveDto("2429493984800", "支付设计大会", "2");

        activeDtoList.add(activeDtoA);
        activeDtoList.add(activeDtoB);
        activeDtoList.add(activeDtoC);

        return activeDtoList;
    }

    /**
     * 活动是否已结束
     * @param activeStatus
     * @return
     */
    public static boolean activeIsEnd(String activeStatus) {
        return ActStatusEnum.END.getCode().equals(activeStatus);
    }

    /**
     * 活动是否已开始（进行中）
     * @param activeStatus
     * @return
     */
    public static boolean activeIsStart(String activeStatus) {
        return ActStatusEnum.START.getCode().equals(activeStatus);
    }

    /**
     * 活动是否未开始
     * @param activeStatus
     * @return
     */
    public static boolean activeIsNotStart(String activeStatus) {
        return ActStatusEnum.NOT_START.getCode().equals(activeStatus);
    }

}
