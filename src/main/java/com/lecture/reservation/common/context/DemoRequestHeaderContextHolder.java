package com.lecture.reservation.common.context;

import java.util.Objects;
import org.springframework.core.NamedThreadLocal;

/**
 * Created by HoYoung on 2023/03/07.
 */
public class DemoRequestHeaderContextHolder {

    private static final NamedThreadLocal<DemoRequestHeaderContext> demoRequestHeaderContextHolder = new NamedThreadLocal<>(
            "DemoRequestHeaderContext");

    public static void setDemoRequestHeaderContext(
            DemoRequestHeaderContext demoRequestHeaderContext) {
        if (!Objects.isNull(demoRequestHeaderContext)) {
            resetDemoRequestHeaderContext();
        }
        demoRequestHeaderContextHolder.set(demoRequestHeaderContext);
    }

    public static DemoRequestHeaderContext getDemoRequestHeaderContext() {
        return demoRequestHeaderContextHolder.get();
    }

    public static void resetDemoRequestHeaderContext() {
        demoRequestHeaderContextHolder.remove();
    }
}
