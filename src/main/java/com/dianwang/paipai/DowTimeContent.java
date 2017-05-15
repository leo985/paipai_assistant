package com.dianwang.paipai;

import java.util.*;

/**
 * Created by leo on 2017/5/16.
 */
public class DowTimeContent {
    private static final int fixedSize = 9;
    private Queue<String> contents = new LinkedList<>();

    public void addContent(String content) {
        if(this.contents.size() < fixedSize) {
            this.contents.offer(content);
        }
        else {
            this.contents.poll();
            this.contents.offer(content);
        }
    }

    public String render() {
        StringBuilder builder = new StringBuilder();
        Iterator<String> it = contents.iterator();
        for(String content : contents) {
            builder.append(content);
            builder.append("\r\n");
        }
        builder.delete(builder.lastIndexOf("\r\n"),builder.length());
        return builder.toString();
    }

}
