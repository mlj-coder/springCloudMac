package com.mlj.stream.consumer;

import com.mlj.stream.channel.MyProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MyProcessor.class)
public class MessageListener {
    //监听binding中的消息
    @StreamListener(MyProcessor.MYINPUT)
    public void input(String message){
        System.out.println("获取的消息："+message);
    }
}
