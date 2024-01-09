package com.baixiu.middleware.test;

import com.baixiu.middleware.id.api.IdGenerator;
import com.jd.rd.product.infrastructure.util.ThreadFactoryUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author baixiu
 * @date 创建时间 2024/1/2 2:24 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
public class TestGeneratorId{
    
    @Autowired
    private IdGenerator idGenerator;
    
    @Test
    public void testParallelGeneratorGlobalId() throws Exception{
        ExecutorService threadPoolExecutor=ThreadFactoryUtil.Pool.PARALLEL.getExecutor();
        CountDownLatch countDownLatch=new CountDownLatch (1000);
        ConcurrentHashMap allNum=new ConcurrentHashMap (1000,0.75f);
        for(int i=0;i<1000;i++){
            int finalI = i;
            Thread thread=new Thread () {
                @Override
                public void run() {
                    long id=idGenerator.generatorGlobalId("product");
                    System.out.println("第"+String.valueOf(finalI)+"次："+id);
                    countDownLatch.countDown();
                    allNum.put(id,id);
                }
            };
            threadPoolExecutor.submit(thread);
        }        
        countDownLatch.await();
        System.out.println ("testParallelGeneratorGlobalId 执行结束."+allNum.size ());         
    }

    @Test
    public void testSingleGeneratorGlobalId(){
        String id= String.valueOf(idGenerator.generatorGlobalId("product"));
        System.out.println ("single id:"+id);
    }
    
}
