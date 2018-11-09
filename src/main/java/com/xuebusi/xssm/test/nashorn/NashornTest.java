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
        String jsStr = "function sum(a, b){\n" +
                "    return a + b;\n" +
                "}";

        try {
            Object eval = engine.eval(jsStr);
            System.out.println(eval);

            // 强转成可调用对象
            Invocable invocable = (Invocable) engine;

            // 调用脚本中的求和函数，并传入所需要的2个参数
            Object result = invocable.invokeFunction("sum", 12, 34);

            // 输出函数返回结果
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
