package com.xuebusi.xssm.test.nashorn;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Nashorn测试
 *
 * @author: shiyanjun
 * @Date: 2018/11/9 上午10:24
 */
public class NashornTest {

    public static void main(String[] args) {

        // 获取脚本引擎
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        // 定义一个求和函数
        String sum = "function sum(a, b){\n" +
                "    return a + b;\n" +
                "}";

        // 求最值函数
        String max = "function max(a,b) {\n" +
                "\treturn a > b ? a : b;\n" +
                "}";

        try {
            engine.eval(sum);
            engine.eval(max);

            // 强转成可调用对象
            Invocable invocable = (Invocable) engine;

            // 调用脚本中的求最值函数，并传入所需要的2个参数
            Object sumResult = invocable.invokeFunction("sum", 12, 11);
            Object maxResult = invocable.invokeFunction("max", 12, 11);

            // 输出函数返回结果
            System.out.println("求和:" + sumResult);
            System.out.println("最大值:" + maxResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
