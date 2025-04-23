package com.yuuhikaze.exam_02.utils;

import java.net.URL;

public class ResourceLocator {
    public static URL locateResource(String resource) {
        return ResourceLocator.class.getClassLoader().getResource("com/yuuhikaze/exam_02/" + resource);
    }
}
