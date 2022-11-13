package com.mlj.stream;

import com.mlj.stream.producer.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class ProducerTest {
    @Autowired
    private MessageSender messageSender;

    @Test
    public void testSend(){
        //messageSender.send("hello 工具类");
        for (int i = 0; i < 10; i++) {
            //messageSender.send("hello 工具类");
            //messageSender.send("hello");
        }
    }
}
