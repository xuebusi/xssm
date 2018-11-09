package com.xuebusi.xssm.test.nashorn;

import org.springframework.core.io.ClassPathResource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;

/**
 * Nashorn测试
 *
 * @author: shiyanjun
 * @Date: 2018/11/9 上午10:24
 */
public class NashornTest {

    // 获取脚本引擎
    public static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    public static void main(String[] args) {
        System.out.println("求和:" + sum(11, 6));
        System.out.println("求最大值:" + max(23, 9));
        System.out.println("调用js文件中的函数:" + evalReader("js/nashorn.js"));
    }

    public static Object evalReader(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            File file = resource.getFile();
            FileReader reader = new FileReader(file);
            engine.eval(reader);
            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("getTest", "Hello World");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double sum(double a, double b) {
        // 定义一个求和函数
        String sum = "function sum(a, b){\n" +
                "    return a + b;\n" +
                "}";
        try {
            engine.eval(sum);
            // 强转成可调用对象
            Invocable invocable = (Invocable) engine;
            // 调用脚本中的求最值函数，并传入所需要的2个参数
            Object sumResult = invocable.invokeFunction("sum", a, b);
            return Double.parseDouble(String.valueOf(sumResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Double max(double a, double b) {
        // 求最值函数
        String max = "function max(a,b) {\n" +
                "\treturn a > b ? a : b;\n" +
                "}";
        try {
            engine.eval(max);
            // 强转成可调用对象
            Invocable invocable = (Invocable) engine;
            // 调用脚本中的求最值函数，并传入所需要的2个参数
            Object maxResult = invocable.invokeFunction("max", a, b);
            return Double.parseDouble(String.valueOf(maxResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
