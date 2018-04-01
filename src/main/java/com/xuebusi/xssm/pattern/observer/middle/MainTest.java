package com.xuebusi.xssm.pattern.observer.middle;

import com.xuebusi.xssm.pattern.observer.middle.impl.BossSub;
import com.xuebusi.xssm.pattern.observer.middle.impl.NbaObserver;
import com.xuebusi.xssm.pattern.observer.middle.impl.StockObserver;

public class MainTest {
    public static void main(String[] args) {
        BossSub bossSub = new BossSub();
        StockObserver stockObserver = new StockObserver("张三", bossSub);
        NbaObserver nbaObserver = new NbaObserver("李四", bossSub);

        bossSub.add(stockObserver);
        bossSub.add(nbaObserver);

        bossSub.setAction("满血复活");
        bossSub.notifyToObs();
    }
}
